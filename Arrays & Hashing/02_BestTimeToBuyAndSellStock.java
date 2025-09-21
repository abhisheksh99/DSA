class Solution {
    // Problem: Best Time to Buy and Sell Stock
    // Given an array of integers prices where prices[i] is the price of a stock on day i,
    // find the maximum profit you can achieve by buying on one day and selling on a later day.
    // If no profit is possible, return 0.

    // DSA Pattern: Two Pointers (Sliding Window variant)
    // This problem is solved using a two-pointer approach, where one pointer (left) tracks the 
    // minimum price seen so far (buying point), and the other (right) iterates through potential 
    // selling points. This is a variation of the sliding window technique, as we maintain a window 
    // of valid buy-sell pairs to maximize profit.

    // Approach:
    // 1. Initialize two pointers: left (buy day) at index 0 and right (sell day) at index 1.
    // 2. Initialize maxP to track the maximum profit, starting at 0.
    // 3. Iterate through the array with the right pointer:
    //    - If prices[left] < prices[right], calculate the profit (prices[right] - prices[left])
    //      and update maxP if the current profit is greater.
    //    - If prices[left] >= prices[right], update left to right, as we found a lower price
    //      that could lead to a higher profit in the future.
    // 4. Increment right pointer in each iteration.
    // 5. Return maxP as the maximum profit achievable.

    // Key Points to Remember:
    // - Use two pointers to track the minimum price (buy) and potential sell price.
    // - Update the left pointer only when a lower price is found to maximize future profits.
    // - Ensure the right pointer always points to a day after the left pointer (buy before sell).
    // - Handle cases where no profit is possible (e.g., prices are in descending order) by returning 0.
    // - Avoid unnecessary computations by updating maxP only when a valid profit is possible.

    // Time Complexity: O(n)
    // - We iterate through the array once with the right pointer, where n is the length of prices.
    // - All operations within the loop (comparisons, updates) are O(1).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, maxP).
    // - No additional data structures are used, so the space complexity is O(1).

    public int maxProfit(int[] prices) {
        // Initialize left pointer (buy day) at the start
        int left = 0;
        // Initialize right pointer (sell day) at the next day
        int right = 1;
        // Initialize maximum profit
        int maxP = 0;

        // Iterate while right pointer is within the array bounds
        while (right < prices.length) {
            // If the price at left is less than the price at right, calculate potential profit
            if (prices[left] < prices[right]) {
                // Update maxP if the current profit is greater
                maxP = Math.max(maxP, prices[right] - prices[left]);
            } else {
                // If price at right is lower or equal, update left to right for a better buy price
                left = right;
            }
            // Move right pointer to the next day
            right++;
        }

        // Return the maximum profit
        return maxP;
    }
}