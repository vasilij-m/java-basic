package ru.otus.http.server;

public class Application {
    // ДЗ Основы Web, часть 1:
    // 1. Добавить обработку запросов в цикле
    // 2. Добавить обработку запросов в тред пуле

    // ДЗ Основы Web, часть 2:
    // 1. Избавиться от sout'ов и подключить логгер
    // 2. Сделать парсинг заголовков запросов в Map<String, String>
    // 3*. Добавить обработку 405 Method Not Allowed (не реализовано)

    public static void main(String[] args) {
        new HttpServer(8080, 100).start();
    }
}
