package ru.otus.java.basic.homeworks.lesson16;

public class Main {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Петров Алексей Иванович", "344-23-23");
        System.out.println(phoneBook.find("Петров Алексей Иванович"));
        phoneBook.add("Петров Алексей Иванович", "344-23-24");
        System.out.println(phoneBook.find("Петров Алексей Иванович"));
        phoneBook.add("Иванов Олег Григорьевич", "211-67-10");

        System.out.println(phoneBook.find("Иванов Олег Григорьевич"));
        System.out.println(phoneBook.find("Греф Анатолий Владимирович"));

        System.out.println(phoneBook.containsPhoneNumber("344-23-24")
                ? "Телефон есть в справочнике"
                : "Данного телефона нет в справочнике");
        System.out.println(phoneBook.containsPhoneNumber("133-66-13")
                ? "Телефон есть в справочнике"
                : "Данного телефона нет в справочнике");
    }
}
