package question_4;

import java.io.*;
import java.net.*;

public class Q04_TCPServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client connected.");
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                String clientMessage = input.readLine();
                System.out.println("Message received from client: " + clientMessage);
                output.println("Hello from Server! Message received successfully.");
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}