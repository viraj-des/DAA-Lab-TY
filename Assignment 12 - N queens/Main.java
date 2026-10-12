
import java.util.Scanner;

class Main {
    static int n;
    static int[] queen;
    static int[] firstSolution;
    static long solutions = 0;

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queen[i] == col ||
                Math.abs(queen[i] - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    static void solve(int row) {
        if (row == n) {
            solutions++;

            if (solutions == 1) {
                for (int i = 0; i < n; i++) {
                    firstSolution[i] = queen[i];
                }
            }
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                queen[row] = col;
                solve(row + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens: ");
        n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Enter a positive number.");
            sc.close();
            return;
        }

        queen = new int[n];
        firstSolution = new int[n];

        solve(0);

        if (solutions == 0) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("One valid solution:");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (firstSolution[i] == j) {
                        System.out.print("Q ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
        }

        System.out.println("Total number of solutions: " + solutions);

        sc.close();
    }
}
