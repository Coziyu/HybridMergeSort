public class MergeSort {
    /**
     * Sorts an array of integers using the merge sort algorithm and counts the number of keycomparisons.
     * Note that this implementation uses auxiliary space for the merging step.
     * 
     * @param array The array to be sorted.
     * @param startIndex The starting index of the array to sort (usually 0).
     * @param endIndex The ending index of the array to sort (usually array.length - 1).
     * @return The number of key comparisons made during sorting.
     */
    public static long sort(int[] array, int startIndex, int endIndex){
        long keyComparisons = 0;
        if (startIndex < endIndex){
            
            int midIndex = (startIndex + endIndex) / 2;
            long leftSubarrayKeyComps = sort(array, startIndex, midIndex);
            long rightSubarrayKeyComps = sort(array, midIndex + 1, endIndex);
            
            long mergeKeyComps = merge(array, startIndex, midIndex, endIndex);

            keyComparisons += leftSubarrayKeyComps;
            keyComparisons += rightSubarrayKeyComps;
            keyComparisons += mergeKeyComps;
        }
        return keyComparisons;
    }
    /**
     * Sorts an array of integers, using a combination of merge sort and insertion sort. 
     * When the size of the subarray falls below a `threshold`, insertion sort is used instead of merge sort.
     * 
     * @param array The array to be sorted. This array is modified in place.
     * @param startIndex The starting index of the subarray to be sorted. This is inclusive.
     * @param endIndex The ending index of the subarray to be sorted. This is inclusive.
     * @param threshold The threshold size below which insertion sort will be used instead of merge sort.
     * @return The total number of key comparisons made during the sort process.
     */
    public static long sortHybrid(int[] array, int startIndex, int endIndex, int threshold){
        long keyComparisons = 0;
        if (endIndex - startIndex + 1 <= threshold){
            keyComparisons += InsertionSort.sort(array, startIndex, endIndex);
        }
        else if (startIndex < endIndex){
            
            int midIndex = (startIndex + endIndex) / 2;
            long leftSubarrayKeyComps = sortHybrid(array, startIndex, midIndex, threshold);
            long rightSubarrayKeyComps = sortHybrid(array, midIndex + 1, endIndex, threshold);
            
            long mergeKeyComps = merge(array, startIndex, midIndex, endIndex);

            keyComparisons += leftSubarrayKeyComps;
            keyComparisons += rightSubarrayKeyComps;
            keyComparisons += mergeKeyComps;
        }
        return keyComparisons;
    }

    /**
     * Merges two sorted subarrays of the given array and counts the number of key comparisons made during the merge process.
     * This will mutate the array with the sorted values from startIndex to endIndex.
     * 
     * @param array The superarray containing the two subarrays to be merged. 
     * @param startIndex The starting index of the first subarray.
     * @param midIndex The index that separates the two subarrays - it is the end of the first subarray and one less than the start of the second subarray.
     * @param endIndex The ending index of the second subarray.
     * @return The number of key comparisons made during the merge process.
     */
    protected static long merge(int[] array, int startIndex, int midIndex, int endIndex) {
        long keyComparisons = 0;
        int leftSize = midIndex - startIndex + 1;
        int rightSize = endIndex - midIndex;

        // Create and populate auxilary arrays for merging.
        int left[] = new int[leftSize];
        int right[] = new int[rightSize];

        for(int i = 0; i < leftSize; i++){
            left[i] = array[startIndex + i];
        }
        for(int i = 0; i < rightSize; i++){
            right[i] = array[midIndex + 1 + i];
        }

        // Pointer to traverse the left, right and original subarray segment.
        int l = 0;
        int r = 0;
        int k = startIndex;
        while(l < leftSize && r < rightSize){
            keyComparisons++;
            if(left[l] <= right[r]){
                array[k] = left[l];
                l++;
            }
            else{
                array[k] = right[r];
                r++;
            }
            k++;
        }

        // Either the left or right subarray is completely empty, so no more key comparisons required.
        // We simply copy any remaining elements from the non empty subarray.
        while(l < leftSize){
            array[k] = left[l];
            l++;
            k++;
        }
        while(r < rightSize){
            array[k] = right[r];
            r++;
            k++;
        }

        return keyComparisons;
    }
}
