import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(task1()));
        System.out.println(Arrays.toString(task2()));
        System.out.println(Arrays.toString(task3()));
        printMatrix(task4());
        System.out.println(Arrays.toString(task5(7, 6)));
        printMaxMinInArray();
        System.out.println(checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        System.out.println(Arrays.toString(shiftInArray(new int[]{1, 2, 3, 4, 5}, -2)));
    }

    public static void printMatrix(int[][] objectss) {
        System.out.println();
        for (int[] objects : objectss) {
            for (int object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
     * Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    public static int[] task1() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < array.length; i++)
            array[i] = (array[i] == 1) ? 0 : 1;
        return array;
    }

    /**
     * 2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
     */
    public static int[] task2() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) array[i] = i + 1;
        return array;
    }

    /**
     * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    public static int[] task3() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < array.length; i++)
            array[i] = (array[i] < 6) ? array[i] * 2 : array[i];
        return array;
    }

    /**
     * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами
     * (можно только одну из диагоналей, если обе сложно).
     * Определить элементы одной из диагоналей можно по следующему принципу:
     * индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
     */
    public static int[][] task4() {
        int n = 5;
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            array[i][i] = 1;
            array[i][n - 1 - i] = 1;
        }
        return array;
    }

    /**
     * 5. Написать метод, принимающий на вход два аргумента: len и initialValue,
     * и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
     */
    public static int[] task5(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    /**
     * * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;
     */
    public static void printMaxMinInArray() {
        int[] array = {2, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int max = array[0];
        int min = array[0];
        for (int i : array) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        System.out.format("Max = %s, Min = %s\n", max, min);
    }

    /**
     * ** Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место,
     * в котором сумма левой и правой части массива равны.
     */

    public static boolean checkBalance(int[] array) {
        int leftSym;
        int rightSym;
        for (int i = 0; i < array.length; i++) {
            leftSym = 0;
            rightSym = 0;

            for (int j = 0; j < i; j++) {
                leftSym += array[j];
            }
            for (int j = i; j < array.length; j++) {
                rightSym += array[j];
            }

            if (leftSym == rightSym) return true;
        }
        return false;
    }

    /**
     * *** Написать метод, которому на вход подается одномерный массив и число n
     * (может быть положительным, или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
     * Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     */
    public static int[] shiftInArray(int[] array, int n) {
        n = n % array.length;
        if (n == 0) return array;
        n = n < 0 ? array.length - -n : n;
        int tempA = array[0];
        int tempI = 0;
        for (int i = 0; i < array.length; i++) {
            int temp = array[(tempI + n) % array.length];
            array[(tempI + n) % array.length] = tempA;
            tempI = (tempI + n) % array.length;
            tempA = temp;
        }
        return array;
    }

}

