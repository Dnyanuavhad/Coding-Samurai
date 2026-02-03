import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // A set to keep track of all connected users' output streams
    private static Set<PrintWriter> allClients = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server started on port 5000...");
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                // Wait for a client to connect
                Socket socket = serverSocket.accept();
                System.out.println("New user connected!");
                
                // Start a new thread for each client so the server can handle many people
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Add this client to the broadcast list
                synchronized (allClients) {
                    allClients.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message: " + message);
                    // Send message to everyone else
                    synchronized (allClients) {
                        for (PrintWriter writer : allClients) {
                            writer.println(message);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("User disconnected.");
            } finally {
                if (out != null) {
                    synchronized (allClients) { allClients.remove(out); }
                }
            }
        }
    }
}