package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

public class AllTerrainTransport extends AbstractTransport implements Movable {

    public AllTerrainTransport(int gasoline) {
        super(gasoline,
                new TerrainTypes[] { TerrainTypes.DENSE_FOREST, TerrainTypes.PLAIN, TerrainTypes.SWAMP },
                "Внедорожник");
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
}
