class Solution {
    // Problem: Valid Anagram
    // Given two strings s and t, return true if t is an anagram of s, and false otherwise.
    // An anagram is a word or phrase formed by rearranging the letters of another, using all
    // the original letters exactly once. The strings consist of lowercase English letters.

    // DSA Pattern: Frequency Counting (Hash Table / Array)
    // This problem is solved using a frequency counting approach with a fixed-size array acting
    // as a hash table. By counting the occurrences of each character in s and decrementing for t,
    // we can determine if the two strings have the same characters with the same frequencies.

    // Approach:
    // 1. Check if the lengths of s and t are equal. If not, they cannot be anagrams, return false.
    // 2. Initialize a fixed-size array charCount of size 26 to store the frequency of each
    //    lowercase letter (a-z).
    // 3. Iterate through both strings simultaneously:
    //    - Increment the count for the character in s (charCount[s.charAt(i) - 'a']++).
    //    - Decrement the count for the character in t (charCount[t.charAt(i) - 'a']--).
    // 4. After the loop, check if all counts in charCount are zero. If any count is non-zero,
    //    the strings are not anagrams, return false.
    // 5. If all counts are zero, the strings are anagrams, return true.

    // Key Points to Remember:
    // - Check lengths first to quickly rule out non-anagrams.
    // - Use a fixed-size array (26 for lowercase letters) instead of a HashMap for efficiency,
    //   as the input is limited to lowercase English letters.
    // - Increment for s and decrement for t in one pass to reduce iteration.
    // - A zero count for each character in charCount indicates identical character frequencies.
    // - Handle edge cases like empty strings (valid anagrams if both empty) or single-character
    //   strings (must be identical).
    // - The approach is case-sensitive and assumes lowercase letters as per problem constraints.

    // Time Complexity: O(n)
    // - Checking lengths is O(1).
    // - Iterating through both strings (length n) takes O(n).
    // - Checking the charCount array (fixed size 26) takes O(1).
    // - Overall, the time complexity is O(n), where n is the length of the strings.

    // Space Complexity: O(1)
    // - The charCount array is of fixed size (26) regardless of input size, so it uses O(1) space.
    // - No additional data structures are used, and only constant extra space is needed for
    //   loop variables.
    // - Overall, the space complexity is O(1).

    public boolean isAnagram(String s, String t) {
        // Check if lengths are different; if so, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Initialize array to store character frequency differences
        int charCount[] = new int[26];

        // Iterate through both strings, increment for s, decrement for t
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++; // Increment count for character in s
            charCount[t.charAt(i) - 'a']--; // Decrement count for character in t
        }

        // Check if any character has non-zero count (indicating mismatch)
        for (int i = 0; i < 26; i++) {
            if (charCount[i] != 0) {
                return false;
            }
        }

        // If all counts are zero, strings are anagrams
        return true;
    }
}