package ru.otus.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Scanner scanner;

    public Client(String serverHost, int serverPort) throws IOException {
        scanner = new Scanner(System.in);
        socket = new Socket(serverHost, serverPort);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exitok") || message.startsWith("/kicked")) {
                            break;
                        }
                        if (message.startsWith("/authok ")) {
                            System.out.println("Successful authentication with username: " + message.split(" ")[1]);
                        }
                        if (message.startsWith("/regok ")) {
                            System.out.println("Successful registration with username: " + message.split(" ")[1]);
                        }
                    } else {
                        System.out.println(message);
                    }
                }
            } catch (IOException e) {
                    e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();

        while (true) {
            String message = scanner.nextLine();
            try {
                out.writeUTF(message);
            } catch (IOException e) {
                break;
            }
            if (message.startsWith("/exit")) {
                break;
            }
        }
    }

    public void disconnect() {
        System.out.println("Disconnecting from server...");
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Disconnected");
    }
}
