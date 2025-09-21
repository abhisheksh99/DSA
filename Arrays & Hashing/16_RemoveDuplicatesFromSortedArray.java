class Solution {
    // Problem: Remove Duplicates from Sorted Array
    // Given a sorted array nums, remove the duplicates in-place such that each unique element
    // appears only once. The relative order of the elements should be kept the same. Return the
    // number of unique elements in nums after removing duplicates. The array is modified in-place,
    // and the first k elements of nums should hold the unique elements.

    // DSA Pattern: Two Pointers (Fast and Slow Pointers)
    // This problem is solved using the two-pointer technique, where one pointer (insertIndex) acts
    // as the "slow" pointer to track the position where the next unique element should be placed,
    // and the loop index (i) acts as the "fast" pointer to scan through the array. This allows
    // us to overwrite duplicates in-place while maintaining the sorted order.

    // Approach:
    // 1. Initialize insertIndex to 1, assuming the first element (nums[0]) is always unique.
    // 2. Iterate through the array starting from index 1:
    //    - If nums[i] is different from nums[i-1], it is a new unique element.
    //    - Place nums[i] at nums[insertIndex] and increment insertIndex.
    //    - Skip duplicates by continuing the loop without updating insertIndex.
    // 3. After the loop, insertIndex represents the number of unique elements and the length
    //    of the modified array.
    // 4. Return insertIndex. The first insertIndex elements in nums now contain the unique elements
    //    in sorted order.

    // Key Points to Remember:
    // - The array is sorted in ascending order, so duplicates are adjacent, allowing a single pass.
    // - Use two pointers: insertIndex (slow) for the next position to place a unique element,
    //   and i (fast) for scanning.
    // - Only overwrite when a new unique element is found (nums[i] != nums[i-1]).
    // - The first element is always unique, so start insertIndex at 1.
    // - Handle edge cases like all unique elements (insertIndex = nums.length) or all duplicates
    //   (insertIndex = 1).
    // - The operation is in-place, modifying nums without extra space for the result.
    // - The relative order is preserved due to the sequential scan.

    // Time Complexity: O(n)
    // - We iterate through the array once, where n is the length of nums.
    // - Each iteration involves O(1) operations (comparison, assignment, increment).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (insertIndex, i).
    // - The array is modified in-place, so no additional data structures are used.
    // - Overall, the space complexity is O(1).

    public int removeDuplicates(int[] nums) {
        // Initialize insertIndex to 1 (first element nums[0] is always unique)
        int insertIndex = 1;
        
        // Iterate starting from index 1
        for (int i = 1; i < nums.length; i++) {
            // If current element is different from previous, it's unique
            if (nums[i - 1] != nums[i]) {
                // Place the unique element at insertIndex
                nums[insertIndex] = nums[i];
                // Increment insertIndex for the next unique position
                insertIndex++;
            }
            // Skip duplicates by not updating insertIndex
        }
        
        // Return the number of unique elements (length of modified array)
        return insertIndex;
    }
}