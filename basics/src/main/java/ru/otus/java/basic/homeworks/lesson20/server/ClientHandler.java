package ru.otus.java.basic.homeworks.lesson20.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements AutoCloseable {

    private final String[] mathOperatorsAllowed;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientHandler(Socket socket) throws IOException {
        mathOperatorsAllowed = new String[] {MathOperators.PLUS.getSign(), MathOperators.MINUS.getSign(),
                MathOperators.MULTIPLICATION.getSign(), MathOperators.DIVISION.getSign()};
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void handleRequest() throws IOException {
        sendInitialMessage();
        String request = inputStream.readUTF();
        try {
            String result = String.format("%.2f", calculate(request));
            outputStream.writeUTF(result);
            outputStream.flush();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            outputStream.writeUTF("Введено нечисловое значение: " + e.getMessage());
            outputStream.flush();
        } catch (IllegalArgumentException | ArithmeticException e) {
                e.printStackTrace();
                outputStream.writeUTF(e.getMessage());
                outputStream.flush();
        }
    }

    private void sendInitialMessage() throws IOException {
        String initialMessage = String.format("""
                        Доступные математические операции: %s
                        Введите два числа и математический оператор через пробел (пример: 20 30 +)""",
                Arrays.toString(mathOperatorsAllowed).replace("[", "").replace("]", "")
        );
        outputStream.writeUTF(initialMessage);
        outputStream.flush();
    }

    private Float calculate(String request) {
        String[] requestArguments = request.split(" ");
        if (requestArguments.length != 3) {
            throw new IllegalArgumentException("Некорректный запрос: недопустимое количество аргументов");
        }
        if (requestArguments[2].equals("/") && Float.parseFloat(requestArguments[1]) == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return switch (requestArguments[2]) {
            case "+" -> Float.parseFloat(requestArguments[0]) + Float.parseFloat(requestArguments[1]);
            case "-" -> Float.parseFloat(requestArguments[0]) - Float.parseFloat(requestArguments[1]);
            case "*" -> Float.parseFloat(requestArguments[0]) * Float.parseFloat(requestArguments[1]);
            case "/" -> Float.parseFloat(requestArguments[0]) / Float.parseFloat(requestArguments[1]);
            default -> throw new IllegalArgumentException(
                    "Некорректный запрос: недопустимый математический оператор " + requestArguments[2]);
        };
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
