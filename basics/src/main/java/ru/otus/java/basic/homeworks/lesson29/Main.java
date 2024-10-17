package ru.otus.java.basic.homeworks.lesson29;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int[] getItemsAfterLastOneNumber(int[] array) {
        boolean hasOneNumber = false;
        List<Integer> resultList = new ArrayList<>();

        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 1) {
                hasOneNumber = true;
                break;
            }
            resultList.add(array[i]);
        }
        if (!hasOneNumber) throw new RuntimeException("Входной массив не содержит единиц");
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(resultList.size() - 1 - i);
        }
        return result;
    }

    public static boolean isContainsOnlyOnesAndTwos(int[] array) {
        boolean hasOnes = false;
        boolean hasTwos = false;

        for (int number : array) {
            if (number == 1) hasOnes = true;
            if (number == 2) hasTwos = true;
            if (number != 1 && number != 2) return false;
        }
        return hasOnes && hasTwos;
    }
}
