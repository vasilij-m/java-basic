package ru.otus.java.basic.homeworks.lesson5;

import java.util.Arrays;

public class ExtraApplication {

    public static void main(String[] args) {
        /*
         * Задание 1:
         * Реализуйте метод, принимающий на вход набор целочисленных массивов, и получающий новый
         * массив равный сумме входящих
         */
        System.out.println("===== Задание 1 =====");
        int[] array11 = {1, 2, 3};
        int[] array12 = {2, 2};
        int[] array13 = {1, 1, 1, 1, 1};
        int[] result1 = ArraysSum.getArraysSum(array11, array12, array13);
        System.out.printf("""
                Результат первого задания:
                  %s
                + %s
                + %s
                = %s
                
                """,
                Arrays.toString(array11), Arrays.toString(array12), Arrays.toString(array13), Arrays.toString(result1));

        /*
         * Задание 2:
         * Реализуйте метод, проверяющий что есть "точка" в массиве, в которой сумма левой и правой части
         * равны. "Точка" находится между элементами
         * Пример: { 1, 1, 1, 1, 1, | 5 }, { 5, | 3, 4, -2 }, { 7, 2, 2, 2 }, { 9, 4 }
         */
        System.out.println("===== Задание 2 =====");
        int[] array21 = {1, 1, 1, 1, 1, 5};
        int[] array22 = {5, 3, 4, -2};
        int[] array23 = {7, 2, 2, 2};
        int[] array24 = {9, 4};
        int[] arrayToCheck = array21;
        int[] result2 = ArraySumPoint.getArraysSumPoint(arrayToCheck);
        String resultString = result2[0] == -1
                ? String.format("'точки' в массиве %s нет", Arrays.toString(arrayToCheck))
                : String.format("'точка' в массиве %s находится между элементами с индексами %d (%d) и %d (%d)",
                Arrays.toString(arrayToCheck), result2[0], result2[1], result2[2], result2[3]);
        System.out.printf("Результат второго задания:\n%s\n\n", resultString);

        /*
         * Задание 3:
         * Реализуйте метод, проверяющий что все элементы массива идут в порядке убывания или
         * возрастания (по выбору пользователя)
         */
        System.out.println("===== Задание 3 =====");
        // запрашиваем ввод массива
        int[] arrayToCheckOrder = ArrayOrderChecker.promptArrayInput();
        System.out.println("Введенный массив: " + Arrays.toString(arrayToCheckOrder));
        // запрашиваем порядок следования элементов массива и проверяем, что массив ему соответствует
        String result3 = ArrayOrderChecker.promptAndCheckArrayItemsOrder(arrayToCheckOrder);
        System.out.println("Результат третьего задания: " + result3);

        /*
         * Задание 4:
         * Реализуйте метод, “переворачивающий” входящий массив
         * Пример: { 1 2 3 4 } => { 4 3 2 1 }
         */
        System.out.println("===== Задание 4 =====");
        int[] array41 = {1, 2, 3, 4};
        System.out.printf("Исходный массив: %s\n", Arrays.toString(array41));
        ArrayInverter.reverseArray(array41);
        System.out.printf("""
                Результат четвертого задания:
                перевернутый массив: %s
                
                """, Arrays.toString(array41));
    }
}
