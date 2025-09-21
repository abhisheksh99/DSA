class Solution {
    // Problem: Maximum Product Subarray
    // Given an integer array nums, find the contiguous subarray (containing at least one number)
    // which has the largest product and return its product.

    // DSA Pattern: Kadane's Algorithm (Modified for Product, Dynamic Programming)
    // This problem is solved using a modified version of Kadane's Algorithm tailored for products
    // instead of sums. Since multiplication involves negative numbers, we need to track both the
    // maximum and minimum product ending at each index to account for the possibility of a negative
    // number flipping the maximum product to a minimum or vice versa.

    // Approach:
    // 1. Initialize min and max to track the minimum and maximum product of subarrays ending at
    //    the current index, starting with nums[0].
    // 2. Initialize result to nums[0] to handle edge cases like single-element arrays.
    // 3. Iterate through the array starting from index 1:
    //    - For each number nums[i], compute the new maximum product by considering:
    //      - The current number alone (curr).
    //      - The product of the current number with the previous maximum product (max * curr).
    //      - The product of the current number with the previous minimum product (min * curr),
    //        as a negative number could make this the maximum.
    //    - Update min similarly, considering the minimum of the same three options.
    //    - Store the new maximum in a temporary variable to avoid overwriting max before computing min.
    //    - Update max to the new maximum product.
    //    - Update result to the maximum of the current result and max.
    // 4. Return result as the maximum product of any contiguous subarray.

    // Key Points to Remember:
    // - Track both maximum and minimum products at each step, as negative numbers can flip the
    //   maximum to minimum and vice versa.
    // - Initialize min, max, and result with nums[0] to handle single-element arrays and edge cases.
    // - Use a temporary variable to store the new maximum before updating min, as min depends on
    //   the previous max.
    // - Handle edge cases like arrays with zeros (resetting products) or negative numbers.
    // - The algorithm runs in a single pass, making it efficient.
    // - Unlike Kadane's for sum, we don’t reset products to 0 on negative values, as negative
    //   products can become maximum with another negative number.

    // Time Complexity: O(n)
    // - We iterate through the array once, starting from index 1, where n is the length of nums.
    // - All operations within the loop (comparisons, multiplications, max/min) are O(1).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (min, max, result, curr, temp).
    // - No additional data structures are used, so the space complexity is O(1).

    public int maxProduct(int[] nums) {
        // Initialize min and max products ending at the current index, and result with nums[0]
        int min = nums[0], max = nums[0], result = nums[0];

        // Iterate through the array starting from index 1
        for (int i = 1; i < nums.length; i++) {
            // Current number
            int curr = nums[i];
            // Compute new maximum product: consider curr alone, max * curr, or min * curr
            int temp = Math.max(curr, Math.max(max * curr, min * curr));
            // Compute new minimum product: consider curr alone, max * curr, or min * curr
            min = Math.min(curr, Math.min(max * curr, min * curr));
            // Update max to the new maximum product
            max = temp;
            // Update result to the maximum of current result and max
            result = Math.max(result, max);
        }

        // Return the maximum product of any contiguous subarray
        return result;
    }
}