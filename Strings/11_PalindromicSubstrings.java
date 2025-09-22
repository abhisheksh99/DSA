class Solution {
    // Problem: Palindromic Substrings
    // Given a string s, return the number of palindromic substrings in it. A substring is
    // palindromic if it reads the same forward and backward. The string consists of lowercase
    // English letters.

    // DSA Pattern: Expand Around Center
    // This problem is solved using the expand-around-center technique, which treats each character
    // and each pair of adjacent characters as potential centers of palindromes. By expanding
    // outward from each center, we count all palindromic substrings efficiently without checking
    // every possible substring.

    // Approach:
    // 1. Initialize a variable ans to count the total number of palindromic substrings.
    // 2. Iterate through each index i in the string:
    //    - For odd-length palindromes, expand around center i (left = i, right = i).
    //    - For even-length palindromes, expand around centers i and i+1 (left = i, right = i+1).
    //    - Use a helper function checkPalindrome to count palindromes by expanding while characters
    //      match and boundaries are valid.
    // 3. The checkPalindrome function:
    //    - Expands left and right pointers while within bounds and characters match.
    //    - Increments count for each valid palindrome found (each expansion step represents a palindrome).
    //    - Returns the count of palindromes for the given center.
    // 4. Sum the counts from all centers and return ans.

    // Key Points to Remember:
    // - Check both odd-length (single character center) and even-length (between two characters)
    //   palindromes at each index.
    // - Each expansion step (left--, right++) counts a new palindrome.
    // - Handle edge cases: empty string (0 palindromes), single character (1 palindrome).
    // - The approach counts all substrings that are palindromes, including single characters and
    //   the entire string if palindromic.
    // - No need to store substrings, only count them, making the solution space-efficient.
    // - The input is lowercase English letters, but the approach works for any character set.

    // Time Complexity: O(n^2)
    // - Iterate through n characters, where n is the length of s.
    // - For each character, checkPalindrome may expand up to O(n) steps in the worst case
    //   (e.g., "aaa" where the entire string is a palindrome).
    // - Total time is O(n * n) = O(n^2) for expansions across all centers.
    // - Character comparisons and counter increments are O(1).

    // Space Complexity: O(1)
    // - Only use a constant amount of extra space for variables (ans, left, right, count).
    // - No additional data structures are used.
    // - The output (integer ans) is required by the problem.
    // - Overall, the space complexity is O(1).

    public int countSubstrings(String s) {
        // Initialize counter for palindromic substrings
        int ans = 0;
        // Iterate through each possible center
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes centered at i
            ans += checkPalindrome(s, i, i);
            // Count even-length palindromes centered between i and i+1
            ans += checkPalindrome(s, i, i + 1);
        }
        // Return total count of palindromic substrings
        return ans;
    }

    // Helper function to count palindromes by expanding around center
    private int checkPalindrome(String s, int left, int right) {
        // Initialize counter for palindromes at this center
        int count = 0;
        // Expand while within bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++; // Increment count for each valid palindrome
        }
        // Return number of palindromes found
        return count;
    }
}