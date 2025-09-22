// Problem: Jump Game
// Given an array of non-negative integers 'nums', where each element represents the maximum jump length from that position,
// determine if you can reach the last index starting from the first index.

// DSA Pattern: Greedy
// This problem is solved using a greedy approach by iterating backward from the second-to-last index and tracking the
// earliest index (goal) that can reach the last index. If the goal reaches the first index (0), it's possible to jump to the end.

// Approach:
// 1. Initialize 'goal' as the last index (nums.length - 1), representing the target to reach.
// 2. Iterate backward through the array from the second-to-last index to the first (0):
//    - For each index i, check if it can reach the current 'goal' (i + nums[i] >= goal).
//    - If it can, update 'goal' to the current index i (move the target closer to the start).
// 3. After the loop, check if 'goal' is 0 (the first index). If true, return true; otherwise, return false.

// Key Points to Remember:
// - The greedy approach works by ensuring that we find the leftmost index that can reach the current goal, progressively
//   moving the goal toward the start.
// - Each position's value (nums[i]) indicates the maximum jump distance from that index.
// - The solution checks if we can reach the last index by making a sequence of valid jumps.
// - Edge cases:
//   - Single element array: always true (already at the last index).
//   - Array with zeros: possible if a prior index can jump over the zero.
//   - All zeros except first: false unless the first element can reach the last index.
// - The solution assumes non-negative integers in the input array.

// Time Complexity: O(n), where n is the length of nums
// - The algorithm performs a single backward pass through the array, with constant-time operations per index.

// Space Complexity: O(1)
// - Only a single variable ('goal') is used, requiring constant extra space.
// - No additional data structures are needed beyond the input array.

// Constraints:
// - 1 <= nums.length <= 10^4
// - 0 <= nums[i] <= 10^5

public class Solution {
    public boolean canJump(int[] nums) {
        // Initialize goal as the last index
        int goal = nums.length - 1;
        
        // Iterate backward from second-to-last index
        for (int i = nums.length - 2; i >= 0; i--) {
            // Check if current index can reach the goal
            if (i + nums[i] >= goal) {
                // Update goal to the current index
                goal = i;
            }
        }
        
        // Return true if goal is the first index
        return goal == 0;
    }
}