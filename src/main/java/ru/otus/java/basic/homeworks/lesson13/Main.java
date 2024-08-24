package ru.otus.java.basic.homeworks.lesson13;

import ru.otus.java.basic.homeworks.lesson13.transport.AllTerrainTransport;
import ru.otus.java.basic.homeworks.lesson13.transport.Bike;
import ru.otus.java.basic.homeworks.lesson13.transport.Car;
import ru.otus.java.basic.homeworks.lesson13.transport.Horse;

public class Main {

    public static void main(String[] args) {
        Human human = new Human("Bruce");
        Car car = new Car(200);
        AllTerrainTransport jeep = new AllTerrainTransport(500);
        Horse horse = new Horse(100);
        Bike bike = new Bike();

        System.out.println("============ ТЕСТ ПЕРЕДВИЖЕНИЯ НА МАШИНЕ ============\n");
        human.takeTransport(car);
        human.move(20, TerrainTypes.DENSE_FOREST);
        human.move(20, TerrainTypes.SWAMP);
        human.move(20, TerrainTypes.PLAIN);
        human.move(200, TerrainTypes.PLAIN);
        human.move(100, TerrainTypes.PLAIN);

        System.out.println("\n============ ТЕСТ ПЕРЕДВИЖЕНИЯ НА ВНЕДОРОЖНИКЕ ============\n");
        human.takeTransport(jeep);
        human.move(20, TerrainTypes.DENSE_FOREST);
        human.move(20, TerrainTypes.SWAMP);
        human.move(20, TerrainTypes.PLAIN);
        human.move(200, TerrainTypes.SWAMP);

        System.out.println("\n============ ТЕСТ ПЕРЕДВИЖЕНИЯ НА ЛОШАДИ ============\n");
        human.takeTransport(horse);
        human.move(20, TerrainTypes.DENSE_FOREST);
        human.move(20, TerrainTypes.SWAMP);
        human.move(20, TerrainTypes.PLAIN);
        human.move(100, TerrainTypes.PLAIN);

        System.out.println("\n============ ТЕСТ ПЕРЕДВИЖЕНИЯ НА ВЕЛОСИПЕДЕ ============\n");
        human.takeTransport(bike);
        human.move(20, TerrainTypes.DENSE_FOREST);
        human.move(20, TerrainTypes.SWAMP);
        human.move(20, TerrainTypes.PLAIN);
        human.move(100, TerrainTypes.PLAIN);

        System.out.println("\n============ ТЕСТ ПЕРЕДВИЖЕНИЯ ПЕШКОМ ============\n");
        human.leaveTransport();
        System.out.println("Текущее состояние выносливости после езды на велосипеде: " + human.getResource());
        human.move(20, TerrainTypes.DENSE_FOREST);
        human.move(20, TerrainTypes.SWAMP);
        human.move(20, TerrainTypes.PLAIN);
    }
}
