package Main;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(isFountOneAntFour(new int[]{1,2,3,4,5,6}));
    }

    public static int[] returnArrayAfterFour(int[] array) {
        int positionFour = -1;
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                positionFour = i;
                break;
            }
        if (positionFour == -1) throw new RuntimeException("There are no fours in the array");

        int[] resultArray = new int[array.length - positionFour - 1];
        System.arraycopy(array, positionFour + 1, resultArray, 0, resultArray.length);
        return resultArray;
    }

    public static boolean isFountOneAntFour(int[] array) {
        int[] temp = array.clone();
        Arrays.sort(temp);
        return Arrays.binarySearch(temp, 1) >= 0 &&
                Arrays.binarySearch(temp, 4) >= 0;
    }
}
