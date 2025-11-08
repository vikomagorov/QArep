package org.example;

public class Cat extends Animal {

    private static int catsCount;

    private boolean satiety; // сытость: true - сыт, false - голоден

    public Cat(String name) {
        super(name);
        this.satiety = false; // при создании кот голодный
        catsCount++;
    }

    public void run(int distance) {
        if (distance <= 0) {
            System.out.println(name + " никуда не побежал.");
        } else if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м. (лимит 200 м)");
        }
    }

    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (satiety) {
            System.out.println(name + " уже сыт и не ест.");
            return;
        }

        if (bowl.takeFood(foodAmount)) {
            satiety = true;
            System.out.println(name + " поел " + foodAmount + " ед. еды. Теперь сыт.");
        } else {
            System.out.println(name + " не стал есть. Мало еды в миске.");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public static int getCatsCount() {
        return catsCount;
    }
}