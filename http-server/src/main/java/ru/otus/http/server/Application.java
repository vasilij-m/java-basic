package ru.otus.http.server;

public class Application {
    // ДЗ Основы Web, часть 1:
    // 1. Добавить обработку запросов в цикле
    // 2. Добавить обработку запросов в тред пуле

    public static void main(String[] args) {
        new HttpServer(8080, 100).start();
    }
}
