class Solution {
    // Problem: Find the Duplicate Number
    // Given an array of integers nums containing n + 1 integers where each integer is
    // in the range [1, n] inclusive, there is exactly one duplicate number (repeated
    // more than once). Return the duplicate number. The solution must not modify the
    // array and must use only constant extra space (O(1)).
    // DSA Pattern: Floyd's Tortoise and Hare (Cycle Detection)
    // This problem is solved using Floyd's Tortoise and Hare algorithm, treating the
    // array as a linked list where each value points to an index. The duplicate number
    // creates a cycle, and the algorithm finds the entrance to this cycle, which is the
    // duplicate number. The approach uses two pointers (slow and fast) to detect the cycle
    // and then find its entrance.
    // Approach:
    // 1. Initialize two pointers: slow starting at nums[0] and fast starting at nums[nums[0]].
    // 2. Phase 1: Find the intersection point of the two pointers:
    //    - Move slow one step: slow = nums[slow].
    //    - Move fast two steps: fast = nums[nums[fast]].
    //    - Continue until slow meets fast, indicating a cycle.
    // 3. Phase 2: Find the entrance to the cycle (the duplicate number):
    //    - Reset slow to 0.
    //    - Move both slow and fast one step at a time: slow = nums[slow], fast = nums[fast].
    //    - The point where they meet is the duplicate number.
    // 4. Return the duplicate number.
    // Key Points to Remember:
    // - The array is treated as a linked list where nums[i] points to index nums[i].
    // - The duplicate number creates a cycle because multiple indices point to the same value.
    // - Floyd's algorithm guarantees finding the cycle's entrance, which is the duplicate.
    // - The solution uses O(1) extra space by only using two pointers.
    // - The array is not modified, satisfying the problem constraints.
    // - Edge cases are handled implicitly (e.g., array length ≥ 2, values in [1, n]).
    // - The algorithm assumes exactly one duplicate exists.
    // Time Complexity: O(n)
    // - n is the length of the array.
    // - Phase 1 (finding intersection) takes O(n) as the pointers traverse the "linked list."
    // - Phase 2 (finding cycle entrance) takes O(n) as pointers move at the same speed.
    // - Total time is O(n).
    // Space Complexity: O(1)
    // - Only two pointers (slow and fast) are used, regardless of array size.
    // - No additional data structures are required.
    public int findDuplicate(int[] nums) {
        // Initialize slow and fast pointers
        int slow = nums[0];
        int fast = nums[nums[0]];

        // Phase 1: Find the intersection point of the two pointers
        while (slow != fast) {
            slow = nums[slow];          // Move slow one step
            fast = nums[nums[fast]];    // Move fast two steps
        }

        // Phase 2: Find the entrance to the cycle (duplicate number)
        slow = 0;                       // Reset slow to 0
        while (slow != fast) {
            slow = nums[slow];          // Move slow one step
            fast = nums[fast];          // Move fast one step
        }

        // Return the duplicate number
        return slow;
    }
}