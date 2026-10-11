import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge e) {
        return Integer.compare(this.weight, e.weight);
    }
}

class MSTResult {
    int[][] edges;
    int count;
    int totalWeight;
    boolean connected;

    MSTResult(int n) {
        edges = new int[Math.max(0, n - 1)][3];
        count = 0;
        totalWeight = 0;
        connected = false;
    }
}

class Main {
    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    static MSTResult kruskal(int[][] graph, int n) {
        MSTResult result = new MSTResult(n);

        Edge[] edges = new Edge[n * (n - 1) / 2];
        int edgeCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] > 0) {
                    edges[edgeCount++] =
                        new Edge(i, j, graph[i][j]);
                }
            }
        }

        Arrays.sort(edges, 0, edgeCount);

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edgeCount; i++) {
            Edge e = edges[i];

            int rootU = find(parent, e.u);
            int rootV = find(parent, e.v);

            if (rootU != rootV) {
                result.edges[result.count][0] = e.u;
                result.edges[result.count][1] = e.v;
                result.edges[result.count][2] = e.weight;
                result.count++;
                result.totalWeight += e.weight;

                parent[rootU] = rootV;

                if (result.count == n - 1) {
                    break;
                }
            }
        }

        result.connected = result.count == n - 1;
        return result;
    }

    static MSTResult prim(int[][] graph, int n) {
        MSTResult result = new MSTResult(n);

        int[] key = new int[n];
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            visited[i] = false;
        }

        key[0] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] &&
                    (u == -1 || key[i] < key[u])) {
                    u = i;
                }
            }

            if (u == -1 || key[u] == Integer.MAX_VALUE) {
                break;
            }

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 &&
                    !visited[v] &&
                    graph[u][v] < key[v]) {

                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (parent[i] != -1) {
                result.edges[result.count][0] = parent[i];
                result.edges[result.count][1] = i;
                result.edges[result.count][2] = graph[parent[i]][i];

                result.totalWeight += graph[parent[i]][i];
                result.count++;
            }
        }

        result.connected = result.count == n - 1;
        return result;
    }

    static void display(String name, MSTResult result,
                        long time, int n) {
        System.out.println("\n" + name);

        if (!result.connected) {
            System.out.println("Graph is disconnected.");
            System.out.println("Minimum spanning tree does not exist.");
            System.out.println("Execution time: " + time + " ns");
            return;
        }

        System.out.println("Selected edges:");

        for (int i = 0; i < result.count; i++) {
            System.out.println(
                result.edges[i][0] + " - " +
                result.edges[i][1] + " : " +
                result.edges[i][2]
            );
        }

        System.out.println("Total weight: " + result.totalWeight);
        System.out.println("Execution time: " + time + " ns");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of vertices.");
            sc.close();
            return;
        }

        int[][] graph = new int[n][n];

        System.out.println(
            "Enter adjacency matrix (0 for no edge):"
        );

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        long start = System.nanoTime();
        MSTResult kResult = kruskal(graph, n);
        long kTime = System.nanoTime() - start;

        start = System.nanoTime();
        MSTResult pResult = prim(graph, n);
        long pTime = System.nanoTime() - start;

        display("Kruskal's Algorithm", kResult, kTime, n);
        display("Prim's Algorithm", pResult, pTime, n);

        sc.close();
    }
}
