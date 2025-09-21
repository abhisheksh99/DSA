class Solution {
    // Problem: Longest Consecutive Sequence
    // Given an unsorted array of integers nums, return the length of the longest consecutive
    // sequence (i.e., numbers that form a sequence with no gaps, like [1,2,3,4]). The numbers
    // do not need to be in order in the array, and duplicates are allowed.

    // DSA Pattern: Hash Set
    // This problem is solved using a HashSet to store all numbers for O(1) average-time lookup.
    // By checking for the start of a sequence (a number with no predecessor) and extending it
    // forward, we can efficiently find the longest consecutive sequence.

    // Approach:
    // 1. If the array is empty, return 0.
    // 2. Add all numbers to a HashSet to enable O(1) lookups and handle duplicates.
    // 3. Iterate through each number in the HashSet:
    //    - Only process numbers that could be the start of a sequence (i.e., num-1 is not in the set).
    //    - For each such number, count the length of the sequence by checking for num+1, num+2, etc.
    //    - Update the longest sequence length if the current sequence is longer.
    // 4. Return the length of the longest consecutive sequence found.

    // Key Points to Remember:
    // - Use a HashSet for O(1) average-time lookups and to eliminate duplicates.
    // - Only start a sequence at a number if its predecessor (num-1) is not in the set to avoid
    //   redundant checks and ensure we process each sequence exactly once.
    // - Check forward (num+1, num+2, ...) to count the length of each sequence.
    // - Handle edge cases like empty arrays, single elements, or no consecutive sequences.
    // - Duplicates do not affect the sequence length since they are handled by the HashSet.
    // - The approach ensures we don’t count the same sequence multiple times by starting only at
    //   the smallest number in each sequence.

    // Time Complexity: O(n)
    // - Adding all numbers to the HashSet takes O(n), where n is the length of nums.
    // - Iterating through the HashSet takes O(n) in the worst case.
    // - For each number that starts a sequence, we check consecutive numbers (num+1, num+2, ...).
    //   Each number is checked at most once across all sequences, as we only start at numbers
    //   without a predecessor, making the total checks O(n).
    // - HashSet operations (add, contains) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(n)
    // - The HashSet stores up to n unique elements, using O(n) space.
    // - Other variables (longest, length) use O(1) space.
    // - Overall, the space complexity is O(n).

    public int longestConsecutive(int[] nums) {
        // Handle empty array case
        if (nums.length == 0) {
            return 0;
        }

        // Initialize HashSet to store numbers
        HashSet<Integer> numSet = new HashSet<>();
        
        // Add all numbers to the HashSet
        for (int num : nums) {
            numSet.add(num);
        }

        // Initialize variable to track the longest sequence
        int longest = 0;

        // Iterate through each number in the HashSet
        for (int num : numSet) {
            // Only process numbers that start a sequence (no num-1 in set)
            if (!numSet.contains(num - 1)) {
                int length = 1; // Start sequence with current number
                // Check for consecutive numbers (num+1, num+2, ...)
                while (numSet.contains(num + length)) {
                    length++;
                }
                // Update longest sequence if current sequence is longer
                longest = Math.max(longest, length);
            }
        }

        // Return the length of the longest consecutive sequence
        return longest;
    }
}