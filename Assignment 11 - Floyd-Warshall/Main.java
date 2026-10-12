
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of vertices.");
            sc.close();
            return;
        }

        int INF = 99999;
        int[][] dist = new int[n][n];

        System.out.println("Enter adjacency matrix (0 for no edge):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = sc.nextInt();

                if (i != j && value == 0) {
                    dist[i][j] = INF;
                } else {
                    dist[i][j] = value;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF &&
                        dist[k][j] != INF) {

                        int newDistance =
                            dist[i][k] + dist[k][j];

                        if (newDistance < dist[i][j]) {
                            dist[i][j] = newDistance;
                        }
                    }
                }
            }
        }

        System.out.println("Shortest distance matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }

        sc.close();
    }
}
