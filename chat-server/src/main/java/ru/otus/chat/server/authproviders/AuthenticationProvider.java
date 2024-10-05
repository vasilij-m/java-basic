package ru.otus.chat.server.authproviders;

import ru.otus.chat.server.ClientHandler;

public interface AuthenticationProvider {

    void initialize();
    boolean authenticate(ClientHandler clientHandler, String login, String password);
    boolean register(ClientHandler clientHandler, String login, String password, String username);
    boolean hasAdminRole(String username);
}
