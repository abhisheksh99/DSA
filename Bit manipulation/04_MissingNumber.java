// Problem: Missing Number
// Given an array nums containing n distinct numbers in the range [0, n], return the only
// number in the range that is missing from the array.

// DSA Pattern: Mathematical (Gauss' Formula)
// This problem is solved using the mathematical formula for the sum of the first n natural
// numbers (0 to n). By calculating the expected sum of numbers from 0 to n and subtracting
// the actual sum of the array elements, the missing number is determined.

// Approach:
// 1. Get the length of the array, n, which represents the range [0, n].
// 2. Calculate the expected sum of numbers from 0 to n using Gauss' formula: n * (n + 1) / 2.
// 3. Compute the actual sum of the array elements using Arrays.stream(nums).sum().
// 4. Return the difference between the expected sum and the actual sum, which is the missing number.

// Key Points to Remember:
// - The array contains n distinct numbers in the range [0, n], so one number is missing.
// - Gauss' formula n * (n + 1) / 2 gives the sum of integers from 0 to n (inclusive).
// - Subtracting the actual sum from the expected sum directly yields the missing number.
// - The problem assumes the array is non-empty and contains distinct integers in [0, n].
// - Edge cases: n = 1 (array has one element, e.g., [0] or [1]), or n = 0 (not applicable due to constraints).
// - The formula avoids explicit iteration over the range, making the solution efficient.
// - Using Arrays.stream().sum() is a concise way to compute the actual sum, though a loop could also be used.

// Time Complexity: O(n)
// - Computing the array length is O(1).
// - Calculating the expected sum using n * (n + 1) / 2 is O(1).
// - Computing the actual sum using Arrays.stream(nums).sum() takes O(n) as it iterates over the array.
// - Total time is dominated by the stream operation, resulting in O(n).

// Space Complexity: O(1)
// - The algorithm uses only a constant amount of extra space (variables: n, Tsum, actualSum).
// - No additional data structures are used beyond the input array.
// - The output (integer result) is required by the problem.

// Example:
// Input: nums = [3, 0, 1]
// Output: 2
// Explanation:
// - n = 3, so the range is [0, 3].
// - Expected sum = 3 * (3 + 1) / 2 = 6.
// - Actual sum = 3 + 0 + 1 = 4.
// - Missing number = 6 - 4 = 2.

import java.util.Arrays;

class Solution {
    public int missingNumber(int[] nums) {
        // Get the length of the array
        int n = nums.length;
        // Calculate expected sum of numbers from 0 to n
        int Tsum = (n * (n + 1)) / 2;
        // Calculate actual sum of array elements
        int actualSum = Arrays.stream(nums).sum();
        // Return the missing number
        return Tsum - actualSum;
    }
}