import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EchoClient {

  public static final String HOST = "localhost";
  public static final int PORT = 9090;

  public static void main(String[] args) {

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    try {
      socket = new Socket(HOST, PORT);
      System.out.println("Connected to " + HOST + " on port: " + PORT);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String messageToSend = "Hello, Server!";
      out.println(messageToSend);

      // read response from server
      String response = in.readLine();
      System.out.println("Received from server: " + response);
      socket.close();
      out.close();
      in.close();

    } catch (IOException e) {
      System.err.println("Could not connect to " + HOST + " on port: " + PORT);
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      if (out != null) {
        try {
          out.close();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      if (in != null) {
        try {
          in.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }

  }
}
