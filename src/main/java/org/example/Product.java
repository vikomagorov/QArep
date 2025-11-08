package org.example;

public class Product {
    String name;
    String productionDate;
    String manufacturer;
    String country;
    double price;
    boolean booked;

    public Product(String name,
                   String productionDate,
                   String manufacturer,
                   String country,
                   double price,
                   boolean booked) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.booked = booked;
    }

    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price + " RUB");
        System.out.println("Забронирован: " + booked);
        System.out.println("---------------------------");
    }
}
