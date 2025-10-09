import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class EchoServer {

  public static final int PORT = 9090;

  // inner class to handle client connections
  static class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;
    }

    public void run() {
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          out.println(inputLine);
        }
        in.close();
        out.close();
        clientSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {

    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(PORT);
      System.out.println("Listening on port: " + PORT);
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Accepted connection from " + clientSocket);

        // handle client in a new thread
        ClientHandler handler = new ClientHandler(clientSocket);
        handler.start();

      }
    } catch (IOException e) {
      System.err.println("Could not listen on port: " + PORT);
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      System.exit(1);
    }
    
  }
}

