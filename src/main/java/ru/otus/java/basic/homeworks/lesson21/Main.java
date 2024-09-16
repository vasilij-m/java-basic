package ru.otus.java.basic.homeworks.lesson21;

public class Main {

    public static void main(String[] args) {
        double[] array1 = new double[100_000_000];
        double[] array2 = new double[100_000_000];

        long time = System.currentTimeMillis();
        fillArray(array1, 0, 100_000_000);
        System.out.println("Время заполнения цикла в одном потоке: " + (System.currentTimeMillis() - time) + " мс");

        Thread t0 = new Thread(() -> fillArray(array2, 0, 25_000_000));
        Thread t1 = new Thread(() -> fillArray(array2, 25_000_000, 50_000_000));
        Thread t2 = new Thread(() -> fillArray(array2, 50_000_000, 75_000_000));
        Thread t3 = new Thread(() -> fillArray(array2, 75_000_000, 100_000_000));

        time = System.currentTimeMillis();
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        try {
            t0.join();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Время заполнения цикла в четырёх потоках: " + (System.currentTimeMillis() - time) + " мс");
    }

    public static void fillArray(double[] array, int left, int right) {
        for (int i = left; i < right; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }
}
