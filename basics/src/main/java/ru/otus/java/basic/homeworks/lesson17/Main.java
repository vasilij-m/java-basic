package ru.otus.java.basic.homeworks.lesson17;

public class Main {

    public static void main(String[] args) {
        PersonDataBase personDB = new PersonDataBase();
        Person[] persons = {
                new Person("Elon", Position.SENIOR_MANAGER, 29759666771L),
                new Person("Dave", Position.BRANCH_DIRECTOR, 29759666772L),
                new Person("Ann", Position.DIRECTOR, 29759666773L),
                new Person("Greg", Position.MANAGER, 29759666774L),
                new Person("Liza", Position.QA, 29759666775L),
                new Person("Mary", Position.DEVELOPER, 29759666776L),
                new Person("Pit", Position.DRIVER, 29759666777L),
                new Person("James", Position.ENGINEER, 29759666778L)
        };

        for (Person person : persons) {
            personDB.add(person);
        }

        try {
            System.out.println(personDB.findById(29759666774L).getName());
            System.out.println(personDB.findById(29759666779L).getName());
        } catch (NullPointerException e) {
            System.out.println("Сотрудника с таким ID не существует");
        }

        try {
            personDB.add(new Person("Bob", Position.JUNIOR_DEVELOPER, 29759666779L));
            personDB.add(new Person("Rob", Position.JUNIOR_DEVELOPER, 29759666779L));
        } catch (IdAlreadyExistsException e) {
            System.out.println("Сотрудник с таким ID уже существует");
        }

        System.out.println(personDB.isManager(persons[1]));
        System.out.println(personDB.isManager(persons[4]));

        System.out.println(personDB.isEmployee(29759666772L));
        System.out.println(personDB.isEmployee(29759666775L));
    }
}
