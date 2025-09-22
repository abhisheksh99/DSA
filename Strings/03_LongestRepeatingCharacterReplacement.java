class Solution {
    // Problem: Longest Repeating Character Replacement
    // Given a string s consisting of uppercase English letters and an integer k, return the
    // length of the longest substring containing the same letter you can get after performing
    // up to k replacements. You can replace any character with any other uppercase letter.

    // DSA Pattern: Sliding Window with Hash Map
    // This problem is solved using a sliding window approach with a HashMap to track character
    // frequencies in the current window. By maintaining the maximum frequency of any character
    // in the window, we can determine if the window is valid (i.e., can be made uniform with
    // at most k replacements) and adjust the window size accordingly.

    // Approach:
    // 1. Initialize a HashMap to store the frequency of characters in the current window.
    // 2. Initialize two pointers: left (window start) and right (window end), and variables
    //    ans (longest valid window length) and maxOccur (maximum frequency of any character).
    // 3. Iterate right from 0 to s.length() - 1:
    //    - Add the current character to the HashMap and update its frequency.
    //    - Update maxOccur with the maximum frequency seen in the current window.
    //    - Check if the window is valid: window size (right - left + 1) minus maxOccur
    //      (number of replacements needed) must not exceed k.
    //    - If invalid (replacements > k), shrink the window by moving left pointer:
    //      - Decrease the frequency of the leftmost character and move left forward.
    //    - Update ans with the maximum window size seen so far.
    // 4. Return ans as the length of the longest valid substring.

    // Key Points to Remember:
    // - The number of replacements needed is the window size minus the frequency of the most
    //   frequent character (right - left + 1 - maxOccur).
    // - A window is valid if replacements needed <= k.
    // - Use maxOccur to avoid recalculating the maximum frequency in each iteration.
    // - Shrink the window from the left when invalid to maintain the maximum valid window size.
    // - Handle edge cases like empty strings (return 0), single characters, or k >= string length.
    // - The HashMap ensures O(1) average-time updates and lookups for frequencies.
    // - The answer is the maximum window size where all characters can be made the same with
    //   at most k replacements.

    // Time Complexity: O(n)
    // - Iterate through the string once with the right pointer, where n is the length of s.
    // - The left pointer moves at most n times (never moves backward), so total iterations
    //   are O(n).
    // - HashMap operations (get, put) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - The HashMap stores frequencies for at most 26 uppercase English letters, so its size
    //   is bounded by a constant (O(26) = O(1)).
    // - Other variables (left, right, ans, maxOccur) use O(1) space.
    // - Overall, the space complexity is O(1).

    public int characterReplacement(String s, int k) {
        // HashMap to store the frequency of characters in the current window
        Map<Character, Integer> freq = new HashMap<>();
        // Initialize left pointer, answer, and max frequency
        int left = 0, ans = 0, maxOccur = 0;

        // Iterate with right pointer to expand the window
        for (int right = 0; right < s.length(); right++) {
            // Get current character
            char c = s.charAt(right);
            // Update frequency in HashMap
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            // Update max frequency in the current window
            maxOccur = Math.max(maxOccur, freq.get(c));

            // If replacements needed exceed k, shrink window from left
            if (right - left + 1 - maxOccur > k) {
                // Get leftmost character
                char leftChar = s.charAt(left);
                // Decrease its frequency
                freq.put(leftChar, freq.get(leftChar) - 1);
                // Move left pointer forward
                left++;
            }
            // Update the longest valid window size
            ans = Math.max(ans, right - left + 1);
        }

        // Return the length of the longest valid substring
        return ans;
    }
}