package ru.otus.chat.server.authproviders;

import ru.otus.chat.server.ClientHandler;
import ru.otus.chat.server.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAuthenticationProvider implements AuthenticationProvider {
    private final Server server;
    private Connection connection;

    public DatabaseAuthenticationProvider(Server server) {
        this.server = server;
    }

    @Override
    public void initialize() {
        try {
            connection = DatabaseConnectionManager.open();
            System.out.println("Authentication service started: Database mode");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        return getUserRole(username).equals(Role.ADMIN.getName());
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        String username = null;
        String sql = """
                SELECT name FROM users
                WHERE login = ? AND password = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    username = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return username;
    }

    private boolean isLoginAlreadyExist(String login) {
        String sql = """
                SELECT login FROM users
                WHERE login = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean isUsernameAlreadyExist(String username) {
        String sql = """
                SELECT name FROM users
                WHERE name = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void addUserToChat(String login, String password, String username) {
        String sql = """
                INSERT INTO users(login, password, name)
                VALUES (?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    addUserIdToRole(userId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUserIdToRole(int userId) {
        int roleId = getRoleId(Role.USER.getName());
        if (roleId < 1) {
            throw new RuntimeException("Invlaid role_id value");
        }
        String sql = """
                INSERT INTO users_to_roles(user_id, role_id)
                VALUES (?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getRoleId(String roleName) {
        int roleId = -1;
        String sql = """
                SELECT role_id FROM roles
                WHERE name = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    roleId = resultSet.getInt("role_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleId;
    }

    private String getUserRole(String username) {
        String roleName = null;
        String sql = """
                SELECT r.name FROM roles r
                JOIN users_to_roles utr ON r.role_id = utr.role_id
                JOIN users u ON utr.user_id = u.user_id
                WHERE u.name = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    roleName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleName;
    }
}
