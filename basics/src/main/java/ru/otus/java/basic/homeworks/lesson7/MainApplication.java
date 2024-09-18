package ru.otus.java.basic.homeworks.lesson7;

import java.util.Arrays;

public class MainApplication {

    public static void main(String[] args) {
        int[][] array1 = {{1, 2, 3}, {4, -5, -6}, {1, 1, 1}};
        int result1 = sumOfPositiveElements(array1);
        System.out.printf("Сумма элементов больше 0 массива %s:\n%d\n", Arrays.deepToString(array1), result1);

        printSquare(7);

        int[][] array2 = {{1, 1, 1, 1, 1}, {2, 2, 2, 2, 2}, {3, 3, 3, 3, 3}, {4, 4, 4, 4, 4}, {5, 5, 5, 5, 5}};
        System.out.printf("Результат зануления диагонали массива %s:\n", Arrays.deepToString(array2));
        setDiagonalItemsToZero(array2);
        for (int[] arr : array2) {
            for (int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        int result2 = findMax(array1);
        System.out.printf("Максимальный элемент массива %s:\n%d\n", Arrays.deepToString(array1), result2);

        int[][] array3 = {{1, 2, 3}, {4, -5, -6}, {1, 1, 1}};
        int secondColumnSum = getSecondColumnSum(array3);
        if (secondColumnSum == -1) {
            System.out.printf("Второго столбца в массиве %s не существует", Arrays.deepToString(array3));
        } else {
            System.out.printf("Сумма элементов второго столбца массива %s:\n%d\n",
                    Arrays.deepToString(array3), secondColumnSum);
        }
    }

    public static int sumOfPositiveElements(int[][] array) {
        /*
        метод возвращает сумму всех эелементов массива, которые больше 0
         */
        int sum = 0;
        for (int[] arr : array) {
            for (int item : arr) {
                if (item > 0) {
                    sum += item;
                }
            }
        }
        return sum;
    }

    public static void printSquare(int size) {
        /*
        метод печатает в консоль квадрат из символов * со стороной, длина которой равна значению аргумента
         */
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3s", "*");
            }
            System.out.println();
        }
    }

    public static void setDiagonalItemsToZero(int[][] array) {
        /*
        метод зануляет элементы "диагонали" двумерного массива
         */
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == j || j ==  array.length - i - 1) {
                    array[i][j] = 0;
                }
            }
        }
    }

    public static int findMax(int[][] array) {
        int max = array[0][0];
        for (int[] arr : array) {
            for (int item : arr) {
                if (item > max) {
                    max = item;
                }
            }
        }
        return max;
    }

    public static int getSecondColumnSum(int[][] array) {
        /*
        метод возвращает сумму элементов второго столбца,
        а если его не существует, то возвращается -1
         */
        int columnsCount = array[0].length;
        if (columnsCount < 2) {
            return -1;
        }
        int sum = 0;
        for (int[] row : array) {
            sum += row[1];
        }
        return sum;
    }
}
