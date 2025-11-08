package org.example;

public class Main {
    public static void main(String[] args) {

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Рэкс");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Пушок");
        Cat cat3 = new Cat("Снежок");

        dog1.run(300);
        dog1.swim(5);
        cat1.run(250);
        cat1.swim(3);

        System.out.println("Всего животных: " + Animal.getAnimalsCount());
        System.out.println("Собак: " + Dog.getDogsCount());
        System.out.println("Котов: " + Cat.getCatsCount());
        System.out.println("---------------");

        // миска и коты
        Bowl bowl = new Bowl(25); // в миске 25 ед. еды

        Cat[] cats = {cat1, cat2, cat3};

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        System.out.println("В миске осталось: " + bowl.getFood() + " ед.");
        System.out.println("Сытость котов:");

        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isSatiety() ? "сыт" : "голоден"));
        }

        bowl.addFood(20);

        for (Cat cat : cats) {
            if (!cat.isSatiety()) {
                cat.eat(bowl, 10);
            }
        }

        System.out.println("В миске осталось: " + bowl.getFood());
        System.out.println("Финальная сытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + ": " + (cat.isSatiety() ? "сыт" : "голоден"));
        }
    }
}