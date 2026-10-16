
import java.util.Scanner;

class Main {
    static int[][] board = new int[9][9];

    static boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num ||
                board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean solveSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku()) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    static boolean validBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];

                if (num != 0) {
                    board[row][col] = 0;
                    boolean safe = isSafe(row, col, num);
                    board[row][col] = num;

                    if (!safe) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Sudoku puzzle (0 for empty cells):");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();

                if (board[i][j] < 0 || board[i][j] > 9) {
                    System.out.println("Invalid input.");
                    sc.close();
                    return;
                }
            }
        }

        if (!validBoard()) {
            System.out.println("Invalid Sudoku puzzle.");
        } else if (solveSudoku()) {
            System.out.println("Solved Sudoku:");
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}
