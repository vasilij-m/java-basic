package ru.otus.java.basic.homeworks.lesson27;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file;
        while (true) {
            System.out.print("Введите имя или путь до файла:\n> ");
            file = new File(scanner.nextLine());
            if (file.isFile()) {
                break;
            }
            System.out.println("Файл не найден");
        }

        System.out.print("Введите последовательность символов:\n> ");
        char[] symbols = scanner.nextLine().toCharArray();
        int sequenceHits = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            int byteRead;
            char symbol;
            int index = 0;

            while ((byteRead = bufferedReader.read()) != -1) {
                symbol = (char) byteRead;
                if (symbol == '\n') continue;
                if (symbol == symbols[index]) {
                    if (index != symbols.length - 1) {
                        index++;
                    } else {
                        sequenceHits += 1;
                        index = 0;
                    }
                } else {
                    index = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("\nПоследовательность '%s' встречается %d раз(а)\n", String.valueOf(symbols), sequenceHits);
    }
}
