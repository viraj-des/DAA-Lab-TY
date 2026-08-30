import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Job {
    String id;
    int deadline;
    int profit;

    Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Job Sequencing using Greedy Method");

        System.out.println("Enter the Number of Jobs:");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Job ID, Deadline and Profit:");
            String id = sc.next();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new Job(id, deadline, profit);
        }

        Arrays.sort(jobs, Comparator.comparingInt((Job j) -> j.profit).reversed());

        int maxDeadline = 0;

        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        String[] slot = new String[maxDeadline + 1];
        int totalProfit = 0;

        for (Job job : jobs) {
            for (int j = job.deadline; j >= 1; j--) {
                if (slot[j] == null) {
                    slot[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        System.out.println("Selected Jobs:");

        for (int i = 1; i <= maxDeadline; i++) {
            if (slot[i] != null) {
                System.out.print(slot[i] + " ");
            }
        }

        System.out.println();
        System.out.println("Maximum Profit: " + totalProfit);
    }
}