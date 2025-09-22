// Problem: Maximum Subarray
// Given an integer array 'nums', find the contiguous subarray (containing at least one number) which has the largest sum
// and return its sum.

// DSA Pattern: Greedy / Kadane's Algorithm
// This problem is solved using Kadane's Algorithm, a greedy approach that iterates through the array while maintaining
// the maximum sum of a subarray ending at each position and tracking the global maximum sum.

// Approach:
// 1. Initialize two variables:
//    - currSum: tracks the maximum sum of the subarray ending at the current index.
//    - maxSum: tracks the global maximum sum, initialized to the first element.
// 2. Iterate through the array:
//    - If currSum becomes negative, reset it to 0 (starting a new subarray is better).
//    - Add the current element to currSum.
//    - Update maxSum if currSum is larger than the current maxSum.
// 3. Return maxSum as the result.

// Key Points to Remember:
// - Kadane's Algorithm greedily decides whether to extend the current subarray or start a new one by checking if currSum is negative.
// - The algorithm ensures at least one number is included in the subarray (per problem constraints).
// - Edge cases:
//   - Single element array: return that element.
//   - All negative numbers: return the largest single number (handled by initializing maxSum to nums[0]).
//   - All positive numbers: sum of the entire array is the maximum.
// - The solution handles both positive and negative integers in the input array.

// Time Complexity: O(n), where n is the length of nums
// - The algorithm performs a single pass through the array, with constant-time operations per element.

// Space Complexity: O(1)
// - Only two variables (currSum and maxSum) are used, requiring constant extra space.
// - No additional data structures are needed beyond the input array.

// Constraints:
// - 1 <= nums.length <= 10^5
// - -10^4 <= nums[i] <= 10^4

public class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize current sum and maximum sum
        int currSum = 0;
        int maxSum = nums[0];
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Reset current sum if it becomes negative
            if (currSum < 0) {
                currSum = 0;
            }
            // Add current element to current sum
            currSum = currSum + nums[i];
            // Update maximum sum if current sum is larger
            maxSum = Math.max(currSum, maxSum);
        }
        
        // Return the maximum subarray sum
        return maxSum;
    }
}