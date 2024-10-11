package ru.otus.java.basic.homeworks.lesson26;

public class Main {

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();
        Box<Fruit> fruitAppleBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            appleBox.add(new Apple(0.242));
            orangeBox.add(new Orange(0.25));
        }

        for (int i = 0; i < 5; i++) {
            fruitBox.add(new Apple(0.242));
            fruitBox.add(new Orange(0.25));
        }

        for (int i = 0; i < 10; i++) {
            fruitAppleBox.add(new Apple(0.242));
        }

        System.out.println("Вес коробки с яблоками: " + appleBox.weight() + " кг");
        System.out.println("Вес коробки с апельсинами: " + orangeBox.weight() + " кг");
        System.out.println("Вес коробки для фруктов с яблоками и апельсинами: " + fruitBox.weight() + " кг");
        System.out.println("Вес коробки для фруктов с яблоками: " + fruitAppleBox.weight() + " кг");

        System.out.println("Веса коробки с яблоками и коробки с апельсинами равны: "
                + appleBox.compare(orangeBox));
        System.out.println("Веса коробки с яблоками и коробки для фруктов с яблоками равны: "
                + appleBox.compare(fruitAppleBox));

        orangeBox.moveFruits(fruitAppleBox);
        System.out.println("***** Пересыпали коробку с апельсинами в коробку для фруктов с яблоками *****");
        System.out.println("Вес коробки с апельсинами: " + orangeBox.weight() + " кг");
        System.out.println("Вес коробки для фруктов с яблоками: " + fruitAppleBox.weight() + " кг");
    }
}
