import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Binary Search Java Implementation");

        System.out.println("Enter the Number of Elements:");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the Elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the Element to Search:");
        int key = sc.nextInt();

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        long start = System.nanoTime();
        int result = binarySearch(sortedArr, key);
        long end = System.nanoTime();

        System.out.println("Sorted Array:");
        for (int i = 0; i < n; i++) {
            System.out.print(sortedArr[i] + " ");
        }

        System.out.println();

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }

        System.out.println("Binary Search Time: " + (end - start) + " ns");

        long startSort = System.nanoTime();
        Arrays.sort(arr);
        binarySearch(arr, key);
        long endSort = System.nanoTime();

        System.out.println("Time for Unsorted Array including Sorting: " + (endSort - startSort) + " ns");
    }
}