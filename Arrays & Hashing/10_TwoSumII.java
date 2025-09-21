class Solution {
    // Problem: Two Sum II - Input Array Is Sorted
    // Given a 1-indexed array of integers numbers that is sorted in ascending order, find two
    // numbers such that they add up to a specific target. Return the indices of the two numbers
    // (1-indexed) as an array [left+1, right+1]. It is guaranteed that exactly one solution exists,
    // and you may not use the same element twice.

    // DSA Pattern: Two Pointers
    // This problem is solved using the two-pointer technique, leveraging the fact that the array is
    // sorted in ascending order. By maintaining two pointers (left and right), we can efficiently
    // find the pair of numbers that sum to the target by adjusting the pointers based on the sum.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (numbers.length - 1).
    // 2. While left < right:
    //    - Calculate the sum of numbers[left] and numbers[right].
    //    - If the sum equals the target, return the 1-indexed positions [left + 1, right + 1].
    //    - If the sum is less than the target, increment left to include a larger number.
    //    - If the sum is greater than the target, decrement right to include a smaller number.
    // 3. If no solution is found (though guaranteed not to happen per problem constraints), return
    //    an empty array.

    // Key Points to Remember:
    // - Leverage the sorted nature of the array to use two pointers, avoiding the need for extra space.
    // - Start with left at the smallest number and right at the largest to efficiently converge on the solution.
    // - Adjust pointers based on the sum: increment left for a larger sum, decrement right for a smaller sum.
    // - Return 1-indexed positions as required by the problem (left + 1, right + 1).
    // - The problem guarantees exactly one solution, so no need to handle cases with no solution.
    // - Handle edge cases like arrays with exactly two elements correctly.
    // - The two-pointer approach ensures we don’t use the same element twice since left < right.

    // Time Complexity: O(n)
    // - The two pointers traverse the array at most once, as left moves right and right moves left until they meet.
    // - Each iteration involves O(1) operations (sum, comparison, pointer updates).
    // - Overall, the time complexity is O(n), where n is the length of numbers.

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, currSum).
    // - The output array is required by the problem and not counted as extra space.
    // - Overall, the space complexity is O(1).

    public int[] twoSum(int[] numbers, int target) {
        // Initialize left pointer at the start of the array
        int left = 0;
        // Initialize right pointer at the end of the array
        int right = numbers.length - 1;

        // Iterate while left pointer is less than right pointer
        while (left < right) {
            // Calculate the sum of the elements at left and right pointers
            int currSum = numbers[left] + numbers[right];
            // If the sum equals the target, return 1-indexed positions
            if (currSum == target) {
                return new int[]{left + 1, right + 1};
            }
            // If the sum is less than the target, increment left to get a larger sum
            else if (currSum < target) {
                left++;
            }
            // If the sum is greater than the target, decrement right to get a smaller sum
            else {
                right--;
            }
        }

        // Return empty array (though problem guarantees a solution exists)
        return new int[0];
    }
}