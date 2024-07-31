package ru.otus.java.basic.homeworks.lesson5;

import java.util.Arrays;

public class MainApplication {

    public static void main(String[] args) {
        int[] array = {1, -3, 12, 4, 10, 4, 5, 2};
        printString(5, "some text");
        getItemsSumGreaterThan5(array);
        fillArray(7, array);
        increaseArrayItems(2, array);
        compareArrayHalves(array);
    }

    public static void printString(int n, String string) {
        /*
         * метод принимает в качестве аргументов целое число и строку и печатает в
         * консоль строку указанное количество раз
         */
        for (int i = 0; i < n; i++) {
            System.out.println(string);
        }
    }

    public static void getItemsSumGreaterThan5(int[] array) {
        /*
         * метод принимает в качестве аргумента целочисленный массив, суммирующий все
         * элементы, значение которых больше 5, и печатает полученную сумму в консоль
         */
        int sum = 0;
        for (int item : array) {
            if (item > 5) {
                sum += item;
            }
        }
        System.out.println(sum);
    }

    public static void fillArray(int n, int[] array) {
        /*
         * метод принимает в качестве аргументов целое число и ссылку на целочисленный
         * массив и заполнет каждую ячейку массива указанным числом
         */
        Arrays.fill(array, n);
    }

    public static void increaseArrayItems(int n, int[] array) {
        /*
         * метод принимает в качестве аргументов целое число и ссылку на целочисленный
         * массив и увеличивает каждый элемент массива на указанное число
         */
        for (int i = 0; i < array.length; i++) {
            array[i] += n;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void compareArrayHalves(int[] array) {
        /*
         * метод принимает в качестве аргумента целочисленный массив и печатает в
         * консоль информацию о том, сумма элементов какой из половин массива больше
         */
        int firstHalfSum = 0;
        int secondHalfSum = 0;
        for (int i = 0; i < array.length / 2; i++) {
            firstHalfSum += array[i];
            secondHalfSum += array[array.length - 1 - i];
        }
        if (firstHalfSum > secondHalfSum) {
            System.out.printf("Сумма элементов первой половины массива (%d) " +
                    "больше суммы элементов второй половины массива (%d)", firstHalfSum, secondHalfSum);
        } else if (firstHalfSum < secondHalfSum) {
            System.out.printf("Сумма элементов первой половины массива (%d) " +
                    "меньше суммы элементов второй половины массива (%d)", firstHalfSum, secondHalfSum);
        } else {
            System.out.printf("Сумма элементов первой половины массива (%d) " +
                    "равна сумме элементов второй половины массива (%d)", firstHalfSum, secondHalfSum);
        }
    }
}
