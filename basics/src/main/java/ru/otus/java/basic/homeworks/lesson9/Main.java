package ru.otus.java.basic.homeworks.lesson9;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        User[] users = {
                new User("Леонид", "Иванов", "Васильевич", 1974, "lbv@mail.ru"),
                new User("Петр", "Романов", "Николаевич", 1987, "prn@mail.ru"),
                new User("Наталья", "Котова", "Валерьевна", 1999, "nkv@mail.ru"),
                new User("Светлана", "Брежнева", "Юрьевна", 1995, "sby@mail.ru"),
                new User("Вера", "Гагарина", "Олеговна", 1990, "vgo@mail.ru"),
                new User("Нина", "Светова", "Петровна", 1980, "nsp@mail.ru"),
                new User("Николай", "Петров", "Иванович", 1981, "npi@mail.ru"),
                new User("Никита", "Жигалко", "Игоревич", 1985, "nzi@mail.ru"),
                new User("Александр", "Поздняков", "Васильевич", 1990, "apv@mail.ru"),
                new User("Елена", "Мазур", "Георгиевна", 1987, "emg@mail.ru"),
        };
        LocalDate year = LocalDate.now();
        for (User user : users) {
            if (user.getYearOfBirth() < year.getYear() - 40) {
                System.out.println(user.getInfo() + "\n");
            }
        }

        Box box = new Box(10, 10, 10, "красный");
        box.getInfo();
        box.paint("белый");
        box.paint("белый");
        box.getInfo();
        box.putIn("клавиатура");
        box.open();
        box.open();
        box.putIn("клавиатура");
        box.getInfo();
        box.close();
        box.close();
        box.putOut("мышь");
        box.open();
        box.putOut("мышь");
        box.putOut("клавиатура");
        box.getInfo();
    }
}
