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
        int largestElement = 10000;

        ArrayList<int[]> testCases = new ArrayList<int[]>();
        for(int n = 1; n <= 10000000; n*=10){
            testCases.add(randIntArray(n, largestElement));
        }

        // i)
        // Arbitraily select an array size to switch from merge to insertion sort.
        System.out.println("Benchmark: fix threshold, vary input length:");
        int insertionSortThreshold = 4;
        for (int[] testCase : testCases) {
            int[] input = testCase.clone();
            long startTime = System.nanoTime();
            long keyComparisons = MergeSort.sortHybrid(input, 0, input.length - 1, insertionSortThreshold);
            long endTime = System.nanoTime();
            System.out.printf("%-9s %-11s %d%n", input.length + ",", keyComparisons + ", ", (endTime - startTime));
        }

        // ii)
        // We test with size = 10000
        int fixedLength = 10000, numSamples = 100;
        testCases = new ArrayList<int[]>();
        for(int n = 0; n < numSamples; n++){
            testCases.add(randIntArray(fixedLength, largestElement));
        }
        System.out.println("\n\nBenchmark: vary threshold, fix input length:");
        for(insertionSortThreshold = 0; insertionSortThreshold <= 100; insertionSortThreshold++){
            double averageKeyComparisons = 0;
            long averageTimeElapsed = 0;
            for (int[] testCase : testCases) {
                int[] input = testCase.clone();
                long startTime = System.nanoTime();
                long keyComparisons = MergeSort.sortHybrid(input.clone(), 0, input.length - 1, insertionSortThreshold);
                long endTime = System.nanoTime();
                averageKeyComparisons += keyComparisons;
                averageTimeElapsed += endTime - startTime;
            }
            averageKeyComparisons /= numSamples;
            averageTimeElapsed /= numSamples;
            System.out.printf("%-3s %-11s %s\n", insertionSortThreshold + ",", averageKeyComparisons + ", ", averageTimeElapsed);
        }
    }    
}
