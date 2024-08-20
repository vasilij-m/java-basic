package ru.otus.java.basic.homeworks.lesson13;

import ru.otus.java.basic.homeworks.lesson13.transport.Movable;
import ru.otus.java.basic.homeworks.lesson13.transport.MovementResult;

public class Human implements Movable {
    /** Имя человека */
    private final String name;
    /** Кол-во выносливости */
    private int stamina;
    /** Текущий транспорт */
    private Movable currentTransport;
    /** Типы местности, по которым человек способе передвигаться */
    private final TerrainTypes[] terrainTypesAllowed;

    public Human(String name) {
        this.name = name;
        this.currentTransport = null;
        this.terrainTypesAllowed = new TerrainTypes[] { TerrainTypes.DENSE_FOREST, TerrainTypes.PLAIN };
        this.stamina = 50;
    }

    public Human(String name, int stamina) {
        this(name);
        this.stamina = stamina;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * Сесть в транспортное средство
     *
     * @param transport транспортное средство
     */
    public void takeTransport(Movable transport) {
        if (currentTransport == transport) {
            System.out.println("Человек уже находится в этом транспортном средстве");
            return;
        }
        currentTransport = transport;
        System.out.println("Текущее транспортное средство: " + currentTransport.getTransportType() +
                "\nТекущее количество бензина (выносливости): " + currentTransport.getResource());
    }

    /** Покинуть транспортное средство */
    public void leaveTransport() {
        if (currentTransport == null) {
            System.out.println("Человек не находится в транспортном средстве");
            return;
        }
        currentTransport = null;
    }

    @Override
    public boolean move(int distance, TerrainTypes terrain) {
        if (currentTransport != null) {
            return currentTransport.move(distance, terrain);
        }
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
            """, "Пешком", movementResult.message, stamina);
        return true;
    }

    @Override
    public String getTransportType() {
        return "Человек не является транспортным средством";
    }

    @Override
    public int getResource() {
        return stamina;
    }
}
