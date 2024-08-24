package ru.otus.java.basic.homeworks.lesson13;

public enum TerrainTypes {
    DENSE_FOREST("Густой лес", 3),
    PLAIN("Равнина", 1),
    SWAMP("Болото", 5);

    /** Название типа местности */
    private final String name;
    /** Кол-во единиц затрачиваемого бензина/выносливости на километр движения по местности */
    private final int resourceRatePerKm;

    TerrainTypes(String name, int resourceRatePerKm) {
        this.name = name;
        this.resourceRatePerKm = resourceRatePerKm;
    }

    public String getName() {
        return name;
    }

    public int getResourceRatePerKm() {
        return resourceRatePerKm;
    }
}
