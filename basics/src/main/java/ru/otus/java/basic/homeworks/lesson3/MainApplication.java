package ru.otus.java.basic.homeworks.lesson3;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int methodNumber;

        do {
            System.out.println("""
                    Введите число от 1 до 5 для вызова соответствующего метода:
                    1 - greetings()
                    2 - checkSign(int a, int b, int c)
                    3 - selectColor()
                    4 - compareNumbers()
                    5 - addOrSubtractAndPrint(int initValue, int delta, boolean increment)""");
            methodNumber = scanner.nextInt();
        } while (methodNumber < 1 || methodNumber > 5);

        if (methodNumber == 1) {
            greetings();
        } else if (methodNumber == 2) {
            // генерируем случайные числа в диапазоне [-100; 100]
            int a = getRandomNumber(-100, 100);
            int b = getRandomNumber(-100, 100);
            int c = getRandomNumber(-100, 100);
            System.out.printf("Вызываем checkSign(%d, %d, %d)\nРезультат: ", a, b, c);
            checkSign(a, b, c);
        } else if (methodNumber == 3) {
            selectColor();
        } else if (methodNumber == 4) {
            compareNumbers();
        } else if (methodNumber == 5) {
            // генерируем случайные числа в диапазоне [0; 100]
            int initValue = getRandomNumber(0, 100);
            int delta = getRandomNumber(0, 100);
            boolean increment = Math.random() < 0.5;
            System.out.printf("Вызываем addOrSubtractAndPrint(%d, %d, %b)\nРезультат: ", initValue, delta, increment);
            addOrSubtractAndPrint(initValue, delta, increment);
        }
    }

    public static int getRandomNumber(int min, int max) {
        // возвращаем случайное число в диапазоне [min; max]
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void greetings() {
        System.out.println("Hello\nWorld\nfrom\nJava");
    }

    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if  (sum >= 0) {
            System.out.printf("Сумма положительная (%d)\n", sum);
        } else {
            System.out.printf("Сумма отрицательная (%d)\n", sum);
        }
    }

    public static void selectColor() {
        int data = 19;
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
    }

    public static void compareNumbers() {
        int a = 0;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        int result = increment
                ? initValue + delta
                : initValue - delta;
        System.out.println(result);
    }
}
