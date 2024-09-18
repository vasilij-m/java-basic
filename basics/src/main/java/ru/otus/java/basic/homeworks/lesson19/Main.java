package ru.otus.java.basic.homeworks.lesson19;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NotDirectoryException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File projectBaseDir = new File(".");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Список файлов корневой директории проекта:");
        listDir(projectBaseDir);

        System.out.print("Введите имя файла:\n> ");
        String filePath = projectBaseDir.getCanonicalPath() + "/" + scanner.nextLine();
        getFileContent(filePath);

        System.out.printf("\n\nВведите строку для записи в файл %s:\n> ", filePath);
        String string = scanner.nextLine();
        writeToFile(filePath, string);
    }

    public static void listDir(File dir) throws IOException {
        if (!dir.exists()) {
            throw new FileNotFoundException(dir.getCanonicalPath() + " не существует");
        }
        if (!dir.isDirectory()) {
            throw new NotDirectoryException(dir.getCanonicalPath() + " не является директорией");
        }
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) System.out.println(file.getName());
        }
    }

    public static void getFileContent(String filePath) throws IOException {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(filePath))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        }
    }

    public static void writeToFile(String filePath, String string) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath, true))) {
            byte[] buffer = string.getBytes(StandardCharsets.UTF_8);
            out.write(buffer);
        }
    }
}
