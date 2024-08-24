package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.Human;
import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public class Car extends AbstractTransport implements Movable {

    public Car(int gasoline) {
        super(gasoline,
                new TerrainTypes[] { TerrainTypes.PLAIN },
                "Машина");
    }

    @Override
    public boolean move(int distance, TerrainTypes terrain) {
        MovementResult movementResult = canMove(gasoline, distance, terrain, terrainTypesAllowed);
        if (!movementResult.result) {
            System.out.println(movementResult.message);
            return false;
        }
        gasoline -= distance * terrain.getResourceRatePerKm();
        System.out.printf("""
            ---->
            Вид транспорта: %s
            %s
            Осталось бензина: %d
            ---->
            """, transportType, movementResult.message, gasoline);
        return true;
    }

    @Override
    public String getTransportType() {
        return transportType;
    }

    @Override
    public int getResource() {
        return gasoline;
    }

    @Override
    public void setOwner(Human owner) {
        this.owner = owner;
    }
}
