package ru.otus.java.basic.homeworks.lesson20.server;

public enum MathOperators {

    PLUS("+"), MINUS("-"), MULTIPLICATION("*"), DIVISION("/");

    private final String sign;

    MathOperators(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
