package ru.otus.java.basic.homeworks.lesson20.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             PingClient client = new PingClient(socket)
        ) {
            System.out.println(client.read());
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            client.send(userInput);
            System.out.println(client.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
