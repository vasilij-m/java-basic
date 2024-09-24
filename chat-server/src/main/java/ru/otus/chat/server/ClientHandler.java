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
    private boolean isKicked;

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        isKicked = false;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                System.out.println("Client connected");

                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMessage("/exitok");
                            break;
                        }
                        if (message.startsWith("/auth")) {
                            String[] elements = message.split(" ");
                            if (elements.length != 3) {
                                sendMessage("Invalid /auth command format");
                                continue;
                            }
                            if (server.getAuthenticationProvider()
                                    .authenticate(this, elements[1], elements[2])) {
                                break;
                            }
                            continue;
                        }
                        if (message.startsWith("/reg")) {
                            String[] elements = message.split(" ");
                            if (elements.length != 4) {
                                sendMessage("Invalid /reg command format");
                                continue;
                            }
                            if (server.getAuthenticationProvider()
                                    .register(this, elements[1], elements[2], elements[3])) {
                                break;
                            }
                            continue;
                        }
                    }
                    sendMessage("First you need to authenticate with the '/auth login password' command " +
                            "or register with the '/reg login password username' command");
                }

                System.out.printf("User %s successfully authenticated%n", username);
                System.out.println("Active clients: " +
                        server.getClients().toString().replace("[", "").replace("]", ""));

                while (true) {
                    if (isKicked) {
                        sendMessage("You have been kicked from the server");
                        sendMessage("/kicked");
                        System.out.printf("Сlient %s was kicked from the server\n", username);
                        break;
                    }
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
                                continue;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                                sendMessage("Invalid private message format");
                            }
                        }
                        if (message.startsWith("/kick")) {
                            String[] elements = message.split(" ");
                            if (elements.length != 2) {
                                sendMessage("Invalid /kick command format");
                                continue;
                            }
                            if (server.getAuthenticationProvider().hasAdminRole(username)) {
                                if (server.getClients().contains(elements[1])) {
                                    server.kick(elements[1]);
                                } else {
                                    sendMessage(String.format("There is no user with name %s on the server", username));
                                }
                            } else {
                                sendMessage("You are not authorized to kick users from server");
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void kick() {
        this.isKicked = true;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
