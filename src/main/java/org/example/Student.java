package org.example;

import java.util.*;

public class Student {
    private final String name;
    private final String group;
    private int course;
    private final Map<String, Integer> grades;

    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new HashMap<>(grades);
    }

    public String getName()  { return name; }
    public String getGroup() { return group; }
    public int getCourse()   { return course; }

    public double average() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int v : grades.values()) sum += v;
        return sum * 1.0 / grades.size();
    }

    public void promoteIfOk() {
        if (average() >= 3.0) course++;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student that = (Student) o;
        return Objects.equals(name, that.name) && Objects.equals(group, that.group);
    }
    @Override public int hashCode() { return Objects.hash(name, group); }

    @Override public String toString() {
        return name + " [" + group + "], курс=" + course + ", ср=" + String.format("%.2f", average());
    }


    public static void removeBadStudents(Set<Student> students) {
        students.removeIf(s -> s.average() < 3.0);
    }

    public static void promoteAllEligible(Set<Student> students) {
        for (Student s : students) s.promoteIfOk();
    }

    public static void printStudents(Set<Student> students, int course) {
        List<String> names = new ArrayList<>();
        for (Student s : students) {
            if (s.getCourse() == course) names.add(s.getName());
        }
        Collections.sort(names);
        for (String n : names) System.out.println(n);
    }
}
