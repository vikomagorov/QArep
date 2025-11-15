package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Student> students = new java.util.LinkedHashSet<>();
        students.add(new Student("Иванов Иван", "A-01", 1, Map.of("Математика",5, "Физика",4)));
        students.add(new Student("Петров Пётр", "A-01", 1, Map.of("Математика",2, "Физика",2)));
        students.add(new Student("Сидорова Анна", "B-02", 2, Map.of("Математика",3, "Физика",4)));
        students.add(new Student("Ефремов Олег",    "B-02", 3, Map.of("Математика",5, "Физика",5)));

        System.out.println("— До фильтрации —");
        for (Student s : students) System.out.println(s);

        Student.removeBadStudents(students);

        System.out.println("\n— После удаления avg<3 —");
        for (Student s : students) System.out.println(s);

        Student.promoteAllEligible(students);

        System.out.println("\n— После перевода на следующий курс —");
        for (Student s : students) System.out.println(s);

        System.out.println("\nСтуденты 2 курса:");
        Student.printStudents(students, 2);
    }
}
