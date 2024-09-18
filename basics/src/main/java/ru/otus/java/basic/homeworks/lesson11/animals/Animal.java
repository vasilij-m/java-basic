package ru.otus.java.basic.homeworks.lesson11.animals;

public class Animal {
    /** Имя животного */
    String name;
    /** Скорость бега */
    protected float speedRun;
    /** Скорость плавания */
    protected float speedSwim;
    /** Уровень выносливости */
    protected int stamina;
    /** Кол-во елиниц выносливости на 1 метр бега */
    protected int staminaRunRatioPerMeter;
    /** Кол-во елиниц выносливости на 1 метр плавания */
    protected int staminaSwimRatioPerMeter;
    /** Признак усталости */
    protected boolean isTired;
    /** Признак способности плавать */
    protected boolean canSwim;

    public Animal(String name, int stamina, float speedRun) {
        this.name = name;
        this.stamina = stamina;
        this.speedRun = speedRun;
        staminaRunRatioPerMeter = 1;
        staminaSwimRatioPerMeter = 1;
        isTired = false;
        canSwim = false;
    }

    public Animal(String name, int stamina, float speedRun, float speedSwim) {
        this(name, stamina, speedRun);
        this.speedSwim = speedSwim;
        canSwim = true;
    }

    /**
     * Возврат времени на преодоление расстояния бегом и вывод сообщений о движении в консоль
     *
     * @param distance расстояние, которое нужно преодолеть
     * @return время, за которое расстояние было преоделено, либо -1 в случае недостатка единиц выносливости
     */
    public float run(int distance) {
        if (isTired) {
            System.out.printf("%s %s устал(а) и не может бежать\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        MovementResult movementResult = move(distance, staminaRunRatioPerMeter, speedRun);
        if (movementResult.getDistance() == 0) {
            System.out.printf("На дистанцию %d метров не хватает выносливаости.\n", distance);
            return -1;
        }
        if (movementResult.getDistance() < distance) {
            System.out.printf("%s %s пробежал(а) %d метров за %.1f секунд. " +
                            "На всю дистанцию %d метров не хватает выносливости.\n",
                    this.getClass().getSimpleName(), name, movementResult.getDistance(), movementResult.getTime(), distance);
            return -1;
        }
        System.out.printf("%s %s пробежал(а) %d метров за %.1f секунд\n",
                this.getClass().getSimpleName(), name, movementResult.getDistance(), movementResult.getTime());
        return movementResult.getTime();
    }

    /**
     * Возврат времени на преодоление расстояния плаванием и вывод сообщений о движении в консоль
     *
     * @param distance расстояние, которое нужно преодолеть
     * @return время, за которое расстояние было преоделено, либо -1 в случае недостатка единиц выносливости
     */
    public float swim(int distance) {
        if (!canSwim) {
            System.out.printf("%s %s не умеет плавать\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        if (isTired) {
            System.out.printf("%s %s устал(а) и не может плыть\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        MovementResult movementResult = move(distance, staminaSwimRatioPerMeter, speedSwim);
        if (movementResult.getDistance() == 0) {
            System.out.printf("На дистанцию %d метров не хватает выносливаости.\n", distance);
            return -1;
        }
        if (movementResult.getDistance() < distance) {
            System.out.printf("%s %s проплыл(а) %d метров за %.1f секунд. " +
                            "На всю дистанцию %d метров не хватает выносливаости.\n",
                    this.getClass().getSimpleName(), name, movementResult.getDistance(), movementResult.getTime(),
                    distance);
            return -1;
        }
        System.out.printf("%s %s проплыл(а) %d метров за %.1f секунд\n",
                this.getClass().getSimpleName(), name, movementResult.getDistance(), movementResult.getTime());
        return movementResult.getTime();
    }

    /**
     * Расчет времени на преодоление расстояния
     *
     * @param distance расстояние, которое нужно преодолеть
     * @param staminaRatioPerMeter кол-во елиниц выносливости на 1 метр
     * @param speed скорость
     * @return объект MovementResult, в котором поле distance - преодоленное расстояние,
     * поле time - время, за которое расстояние было преоделено, либо -1 в случае недостатка единиц выносливости
     */
    protected MovementResult move(int distance, int staminaRatioPerMeter, float speed) {
        int requiredStamina = distance * staminaRatioPerMeter;
        if (requiredStamina <= stamina) {
            float time = distance / speed;
            stamina -= requiredStamina;
            if (stamina <= 0) isTired = true;
            return new MovementResult(distance, time);
        }
        int distanceCovered = stamina / staminaRatioPerMeter;
        if (distanceCovered == 0) {
            return new MovementResult(0, -1);
        }
        float time = distanceCovered / speed;
        stamina -= distanceCovered * staminaRatioPerMeter;
        if (stamina <= 0) isTired = true;
        return new MovementResult(distanceCovered, time);
    }

    public void info() {
        System.out.printf("""
                Состояние %s %s:
                Выносливость: %d
                Усталость: %s
                """, this.getClass().getSimpleName(), name, stamina, (isTired) ? "есть" : "нет");
    }
}
