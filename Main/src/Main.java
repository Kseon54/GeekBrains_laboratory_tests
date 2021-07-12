import Exception.MyArraySizeException;
import Exception.MyArrayDataException;

public class Main {

    public static final int N = 4;

    public static void main(String[] args) {
        String[][] strings = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
        };

        try {
            System.out.println(arrayStringToArrayInt(strings));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int arrayStringToArrayInt(String[][] strings) {
        if (strings.length != N) throw new MyArraySizeException(String.format("Ошибка. Массив не %dх%d.", N, N));
        for (String[] string : strings)
            if (string.length != N) throw new MyArraySizeException(String.format("Ошибка. Массив не %dх%d.", N, N));

        int a = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                try {
                    a += Integer.parseInt(strings[i][j].trim());
                } catch (Exception e) {
                    throw new MyArrayDataException(String.format("Ошибка. Элемент [%d][%d] не int.", i + 1, j + 1));
                }
            }
        }
        return a;
    }
}
