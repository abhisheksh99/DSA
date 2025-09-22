class Solution {
    // Problem: Minimum Window Substring
    // Given two strings s and t, return the minimum window substring of s that contains all
    // characters in t (including duplicates). If no such substring exists, return an empty string.
    // The strings consist of English letters (uppercase and lowercase).

    // DSA Pattern: Sliding Window with Hash Map
    // This problem is solved using a sliding window approach with two HashMaps to track character
    // frequencies. One HashMap stores the required frequencies for t, and another tracks the
    // frequencies in the current window of s. The window expands until all required characters
    // are included and contracts to find the smallest valid window.

    // Approach:
    // 1. If s is shorter than t, return an empty string (no valid window possible).
    // 2. Create a HashMap (needed) to store the frequency of each character in t.
    // 3. Initialize variables:
    //    - required: number of unique characters in t.
    //    - window: HashMap to store character frequencies in the current window.
    //    - formed: number of characters in the window matching their required frequency.
    //    - left, right: pointers for the sliding window.
    //    - minLen, start: track the minimum window length and its starting index.
    // 4. Expand the window by moving right:
    //    - Add the current character to the window HashMap.
    //    - If the character is in t and its frequency matches the required frequency, increment formed.
    // 5. When formed equals required (window contains all characters of t):
    //    - Update minLen and start if the current window is smaller.
    //    - Shrink the window by moving left:
    //      - Decrease the frequency of the leftmost character.
    //      - If the character is in t and its frequency falls below the required frequency, decrement formed.
    //      - Move left pointer forward.
    // 6. Continue expanding right until the end of s.
    // 7. Return the minimum window substring or an empty string if no valid window is found.

    // Key Points to Remember:
    // - Use two HashMaps: one for t's character frequencies and one for the window's frequencies.
    // - Track the number of characters with matching frequencies (formed) to determine a valid window.
    // - Only update formed when a character's frequency exactly matches the required frequency.
    // - Shrink the window when valid to find the smallest possible window.
    // - Handle edge cases: s shorter than t, t empty, no valid window, or all characters present.
    // - Use Integer.MAX_VALUE for minLen to detect if no valid window is found.
    // - The substring is extracted using start and minLen at the end.

    // Time Complexity: O(n + m)
    // - Building the needed HashMap for t takes O(m), where m is the length of t.
    // - Iterating through s with the right pointer takes O(n), where n is the length of s.
    // - The left pointer moves at most n times (never moves backward), so total movements are O(n).
    // - HashMap operations (get, put) are O(1) on average.
    // - Overall, the time complexity is O(n + m), typically dominated by O(n) since m <= n.

    // Space Complexity: O(k)
    // - The needed HashMap stores at most k entries, where k is the number of unique characters in t.
    // - The window HashMap stores at most k' entries, where k' is the number of unique characters in s.
    // - In the worst case, k and k' are bounded by the character set size (e.g., 52 for uppercase/lowercase letters).
    // - Other variables (left, right, formed, minLen, start) use O(1) space.
    // - Overall, the space complexity is O(k), where k is the number of unique characters (bounded by a constant).

    public String minWindow(String s, String t) {
        // If s is shorter than t, no valid window is possible
        if (s.length() < t.length()) {
            return "";
        }

        // HashMap to store the required frequency of characters in t
        Map<Character, Integer> needed = new HashMap<>();
        for (char c : t.toCharArray()) {
            needed.put(c, needed.getOrDefault(c, 0) + 1);
        }

        // Number of unique characters needed in the window
        int required = needed.size();
        // HashMap to store character frequencies in the current window
        Map<Character, Integer> window = new HashMap<>();
        // Number of characters with matching required frequency
        int formed = 0;

        // Sliding window pointers and variables to track minimum window
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        // Expand window with right pointer
        while (right < s.length()) {
            // Add current character to window
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // If character is needed and its frequency matches, increment formed
            if (needed.containsKey(c) && window.get(c).intValue() == needed.get(c).intValue()) {
                formed++;
            }

            // Shrink window when valid (formed == required)
            while (left <= right && formed == required) {
                // Update minimum window if current window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Remove leftmost character and shrink window
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                // If a needed character's frequency falls below required, decrement formed
                if (needed.containsKey(leftChar) && window.get(leftChar).intValue() < needed.get(leftChar).intValue()) {
                    formed--;
                }
                left++;
            }
            // Expand window by moving right pointer
            right++;
        }

        // Return minimum window substring or empty string if no valid window found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}