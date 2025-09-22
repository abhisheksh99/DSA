class Solution {
    // Problem: Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s. A palindrome is a string
    // that reads the same forward and backward. The string consists of printable ASCII characters.

    // DSA Pattern: Expand Around Center
    // This problem is solved using the expand-around-center technique, which treats each character
    // (and each pair of characters) as a potential center of a palindrome and expands outward to
    // find the longest palindrome centered at that position. This avoids checking all possible
    // substrings, making it more efficient than a brute-force approach.

    // Approach:
    // 1. Handle edge cases: if s is null or empty, return an empty string.
    // 2. Initialize variables start and end to track the indices of the longest palindromic substring.
    // 3. Iterate through each index i in the string:
    //    - Check for odd-length palindromes by expanding around center i (left = i, right = i).
    //    - Check for even-length palindromes by expanding around centers i and i+1 (left = i, right = i+1).
    //    - Use a helper function expandAroundCenter to compute the length of the palindrome.
    //    - If the palindrome length is greater than the current longest (end - start), update start
    //      and end to the new palindrome's boundaries.
    // 4. The expandAroundCenter function:
    //    - Expands left and right pointers while within bounds and characters match.
    //    - Returns the length of the palindrome (right - left - 1).
    // 5. Return the substring from start to end+1 as the longest palindromic substring.

    // Key Points to Remember:
    // - Check both odd (single center) and even (between two characters) palindromes at each index.
    // - Update start and end based on the longest palindrome found using (len - 1) / 2 and len / 2.
    // - Handle edge cases: null/empty string, single character (palindrome of length 1).
    // - The formula right - left - 1 gives the palindrome length after expansion stops.
    // - Use integer division for calculating new start and end indices to handle both odd and even cases.
    // - The approach avoids generating all substrings, focusing only on potential palindrome centers.
    // - The output is the first longest palindromic substring if multiple exist (per problem behavior).

    // Time Complexity: O(n^2)
    // - Iterate through n characters, where n is the length of s.
    // - For each character, expandAroundCenter may expand up to O(n) steps in the worst case
    //   (e.g., "aaaa" where the entire string is a palindrome).
    // - Total time is O(n * n) = O(n^2) for the expansion across all centers.
    // - Other operations (substring, comparisons) are O(1) or absorbed into the expansion.

    // Space Complexity: O(1)
    // - Only use a constant amount of extra space for variables (start, end, left, right).
    // - The output substring is required by the problem and not counted as extra space.
    // - No additional data structures are used.
    // - Overall, the space complexity is O(1).

    public String longestPalindrome(String s) {
        // Handle edge cases: null or empty string
        if (s == null || s.length() < 1) {
            return "";
        }
        
        // Initialize indices for the longest palindromic substring
        int start = 0, end = 0;
        
        // Iterate through each possible center
        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindrome centered at i
            int len1 = expandAroundCenter(s, i, i);
            // Check even-length palindrome centered between i and i+1
            int len2 = expandAroundCenter(s, i, i + 1);
            // Get the maximum length of the two
            int len = Math.max(len1, len2);
            
            // Update start and end if the current palindrome is longer
            if (len > end - start) {
                start = i - (len - 1) / 2; // Calculate new start index
                end = i + len / 2;         // Calculate new end index
            }
        }
        
        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }
    
    // Helper function to expand around a center and return palindrome length
    private int expandAroundCenter(String s, int left, int right) {
        // Expand while within bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return length of palindrome (right - left - 1)
        return right - left - 1;
    }
}