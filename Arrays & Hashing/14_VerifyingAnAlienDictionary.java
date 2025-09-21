class Solution {
    // Problem: Verifying an Alien Dictionary
    // Given an array of strings words and a string order representing the alien language's
    // alphabet order, return true if the words are sorted lexicographically according to
    // the alien alphabet, and false otherwise. Each character in order is unique, and all
    // words consist of lowercase English letters.

    // DSA Pattern: Custom Sorting / Array Mapping
    // This problem is solved by mapping the alien alphabet order to ranks and then comparing
    // adjacent words in the array based on these ranks. It involves a custom lexicographical
    // comparison tailored to the alien alphabet, without explicitly sorting the array.

    // Approach:
    // 1. Create a rank array to map each character (a-z) to its position in the alien alphabet order.
    // 2. Iterate through the words array, comparing each pair of adjacent words (words[i], words[i+1]):
    //    - For each pair, compare characters at the same position up to the length of the shorter word.
    //    - If characters differ, check if the rank of the character in the first word is greater
    //      than the rank of the character in the second word. If so, the words are not sorted, return false.
    //    - If characters are the same up to the shorter word's length, check if the first word is
    //      longer than the second. If so, it violates lexicographical order (e.g., "apple" > "app"), return false.
    // 3. If all pairs are in order, return true.

    // Key Points to Remember:
    // - Map the alien alphabet to ranks for O(1) comparison of characters.
    // - Compare adjacent words to verify the entire array is sorted.
    // - Handle cases where words have different lengths: a longer word should not come before a shorter
    //   word with the same prefix (e.g., "apple" after "app").
    // - Break the character comparison loop after finding the first differing character to avoid
    //   unnecessary checks.
    // - Handle edge cases like a single word (always sorted) or empty array (trivially sorted).
    // - All characters are lowercase, and the order string contains all 26 letters exactly once.

    // Time Complexity: O(n * m)
    // - n is the number of words, and m is the maximum length of any word.
    // - Creating the rank array takes O(1) since order is always 26 characters.
    // - We iterate through n-1 pairs of words, and for each pair, we compare up to m characters
    //   (the length of the shorter word).
    // - Each character comparison is O(1) due to the rank array lookup.
    // - Overall, the time complexity is O(n * m) in the worst case.

    // Space Complexity: O(1)
    // - The rank array is of fixed size (26 for lowercase letters), so it uses O(1) space.
    // - Other variables (loop counters, len) use constant space.
    // - The input arrays are modified in-place (no extra space for output).
    // - Overall, the space complexity is O(1).

    public boolean isAlienSorted(String[] words, String order) {
        // Initialize rank array to map each character to its position in the alien alphabet
        int[] rank = new int[26];
        for (int i = 0; i < 26; i++) {
            rank[order.charAt(i) - 'a'] = i;
        }

        // Iterate through adjacent pairs of words
        for (int i = 0; i < words.length - 1; i++) {
            // Get the current and next words
            String w1 = words[i], w2 = words[i + 1];
            // Get the length of the shorter word for character comparison
            int len = Math.min(w1.length(), w2.length());
            
            // Compare characters of both words
            for (int j = 0; j < len; j++) {
                // If characters differ
                if (w1.charAt(j) != w2.charAt(j)) {
                    // Check if the rank of w1's character is greater than w2's (out of order)
                    if (rank[w1.charAt(j) - 'a'] > rank[w2.charAt(j) - 'a']) {
                        return false;
                    }
                    // Break after finding the first differing character
                    break;
                }
                // If we reach the end of the shorter word and w1 is longer, it's out of order
                if (j == len - 1 && w1.length() > w2.length()) {
                    return false;
                }
            }
        }

        // If all pairs are in order, return true
        return true;
    }
}