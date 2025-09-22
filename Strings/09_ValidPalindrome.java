class Solution {
    // Problem: Valid Palindrome
    // Given a string s, determine if it is a palindrome, considering only alphanumeric
    // characters (letters and digits) and ignoring cases. A palindrome reads the same
    // forward and backward. Non-alphanumeric characters (e.g., spaces, punctuation) are ignored.

    // DSA Pattern: Two Pointers
    // This problem is solved using a two-pointer technique, where pointers start at opposite
    // ends of the string and move inward, comparing only alphanumeric characters while
    // ignoring case and non-alphanumeric characters.

    // Approach:
    // 1. Initialize two pointers: left at the start (0) and right at the end (s.length() - 1).
    // 2. While left < right:
    //    - Skip non-alphanumeric characters at left by incrementing left until an alphanumeric
    //      character is found or left meets right.
    //    - Skip non-alphanumeric characters at right by decrementing right until an alphanumeric
    //      character is found or right meets left.
    //    - Compare the lowercase versions of the characters at left and right.
    //    - If they differ, return false (not a palindrome).
    //    - Increment left and decrement right to continue checking.
    // 3. If the loop completes, return true (the string is a palindrome).

    // Key Points to Remember:
    // - Use Character.isLetterOrDigit to check for alphanumeric characters.
    // - Convert characters to lowercase using Character.toLowerCase for case-insensitive comparison.
    // - Skip non-alphanumeric characters (spaces, punctuation, etc.) during iteration.
    // - Handle edge cases: empty string (valid palindrome), single character (valid), or
    //   strings with only non-alphanumeric characters (valid).
    // - The two-pointer approach ensures we only compare relevant characters.
    // - The comparison is case-insensitive, as specified by the problem.
    // - Ensure left < right in the loop to avoid redundant checks when pointers meet.

    // Time Complexity: O(n)
    // - Iterate through the string with two pointers, where n is the length of s.
    // - Each character is visited at most once (left and right pointers move inward).
    // - Character.isLetterOrDigit and Character.toLowerCase are O(1) operations.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(1)
    // - Only use two pointers (left, right) and temporary variables for characters.
    // - No additional data structures are used.
    // - Overall, the space complexity is O(1).

    public boolean isPalindrome(String s) {
        // Initialize pointers at the start and end of the string
        int left = 0;
        int right = s.length() - 1;
        
        // Iterate while left pointer is less than right pointer
        while (left < right) {
            // Skip non-alphanumeric characters from the left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric characters from the right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // Compare lowercase characters; if they differ, return false
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            // Move pointers inward
            left++;
            right--;
        }
        
        // If all comparisons pass, the string is a palindrome
        return true;
    }
}