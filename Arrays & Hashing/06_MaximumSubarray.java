class Solution {
    // Problem: Maximum Subarray
    // Given an integer array nums, find the contiguous subarray (containing at least one number)
    // which has the largest sum and return its sum.

    // DSA Pattern: Kadane's Algorithm (Dynamic Programming)
    // This problem is solved using Kadane's Algorithm, a dynamic programming approach that
    // efficiently finds the maximum sum of a contiguous subarray by iterating through the array
    // once and maintaining the maximum sum ending at each position.

    // Approach:
    // 1. Initialize currSum to track the maximum sum of the subarray ending at the current index.
    // 2. Initialize maxSum to the first element of the array to handle cases where the array
    //    has only one element or all negative numbers.
    // 3. Iterate through the array:
    //    - If currSum becomes negative, reset it to 0, as starting a new subarray from the
    //      current element is more beneficial.
    //    - Add the current element to currSum.
    //    - Update maxSum by taking the maximum of currSum and maxSum.
    // 4. Return maxSum as the result, which represents the maximum subarray sum.

    // Key Points to Remember:
    // - Kadane's Algorithm optimizes the problem by avoiding the need to check all possible subarrays.
    // - Reset currSum to 0 when it becomes negative, as a negative sum reduces the potential
    //   maximum sum of future subarrays.
    // - Initialize maxSum with nums[0] to handle edge cases like single-element arrays or arrays
    //   with all negative numbers.
    // - The algorithm works in a single pass, making it highly efficient.
    // - Handle edge cases like arrays with all negative numbers, where the maximum sum is the
    //   largest single element.

    // Time Complexity: O(n)
    // - We iterate through the array once, where n is the length of nums.
    // - All operations within the loop (comparisons, additions, max) are O(1).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (currSum, maxSum).
    // - No additional data structures are used, so the space complexity is O(1).

    public int maxSubArray(int[] nums) {
        // Initialize current sum to track the maximum sum ending at the current index
        int currSum = 0;
        // Initialize maxSum with the first element to handle edge cases
        int maxSum = nums[0];

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If currSum is negative, reset it to 0 to start a new subarray
            if (currSum < 0) {
                currSum = 0;
            }
            // Add the current element to currSum
            currSum = currSum + nums[i];
            // Update maxSum if currSum is greater
            maxSum = Math.max(currSum, maxSum);
        }

        // Return the maximum subarray sum
        return maxSum;
    }
}