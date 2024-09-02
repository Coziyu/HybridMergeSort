public class InsertionSort {    
    /**
     * Sorts an array of integers using the insertion sort algorithm and counts the number of key comparisons.
     * Note that this mutates the original array.
     *
     * @param array The array to be sorted.
     * @param startIndex The starting index of the array to sort (usually 0).
     * @param endIndex The ending index of the array to sort (usually array.length - 1).
     * @return The number of key comparisons made during sorting.
     */
    public static long sort(int[] array, int startIndex, int endIndex) {
        long keyComparisons = 0;

        // Iterate over each element starting from the second one
        for (int i = startIndex + 1; i <= endIndex; i++) {
            int key = array[i];
            int j = i - 1;

            // Insert the key into the sorted portion of the array (array[startIndex, ..., i-1])
            while (j >= startIndex) {
                keyComparisons++;

                // Shift elements to the right to make space for the key
                if (array[j] > key) {
                    // Note that while this seemingly overrides the element on the right,
                    // the only element that is effectively "lost to overriding" is the key - 
                    // which we already kept track of in the aptly named variable `key`.
                    array[j + 1] = array[j];
                    j--;
                } else {
                    // Key is in the correct position
                    break;
                }
            }

            // Place the key in its correct position
            array[j + 1] = key;
        }

        return keyComparisons;
    }
}
