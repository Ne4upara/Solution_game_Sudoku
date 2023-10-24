package sudoku.game;

import java.util.Arrays;

/**
 * @author Ne4upara
 */
public class Main {

    public static void main(String[] args) {

        int[][] board = {
                {0, 0, 0, 0, 0, 0, 3, 0, 0},
                {1, 0, 0, 0, 5, 0, 0, 4, 0},
                {0, 2, 0, 0, 8, 0, 0, 9, 0},
                {4, 0, 0, 0, 1, 0, 6, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 7, 0},
                {0, 0, 3, 0, 0, 9, 0, 0, 5},
                {0, 0, 0, 6, 0, 4, 5, 0, 0},
                {7, 0, 9, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 3, 0, 0, 0}};

        new Main().getPoint(board);
        if (new Main().getPoint(board)) {
            printBoard(board);
        } else {
            System.out.println("Error not game.");
        }

    }

    private boolean getPoint(int[][] board) {
           for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int point = 1; point <= 9; point++) {
                        if ((isLine(i, point, board) || isColumb(j, point, board) || isBox(i, j, point, board))) {
                            continue;
                        }
                        board[i][j] = point;
                        if (getPoint(board)) {
                            return true;
                        }
                        board[i][j] = 0;
                    }

                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("__________________");
            }
            for (int n = 0; n < 9; n++) {
                if (n % 3 == 0) {
                    System.out.print(" | ");
                }
                System.out.print(board[i][n]);
            }
            System.out.println();
        }
    }

    private boolean isLine(int line, int point, int[][] board) {
        int[] array = board[line];
        return Arrays.stream(array).anyMatch(x -> x == point);
    }

    private boolean isColumb(int columb, int point, int[][] board) {
        int[] array = new int[9];
        for (int i = 0; i < 9; i++) {
            array[i] = board[i][columb];
        }
        return Arrays.stream(array).anyMatch(x -> x == point);
    }

    private boolean isBox(int line, int columb, int point, int[][] board) {
        int[] array = new int[9];
        int index = 0;
        int startLine = (line / 3) * 3;
        int startColumb = (columb / 3) * 3;

        for (int i = startLine; i < startLine + 3; i++) {
            for (int j = startColumb; j < startColumb + 3; j++) {
                array[index] = board[i][j];
                index++;
            }
        }
        return Arrays.stream(array).anyMatch(x -> x == point);
    }
}