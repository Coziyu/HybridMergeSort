import java.util.Arrays;

// Ugly messy code here for a rough test to check whether InsertionSort implementation is correct.

public class TestInsertionSort {
    public static void main(String[] args) {
        int[][] testCases = {
            {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 8},  // Unsorted array with random values
            {1, 2, 3, 4, 5},                    // Already sorted array
            {5, 4, 3, 2, 1},                    // Reverse sorted array
            {1, 9, 5, 13, 3, 11, 7, 15, 
             2, 10, 6, 14, 4, 12, 8, 16},       // Worst case mergesort
            {8, 0, 4, 6, 2, 5, 1, 7, 3}
        };

        // Iterate through each test case
        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i].clone();
            System.out.println("[Test Suite] " + (i + 1) + ":");
            System.out.println("Original Array: \n" + Arrays.toString(arr));

            // Sort the array and count the key comparisons
            long keyComparisons = InsertionSort.sort(arr, 0, arr.length - 1);

            System.out.println("Sorted Array:\n" + Arrays.toString(arr));
            System.out.println("Total Key Comparisons: " + keyComparisons);
            
            // // Test with different starting and ending indices: 
            // arr = testCases[i].clone();

            // System.out.println("[Test Case] Start Index 2: ");
            // // Sort the array and count the key comparisons
            // keyComparisons = InsertionSort.sort(arr, 2, arr.length - 1);

            // System.out.println("Sorted Array:\n" + Arrays.toString(arr));
            // System.out.println("Total Key Comparisons: " + keyComparisons);

            // arr = testCases[i].clone();

            // System.out.println("[Test Case] Ending Index 2: ");
            // // Sort the array and count the key comparisons
            // keyComparisons = InsertionSort.sort(arr, 0, 2);

            // System.out.println("Sorted Array:\n" + Arrays.toString(arr));
            // System.out.println("Total Key Comparisons: " + keyComparisons);

            // arr = testCases[i].clone();

            // System.out.println("[Test Case] Starting Index 1 Ending Index len - 2: ");
            // // Sort the array and count the key comparisons
            // keyComparisons = InsertionSort.sort(arr, 1, arr.length - 2);

            // System.out.println("Sorted Array:\n" + Arrays.toString(arr));
            // System.out.println("Total Key Comparisons: " + keyComparisons);


            System.out.println();
        }
    }
}
