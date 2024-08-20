package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public class Horse implements Movable {
    /** Кол-во выносливости */
    private int stamina;
    /** Типы местности, по которым лошадь способна передвигаться */
    private final TerrainTypes[] terrainTypesAllowed;
    /** Тип транспортного средства */
    private final String transportType;

    public Horse(int stamina) {
        this.stamina = stamina;
        this.terrainTypesAllowed = new TerrainTypes[] { TerrainTypes.DENSE_FOREST, TerrainTypes.PLAIN };
        this.transportType = "Лошадь";
    }

    @Override
    public boolean move(int distance, TerrainTypes terrain) {
        MovementResult movementResult = canMove(stamina, distance, terrain, terrainTypesAllowed);
        if (!movementResult.result) {
            System.out.println(movementResult.message);
            return false;
        }
        stamina -= distance * terrain.getResourceRatePerKm();
        System.out.printf("""
            ---->
            Вид транспорта: %s
            %s
            Осталось выносливости: %d
            ---->
            """, transportType, movementResult.message, stamina);
        return true;
    }

    @Override
    public String getTransportType() {
        return transportType;
    }

    @Override
    public int getResource() {
        return stamina;
    }
}
