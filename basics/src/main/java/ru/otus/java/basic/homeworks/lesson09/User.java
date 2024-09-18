package ru.otus.java.basic.homeworks.lesson09;

public class User {
    private String name;
    private String surname;
    private String middleName;
    private int yearOfBirth;
    private String email;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public User(String name, String surname, String middleName, int yearOfBirth, String email) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }

    public String getInfo() {
        return String.format("""
                ФИО: %s %s %s
                Год рождения: %d
                e-mail: %s""", surname, name, middleName, yearOfBirth, email);
    }
}
