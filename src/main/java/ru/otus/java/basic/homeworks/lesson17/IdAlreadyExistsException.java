package ru.otus.java.basic.homeworks.lesson17;

public class IdAlreadyExistsException extends RuntimeException {

    public IdAlreadyExistsException(Long id) {
        super(String.format("Person with ID %d already exists", id));
    }
}
