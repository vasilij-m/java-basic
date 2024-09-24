package ru.otus.chat.server;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super(String.format("User with name %s already exists", username));
    }
}
