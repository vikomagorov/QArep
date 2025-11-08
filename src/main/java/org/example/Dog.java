package org.example;

public class Dog extends Animal {

    private static int dogsCount;

    public Dog(String name) {
        super(name);
        dogsCount++;
    }

    public void run(int distance) {
        if (distance <= 0) {
            System.out.println(name + " никуда не побежал.");
        } else if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м. (лимит 500 м)");
        }
    }

    public void swim(int distance) {
        if (distance <= 0) {
            System.out.println(name + " не поплыл.");
        } else if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не смог проплыть " + distance + " м. (лимит 10 м)");
        }
    }

    public static int getDogsCount() {
        return dogsCount;
    }
}