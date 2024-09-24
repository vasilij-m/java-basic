package ru.otus.chat.server;

public class ServerApplication {

    public static void main(String[] args) {
        new Server(8080).start();
    }
}
