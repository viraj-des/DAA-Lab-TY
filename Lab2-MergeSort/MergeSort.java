import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

class MergeSort {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        // 5, 50, 500, 5k -> time com
        
        int[] array = {};

        System.out.print("How many elements do you want to enter? : ");
        int numberOfElements = sc.nextInt();

        if(numberOfElements <= 5){
            array = new int[numberOfElements];

            for(int i = 0; i < numberOfElements; i++){
                System.out.print("Enter element " + (i + 1) + " : ");
                array[i] = sc.nextInt();
            }
        } else if(numberOfElements == 50) {

            array = new int[numberOfElements];

            for (int i = 0; i < numberOfElements; i++) {
                array[i] = rand.nextInt(50); 
            }
        } else if(numberOfElements == 500) {
            array = new int[numberOfElements];

            for (int i = 0; i < numberOfElements; i++) {
                array[i] = rand.nextInt(500);
            }
        } else if(numberOfElements == 5000) {
            array = new int[numberOfElements];

            for (int i = 0; i < numberOfElements; i++) {
                array[i] = rand.nextInt(5000);
            }
        }
        
        System.out.println("Original Array to be sorted: ");
        System.out.println(Arrays.toString(array));

        long startTime = System.nanoTime();
        
        mergeSort(array, 0, array.length - 1);

        long endTime = System.nanoTime();
        
        System.out.println("\nSorted Array: ");
        System.out.println(Arrays.toString(array));

        long elapsedTime = endTime - startTime;

        System.out.println("\nTime Taken for sorting " + numberOfElements + " elements: ");
        System.out.println(elapsedTime + " nano seconds");
        
        sc.close();

    }
    
    public static void mergeSort(int[] array, int left, int right){

        // start time 
        
        if(left < right){
            int middle = (left+right) / 2;
            
            mergeSort(array, left, middle);
            
            mergeSort(array, middle + 1, right);
            
            merge(array, left, middle, right);
        }
        
        // end time 
        // var store time
    }
    
    public static void merge(int[] array, int left, int middle, int right){
        int sizeLeft = middle - left+1;
        int sizeRight = right - middle;
        
        int[] leftArray = new int[sizeLeft];
        int[] rightArray = new int[sizeRight];
        
        for (int i = 0; i < sizeLeft; ++i) {
        leftArray[i] = array[left + i];
        }
        
        for (int j = 0; j < sizeRight; ++j) {
            rightArray[j] = array[middle + 1 + j];
        }
        
        int i = 0;
        int j = 0;
        int k = left;
        
        while (i<sizeLeft && j<sizeRight){
            if(leftArray[i] <= rightArray[j]){
                array[k] = leftArray[i];
                i++;
            }
            else{
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while(i < sizeLeft){
            array[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < sizeRight) {
        array[k] = rightArray[j];
        j++;
        k++;
    }
    }

}