package ru.otus.java.basic.homeworks.lesson5;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayOrderChecker {

    public static int[] promptArrayInput() {
        /*
         * метод запрашивает у пользователя ввод целочиселнного массива и возвращает его
         *
         * переменные в методе:
         * int arrayLength - длинна массива
         */
        int arrayLength;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длину массива: ");
        arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
            System.out.println("Введите элементы массива:");
            for (int i = 0; i < arrayLength; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static String promptAndCheckArrayItemsOrder(int[] array) {
        /*
         * метод принимает на вход целочисленный массив, запрашивает у пользователя,
         * в каком порядке должны следовать элементы этого массива (убывания или возрастания)
         * и возвращает результат проверки
         *
         * переменные в методе:
         * int itemsOrder - порядок (1 - убывания или 2 - возрастания) элементов массива для проверки
         * String order - строковое представление порядка элементов массива (desc или asc)
         * String stringOrderForResult - подстрока для отображения в строке ответа (убывания или возрастания)
         */
        int itemsOrder;
        String order;
        String stringOrderForResult;

        // запрашиваем порядок следования элементов массива для проверки
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                        Выберете, как должны быть расположены элементы массива (введите число 1 или 2):
                        1 - в порядке убывания
                        2 - в порядке возрастания""");
            itemsOrder = scanner.nextInt();
        } while (itemsOrder < 1 || itemsOrder > 2);

        // инициализируем переменные order и stringOrderForResult соответствующими значениями
        if (itemsOrder == 1) {
            order = "desc";
            stringOrderForResult = "убывания";
        } else {
            order = "asc";
            stringOrderForResult = "возрастания";
        }

        // подготавливаем строку для возврата из метода в качестве ответа
        String negativeResult = String.format("Элементы массива %s НЕ идут в порядке %s",
                Arrays.toString(array), stringOrderForResult);
        String positiveResult = String.format("Элементы массива %s идут в порядке %s",
                Arrays.toString(array), stringOrderForResult);

        // проверяем, что элементы массива следуют в запрошенном порядке и возвращаем результат
        if (order.equals("desc")) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] <= array[i+1]) {
                    return negativeResult;
                }
            }
        } else {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] >= array[i+1]) {
                    return negativeResult;
                }
            }
        }
        return positiveResult;
    }
}
