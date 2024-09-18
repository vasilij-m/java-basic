package ru.otus.java.basic.homeworks.lesson12;

public class Main {

    public static void main(String[] args) {
        Plate plate = new Plate(60);
        Cat[] cats = {
                new Cat("Barsik", 20),
                new Cat("Murzik", 50),
                new Cat("Lukas", 30),
        };
        for (Cat cat : cats) {
            cat.eat(plate);
            cat.info();
        }
        System.out.printf("В тарелке сейчас %d единиц еды\n", plate.getFoodAmount());
        plate.addFood(20);
        System.out.printf("В тарелке сейчас %d единиц еды\n", plate.getFoodAmount());
        plate.addFood(50);
        System.out.printf("В тарелке сейчас %d единиц еды\n", plate.getFoodAmount());
    }
}
