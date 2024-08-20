package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.Human;
import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public class Bike implements Movable {
    /** Человек, использующий велосипед */
    private Human human;
    /** Типы местности, по которым велосипед способен передвигаться */
    private final TerrainTypes[] terrainTypesAllowed;
    /** Тип транспортного средства */
    private final String transportType;

    public Bike(Human human) {
        this.human = human;
        this.terrainTypesAllowed = new TerrainTypes[] { TerrainTypes.DENSE_FOREST, TerrainTypes.PLAIN };
        this.transportType = "Велосипед";
    }

    @Override
    public boolean move(int distance, TerrainTypes terrain) {
        MovementResult movementResult = canMove(human.getStamina(), distance, terrain, terrainTypesAllowed);
        if (!movementResult.result) {
            System.out.println(movementResult.message);
            return false;
        }
        human.setStamina(human.getStamina() - distance * terrain.getResourceRatePerKm());
        System.out.printf("""
            ---->
            Вид транспорта: %s
            %s
            Осталось выносливости: %d
            ---->
            """, transportType, movementResult.message, human.getStamina());
        return true;
    }

    @Override
    public String getTransportType() {
        return transportType;
    }

    @Override
    public int getResource() {
        return human.getStamina();
    }
}
