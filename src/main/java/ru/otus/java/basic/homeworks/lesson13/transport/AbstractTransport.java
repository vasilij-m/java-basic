package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public abstract class AbstractTransport {
    /** Количество бензина */
    protected int gasoline;
    /** Типы местности, по которым транспортное средство способно передвигаться */
    protected final TerrainTypes[] terrainTypesAllowed;
    /** Тип транспортного средства */
    protected final String transportType;

    public AbstractTransport(int gasoline, TerrainTypes[] terrainTypesAllowed, String transportType) {
        this.gasoline = gasoline;
        this.terrainTypesAllowed = terrainTypesAllowed;
        this.transportType = transportType;
    }
}
