package org.example;

public class Main {
    public static void main(String[] args) {

        // Товары

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product(
                "Samsung S25 Ultra",
                "01.02.2025",
                "Samsung Corp.",
                "Korea",
                5599,
                true
        );

        productsArray[1] = new Product(
                "iPhone 17 Pro",
                "15.09.2025",
                "Apple Inc.",
                "USA",
                6999,
                false
        );

        productsArray[2] = new Product(
                "PlayStation 6",
                "10.11.2025",
                "Sony",
                "Japan",
                3999,
                true
        );

        productsArray[3] = new Product(
                "Xiaomi Vacuum X20",
                "05.06.2025",
                "Xiaomi",
                "China",
                499,
                false
        );

        productsArray[4] = new Product(
                "LG OLED TV",
                "20.03.2025",
                "LG",
                "Korea",
                2999,
                false
        );

        System.out.println("ТОВАРЫ");
        for (Product product : productsArray) {
            product.printInfo();
        }

        // Парк и аттракционы

        Park.Attraction bigWheel = new Park.Attraction(
                "Колесо обозрения",
                "10:00 - 22:00",
                500.5
        );

        Park.Attraction rollerCoaster = new Park.Attraction(
                "Американские горки",
                "11:00 - 21:00",
                700.2
        );

        Park.Attraction kidsTrain = new Park.Attraction(
                "Детский паровозик",
                "09:00 - 19:00",
                300.7
        );

        System.out.println("АТТРАКЦИОНЫ ПАРКА");
        bigWheel.printInfo();
        rollerCoaster.printInfo();
        kidsTrain.printInfo();
    }
}
