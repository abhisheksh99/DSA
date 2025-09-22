// Problem: Partition Labels
// Given a string 's' consisting of lowercase English letters, partition the string into as many parts as possible
// such that each letter appears in at most one part, and return a list of integers representing the size of each part.

// DSA Pattern: Greedy / Two-Pointer
// This problem is solved using a greedy approach by tracking the last occurrence of each character and partitioning
// the string whenever all characters in a segment have no further occurrences beyond that segment.

// Approach:
// 1. Create a HashMap to store the last index of each character in the string.
// 2. Iterate through the string to populate the HashMap with the last occurrence of each character.
// 3. Initialize variables:
//    - size: tracks the length of the current partition.
//    - end: tracks the farthest index any character in the current partition can reach.
//    - res: a list to store the sizes of each partition.
// 4. Iterate through the string again:
//    - Increment size for each character.
//    - Update end to the maximum of the current end and the last index of the current character.
//    - If the current index equals end, a valid partition is found; add size to res and reset size to 0.
// 5. Return the list of partition sizes.

// Key Points to Remember:
// - The greedy approach ensures maximum partitions by creating a partition as soon as all characters in the current
//   segment have no further occurrences.
// - Each character can only appear in one partition, so we extend the partition to include the last occurrence of each character.
// - The HashMap allows O(1) lookups for the last index of each character.
// - Edge cases:
//   - Single character: returns [1].
//   - All unique characters: returns a list of 1s.
//   - Empty string: not applicable due to constraints.
// - The solution assumes the input string contains only lowercase English letters.

// Time Complexity: O(n), where n is the length of the string s
// - Building the HashMap takes O(n) with a single pass through the string.
// - The second pass through the string to compute partitions takes O(n), with constant-time operations per character.
// - HashMap operations (put, get) are O(1) on average.

// Space Complexity: O(1) excluding the output
// - The HashMap stores at most 26 entries (one for each lowercase letter), which is O(1).
// - The output list 'res' is not counted as extra space (per standard convention for output).
// - No additional significant space is used beyond the input and output.

// Constraints:
// - 1 <= s.length <= 500
// - s consists of lowercase English letters.

public class Solution {
    public List<Integer> partitionLabels(String s) {
        // Store the last index of each character
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }
        
        // Initialize result list and variables for partition size and end index
        List<Integer> res = new ArrayList<>();
        int size = 0, end = 0;
        
        // Iterate through the string to form partitions
        for (int i = 0; i < s.length(); i++) {
            size++;
            // Update the farthest index reachable by current partition
            end = Math.max(end, lastIndex.get(s.charAt(i)));
            // If current index is the end of a partition, add size to result
            if (i == end) {
                res.add(size);
                size = 0;
            }
        }
        
        // Return the list of partition sizes
        return res;
    }
}