class Solution {
    // Problem: Product of Array Except Self
    // Given an integer array nums, return an array result such that result[i] is the product
    // of all the numbers in nums except nums[i]. You must solve it without using division
    // and in O(n) time complexity.

    // DSA Pattern: Prefix and Suffix Products (Array Manipulation)
    // This problem is solved using the concept of prefix and suffix products. Instead of using
    // division, we compute the product of all elements before and after each index in two
    // separate passes, combining them to get the desired result. This is a form of array
    // manipulation that leverages precomputed products to avoid redundant calculations.

    // Approach:
    // 1. Initialize a result array of the same length as nums to store the output.
    // 2. First pass (left to right):
    //    - Compute the product of all elements before each index (prefix product).
    //    - Store the prefix product in result[i] and update the prefix product by multiplying
    //      it with nums[i].
    // 3. Second pass (right to left):
    //    - Compute the product of all elements after each index (suffix product).
    //    - Multiply result[i] by the suffix product and update the suffix product by
    //      multiplying it with nums[i].
    // 4. Return the result array, where each element is the product of all numbers except nums[i].

    // Key Points to Remember:
    // - Avoid division to handle cases where nums contains zeros and to meet the problem's constraints.
    // - Use two passes (left-to-right and right-to-left) to compute prefix and suffix products.
    // - The prefix product for index i is the product of all elements before i.
    // - The suffix product for index i is the product of all elements after i.
    // - Combining prefix and suffix products gives the product of all elements except the current one.
    // - Handle edge cases like arrays with one or more zeros, ensuring the result is correct.
    // - Use O(1) extra space (excluding the output array) by modifying the result array in-place.

    // Time Complexity: O(n)
    // - We perform two separate passes through the array, each taking O(n) time, where n is the length of nums.
    // - All operations within the loops (multiplication, assignment) are O(1).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1) (excluding the output array)
    // - We use only a constant amount of extra space for variables (pre, post).
    // - The result array is required by the problem and not counted as extra space.
    // - Overall, the space complexity is O(1) excluding the output array.

    public int[] productExceptSelf(int[] nums) {
        // Initialize the result array to store the output
        int[] result = new int[nums.length];
        // Initialize prefix product
        int pre = 1;
        // Initialize suffix product
        int post = 1;

        // First pass: Compute prefix products and store in result
        for (int i = 0; i < nums.length; i++) {
            // Store the product of all elements before index i
            result[i] = pre;
            // Update prefix product by multiplying with nums[i]
            pre *= nums[i];
        }

        // Second pass: Multiply by suffix products to get final result
        for (int i = nums.length - 1; i >= 0; i--) {
            // Multiply result[i] by the product of all elements after index i
            result[i] *= post;
            // Update suffix product by multiplying with nums[i]
            post *= nums[i];
        }

        // Return the result array
        return result;
    }
}