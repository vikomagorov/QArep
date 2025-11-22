package org.example;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
            if (res < 0) throw new ArithmeticException("overflow");
        }
        return res;
    }

    public static double triangleArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("side must be > 0");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("triangle inequality violated");
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static int add(int a, int b) { return a + b; }
    public static int sub(int a, int b) { return a - b; }
    public static int mul(int a, int b) { return a * b; }
    public static int div(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("division by zero");
        return a / b;
    }

    public static int cmp(int a, int b) { return Integer.compare(a, b); }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }


    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню");
            System.out.println("1) Факториал числа");
            System.out.println("2) Площадь треугольника");
            System.out.println("3) Арифметика двух целых ( +  -  *  / )");
            System.out.println("4) Сравнение двух целых");
            System.out.println("0) Выход");

            int choice = readInt(sc, "Ваш выбор: ");

            try {
                switch (choice) {
                    case 1:
                        int n = readInt(sc, "Введите n (>=0): ");
                        long f = factorial(n);
                        System.out.println("n! = " + f);
                        break;

                    case 2:
                        double a = readDouble(sc, "Сторона a: ");
                        double b = readDouble(sc, "Сторона b: ");
                        double c = readDouble(sc, "Сторона c: ");
                        double area = triangleArea(a, b, c);
                        System.out.println("Площадь = " + area);
                        break;

                    case 3:
                        int x = readInt(sc, "a: ");
                        int y = readInt(sc, "b: ");
                        System.out.println("a + b = " + add(x, y));
                        System.out.println("a - b = " + sub(x, y));
                        System.out.println("a * b = " + mul(x, y));
                        try {
                            System.out.println("a / b = " + div(x, y));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;

                    case 4:
                        int i1 = readInt(sc, "a: ");
                        int i2 = readInt(sc, "b: ");
                        int r = cmp(i1, i2);
                        System.out.println("Результат сравнения: " + r +
                                (r < 0 ? " (a<b)" : (r == 0 ? " (a==b)" : " (a>b)")));
                        break;

                    case 0:
                        System.out.println("Пока!");
                        return;

                    default:
                        System.out.println("Неизвестный пункт меню.");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
