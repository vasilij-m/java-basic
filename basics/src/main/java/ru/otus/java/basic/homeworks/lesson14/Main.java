package ru.otus.java.basic.homeworks.lesson14;

public class Main {

    public static void main(String[] args) {
        String[][] array1 = {
            {"1", "2", "3"},
            {"1", "2", "3"},
            {"1", "2", "3", "4"},
            {"1", "2", "3"}
        };
        try {
            System.out.println(arrayToIntAndSum(array1));
        } catch (AppArraySizeException | AppArrayDataException e) {
            e.printStackTrace();
        }

        String[][] array2 = {
            {"1", "2", "3", "4"},
            {"1", "2", "text", "4"},
            {"1", "2", "3", "4"},
            {"1", "2", "3", "4"}
        };
        try {
            System.out.println(arrayToIntAndSum(array2));
        } catch (AppArraySizeException | AppArrayDataException e) {
            e.printStackTrace();
        }

        String[][] array3 = {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
        try {
            System.out.println(arrayToIntAndSum(array3));
        } catch (AppArraySizeException | AppArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int arrayToIntAndSum(String[][] array) {

        if (array.length != 4) {
            throw new AppArraySizeException("Размерность массива не 4x4");
        }
        for (String[] item : array) {
            if (item.length != 4) {
                throw new AppArraySizeException("Размерность массива не 4x4");
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}
