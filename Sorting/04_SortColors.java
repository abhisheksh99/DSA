class Solution {
    // Problem: Sort Colors
    // Given an array nums containing n objects colored red (0), white (1), or blue (2),
    // sort them in-place so that objects of the same color are adjacent, in the order red,
    // white, and blue. The array contains only 0s, 1s, and 2s, and the solution must use
    // constant extra space (O(1)) and run in O(n) time.

    // DSA Pattern: Two-Pointer (Dutch National Flag Algorithm)
    // This problem is solved using the Dutch National Flag algorithm, which sorts the array
    // in a single pass using three pointers: one for the boundary of 0s (start), one for
    // the boundary of 2s (end), and one for the current element (index). The algorithm
    // partitions the array into three sections: 0s at the start, 1s in the middle, and 2s
    // at the end.

    // Approach:
    // 1. Handle base cases: if the array is empty or has one element, return as it is sorted.
    // 2. Initialize three pointers:
    //    - start: points to the next position where a 0 should be placed.
    //    - end: points to the next position where a 2 should be placed.
    //    - index: iterates through the array to process each element.
    // 3. Iterate while index <= end:
    //    - If nums[index] == 0, swap with nums[start], increment start and index.
    //    - If nums[index] == 2, swap with nums[end], decrement end (do not increment index).
    //    - If nums[index] == 1, increment index (no swap needed).
    // 4. The swap function exchanges elements at two indices.
    // 5. The process continues until index exceeds end, resulting in a sorted array.

    // Key Points to Remember:
    // - The Dutch National Flag algorithm efficiently sorts three distinct values (0, 1, 2).
    // - The start pointer ensures all 0s are placed at the beginning.
    // - The end pointer ensures all 2s are placed at the end.
    // - The index pointer processes each element, leaving 1s in the middle naturally.
    // - When swapping with end, do not increment index, as the swapped element needs checking.
    // - The algorithm modifies the array in-place, using only O(1) extra space.
    // - The array is guaranteed to contain only 0s, 1s, and 2s, simplifying the logic.
    // - The result places all 0s first, followed by all 1s, then all 2s.

    // Time Complexity: O(n)
    // - n is the length of the input array nums.
    // - The algorithm performs a single pass through the array using the index pointer.
    // - Each element is processed at most twice (once when index reaches it, and once if
    //   swapped from the end), but this is still O(n) total operations.
    // - The swap operation is O(1).
    // - Base case checks are O(1).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (three pointers: start, end,
    //   and index, plus a temporary variable for swapping).
    // - The array is modified in-place, and no additional data structures are used.
    // - The space for the output (modified array) is required by the problem.

    public void sortColors(int[] nums) {
        // Handle base cases: empty array or single element
        if (nums.length == 0 || nums.length == 1) return;

        // Initialize pointers:
        // start: next position for 0
        // end: next position for 2
        // index: current element being processed
        int start = 0, end = nums.length - 1;
        int index = 0;

        // Process elements until index exceeds end
        while (index <= end) {
            if (nums[index] == 0) {
                // If current element is 0, swap with start and move both pointers
                swap(nums, index, start);
                start++;
                index++;
            } else if (nums[index] == 2) {
                // If current element is 2, swap with end and move end pointer
                swap(nums, index, end);
                end--;
                // Do not increment index, as the swapped element needs checking
            } else {
                // If current element is 1, no swap needed, move to next element
                index++;
            }
        }
    }

    // Helper function to swap elements at indices i and j
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}