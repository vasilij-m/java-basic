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

    public Client(String serverHost, int serverPort) throws IOException {
        Scanner scanner = new Scanner(System.in);
        socket = new Socket(serverHost, serverPort);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        while (true) {
            try {
                if (registerUser(scanner)) {
                    System.out.println("Successful registration");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exitok")) {
                            break;
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
            out.writeUTF(message);
            if (message.startsWith("/exit")) {
                break;
            }
        }
    }

    public boolean registerUser(Scanner scanner) throws IOException {
        String message = in.readUTF();
        System.out.print(message);
        String username = scanner.nextLine();
        out.writeUTF(username);
        String response = in.readUTF();
        System.out.println(response);
        return response.equals("/registerok");
    }

    public void disconnect() {
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
    }
}
