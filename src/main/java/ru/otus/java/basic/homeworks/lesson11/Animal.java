package ru.otus.java.basic.homeworks.lesson11;

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
    protected float run(int distance) {
        if (isTired) {
            System.out.printf("%s %s устал(а) и не может бежать\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        float[] movementResult = move(distance, staminaRunRatioPerMeter, speedRun);
        if (movementResult[0] == 0) {
            System.out.printf("На дистанцию %d метров не хватает выносливаости.\n", distance);
            return -1;
        }
        if (movementResult[0] < distance) {
            System.out.printf("%s %s пробежал(а) %d метров за %.1f секунд. " +
                            "На всю дистанцию %d метров не хватает выносливости.\n",
                    this.getClass().getSimpleName(), name, (int) movementResult[0], movementResult[1], distance);
            return -1;
        }
        System.out.printf("%s %s пробежал(а) %.1f метров за %.1f секунд\n",
                this.getClass().getSimpleName(), name, movementResult[0], movementResult[1]);
        return movementResult[1];
    }

    /**
     * Возврат времени на преодоление расстояния плаванием и вывод сообщений о движении в консоль
     *
     * @param distance расстояние, которое нужно преодолеть
     * @return время, за которое расстояние было преоделено, либо -1 в случае недостатка единиц выносливости
     */
    protected float swim(int distance) {
        if (!canSwim) {
            System.out.printf("%s %s не умеет плавать\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        if (isTired) {
            System.out.printf("%s %s устал(а) и не может плыть\n", this.getClass().getSimpleName(), name);
            return -1;
        }
        float[] movementResult = move(distance, staminaSwimRatioPerMeter, speedSwim);
        if (movementResult[0] == 0) {
            System.out.printf("На дистанцию %d метров не хватает выносливаости.\n", distance);
            return -1;
        }
        if (movementResult[0] < distance) {
            System.out.printf("%s %s проплыл(а) %d метров за %.1f секунд. " +
                            "На всю дистанцию %d метров не хватает выносливаости.\n",
                    this.getClass().getSimpleName(), name, (int) movementResult[0], movementResult[1], distance);
            return -1;
        }
        System.out.printf("%s %s проплыл(а) %.1f метров за %.1f секунд\n",
                this.getClass().getSimpleName(), name, movementResult[0], movementResult[1]);
        return movementResult[1];
    }

    /**
     * Расчет времени на преодоление расстояния
     *
     * @param distance расстояние, которое нужно преодолеть
     * @param staminaRatioPerMeter кол-во елиниц выносливости на 1 метр
     * @param speed скорость
     * @return массив, в котором первый элемент - преодоленное расстояние,
     * второй элемент - время, за которое расстояние было преоделено, либо -1 в случае недостатка единиц выносливости
     */
    protected float[] move(int distance, int staminaRatioPerMeter, float speed) {
        int requiredStamina = distance * staminaRatioPerMeter;
        if (requiredStamina <= stamina) {
            float time = distance / speed;
            stamina -= requiredStamina;
            if (stamina <= 0) isTired = true;
            return new float[] {distance, time};
        }
        int distanceCovered = stamina / staminaRatioPerMeter;
        if (distanceCovered == 0) {
            return new float[] {0, -1};
        }
        float time = distanceCovered / speed;
        stamina -= distanceCovered * staminaRatioPerMeter;
        if (stamina <= 0) isTired = true;
        return new float[] {distanceCovered, time};
    }

    protected void info() {
        System.out.printf("""
                Состояние %s %s:
                Выносливость: %d
                Усталость: %s
                """, this.getClass().getSimpleName(), name, stamina, (isTired) ? "есть" : "нет");
    }
}
