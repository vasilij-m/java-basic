package ru.otus.java.basic.homeworks.lesson13.transport;

public final class MovementResult {
    /** Результат, было ли выполненино движение */
    public final boolean result;
    /** Краткое информационное сообщение */
    public final String message;

    public MovementResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
