package ru.otus.java.basic.homeworks.lesson11;

public class Dog extends Animal {

    public Dog(String name, int stamina, float speedRun, float speedSwim) {
        super(name, stamina, speedRun, speedSwim);
        staminaSwimRatioPerMeter = 2;
        canSwim = true;
    }
}
