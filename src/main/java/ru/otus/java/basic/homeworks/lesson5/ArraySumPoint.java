package ru.otus.java.basic.homeworks.lesson5;

public class ArraySumPoint {

    public static int[] getArraysSumPoint(int[] array) {
        /*
         * метод принимает на вход целочисленный массив и возвращает индексы элементов и их значения,
         * если суммы левой и правой части в 'точке' между ними равны,
         * иначе возвращается массив {-1}
         */
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < array.length; i++) {
            leftSum += array[i];
            for (int j = i + 1; j < array.length; j++) {
                rightSum += array[j];
            }
            if (leftSum == rightSum) {
                return new int[] {i, array[i], i + 1, array[i + 1]};
            } else {
                rightSum = 0;
            }
        }
        return new int[] {-1};
    }
}
