class Solution {
    // Problem: Binary Search
    // Given a sorted array of integers nums (in ascending order) and an integer target,
    // return the index of target in nums, or -1 if target does not exist. The array is
    // guaranteed to be sorted, may contain duplicates, and could be empty.

    // DSA Pattern: Binary Search
    // This problem is solved using binary search, which efficiently finds the target in a
    // sorted array by repeatedly dividing the search space in half. By comparing the middle
    // element to the target, we can eliminate half of the remaining elements in each step,
    // adjusting the search boundaries based on whether the target is smaller or larger.

    // Approach:
    // 1. Initialize two pointers: left (start of array) and right (end of array).
    // 2. While left <= right:
    //    - Calculate the middle index as mid = left + (right - left) / 2 to avoid integer overflow.
    //    - If nums[mid] equals the target, return mid as the found index.
    //    - If nums[mid] is greater than the target, search the left half by setting right = mid - 1.
    //    - If nums[mid] is less than the target, search the right half by setting left = mid + 1.
    // 3. If the loop ends without finding the target, return -1.

    // Key Points to Remember:
    // - The array must be sorted in ascending order for binary search to work correctly.
    // - Use left + (right - left) / 2 to calculate mid to prevent integer overflow.
    // - Update left = mid + 1 or right = mid - 1 to exclude the middle element after comparison.
    // - The algorithm assumes the target may not exist, requiring a -1 return in such cases.
    // - If duplicates exist, this returns the index of any occurrence of the target.
    // - The approach is efficient for large sorted arrays, reducing the search space logarithmically.
    // - Handle edge cases: empty array, single element, or target not present.

    // Time Complexity: O(log n)
    // - n is the length of the input array nums.
    // - Binary search divides the search space in half with each iteration, leading to
    //   O(log n) iterations.
    // - Each iteration performs O(1) operations (comparisons and pointer updates).
    // - Total time is O(log n).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (three variables: left,
    //   right, and mid).
    // - No additional data structures are used, and the array is not modified.
    // - The output (returned index) is required by the problem.

    public int search(int[] nums, int target) {
        // Initialize pointers for the start and end of the search space
        int left = 0;
        int right = nums.length - 1;

        // Continue searching while the search space is valid
        while (left <= right) {
            // Calculate middle index, avoiding integer overflow
            int mid = left + (right - left) / 2;

            // If target is found at mid, return the index
            if (nums[mid] == target) {
                return mid;
            }

            // If middle element is greater than target, search the left half
            if (nums[mid] > target) {
                right = mid - 1; // Exclude mid and search left
            } else {
                // If middle element is less than target, search the right half
                left = mid + 1; // Exclude mid and search right
            }
        }

        // If target is not found, return -1
        return -1;
    }
}