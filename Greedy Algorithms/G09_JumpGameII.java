// Problem: Jump Game II
// Given an array of non-negative integers 'nums', where each element represents the maximum jump length from that position,
// return the minimum number of jumps required to reach the last index from the first index. You can assume the last index
// is always reachable.

// DSA Pattern: Greedy
// This problem is solved using a greedy approach by maintaining a sliding window of reachable positions and choosing the
// farthest reachable position in each jump to minimize the number of jumps.

// Approach:
// 1. Initialize three variables:
//    - jumps: counts the number of jumps needed (initially 0).
//    - currEnd: marks the farthest index reachable with the current number of jumps.
//    - currMax: tracks the farthest index reachable from any position in the current jump range.
// 2. Iterate through the array from index 0 to the second-to-last index (nums.length - 1):
//    - Update currMax to the maximum of currMax and the farthest reachable index from the current position (i + nums[i]).
//    - When the current index i equals currEnd, a jump is required:
//      - Increment jumps.
//      - Set currEnd to currMax (extend the range to the farthest reachable position).
// 3. Return the total number of jumps.

// Key Points to Remember:
// - The greedy approach minimizes jumps by always choosing the position that allows the farthest reach in each step.
// - The loop stops at the second-to-last index because reaching the last index completes the process.
// - The assumption that the last index is always reachable simplifies the logic (no need to check for unreachable cases).
// - Edge cases:
//   - Single element array: return 0 (no jumps needed).
//   - Two elements: return 1 if the first element can reach the second (guaranteed by constraints).
// - The solution assumes non-negative integers in the input array.

// Time Complexity: O(n), where n is the length of nums
// - The algorithm performs a single pass through the array, with constant-time operations (max comparison) per index.

// Space Complexity: O(1)
// - Only three variables (jumps, currMax, currEnd) are used, requiring constant extra space.
// - No additional data structures are needed beyond the input array.

// Constraints:
// - 1 <= nums.length <= 10^4
// - 0 <= nums[i] <= 1000
// - It's guaranteed that you can reach the last index.

public class Solution {
    public int jump(int[] nums) {
        // Initialize variables for jumps, current range end, and maximum reachable index
        int jumps = 0, currMax = 0, currEnd = 0;
        
        // Iterate until the second-to-last index
        for (int i = 0; i < nums.length - 1; i++) {
            // Update the farthest reachable index
            currMax = Math.max(currMax, i + nums[i]);
            // If current index reaches the end of the current range, make a jump
            if (i == currEnd) {
                jumps++;
                currEnd = currMax;
            }
        }
        
        // Return the minimum number of jumps
        return jumps;
    }
}