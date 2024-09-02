import java.util.Arrays;

// Ugly messy code here for a rough test to check whether MergeSort implementation is correct.

public class TestHybridMergeSort {
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
            
            
            for(int threshold = 0; threshold <= 10; threshold++){
                arr = testCases[i].clone();
                System.out.println("Threshold: " + threshold);
                // Sort the array and count the key comparisons
                int keyComparisons = MergeSort.sortHybrid(arr, 0, arr.length - 1, threshold);

                System.out.println("Sorted Array:\n" + Arrays.toString(arr));
                System.out.println("Total Key Comparisons: " + keyComparisons);
            }
            System.out.println();
        }
    }
}