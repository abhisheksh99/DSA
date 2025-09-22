class Solution {
    // Problem: Largest Rectangle in Histogram
    // Given an array of non-negative integers heights representing the heights of bars in a histogram,
    // where each bar has a width of 1, find the area of the largest rectangle that can be formed
    // within the histogram. The rectangle must be formed by contiguous bars, and its height is
    // determined by the minimum height of the bars it spans.

    // DSA Pattern: Monotonic Stack
    // This problem is solved using a monotonic stack to maintain a strictly increasing sequence of
    // bar heights (via their indices). The stack helps efficiently calculate the area of rectangles
    // by tracking bars that could form a rectangle with a given height. When a bar with a smaller
    // height is encountered, we pop taller bars from the stack, compute the area of the rectangle
    // formed by each popped bar, and update the maximum area. A sentinel value (height 0) is used
    // to process any remaining bars in the stack at the end.

    // Approach:
    // 1. Initialize a stack to store indices of bars in increasing order of their heights.
    // 2. Initialize maxArea to track the largest rectangle area found.
    // 3. Iterate through the array (indices 0 to n, where n is the length of heights):
    //    - For index i = n, use a sentinel height of 0 to process remaining bars.
    //    - For each index i, get the current height (heights[i] or 0 if i == n).
    //    - While the stack is not empty and the current height is less than the height of the bar
    //      at the index on top of the stack:
    //      - Pop the top index and get its height (heights[popped_index]).
    //      - Calculate the width of the rectangle:
    //        - If the stack is empty, width = i (rectangle spans from index 0 to i-1).
    //        - Otherwise, width = i - stack.peek() - 1 (rectangle spans from stack.peek() + 1 to i-1).
    //      - Compute the area (height * width) and update maxArea if larger.
    //    - Push the current index i onto the stack.
    // 4. Return maxArea as the result.

    // Key Points to Remember:
    // - The stack maintains indices of bars in increasing order of their heights, ensuring that when a
    //   smaller height is encountered, we can compute areas for all taller bars that end at the current index.
    // - The sentinel height of 0 at i = n ensures all bars are popped and processed, as 0 is less than
    //   any valid height.
    // - The width calculation accounts for the span of the rectangle:
    //   - If the stack is empty after popping, the rectangle extends from index 0 to i-1.
    //   - Otherwise, it extends from the index after the new top of the stack to i-1.
    // - Each index is pushed and popped at most once, ensuring efficient processing.
    // - Edge cases are handled:
    //   - Empty array: Returns 0 (no rectangle possible).
    //   - Single bar: Area is heights[0] * 1.
    //   - Non-increasing heights: Stack processes each bar as a potential rectangle end.
    // - The approach finds the maximum area by considering each bar as the height of a rectangle
    //   bounded by the nearest smaller bars on the left and right.

    // Time Complexity: O(n)
    // - n: Length of the input array heights.
    // - Iterating through the array (including sentinel): O(n).
    // - Each index is pushed onto the stack once (O(1)) and popped at most once (O(1)).
    // - Area calculations and comparisons are O(1) per operation.
    // - Total complexity: O(n), as each bar is processed with amortized constant-time operations.

    // Space Complexity: O(n)
    // - Stack stores at most n indices in the worst case (e.g., strictly increasing heights): O(n).
    // - Additional variables (maxArea, currentHeight, etc.) use O(1) space.
    // - Overall space: O(n) due to the stack.

    public int largestRectangleArea(int[] heights) {
        // Initialize variables
        int maxArea = 0; // Tracks the largest rectangle area
        Stack<Integer> stack = new Stack<>(); // Stack to store indices of bars
        int n = heights.length; // Length of the input array

        // Step 1: Process each bar and a sentinel height of 0
        for (int i = 0; i <= n; i++) {
            // Get current height (0 for sentinel at i == n)
            int currentHeight = (i == n) ? 0 : heights[i];

            // Step 2: Pop bars taller than currentHeight and calculate areas
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; // Height of popped bar
                // Calculate width: If stack is empty, width = i; otherwise, i - stack.peek() - 1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width); // Update maxArea (O(1))
            }

            // Step 3: Push current index onto the stack
            stack.push(i); // O(1)
        }

        // Step 4: Return the maximum area found
        return maxArea;
    }
}