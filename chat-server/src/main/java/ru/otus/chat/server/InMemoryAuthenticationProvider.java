package ru.otus.chat.server;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    private class User {
        private String login;
        private String password;
        private String username;
        private Role role;

        public User(String login, String password, String username, Role role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
        }
    }

    private enum Role {
        USER, ADMIN
    }

    private Server server;
    private List<User> users;

    public InMemoryAuthenticationProvider(Server server) {
        this.server = server;
        users = new ArrayList<>();
        users.add(new User("login1", "pswd1", "user1", Role.USER));
        users.add(new User("login2", "pswd2", "user2", Role.USER));
        users.add(new User("login3", "admin", "admin", Role.ADMIN));
    }

    @Override
    public void initialize() {
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
        users.add(new User(login, password, username, Role.USER));
        clientHandler.setUsername(username);
        server.subscribe(clientHandler);
        clientHandler.sendMessage("/regok " + username);
        return true;
    }

    @Override
    public boolean hasAdminRole(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user.role == Role.ADMIN;
            }
        }
        return false;
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                return user.username;
            }
        }
        return null;
    }

    private boolean isLoginAlreadyExist(String login) {
        for (User user : users) {
            if (user.login.equals(login)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameAlreadyExist(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }
}
