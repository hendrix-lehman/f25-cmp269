// add package name here if needed
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class ConsoleClient {

  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = ChatServer.PORT;

  private static class IncomingMessageHandler extends Thread {
    private BufferedReader reader;

    public IncomingMessageHandler(BufferedReader reader) {
      this.reader = reader;
    }

    @Override
    public void run() {
      String message;
      try {
        while ((message = reader.readLine()) != null) {
          System.out.println(message);
        }
      } catch (IOException e) {
        System.out.println("Error reading from server: " + e);
      }
    }
  }

  public static void main(String[] args) {
    System.out.printf("Connecting to chat server at %s:%d...%n", SERVER_ADDRESS, SERVER_PORT);

    Socket socket = null;
    PrintWriter writer = null;
    BufferedReader reader = null;

    try {
      socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
      writer = new PrintWriter(socket.getOutputStream(), true);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      Thread incomingHandler = new IncomingMessageHandler(reader);
      incomingHandler.start();

      System.out.println("Connected to chat server.");
      System.out.println("Type your name: ");
      Scanner scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        writer.println(userInput);
      }

    } catch (IOException e) {
      System.out.println("Error connecting to server: " + e);
    } finally {
      try {
        if (reader != null) reader.close();
        if (writer != null) writer.close();
        if (socket != null && !socket.isClosed()) socket.close();
      } catch (IOException e) {
        System.out.println("Error closing resources: " + e);
      }
    }
    
  }
}

