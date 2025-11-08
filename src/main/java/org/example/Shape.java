package org.example;

public interface Shape {

    double getPerimeter();
    double getArea();

    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println("---------------------------");
    }
}