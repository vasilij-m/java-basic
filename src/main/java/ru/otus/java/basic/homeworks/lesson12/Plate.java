package ru.otus.java.basic.homeworks.lesson12;

public class Plate {

    private final int CAPACITY;
    private int foodAmount;

    public Plate(int capacity) {
        this.CAPACITY = capacity;
        this.foodAmount = capacity;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        if (foodAmount == CAPACITY) {
            System.out.println("Тарелка наполнена полностью");
            return;
        }
        if (foodAmount + amount > CAPACITY) {
            System.out.printf("Добавили в тарелку %d единиц еды до максимума\n", CAPACITY - foodAmount);
            foodAmount = CAPACITY;
            return;
        }
        foodAmount += amount;
        System.out.printf("Добавили в тарелку %d единиц еды\n", amount);
    }

    public void takeFood(int amount) {
        if (hasFood(amount)) {
            foodAmount -= amount;
        } else {
            System.out.println("В тарелке недостаточно еды");
        }
    }

    public boolean hasFood(int amount) {
        return foodAmount - amount >= 0;
    }
}
