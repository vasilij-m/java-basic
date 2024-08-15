package ru.otus.java.basic.homeworks.lesson12;

public class Cat {

    private final String name;
    private final int appetite;
    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        isFull = false;
    }

    public void eat(Plate plate) {
        System.out.printf("Кот %s собирается поесть...\n", name);
        if (!plate.takeFood(appetite)) {
            System.out.printf("Коту %s не хватает еды для насыщения\n", name);
            return;
        }
        plate.takeFood(appetite);
        isFull = true;
        System.out.printf("Кот %s поел и насытился\n", name);
    }

    public void info() {
        String result = String.format(isFull ? "Кот %s сыт\n" : "Кот %s голоден\n", name);
        System.out.printf(result);
    }
}
