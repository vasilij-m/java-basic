package ru.otus.java.basic.homeworks.lesson05;

public class ArraysSum {

    public static int[] getArraysSum(int[]... arrays) {
        /*
         * метод принимает на вход набор целочисленных массивов и возвращает новый
         * массив равный сумме входящих
         */
        int maxArrayLength = getMaxArrayLength(arrays);
        int[] resultArray = new int[maxArrayLength];
        for (int[] array : arrays) {
            for (int i = 0; i < array.length; i++) {
                resultArray[i] += array[i];
            }
        }
        return resultArray;
    }

    public static int getMaxArrayLength(int[]... arrays) {
        /*
         * метод принимает на вход набор целочисленных массивов и возвращает длинну
         * максимального массива
         */
        int maxArrayLength = arrays[0].length;
        for (int[] array : arrays) {
            maxArrayLength = Math.max(maxArrayLength, array.length);
        }
        return maxArrayLength;
    }
}
