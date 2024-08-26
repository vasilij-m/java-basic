package ru.otus.java.basic.homeworks.lesson15;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = getListFromRange(2, 10);
        System.out.println(list);

        System.out.println(getListItemsSumMoreThan5(list));

        overrideListItemsWithNumber(7, list);
        System.out.println(list);

        increaseListItemsWithNumber(1, list);
        System.out.println(list);

        List<Employee> employees = new ArrayList<>(5);
        employees.add(new Employee("John", 37));
        employees.add(new Employee("Dave", 25));
        employees.add(new Employee("Mark", 30));
        employees.add(new Employee("Mary", 40));
        employees.add(new Employee("Kate", 34));
        System.out.println(getEmployeeNames(employees));

        for (Employee employee : getEmployeesOlder(employees, 37)) {
            System.out.println(employee.getName());
        }

        System.out.println(isEmployeesOlder(employees, 33) ?
                "Средний возраст сотрудников превышает указанный аргумент" :
                "Средний возраст сотрудников не превышает указанный аргумент");

        System.out.println(getYoungestEmployee(employees).getName());
    }

    public static ArrayList<Integer> getListFromRange(int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int getListItemsSumMoreThan5(List<Integer> list) {
        int sum = 0;
        for (int item : list) {
            if (item > 5) {
                sum += item;
            }
        }
        return sum;
    }

    public static void overrideListItemsWithNumber(int number, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, number);
        }
    }

    public static void increaseListItemsWithNumber(int number, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + number);
        }
    }

    public static List<String> getEmployeeNames(List<Employee> list) {
        List<String> names = new ArrayList<>();
        for (Employee employee : list) {
            names.add(employee.getName());
        }
        return names;
    }

    public static List<Employee> getEmployeesOlder(List<Employee> list, int age) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() >= age) {
                employees.add(employee);
            }
        }
        return employees;
    }

    public static boolean isEmployeesOlder(List<Employee> list, int age) {
        float sum = 0f;
        for (Employee employee : list) {
            sum += employee.getAge();
        }
        return sum / list.size() > age;
    }

    public static Employee getYoungestEmployee(List<Employee> list) {
        int minAge = list.get(0).getAge();
        int indexMinAge = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getAge() < minAge) {
                minAge = list.get(i).getAge();
                indexMinAge = i;
            }
        }
        return list.get(indexMinAge);
    }
}
