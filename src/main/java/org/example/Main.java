package org.example;

public class Main {
    static class MyArraySizeException extends RuntimeException {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    static class MyArrayDataException extends RuntimeException {
        private final int row;
        private final int col;
        private final String value;

        public MyArrayDataException(int row, int col, String value) {
            super("Некорректные данные в ячейке [" + row + "][" + col + "]: \"" + value + "\"");
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
        public String getValue() { return value; }
    }


    public static int sum4x4(String[][] arr) {
        if (arr == null || arr.length != 4) {
            throw new MyArraySizeException("Ожидался массив 4x4 по строкам, а получено: " + (arr == null ? "null" : arr.length));
        }
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i] == null || arr[i].length != 4) {
                throw new MyArraySizeException("В строке " + i + " ожидалось 4 элемента, а получено: " + (arr[i] == null ? "null" : arr[i].length));
            }
            for (int j = 0; j < 4; j++) {
                String cell = arr[i][j];
                try {
                    int value = Integer.parseInt(cell);
                    sum += value;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, cell);
                }
            }
        }
        return sum;
    }

    // AIOOBE
    public static void demoArrayIndexOutOfBounds() {
        try {
            int[] a = {1, 2, 3};
            System.out.println(a[3]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Поймали ArrayIndexOutOfBoundsException: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        // Корректный 4x4
        String[][] ok = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"9","10","11","12"},
                {"13","14","15","16"}
        };

        // Неверный размер
        String[][] badSize = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"9","10","11","12"}
        };

        // Неверные данные
        String[][] badData = {
                {"1","2","3","4"},
                {"5","6","7","8"},
                {"9","X","11","12"},
                {"13","14","15","16"}
        };

        // 1) корректный кейс
        try {
            int sum = sum4x4(ok);
            System.out.println("Сумма ok: " + sum);
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println("Ошибка при суммировании ok: " + ex.getMessage());
        }

        // 2) неправильный размер
        try {
            int sum = sum4x4(badSize);
            System.out.println("Сумма badSize: " + sum);
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println("Ошибка при суммировании badSize: " + ex.getMessage());
        }

        // 3) битые данные
        try {
            int sum = sum4x4(badData);
            System.out.println("Сумма badData: " + sum);
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println("Ошибка при суммировании badData: " + ex.getMessage());
        }

        // 4) демонстрация ArrayIndexOutOfBoundsException
        demoArrayIndexOutOfBounds();
    }
}
