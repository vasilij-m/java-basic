package ru.otus.java.basic.homeworks.lesson28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Object mon = new Object();
    private static final Letter letter = new Letter("C");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> printA());
        executorService.execute(() -> printB());
        executorService.execute(() -> printC());
        executorService.shutdown();
    }

    public static void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!letter.getValue().equals("C")) {
                        mon.wait();
                    }
                    System.out.print("A");
                    letter.setValue("A");
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!letter.getValue().equals("A")) {
                        mon.wait();
                    }
                    System.out.print("B");
                    letter.setValue("B");
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!letter.getValue().equals("B")) {
                        mon.wait();
                    }
                    System.out.print("C");
                    letter.setValue("C");
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
