package org.example;

import java.util.Arrays;

public class HomeworkMethodsArrays {

    // 1. Печать трех слов в столбик
    static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Сумма двух чисел и знак суммы
    static void checkSumSign() {
        int a = 7;
        int b = -10;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Цвет по значению (0 — в "Красный")
    static void printColor() {
        int value = 42;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Сравнение чисел
    static void compareNumbers() {
        int a = 5;
        int b = 5;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Сумма в [10..20]
    static boolean isSumBetween10And20(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Печать знака числа (ноль — положительный)
    static void printSign(int n) {
        if (n >= 0) System.out.println("Положительное");
        else System.out.println("Отрицательное");
    }

    // 7. true если число отрицательное (ноль — положительный)
    static boolean isNegative(int n) {
        return n < 0;
    }

    // 8. Печать строки N раз
    static void printStringNTimes(String s, int times) {
        for (int i = 0; i < times; i++) System.out.println(s);
    }

    // 9. Високосный год
    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10. Инвертировать массив 0/1
    static void invertBinaryArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) arr[i] = (arr[i] == 0) ? 1 : 0;
    }

    // 11. Массив 1..100
    static int[] fill1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;
        return arr;
    }

    // 12. Умножить элементы < 6 на 2
    static void multiplyLessThan6(int[] arr) {
        for (int i = 0; i < arr.length; i++) if (arr[i] < 6) arr[i] *= 2;
    }

    // 13. Матрица n×n: единицы по диагоналям
    static int[][] fillDiagonals(int n) {
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            m[i][i] = 1;
            m[i][n - 1 - i] = 1;
        }
        return m;
    }

    // 14. Массив длиной len, заполненный initialValue
    static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    // Проверка всех методов
    public static void main(String[] args) {

        System.out.println("1) printThreeWords()");
        printThreeWords();

        System.out.println("\n2) checkSumSign()");
        checkSumSign();

        System.out.println("\n3) printColor()");
        printColor();

        System.out.println("\n4) compareNumbers()");
        compareNumbers();

        System.out.println("\n5) isSumBetween10And20(9, 2) -> " + isSumBetween10And20(9, 2));
        System.out.println("5) isSumBetween10And20(3, 4) -> " + isSumBetween10And20(3, 4));

        System.out.println("\n6) printSign(-1):");
        printSign(-1);
        System.out.println("6) printSign(0):");
        printSign(0);

        System.out.println("\n7) isNegative(-5) -> " + isNegative(-5));
        System.out.println("7) isNegative(0) -> " + isNegative(0));

        System.out.println("\n8) printStringNTimes(\"String\", 3):");
        printStringNTimes("String", 3);

        System.out.println("\n9) isLeapYear(2020) -> " + isLeapYear(2020));
        System.out.println("9) isLeapYear(2025) -> " + isLeapYear(2025));
        System.out.println("9) isLeapYear(2026) -> " + isLeapYear(2026));

        System.out.println("\n10) invertBinaryArray:");
        int[] bin = {1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1};
        System.out.println("   before: " + Arrays.toString(bin));
        invertBinaryArray(bin);
        System.out.println("   after : " + Arrays.toString(bin));

        System.out.println("\n11) fill1To100:");
        int[] oneToHundred = fill1To100();
        System.out.println("   100: " + Arrays.toString(Arrays.copyOf(oneToHundred, 100)));

        System.out.println("\n12) multiplyLessThan6:");
        int[] arr12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("   before: " + Arrays.toString(arr12));
        multiplyLessThan6(arr12);
        System.out.println("   after : " + Arrays.toString(arr12));

        System.out.println("\n13) fillDiagonals(5):");
        int[][] m = fillDiagonals(5);
        for (int[] row : m) System.out.println("   " + Arrays.toString(row));

        System.out.println("\n14) createArray(5, 7): " + Arrays.toString(createArray(5, 7)));
    }
}
