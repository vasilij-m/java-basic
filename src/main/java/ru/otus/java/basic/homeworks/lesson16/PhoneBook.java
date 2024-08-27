package ru.otus.java.basic.homeworks.lesson16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PhoneBook {

    private final Map<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new HashSet<>());
        }
        phoneBook.get(name).add(phone);
    }

    public String find(String name) {
        if (!phoneBook.containsKey(name)) {
            return "Нет записей для этого имени";
        }
        return phoneBook.get(name).toString().replace("[", "").replace("]", "");
    }

    public boolean containsPhoneNumber(String phone) {
        for (HashSet<String> phoneSet : phoneBook.values()) {
            if (phoneSet.contains(phone)) {
                return true;
            }
        }
        return false;
    }
}
