class Solution {
    // Problem: Search in Rotated Sorted Array
    // Given a sorted array nums that has been rotated an unknown number of times and a target
    // value, return the index of the target if it exists in the array, or -1 if it does not.
    // The array is sorted in ascending order before rotation, and all elements are unique.

    // DSA Pattern: Binary Search (Modified for Rotated Sorted Array)
    // This problem is solved using a modified binary search to efficiently find the target in a
    // rotated sorted array. The rotation creates a pivot point, and binary search determines which
    // half of the array is sorted to decide where to search for the target.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (nums.length - 1).
    // 2. While left <= right:
    //    - Calculate the middle index (mid) using left + (right - left) / 2 to avoid overflow.
    //    - If nums[mid] equals the target, return mid.
    //    - Check if the left half (nums[left] to nums[mid]) is sorted by comparing nums[left]
    //      with nums[mid]:
    //      - If sorted (nums[left] <= nums[mid]), check if the target lies in the left half
    //        (nums[left] <= target < nums[mid]). If so, search the left half (right = mid - 1).
    //        Otherwise, search the right half (left = mid + 1).
    //      - If the left half is not sorted, the right half (nums[mid] to nums[right]) must be
    //        sorted. Check if the target lies in the right half (nums[mid] < target <= nums[right]).
    //        If so, search the right half (left = mid + 1). Otherwise, search the left half
    //        (right = mid - 1).
    // 3. If the target is not found, return -1.

    // Key Points to Remember:
    // - Use binary search to reduce the search space by half in each iteration.
    // - Determine which half of the array is sorted by comparing nums[mid] with nums[left].
    // - Check if the target lies within the sorted half’s range to decide which half to search.
    // - Handle edge cases like an array with no rotation, single element, or target not present.
    // - Ensure mid calculation avoids integer overflow by using left + (right - left) / 2.
    // - The loop continues while left <= right to include the last element in the search.
    // - Return -1 if the target is not found after the search.

    // Time Complexity: O(log n)
    // - Binary search divides the search space in half with each iteration, where n is the length
    //   of nums.
    // - Each iteration involves O(1) operations (comparisons, assignments).
    // - Overall, the time complexity is O(log n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, mid).
    // - No additional data structures are used, so the space complexity is O(1).

    public int search(int[] nums, int target) {
        // Initialize left pointer at the start of the array
        int left = 0;
        // Initialize right pointer at the end of the array
        int right = nums.length - 1;

        // Perform binary search while left pointer is less than or equal to right pointer
        while (left <= right) {
            // Calculate middle index to avoid integer overflow
            int mid = left + (right - left) / 2;

            // If the middle element is the target, return its index
            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left half (nums[left] to nums[mid]) is sorted
            if (nums[left] <= nums[mid]) {
                // If target lies in the sorted left half (nums[left] <= target < nums[mid])
                if (nums[left] <= target && target < nums[mid]) {
                    // Search the left half
                    right = mid - 1;
                } else {
                    // Search the right half
                    left = mid + 1;
                }
            }
            // If left half is not sorted, right half (nums[mid] to nums[right]) is sorted
            else {
                // If target lies in the sorted right half (nums[mid] < target <= nums[right])
                if (nums[mid] < target && target <= nums[right]) {
                    // Search the right half
                    left = mid + 1;
                } else {
                    // Search the left half
                    right = mid - 1;
                }
            }
        }

        // If target is not found, return -1
        return -1;
    }
}