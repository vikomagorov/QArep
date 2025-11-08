package org.example;

public class Bowl {

    private int food; // сколько еды в миске

    public Bowl(int food) {
        if (food < 0) {
            this.food = 0;
        } else {
            this.food = food;
        }
    }

    public int getFood() {
        return food;
    }

    // добавить еду
    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("В миску добавлено " + amount + " ед. еды. Теперь в миске: " + food + " ед.");
        } else {
            System.out.println("Нельзя добавить отрицательное или нулевое количество еды.");
        }
    }
    public boolean takeFood(int amount) {
        if (amount <= 0) {
            return false;
        }
        if (food >= amount) {
            food -= amount;
            return true;
        } else {
            return false;
        }
    }
}