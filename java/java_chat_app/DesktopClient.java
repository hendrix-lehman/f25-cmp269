
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class DesktopClient extends Application {

    private TextArea chatArea;
    private TextField messageField;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat");

        chatArea = new TextArea();
        chatArea.setEditable(false);

        messageField = new TextField();
        messageField.setPromptText("Enter your message...");

        sendButton = new Button("Send");

        BorderPane root = new BorderPane();
        root.setCenter(chatArea);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setCenter(messageField);
        bottomPane.setRight(sendButton);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            client = new ChatClient("localhost", 59001, this::onMessageReceived);
            new Thread(client).start();
        } catch (IOException e) {
            chatArea.appendText("Failed to connect to server: " + e.getMessage() + "\n");
        }

        sendButton.setOnAction(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                client.sendMessage(message);
                messageField.clear();
            }
        });

        primaryStage.setOnCloseRequest(e -> {
            if (client != null) {
                client.stop();
            }
        });
    }

    private void onMessageReceived(String message) {
        chatArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ChatClient client;
}

class ChatClient implements Runnable {

    private final String serverAddress;
    private final int serverPort;
    private final Consumer<String> onMessageReceived;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private volatile boolean running = true;

    public ChatClient(String serverAddress, int serverPort, Consumer<String> onMessageReceived) throws IOException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.onMessageReceived = onMessageReceived;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverAddress, serverPort);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while (running && (message = reader.readLine()) != null) {
                onMessageReceived.accept(message);
            }
        } catch (IOException e) {
            if (running) {
                onMessageReceived.accept("Error: " + e.getMessage());
            }
        } finally {
            stop();
        }
    }

    public void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    public void stop() {
        running = false;
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }
}
