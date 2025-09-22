class Solution {
    // Problem: Longest Common Prefix
    // Given an array of strings strs, return the longest common prefix string that is a prefix
    // of all strings in the array. If there is no common prefix, return an empty string.
    // The strings consist of lowercase English letters, and the array may be empty.

    // DSA Pattern: String Comparison
    // This problem is solved by iteratively refining a candidate prefix, starting with the first
    // string and shortening it until it matches the beginning of all other strings. The approach
    // leverages string operations (indexOf and substring) to check and adjust the prefix.

    // Approach:
    // 1. Handle edge cases: if strs is null or empty, return an empty string.
    // 2. Initialize prefix as the first string (strs[0]) as a starting point.
    // 3. Iterate through the remaining strings (starting from index 1):
    //    - For each string, check if prefix is at its start (using indexOf(prefix) == 0).
    //    - If not, shorten prefix by removing its last character and check again.
    //    - Repeat until prefix matches or becomes empty.
    //    - If prefix becomes empty, return an empty string (no common prefix exists).
    // 4. Return the final prefix, which is guaranteed to be a prefix of all strings.

    // Key Points to Remember:
    // - Start with the first string as the initial prefix to simplify the process.
    // - Use indexOf to check if prefix is at the start of each string (returns 0 if true).
    // - Shorten prefix by one character at a time when it doesn’t match to find the longest
    //   common prefix.
    // - Handle edge cases: null or empty array, single string, or no common prefix.
    // - If any string is empty, the common prefix is empty.
    // - The approach ensures the prefix is valid for all strings by the end of the loop.
    // - The output is a string that is a prefix of every string in the input array.

    // Time Complexity: O(S)
    // - S is the total number of characters in all strings (sum of string lengths).
    // - Iterating through n strings (where n is strs.length) involves checking each string.
    // - For each string, indexOf takes O(m) time (where m is the length of the prefix), and
    //   substring operations may occur multiple times, up to the length of the prefix.
    // - In the worst case, we process each character of each string, leading to O(S).
    // - Edge case checks (null/empty array) are O(1).

    // Space Complexity: O(1)
    // - We only use a single string variable (prefix) for storage, modified in-place.
    // - No additional data structures grow with input size.
    // - The space for the output string is required by the problem.
    // - Overall, the space complexity is O(1), excluding the output.

    public String longestCommonPrefix(String[] strs) {
        // Handle edge cases: null or empty array
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Initialize prefix as the first string
        String prefix = strs[0];

        // Iterate through remaining strings
        for (int i = 1; i < strs.length; i++) {
            // While prefix is not at the start of strs[i], shorten prefix
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                // If prefix becomes empty, no common prefix exists
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        // Return the longest common prefix
        return prefix;
    }
}