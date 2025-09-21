class Solution {
    // Problem: Find First and Last Position of Element in Sorted Array
    // Given a sorted array nums in ascending order, find the starting and ending position of a
    // given target value. Return [-1, -1] if the target is not found. The array may contain
    // duplicates, and the goal is to find the first and last indices where the target appears.

    // DSA Pattern: Binary Search (Modified for First and Last Position)
    // This problem is solved using two binary searches: one to find the first occurrence of the
    // target and another to find the last occurrence. The sorted nature of the array allows binary
    // search to efficiently locate the boundaries of the target's occurrences.

    // Approach:
    // 1. Define a helper function findPosition that uses binary search to find either the first
    //    or last occurrence of the target, controlled by a boolean flag findFirst.
    // 2. In findPosition:
    //    - Initialize left (0) and right (nums.length - 1) pointers, and pos (-1) to store the result.
    //    - While left <= right:
    //      - Compute mid using left + (right - left) / 2 to avoid overflow.
    //      - If nums[mid] == target:
    //        - Store mid in pos (potential answer).
    //        - If findFirst is true, search left half (right = mid - 1) to find earlier occurrences.
    //        - If findFirst is false, search right half (left = mid + 1) to find later occurrences.
    //      - If nums[mid] < target, search right half (left = mid + 1).
    //      - If nums[mid] > target, search left half (right = mid - 1).
    //    - Return pos (the first or last position, or -1 if not found).
    // 3. Call findPosition twice: once with findFirst = true to get the first position, and once
    //    with findFirst = false to get the last position.
    // 4. Return an array containing the first and last positions.

    // Key Points to Remember:
    // - Use binary search twice to find the boundaries of the target's occurrences.
    // - Modify binary search to continue searching even after finding the target to ensure the
    //   first or last occurrence is found.
    // - Use findFirst flag to control whether to search left (for first) or right (for last).
    // - Handle edge cases like empty arrays, target not present, or single occurrence of the target.
    // - Ensure mid calculation avoids integer overflow using left + (right - left) / 2.
    // - The sorted array guarantees that all occurrences of the target are contiguous.
    // - Return [-1, -1] if the target is not found.

    // Time Complexity: O(log n)
    // - Each call to findPosition uses binary search, which takes O(log n), where n is the length
    //   of nums.
    // - We perform two binary searches (one for first, one for last).
    // - Overall, the time complexity is O(log n) + O(log n) = O(log n).
    // - All operations within the loop (comparisons, assignments) are O(1).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, mid, pos).
    // - The output array [first, last] is required by the problem and not counted as extra space.
    // - Overall, the space complexity is O(1).

    public int[] searchRange(int[] nums, int target) {
        // Find the first and last positions using binary search
        int first = findPosition(nums, target, true);
        int last = findPosition(nums, target, false);
        
        // Return the result as an array
        return new int[] { first, last };
    }

    // Helper function to find either the first or last position of the target
    private int findPosition(int[] nums, int target, boolean findFirst) {
        // Initialize pointers and position
        int left = 0;
        int right = nums.length - 1;
        int pos = -1;

        // Perform binary search
        while (left <= right) {
            // Calculate mid to avoid integer overflow
            int mid = left + (right - left) / 2;

            // If target is found
            if (nums[mid] == target) {
                // Store the current position
                pos = mid;
                // If finding first occurrence, search left half for earlier occurrences
                if (findFirst) {
                    right = mid - 1;
                }
                // If finding last occurrence, search right half for later occurrences
                else {
                    left = mid + 1;
                }
            }
            // If target is greater, search right half
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, search left half
            else {
                right = mid - 1;
            }
        }

        // Return the found position (or -1 if not found)
        return pos;
    }
}