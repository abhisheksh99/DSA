class Solution {
    // Problem: Find Minimum in Rotated Sorted Array
    // Given a sorted array nums that has been rotated an unknown number of times, find the
    // minimum element in the array. The array is sorted in ascending order before rotation,
    // and all elements are unique.

    // DSA Pattern: Binary Search (Modified for Rotated Sorted Array)
    // This problem is solved using a modified binary search to efficiently find the minimum
    // element in a rotated sorted array. The rotation creates a pivot point where the minimum
    // element lies, and binary search helps locate this pivot by comparing the middle element
    // with the left or right boundary.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (nums.length - 1).
    // 2. While left < right:
    //    - Check if nums[left] < nums[right]. If true, the array is sorted (no rotation or
    //      minimum is at left), so return nums[left].
    //    - Calculate the middle index (mid) using left + (right - left) / 2 to avoid overflow.
    //    - Compare nums[mid] with nums[left]:
    //      - If nums[mid] >= nums[left], the left half is sorted, and the minimum lies in the
    //        right half, so set left = mid + 1.
    //      - If nums[mid] < nums[left], the minimum lies in the left half (including mid), so
    //        set right = mid.
    // 3. When left == right, return nums[left] as the minimum element.

    // Key Points to Remember:
    // - Use binary search to reduce the search space by half in each iteration.
    // - The minimum element is at the pivot point where the sorted order breaks in the rotated array.
    // - Compare nums[mid] with nums[left] to determine which half contains the minimum.
    // - If nums[left] < nums[right], the array is sorted, and the minimum is nums[left].
    // - Handle edge cases like an array with no rotation (sorted) or a single element.
    // - Ensure mid calculation avoids integer overflow by using left + (right - left) / 2.
    // - The loop ends when left == right, pointing to the minimum element.

    // Time Complexity: O(log n)
    // - Binary search divides the search space in half with each iteration, where n is the length
    //   of nums.
    // - Each iteration involves O(1) operations (comparisons, assignments).
    // - Overall, the time complexity is O(log n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, mid).
    // - No additional data structures are used, so the space complexity is O(1).

    public int findMin(int[] nums) {
        // Initialize left pointer at the start of the array
        int left = 0;
        // Initialize right pointer at the end of the array
        int right = nums.length - 1;

        // Perform binary search while left < right
        while (left < right) {
            // If left element is less than right element, array is sorted, return nums[left]
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            // Calculate middle index to avoid integer overflow
            int mid = left + (right - left) / 2;
            // If mid element is greater than or equal to left element, minimum is in right half
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            }
            // If mid element is less than left element, minimum is in left half (including mid)
            else {
                right = mid;
            }
        }

        // When left == right, return the minimum element
        return nums[left];
    }
}