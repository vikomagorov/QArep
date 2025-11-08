package org.example;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(6, "red", "black");
        Shape rect   = new Rectangle(4, 6, "blue", "yellow");
        Shape tri    = new Triangle(3, 4, 5, "green", "black");

        circle.printInfo();
        rect.printInfo();
        tri.printInfo();
    }
}
