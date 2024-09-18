package ru.otus.java.basic.homeworks.lesson12;

public class Plate {

    private final int capacity;
    private int foodAmount;

    public Plate(int capacity) {
        this.capacity = capacity;
        this.foodAmount = capacity;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        if (foodAmount == capacity) {
            System.out.println("Тарелка наполнена полностью");
            return;
        }
        if (foodAmount + amount > capacity) {
            System.out.printf("Добавили в тарелку %d единиц еды до максимума\n", capacity - foodAmount);
            foodAmount = capacity;
            return;
        }
        foodAmount += amount;
        System.out.printf("Добавили в тарелку %d единиц еды\n", amount);
    }

    public boolean takeFood(int amount) {
        if (foodAmount - amount >= 0) {
            foodAmount -= amount;
            return true;
        } else {
            System.out.println("В тарелке недостаточно еды");
            return false;
        }
    }
}
