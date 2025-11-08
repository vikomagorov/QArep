package org.example;

public class Park {
    public static class Attraction {
        String name;
        String workTime;
        double price;

        public Attraction(String name, String workTime, double price) {
            this.name = name;
            this.workTime = workTime;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workTime);
            System.out.println("Цена: " + price + " RUB");
            System.out.println("---------------------------");
        }
    }
}