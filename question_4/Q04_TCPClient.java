package question_4;

import java.io.*;
import java.net.*;

public class Q04_TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        try (Socket socket = new Socket(hostname, port)) {
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = "Hello Server, this is the client.";
            output.println(message);
            System.out.println("Sent to server: " + message);
            String response = input.readLine();
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}