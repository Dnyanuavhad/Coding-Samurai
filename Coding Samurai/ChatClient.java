import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to the server!");

            // Thread to listen for messages from other users
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String incoming;
                    while ((incoming = in.readLine()) != null) {
                        System.out.println("\n" + incoming);
                        System.out.print("> "); // Input prompt
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost.");
                }
            }).start();

            // Main thread handles sending your messages
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            while (true) {
                String msg = scanner.nextLine();
                out.println(name + ": " + msg);
            }
        } catch (IOException e) {
            System.out.println("Could not connect to server.");
        }
    }
}