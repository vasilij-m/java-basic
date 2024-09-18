package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.Human;
import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public class Bike implements Movable {
    /** Типы местности, по которым велосипед способен передвигаться */
    private final TerrainTypes[] terrainTypesAllowed;
    /** Тип транспортного средства */
    private final String transportType;
    /** Текущий владелец транспортного средства */
    private Human owner;

    public Bike() {
        this.terrainTypesAllowed = new TerrainTypes[] { TerrainTypes.DENSE_FOREST, TerrainTypes.PLAIN };
        this.transportType = "Велосипед";
        this.owner = null;
    }

    @Override
    public boolean move(int distance, TerrainTypes terrain) {
        MovementResult movementResult = canMove(owner.getStamina(), distance, terrain, terrainTypesAllowed);
        if (!movementResult.result) {
            System.out.println(movementResult.message);
            return false;
        }
        owner.setStamina(owner.getStamina() - distance * terrain.getResourceRatePerKm());
        System.out.printf("""
            ---->
            Вид транспорта: %s
            %s
            Осталось выносливости: %d
            ---->
            """, transportType, movementResult.message, owner.getStamina());
        return true;
    }

    @Override
    public String getTransportType() {
        return transportType;
    }

    @Override
    public int getResource() {
        return owner.getStamina();
    }

    @Override
    public void setOwner(Human owner) {
        this.owner = owner;
    }
}
