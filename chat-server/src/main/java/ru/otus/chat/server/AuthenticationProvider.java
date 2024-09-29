package ru.otus.chat.server;

public interface AuthenticationProvider {

    void initialize();
    boolean authenticate(ClientHandler clientHandler, String login, String password);
    boolean register(ClientHandler clientHandler, String login, String password, String username);
    boolean hasAdminRole(String login);
}
