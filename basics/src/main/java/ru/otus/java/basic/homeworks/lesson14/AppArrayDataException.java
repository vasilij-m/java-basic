package ru.otus.java.basic.homeworks.lesson14;

public class AppArrayDataException extends RuntimeException {

    public AppArrayDataException(int iIndex, int jIndex) {
        super(String.format("В ячейке (%d, %d) недопустимое значение", iIndex, jIndex));
    }
}
