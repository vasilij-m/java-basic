package ru.otus.java.basic.homeworks.lesson09;

public class Box {
    private final int length;
    private final int width;
    private final int height;
    private String color;
    private Boolean isOpened;
    private Boolean isFull;
    private String item;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public Boolean isOpened() {
        return isOpened;
    }

    public Boolean isFull() {
        return isFull;
    }

    public String getItem() {
        return item;
    }

    public Box(int length, int width, int height, String color) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isOpened = false;
        this.isFull = false;
        this.item = null;
    }

    public void getInfo() {
        String isOpenedString = isOpened ? "да" : "нет";
        String isFullString = isFull ? "да" : "нет";
        String itemString = item != null ? item : "в коробке ничего нет";
        System.out.printf("""
                
                *** Информация о коробке ***
                Размеры (ДхШхВ, в сантиметрах): %dx%dx%d
                Цвет: %s
                Открыта: %s
                Заполнена: %s
                Предмет в коробке: %s
                *** -------------------- ***
                
                """, length, width, height, color, isOpenedString, isFullString, itemString);
    }

    public void open() {
        System.out.println("Пытаемся открыть коробку...");
        if (isOpened) {
            System.out.println("Коробка уже открыта");
            return;
        }
        isOpened = true;
        System.out.println("Успех!");
    }

    public void close() {
        System.out.println("Пытаемся закрыть коробку...");
        if (!isOpened) {
            System.out.println("Коробка уже закрыта");
            return;
        }
        isOpened = false;
        System.out.println("Успех!");
    }

    public void paint(String color) {
        System.out.printf("Пытаемся покрасить коробку в %s цвет...\n", color);
        if (color.equals(this.color)) {
            System.out.printf("Коробка уже окрашена в %s цвет\n", color);
            return;
        }
        this.color = color;
        System.out.println("Успех!");
    }

    public void putIn(String item) {
        System.out.printf("Пытаемся положить в коробку %s...\n", item);
        if (!isOpened) {
            System.out.println("Сначала откройте коробку");
            return;
        }
        if (isFull) {
            System.out.printf("В коробке уже лежит %s, в коробке нет места\n", this.item);
            return;
        }
        this.item = item;
        isFull = true;
        System.out.println("Успех!");
    }

    public void putOut(String item) {
        System.out.printf("Пытаемся убрать из коробки %s...\n", item);
        if (!isOpened) {
            System.out.println("Сначала откройте коробку");
            return;
        }
        if (!isFull) {
            System.out.println("В коробке ничего нет");
            return;
        }
        if (!item.equals(this.item)) {
            System.out.printf("В коробке лежит %s, а не %s\n", this.item, item);
            return;
        }
        this.item = null;
        isFull = false;
        System.out.println("Успех!");
    }
}
