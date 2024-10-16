package ru.otus.java.basic.homeworks.lesson28;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Object MON = new Object();
    private static final Letter LETTER = new Letter("C");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> printA());
        executorService.execute(() -> printB());
        executorService.execute(() -> printC());
        executorService.shutdown();
    }

    public static void printA() {
        synchronized (MON) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!LETTER.getValue().equals("C")) {
                        MON.wait();
                    }
                    System.out.print("A");
                    LETTER.setValue("A");
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void printB() {
        synchronized (MON) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!LETTER.getValue().equals("A")) {
                        MON.wait();
                    }
                    System.out.print("B");
                    LETTER.setValue("B");
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void printC() {
        synchronized (MON) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!LETTER.getValue().equals("B")) {
                        MON.wait();
                    }
                    System.out.print("C");
                    LETTER.setValue("C");
                    MON.notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
