package ru.otus.java.basic.homeworks.lesson13.transport;

import ru.otus.java.basic.homeworks.lesson13.TerrainTypes;

import java.util.Arrays;

public interface Movable {
    /**
     * Попытка движения по местности
     *
     * @param distance расстояние, которое нужно преодолеть
     * @param terrain тип местности, которую нужно преодолеть
     * @return возможность такого движения
     */
    boolean move(int distance, TerrainTypes terrain);

    /**
     * Получение типа используемого транспорта
     *
     * @return тип используемого транспорта
     */
    String getTransportType();

    /**
     * Получение текущего количества бензина/выносливости
     *
     * @return текущее количество бензина/выносливости
     */
    int getResource();

    /**
     * Расчет возможности передвижения и формирование информационного сообщения
     *
     * @param resource текущее количество бензина/выносливости
     * @param distance расстояние, которое нужно преодолеть
     * @param terrain тип местности, по которой предполагается движение
     * @param terrainTypesAllowed типы местности, по которым транспортное средство способно передвигаться
     * @return результат расчета возможности передвижения
     */
    default MovementResult canMove(int resource, int distance, TerrainTypes terrain,
                                   TerrainTypes[] terrainTypesAllowed) {
        if (!Arrays.asList(terrainTypesAllowed).contains(terrain)) {
            return new MovementResult(false,
                    String.format("Нет возможности передвигаться по типу местности %s", terrain.getName()));
        }
        if (resource - distance * terrain.getResourceRatePerKm() < 0) {
            return new MovementResult(false,
                    String.format("Не хватает бензина (выносливости) для передвижения на %d километров по местности %s",
                            distance, terrain.getName()));
        }
        return new MovementResult(true,
                String.format("Успешно преодолено %d километров по местности %s", distance, terrain.getName()));
    }
}
