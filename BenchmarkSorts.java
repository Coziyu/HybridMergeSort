import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BenchmarkSorts {

    
    private static int[] randIntArray(int length, int largestElement){
        return new Random().ints(length, 0, largestElement).toArray();
    }
    // Becareful using this with elements that are too large. Very inefficient implementation.
    private static int[] randUniqueIntArray(int length, int largestElement){
        if(length > largestElement){
            System.err.println("[Warn]: randUniqueIntArray cannot generate " + length + " unique integers from 0 to " + largestElement);
            return new int[0];
        }
        ArrayList<Integer> numbers = new ArrayList<>(largestElement);
        int[] arr = new int[length];
        for(int i = 0; i < largestElement; i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for(int i = 0; i < length; i++){
            arr[i] = numbers.get(i);
        }
        return arr;
    }
    public static void main(String[] args) {
        int largestElement = 1000000;

        // i)
        // Arbitraily select an array size to switch from merge to insertion sort.
        ArrayList<int[]> testCases = new ArrayList<int[]>();
        for(int n = 1000; n <= 10000000; n*=10){
            testCases.add(randIntArray(n, largestElement));
        }
        System.out.println("i) Benchmark: Fix threshold S = 4, vary Array Length:");
        System.out.println("Array Length, Key Comps, Time (ms)");
        int insertionSortThreshold = 4;
        for (int[] testCase : testCases) {
            int[] input = testCase.clone();
            long startTime = System.nanoTime();
            long keyComparisons = MergeSort.sortHybrid(input, 0, input.length - 1, insertionSortThreshold);
            long endTime = System.nanoTime();
            System.out.printf("%-9s %-11s %10.5f%n", input.length + ",", keyComparisons + ", ", (double)(endTime - startTime)/1000000);
        }

        // ii)
        // We test with size = 100000
        int fixedLength = 100000, numSamples = 100;
        testCases = new ArrayList<int[]>();
        for(int n = 0; n < numSamples; n++){
            testCases.add(randIntArray(fixedLength, largestElement));
        }
        System.out.println("\n\nii) Benchmark: Fix Array Length = 100,000, vary threshold S, average of " + numSamples + " samples:");
        System.out.println("Threshold, Key Comps, Time (ms)");
        for(insertionSortThreshold = 0; insertionSortThreshold <= 100; insertionSortThreshold++){
            long averageKeyComparisons = 0;
            double averageTimeElapsed = 0;
            for (int[] testCase : testCases) {
                int[] input = testCase.clone();
                long startTime = System.nanoTime();
                long keyComparisons = MergeSort.sortHybrid(input.clone(), 0, input.length - 1, insertionSortThreshold);
                long endTime = System.nanoTime();
                averageKeyComparisons += keyComparisons;
                averageTimeElapsed += (double)(endTime - startTime)/1000000;
            }
            averageKeyComparisons /= numSamples;
            averageTimeElapsed /= numSamples;
            System.out.printf("%-4s %-11s %10.5f\n", insertionSortThreshold + ",", averageKeyComparisons + ", ", averageTimeElapsed);
        }


        // iii)?
        // 10,000,000 size, naive merge vs threshold = 6
        numSamples = 10;
        testCases = new ArrayList<int[]>();
        for(int n = 0; n < numSamples; n++){
            testCases.add(randIntArray(10000000, largestElement));
        }
        System.out.println("\n\niii) Benchmark: Compare Classic Mergesort, and Hybrid Mergesort with threshold S = 6, average of " + numSamples + " samples:");
        System.out.println("Threshold, Key Comps, Time (ms)");
        for(insertionSortThreshold = 0; insertionSortThreshold <= 6; insertionSortThreshold += 6){
            long averageKeyComparisons = 0;
            double averageTimeElapsed = 0;
            for (int[] testCase : testCases) {
                int[] input = testCase.clone();
                long startTime, endTime, keyComparisons;
                if(insertionSortThreshold == 0){
                    startTime = System.nanoTime();
                    keyComparisons = MergeSort.sort(input.clone(), 0, input.length - 1);
                    endTime = System.nanoTime();        
                }
                else{
                    startTime = System.nanoTime();
                    keyComparisons = MergeSort.sortHybrid(input.clone(), 0, input.length - 1, insertionSortThreshold);
                    endTime = System.nanoTime();
                }
                averageKeyComparisons += keyComparisons;
                averageTimeElapsed += (double)(endTime - startTime)/1000000;
            }
            averageKeyComparisons /= numSamples;
            averageTimeElapsed /= numSamples;
            System.out.printf("%-4s %-11s %10.5f\n", insertionSortThreshold + ",", averageKeyComparisons + ", ", averageTimeElapsed);
        }
    }    
}
