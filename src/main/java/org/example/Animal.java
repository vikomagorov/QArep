package org.example;

public class Animal {

    protected String name;
    protected static int animalsCount;

    public Animal(String name) {
        this.name = name;
        animalsCount++;
    }

    public static int getAnimalsCount() {
        return animalsCount;
    }
}