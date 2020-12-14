import java.util.Scanner;

public class ConnectFour {

    public static void printBoard(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                System.out.println("");
            }
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    public static void initializeBoard(char[][] array) {
        char[][] temp = new char[array.length][array[0].length];
        for (int i = 0; i < array.length / 2; i++) {
            temp[i] = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp[i];
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) { ;
                array[i][j] = '-';
            }
        }
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int returnRow = -2;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i][col] == '-') {
                if (chipType == 'x') {
                    array[i][col] = 'x';
                    return i;
                }
                else if (chipType == 'o') {
                    array[i][col] = 'o';
                    return i;
                }
            }
        }
        return returnRow;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        boolean winner = false;
        int xHorizontalCount = 0;
        int oHorizontalCount = 0;
        int xVerticalCount = 0;
        int oVerticalCount = 0;
        // check if horizontal win
        for (int i = 0; i < array[0].length; i++) {
            if (array[row][i] == 'x') {
                xHorizontalCount++;
            }
            else if (array[row][i] == 'o') {
                oHorizontalCount++;
            }
        }
        // check if vertical win
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == 'x') {
                xVerticalCount++;
            }
            else if (array[i][col] == 'o') {
                oVerticalCount++;
            }
        }
        if (xHorizontalCount == 4 || xVerticalCount == 4 || oHorizontalCount == 4 || oVerticalCount == 4) {
            winner = true;
        }
        return winner;
    }

    public static boolean checkIfDraw(char[][] array) {
        boolean draw = false;
        int dashCount = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '-') {
                    dashCount++;
                }
            }
        }
        if (dashCount == 0) {
            draw = true;
        }
        return draw;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int height = scanner.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scanner.nextInt();

        char[][] board = new char[height][length];
        initializeBoard(board);
        printBoard(board);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println("");

        char player1 = 'x';
        char player2 = 'o';
        int col = 0;
        int row = 0;

        boolean gameOn = true;

        while (gameOn) {
            System.out.print("Player 1: Which column would you like to choose? ");
            col = scanner.nextInt();
            row = insertChip(board, col, player1);
            printBoard(board);
            if (checkIfWinner(board, col, row, player1)) {
                System.out.println("Player 1 won the game!");
                break;
            }
            else if (checkIfDraw(board)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
            System.out.print("Player 2: Which column would you like to choose? ");
            col = scanner.nextInt();
            row = insertChip(board, col, player2);
            printBoard(board);
            if (checkIfWinner(board, col, row, player2)) {
                System.out.println("Player 2 won the game!");
                break;
            }
            else if (checkIfDraw(board)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }
}
