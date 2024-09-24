package ru.otus.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String username;

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                System.out.println("Client connected");
                while (true) {
                    try {
                        if (registerUser()) {
                            server.subscribe(this);
                            System.out.printf("User %s has been successfully registered%n", username);
                            break;
                        }
                    } catch (UserAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Active clients: " +
                        server.getClients().toString().replace("[", "").replace("]", ""));

                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }
                        if (message.startsWith("/w")) {
                            try {
                                String[] messageData = message.split(" ", 3);
                                server.sendPrivateMessage(this, messageData[1], username + ": " + messageData[2]);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                                sendMessage("Invalid private message format");
                            }
                        }
                    } else {
                        server.broadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public String getUsername() {
        return username;
    }

    public boolean registerUser() throws IOException, UserAlreadyExistsException {
        sendMessage("Enter your name:> ");
        String username = in.readUTF();
        if (server.getClients().contains(username)) {
            sendMessage(String.format("User with name %s already exists", username));
            throw new UserAlreadyExistsException(username);
        } else {
            this.username = username;
            sendMessage("/registerok");
            return true;
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }

    public void disconnect() {
        server.unsubscribe(this);
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
