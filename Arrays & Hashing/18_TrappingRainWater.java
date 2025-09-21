class Solution {
    // Problem: Trapping Rain Water
    // Given an array height where height[i] represents the height of a bar at index i, compute
    // how much water can be trapped between bars after raining. The width of each bar is 1, and
    // water can be trapped if there are higher bars on both sides of a lower bar.

    // DSA Pattern: Two Pointers
    // This problem is solved using the two-pointer technique, where pointers start at opposite ends
    // of the array and move inward. By tracking the maximum height seen from the left and right,
    // we can calculate the water trapped at each position based on the minimum of the two maxima,
    // ensuring we process each position efficiently in a single pass.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (height.length - 1).
    // 2. Initialize leftMax and rightMax to track the maximum heights seen from the left and right,
    //    starting with height[0] and height[right], respectively.
    // 3. Initialize total to track the total water trapped.
    // 4. While left < right:
    //    - If height[left] < height[right], process the left side:
    //      - Update leftMax as the maximum of leftMax and height[left].
    //      - If leftMax > height[left], add the trapped water (leftMax - height[left]) to total.
    //      - Increment left.
    //    - Otherwise, process the right side:
    //      - Update rightMax as the maximum of rightMax and height[right].
    //      - If rightMax > height[right], add the trapped water (rightMax - height[right]) to total.
    //      - Decrement right.
    // 5. Return total as the amount of water trapped.

    // Key Points to Remember:
    // - Water trapped at a position is determined by the minimum of the maximum heights on its
    //   left and right minus the current height.
    // - Process the smaller height (left or right) to ensure the minimum of leftMax and rightMax
    //   is sufficient to calculate water at the current position.
    // - Update leftMax or rightMax as pointers move to track the tallest bars seen so far.
    // - Move the pointer corresponding to the smaller height to potentially find a taller bar.
    // - Handle edge cases like empty arrays, arrays with no trapped water (monotonic), or single bars.
    // - The two-pointer approach avoids computing leftMax and rightMax for every position, reducing
    //   complexity compared to a brute-force approach.

    // Time Complexity: O(n)
    // - The two pointers traverse the array at most once, as left moves right and right moves left
    //   until they meet, where n is the length of height.
    // - Each iteration involves O(1) operations (comparisons, max, addition, pointer updates).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, leftMax, rightMax, total).
    // - No additional data structures are used, so the space complexity is O(1).

    public int trap(int[] height) {
        // Initialize left pointer at the start of the array
        int left = 0;
        // Initialize right pointer at the end of the array
        int right = height.length - 1;
        
        // Initialize total water trapped
        int total = 0;
        // Initialize maximum height seen from the left
        int leftMax = height[0];
        // Initialize maximum height seen from the right
        int rightMax = height[right];
        
        // Iterate while left pointer is less than right pointer
        while (left < right) {
            // If left height is smaller, process left side
            if (height[left] < height[right]) {
                // Update leftMax with the maximum height seen so far
                leftMax = Math.max(leftMax, height[left]);
                // If leftMax > current height, add trapped water to total
                if (leftMax - height[left] > 0) {
                    total = total + leftMax - height[left];
                }
                // Move left pointer inward
                left++;
            }
            // If right height is smaller or equal, process right side
            else {
                // Update rightMax with the maximum height seen so far
                rightMax = Math.max(rightMax, height[right]);
                // If rightMax > current height, add trapped water to total
                if (rightMax - height[right] > 0) {
                    total = total + rightMax - height[right];
                }
                // Move right pointer inward
                right--;
            }
        }
        
        // Return the total water trapped
        return total;
    }
}