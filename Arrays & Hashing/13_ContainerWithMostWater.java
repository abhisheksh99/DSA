class Solution {
    // Problem: Container With Most Water
    // Given an integer array height where height[i] represents the height of a vertical line at
    // index i, find two lines that together with the x-axis form a container that holds the most
    // water. Return the maximum area of water the container can hold. The area is calculated as
    // the minimum height of the two lines multiplied by the distance between their indices.

    // DSA Pattern: Two Pointers
    // This problem is solved using the two-pointer technique, where pointers start at opposite ends
    // of the array and move inward based on the height of the lines. By maximizing the width initially
    // and adjusting based on the smaller height, we efficiently explore possible containers to find
    // the maximum area.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (height.length - 1).
    // 2. Initialize maxA to track the maximum area found, starting at 0.
    // 3. While left < right:
    //    - Calculate the area as the minimum of height[left] and height[right] multiplied by the
    //      distance (right - left).
    //    - Update maxA if the current area is greater.
    //    - Move the pointer pointing to the smaller height inward:
    //      - If height[left] < height[right], increment left to potentially increase the height.
    //      - Otherwise, decrement right to potentially increase the height.
    // 4. Return maxA as the maximum area found.

    // Key Points to Remember:
    // - Use two pointers to maximize the width initially and explore all possible containers.
    // - The area is limited by the shorter height, so move the pointer at the smaller height to
    //   potentially find a taller line and increase the area.
    // - Moving both pointers when heights are equal is also valid, but moving one (e.g., right) is
    //   sufficient and simplifies the logic.
    // - Handle edge cases like arrays with exactly two elements (single area calculation).
    // - The two-pointer approach ensures we consider all necessary pairs without checking every
    //   combination, reducing complexity.
    // - The maximum area is updated in each iteration if a larger area is found.

    // Time Complexity: O(n)
    // - The two pointers traverse the array at most once, as left moves right and right moves left
    //   until they meet, where n is the length of height.
    // - Each iteration involves O(1) operations (min, multiplication, max, pointer updates).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (left, right, maxA, area).
    // - No additional data structures are used, so the space complexity is O(1).

    public int maxArea(int[] height) {
        // Initialize left pointer at the start of the array
        int left = 0;
        // Initialize right pointer at the end of the array
        int right = height.length - 1;
        // Initialize maximum area
        int maxA = 0;

        // Iterate while left pointer is less than right pointer
        while (left < right) {
            // Calculate area as min height * width
            int area = Math.min(height[left], height[right]) * (right - left);
            // Update maxA if current area is greater
            maxA = Math.max(maxA, area);
            // Move the pointer with the smaller height inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        // Return the maximum area found
        return maxA;
    }
}