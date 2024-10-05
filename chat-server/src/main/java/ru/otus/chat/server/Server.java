package ru.otus.chat.server;

import ru.otus.chat.server.authproviders.AuthenticationProvider;
import ru.otus.chat.server.authproviders.DatabaseAuthenticationProvider;
import ru.otus.chat.server.authproviders.InMemoryAuthenticationProvider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    private final int port;
    private final Map<String, ClientHandler> clients;
    private AuthenticationProvider authenticationProvider;

    public Server(int port) {
        this.port = port;
        clients = new HashMap<>();

        try {
            authenticationProvider = new DatabaseAuthenticationProvider(this);
            authenticationProvider.initialize();
        } catch (RuntimeException e) {
            System.out.println("Initialization of the authentication service in database mode failed");
            authenticationProvider = new InMemoryAuthenticationProvider(this);
            authenticationProvider.initialize();
        }
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getClients() {
        return clients.keySet();
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.put(clientHandler.getUsername(), clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler.getUsername());
    }

    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(message);
        }
    }

    public synchronized void sendPrivateMessage(
            ClientHandler client, String recipient, String message) throws IOException {
        if (!clients.containsKey(recipient)) {
            client.sendMessage("Unknown user " + recipient);
        } else {
            clients.get(recipient).sendMessage(message);
        }
    }

    public boolean isUsernameBusy(String username) {
        return clients.containsKey(username);
    }

    public void kick(String username) {
        clients.get(username).kick();
    }
}
