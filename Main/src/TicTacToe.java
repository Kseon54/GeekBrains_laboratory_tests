import java.awt.Point;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final char EMPTY_CELL_SYMBOL = '-';
    static final char PLAYER_CELL_SYMBOL = 'X';
    static final char BOT_CELL_SYMBOL = '0';
    static final Scanner scanner = new Scanner(System.in);

    int dimension;
    char[][] field;

    boolean isGameOver;


    public void setField(char[][] field) {
        this.field = field;
    }

    public void start() {
        giveDimension();
        clear();
        printField();
        Moves();
    }

    public void Moves() {
        while (true) {
            if (isGameOver) break;
            doPlayerMove();
            printField();
            checkVictory(PLAYER_CELL_SYMBOL);

            if (isGameOver) break;
            doBotMove();
            printField();
            checkVictory(BOT_CELL_SYMBOL);
        }
        start();
    }

    public void printField() {
        for (char[] chars : field) {
            for (char c : chars) {
                System.out.printf("%c\t", c);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void doPlayerMove() {
        Point point;
        do {
            point = giveCoordinates();

            if (!checkPosition(point)) {
                System.out.println("ОШИБКА. Поле занято!!!");
                System.out.print("Еще раз. ");
            }
        }
        while (!checkPosition(point));

        field[point.x][point.y] = PLAYER_CELL_SYMBOL;

    }

    private Point giveCoordinates() {
        Point point = new Point();
        do {
            System.out.printf("Введите коорденаты [1..%s]\n", dimension);
            point.x = scanner.nextInt() - 1;
            point.y = scanner.nextInt() - 1;

            if (!checkPoint(point)) {
                System.out.println("ОШИБКА. Выход за пределы поля!!!");
                System.out.print("Еще раз. ");
            }
        }
        while (!checkPoint(point));

        return point;
    }

    private boolean checkPoint(Point point) {
        return (point.x >= 0 && point.x < dimension && point.y >= 0 && point.y < dimension);
    }

    private boolean checkPosition(Point point) {
        return field[point.x][point.y] == EMPTY_CELL_SYMBOL;
    }

    //логока бота

    private void doBotMove() {
        Point point;

        point = checkVictoryPoint(BOT_CELL_SYMBOL);
        if (point.x != -1) {
            field[point.x][point.y] = BOT_CELL_SYMBOL;
            return;
        }

        point = checkVictoryPoint(PLAYER_CELL_SYMBOL);
        if (point.x != -1) {
            field[point.x][point.y] = BOT_CELL_SYMBOL;
            return;
        }

        Random random = new Random();
        do {
            point = new Point(random.nextInt(dimension), random.nextInt(dimension));
        }
        while (!checkPosition(point));

        field[point.x][point.y] = BOT_CELL_SYMBOL;
    }

    private Point checkVictoryPoint(char c) {
        Point point;

        point = isLinePointVictory(field, c);
        if (point.x != -1) return point;

        char[][] temp = matrixTransposition(field);
        point = isLinePointVictory(temp, c);
        if (point.x != -1) return new Point(point.y,point.x);

        char[] temp1 = new char[dimension];
        char[] temp2 = new char[dimension];
        for (int i = 0; i < dimension; i++) {
            temp1[i] = field[i][i];
            temp2[i] = field[dimension - 1 - i][i];
        }

        int tempInt;
        tempInt = isLinePointVictory(temp1, c);
        if (tempInt != -1) return new Point(tempInt, tempInt);
        tempInt = isLinePointVictory(temp2, c);
        if (tempInt != -1) return new Point(tempInt, tempInt);

        return new Point(-1, -1);
    }

    private int isLinePointVictory(char[] chars, char c) {
        String s = String.copyValueOf(chars);
        int countEmpty = (int) s.chars().filter(ch -> ch == EMPTY_CELL_SYMBOL).count();
        int countC = (int) s.chars().filter(ch -> ch == c).count();
        if (countEmpty == 1 && countC == dimension - 1) return s.indexOf(EMPTY_CELL_SYMBOL);
        return -1;
    }

    private Point isLinePointVictory(char[][] field, char c) {
        for (int i = 0; i < field.length; i++) {
            int y = isLinePointVictory(field[i], c);
            if (y != -1) return new Point(i, y);
        }
        return new Point(-1, -1);
    }

    // конец логики бота


// условия завершения игры

    private void checkVictory(char c) {
        if (isVictory()) {
            System.out.printf("Победили %c!\n", c);
            isGameOver = true;
            return;
        }
        if (isDraw()) {
            System.out.println("Ничья");
            isGameOver = true;
            return;
        }
    }

    private boolean isDraw() {
        for (char[] chars : field) {
            for (char c : chars) {
                if (c == EMPTY_CELL_SYMBOL) return false;
            }
        }
        return true;
    }

    private boolean isVictory() {

        if (isLineVictory(field)) return true;

        char[][] temp = matrixTransposition(field);
        if (isLineVictory(temp)) return true;

        char[] temp1 = new char[dimension];
        char[] temp2 = new char[dimension];
        for (int i = 0; i < dimension; i++) {
            temp1[i] = field[i][i];
            temp2[i] = field[dimension - 1 - i][i];
        }
        if (isLineVictory(temp1)) return true;
        if (isLineVictory(temp2)) return true;

        return false;
    }

    private boolean isLineVictory(char[] chars) {
        char[] line = chars.clone();
        Arrays.sort(line);
        return line[0] == line[dimension - 1] && Arrays.binarySearch(line, EMPTY_CELL_SYMBOL) == -1;
    }

    private boolean isLineVictory(char[][] chars) {
        char[][] matrix = matrixClone(chars);
        for (char[] value : matrix) {
            if (isLineVictory(value)) return true;
        }
        return false;
    }

    // конец условий завершения игры

    private char[][] matrixTransposition(char[][] chars) {
        char[][] matrix = matrixClone(chars);

        for (int i = 0; i < dimension; i++) {
            for (int j = i + 1; j < dimension; j++) {
                char temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    private char[][] matrixClone(char[][] chars) {
        char[][] matrix = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            matrix[i] = chars[i].clone();
        }
        return matrix;
    }

    private void giveDimension() {
        do {
            System.out.println("Введите размерность поля. Размерость > 2");
            this.dimension = scanner.nextInt();
        }
        while (dimension <= 2);
    }

    private void clear() {
        this.field = new char[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                field[i][j] = EMPTY_CELL_SYMBOL;
            }
        }
        isGameOver = false;
    }
}