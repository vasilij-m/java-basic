package ru.otus.chat.server.authproviders;

import ru.otus.chat.server.ClientHandler;
import ru.otus.chat.server.Server;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {
    private final Server server;
    private List<User> users;

    public InMemoryAuthenticationProvider(Server server) {
        this.server = server;
    }

    @Override
    public void initialize() {
        users = new ArrayList<>();
        users.add(new User("admin", "@dmin", "admin", Role.ADMIN));
        users.add(new User("user1", "pswd1", "user1", Role.USER));
        users.add(new User("user2", "pswd2", "user2", Role.USER));
        System.out.println("Authentication service started: In memory mode");
    }

    @Override
    public synchronized boolean authenticate(ClientHandler clientHandler, String login, String password) {
        String authName = getUsernameByLoginAndPassword(login, password);
        if (authName == null) {
            clientHandler.sendMessage("Incorrect login/password");
            return false;
        }
        if (server.isUsernameBusy(authName)) {
            clientHandler.sendMessage(String.format("User with name %s already connected", authName));
            return false;
        }
        clientHandler.setUsername(authName);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/authok " + authName);
        return true;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String login, String password, String username) {
        if (login.trim().length() < 3 || password.trim().length() < 6 || username.trim().length() < 2) {
            clientHandler.sendMessage("The login (3+ characters), password (6+ characters), " +
                    "or username (2+ characters) length requirements are not met.");
            return false;
        }
        if (isLoginAlreadyExist(login)) {
            clientHandler.sendMessage(String.format("User with login %s already exists", login));
            return false;
        }
        if (isUsernameAlreadyExist(username)) {
            clientHandler.sendMessage(String.format("User with username %s already exists", username));
            return false;
        }
        addUserToChat(login, password, username);
        clientHandler.setUsername(username);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/regok " + username);
        return true;
    }

    @Override
    public boolean hasAdminRole(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getRole() == Role.ADMIN;
            }
        }
        return false;
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExist(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExist(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void addUserToChat(String login, String password, String username) {
        users.add(new User(login, password, username, Role.USER));
    }
}
