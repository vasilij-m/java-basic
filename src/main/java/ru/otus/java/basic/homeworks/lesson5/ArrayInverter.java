package ru.otus.java.basic.homeworks.lesson5;

public class ArrayInverter {

    public static void reverseArray(int[] array) {
        /*
         * метод принимает на вход целочисленный массив и переворачивает его
         */
        for (int i = 0; i < array.length / 2; i++) {
            int tempItem = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tempItem;
        }
    }
}
