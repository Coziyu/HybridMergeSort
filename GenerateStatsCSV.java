import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class GenerateStatsCSV {
    private static int[] randIntArray(int length, int largestElement){
        return new Random().ints(length, 0, largestElement).toArray();
    }
    public static void main(String[] args) {
        // Create a stream to redirect the standard print function output to:
        File csvFile = new File("stats_combined.csv");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(csvFile, false);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Using ps.print... will write to the csvFile now.
        PrintStream ps = new PrintStream(fos);


        int largestElement = 10000000;
        int longestArray = 10000000;
        int numSamples = 1;
        int arrayLength = 10000000;
        System.out.println("Generating statistics.\n");
        ps.println("thres, size, keycomp, time");
        for(double lenPower = 3; arrayLength <= longestArray; arrayLength = (int)Math.round(Math.pow(10, lenPower)), lenPower+=1){ //! Change lenPower to += 0.25 for more values
            ArrayList<int[]> testCases = new ArrayList<int[]>();
            for(int n = 0; n < numSamples; n++){
                testCases.add(randIntArray(arrayLength, largestElement));
            }
            for(int insertionSortThreshold = 0; insertionSortThreshold <= 100; insertionSortThreshold++){
                
                // These esoteric lines are for printing progress //
                System.out.print(String.format("\033[%dA",1)); // Move up
                System.out.print("\033[2K"); // Erase line content
                double progress = ((Math.log10(arrayLength) - 1) + (double)insertionSortThreshold / (double)(100)) / Math.log10(longestArray);
                System.out.printf("Progress: %.2f %%%n", progress * 100); 
                // These esoteric lines are for printing progress //
                
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
                ps.printf("%-4s %-9s %-11.1f %s\n", insertionSortThreshold + ",", arrayLength + ",", averageKeyComparisons, ", " + averageTimeElapsed);
            }
        }

        ps.close();
    }
}
