package ru.otus.java.basic.homeworks.lesson26;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public double weight() {
        double boxWeight = 0;
        for (T fruit : fruits) {
            boxWeight += fruit.getWeight();
        }
        return boxWeight;
    }

    public boolean compare(Box<? extends Fruit> another) {
        return this.weight() == another.weight();
    }

    public void moveFruits(Box<? super T> another) {
        another.getFruits().addAll(this.fruits);
        this.fruits.clear();
    }
}
