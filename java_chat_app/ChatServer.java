// add package name here if needed
//
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class ChatServer {

  private static Set<PrintWriter> clientWriters = Collections.synchronizedSet(new HashSet<>());

  public static final int PORT = 59001; // accessible port from localhost:59001

  private static class ClientHandler extends Thread {

    // the socket is used to communicate with the client
    private Socket socket;

    public ClientHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      BufferedReader reader = null;
      PrintWriter writer = null;
      String userName = null;

      try {
        // Create the reader and writer for the socket
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);

        clientWriters.add(writer);

        // prompt for a name
        writer.println("SERVER: Enter your name: ");
        userName = reader.readLine();
        System.out.printf("User %s connected.%n", userName);

        // Announce that a new user has joined
        broadcast("SERVER: User " + userName + " has joined the chat");

        String message;
        while ((message = reader.readLine()) != null) {
          broadcast(userName + ": " + message);
        }
      } catch (IOException e) {
        System.out.println("Error handling client: " + e);
      } finally {
        // Client is leaving, clean up
        if (writer != null) {
          clientWriters.remove(writer);
          writer.close();
        }
        if (reader != null) {
          try {
            reader.close();
          } catch (IOException e) {
            System.out.println("Could not close reader: " + e);
          }
        }
        if (userName != null) {
          broadcast("User " + userName + " has left the chat");
        }
        try {
          socket.close();
        } catch (IOException e) {
          System.out.println("Could not close a socket: " + e);
        }
      }
    }

    private void broadcast(String message) {
      System.out.println("Broadcasting: " + message);
      synchronized (clientWriters) {
        for (PrintWriter writer : clientWriters) {
          writer.println(message);
        }
      }
    }
  }


  public static void main(String[] args) {

    System.out.println("The chat server is running. Awaiting connections...");

    try (ServerSocket listener = new ServerSocket(PORT)) {

      while (true) {
        // Accept a new client connection
        var socket = listener.accept(); // blocking call until a client connects
        System.out.printf("New client connected from %s%n", socket.getRemoteSocketAddress());

        // Create a new handler for the client
        Thread handler = new ClientHandler(socket);
        handler.start(); // start the thread to handle the client
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

