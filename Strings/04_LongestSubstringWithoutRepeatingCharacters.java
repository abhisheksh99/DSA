class Solution {
    // Problem: Longest Substring Without Repeating Characters
    // Given a string s, find the length of the longest substring without repeating characters.
    // The string consists of English letters, digits, symbols, and spaces.

    // DSA Pattern: Sliding Window with Hash Set
    // This problem is solved using a sliding window approach with a HashSet to track unique
    // characters in the current window. The window expands by moving the right pointer and
    // contracts by moving the left pointer when a duplicate is found, ensuring no repeating
    // characters in the substring.

    // Approach:
    // 1. Handle edge cases: if s is null or empty, return 0; if s has one character, return 1.
    // 2. Initialize two pointers: left and right (both starting at 0), a HashSet to store
    //    characters in the current window, and ans to track the maximum substring length.
    // 3. Iterate with the right pointer until the end of the string:
    //    - Get the current character at right.
    //    - If the character is already in the HashSet, shrink the window from the left:
    //      - Remove the character at left from the HashSet and increment left until the
    //        duplicate is removed.
    //    - Add the current character to the HashSet.
    //    - Update ans with the maximum window size (right - left + 1).
    //    - Increment right to expand the window.
    // 4. Return ans as the length of the longest substring without repeating characters.

    // Key Points to Remember:
    // - Use a HashSet for O(1) average-time lookup and insertion to track unique characters.
    // - The sliding window ensures no repeating characters by contracting when a duplicate is found.
    // - Update the maximum length after each valid window expansion.
    // - Handle edge cases like null/empty strings or single-character strings efficiently.
    // - The window size (right - left + 1) represents the length of the current valid substring.
    // - The left pointer only moves forward, ensuring each character is processed efficiently.
    // - The approach works for any character set (letters, digits, symbols, spaces).

    // Time Complexity: O(n)
    // - The right pointer iterates through the string once, taking O(n) steps, where n is the length of s.
    // - The left pointer moves at most n times (never moves backward), so total movements are O(n).
    // - HashSet operations (contains, add, remove) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(min(m, n))
    // - The HashSet stores at most min(m, n) characters, where m is the size of the character set
    //   (e.g., 128 for ASCII, 256 for extended ASCII) and n is the string length.
    // - In the worst case (all unique characters up to string length), the HashSet uses O(n) space.
    // - Other variables (left, right, ans) use O(1) space.
    // - Overall, the space complexity is O(min(m, n)), typically O(n) for large strings with unique characters.

    public int lengthOfLongestSubstring(String s) {
        // Handle edge cases: null or empty string
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Handle single-character string
        if (s.length() == 1) {
            return 1;
        }
        
        // Initialize pointers and result
        int left = 0;
        int right = 0;
        int ans = 0;
        
        // Initialize HashSet to track unique characters in the window
        HashSet<Character> set = new HashSet<>();
        
        // Iterate with right pointer to expand the window
        while (right < s.length()) {
            // Get current character
            char c = s.charAt(right);
            // If character is already in set, shrink window from left
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            // Add current character to set
            set.add(c);
            // Update maximum substring length
            ans = Math.max(ans, right - left + 1);
            // Expand window by moving right pointer
            right++;
        }
        
        // Return the length of the longest substring without repeating characters
        return ans;
    }
}