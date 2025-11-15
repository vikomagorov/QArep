package org.example;

public class PhoneBookDemo {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.add("Иванов", "+375 29 111-11-11");
        pb.add("Иванов", "+375 29 222-22-22");
        pb.add("Петров", "+375 33 333-33-33");

        System.out.println("Иванов -> " + pb.get("Иванов"));
        System.out.println("Петров  -> " + pb.get("Петров"));
        System.out.println("Сидоров -> " + pb.get("Сидоров"));
    }
}
