package ru.otus.java.basic.homeworks.lesson11;

import ru.otus.java.basic.homeworks.lesson11.animals.Cat;
import ru.otus.java.basic.homeworks.lesson11.animals.Dog;
import ru.otus.java.basic.homeworks.lesson11.animals.Horse;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 20, 3.6f);
        Dog dog = new Dog("Rex", 60, 5.1f, 1.2f);
        Horse horse = new Horse("Buran", 200, 19.7f, 0.8f);

        System.out.println("****************** CAT TEST ******************");
        cat.info();
        cat.run(18);
        cat.info();
        cat.run(18);
        cat.info();
        cat.run(13);
        cat.info();
        cat.swim(10);

        System.out.println("\n****************** DOG TEST ******************");
        dog.info();
        dog.run(18);
        dog.info();
        dog.run(18);
        dog.info();
        dog.run(13);
        dog.info();
        dog.swim(10);
        dog.info();
        dog.swim(1);
        dog.run(1);
        dog.info();

        System.out.println("\n****************** HORSE TEST ******************");
        horse.info();
        horse.run(18);
        horse.info();
        horse.run(18);
        horse.info();
        horse.run(13);
        horse.info();
        horse.swim(10);
        horse.info();
        horse.swim(1);
        horse.run(1);
        horse.info();
    }
}
