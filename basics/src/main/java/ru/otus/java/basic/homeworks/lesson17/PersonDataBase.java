package ru.otus.java.basic.homeworks.lesson17;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class PersonDataBase {
    private static final Set<Position> MANAGER_POSITIONS = new HashSet<>(
            Arrays.asList(Position.MANAGER, Position.DIRECTOR, Position.BRANCH_DIRECTOR, Position.SENIOR_MANAGER));

    private final Map<Long, Person> persons;

    public PersonDataBase() {
        persons = new HashMap<>();
    }

    Person findById(Long id) {
        return persons.get(id);
    }

    void add(Person person) {
        Long id = person.getId();
        if (persons.containsKey(id)) {
            throw new IdAlreadyExistsException(id);
        }
        persons.put(id, person);
    }

    boolean isManager(Person person) {
        return MANAGER_POSITIONS.contains(person.getPosition());
    }

    boolean isEmployee(Long id) {
        return !isManager(findById(id));
    }
}
