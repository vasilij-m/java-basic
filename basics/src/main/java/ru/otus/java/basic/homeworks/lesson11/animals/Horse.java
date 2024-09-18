package ru.otus.java.basic.homeworks.lesson11.animals;

public class Horse extends Animal {

    public Horse(String name, int stamina, float speedRun, float speedSwim) {
        super(name, stamina, speedRun, speedSwim);
        staminaSwimRatioPerMeter = 4;
        canSwim = true;
    }
}
