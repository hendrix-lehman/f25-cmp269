// add package name here if needed
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

class ConsoleClient {

  private static final String SERVER_ADDRESS = "localhost";
  private static final int SERVER_PORT = ChatServer.PORT;

  public static void main(String[] args) {
    System.out.printf("Connecting to chat server at %s:%d...%n", SERVER_ADDRESS, SERVER_PORT);

    Socket socket = null;
    try {
      socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

    } catch (IOException e) {
      System.out.println("Error connecting to server: " + e);
    }
    
  }
}

