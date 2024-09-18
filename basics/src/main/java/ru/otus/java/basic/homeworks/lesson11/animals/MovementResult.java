package ru.otus.java.basic.homeworks.lesson11.animals;

public final class MovementResult {
    /** Преодолённое расстояние */
    private final int distance;
    /** Время, за которое расстояние было преоделено */
    private final float time;

    public MovementResult(int distance, float time) {
        this.distance = distance;
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public float getTime() {
        return time;
    }
}
