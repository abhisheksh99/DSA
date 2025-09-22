// Problem: Valid Parenthesis String
// Given a string containing '(', ')', and '*' characters, determine if the string is valid.
// A string is valid if it can represent a valid sequence of balanced parentheses, where '*' can be
// treated as '(', ')', or an empty string.

// DSA Pattern: Greedy / Two Counters
// This problem is solved by tracking the minimum and maximum possible counts of open parentheses
// while iterating through the string, accounting for the flexibility of '*' characters.

// Approach:
// 1. Initialize two counters:
//    - minOpen: Tracks the minimum possible number of unmatched open parentheses.
//    - maxOpen: Tracks the maximum possible number of unmatched open parentheses.
// 2. Iterate through each character in the string:
//    - If '(', increment both minOpen and maxOpen (treat as open parenthesis).
//    - If ')', decrement both minOpen and maxOpen (treat as closing parenthesis).
//    - If '*', decrement minOpen (treat as ')') and increment maxOpen (treat as '(').
// 3. After each character:
//    - If maxOpen < 0, there are too many closing parentheses, so return false.
//    - Ensure minOpen does not go below 0 (set to 0 if negative), as we cannot have unmatched closing parentheses.
// 4. At the end, return true if minOpen == 0 (all open parentheses can be matched).

// Key Points to Remember:
// - The '*' character can be '(', ')', or empty, giving flexibility in balancing parentheses.
// - minOpen represents the case where '*' is used as ')' or empty to minimize open parentheses.
// - maxOpen represents the case where '*' is used as '(' to maximize open parentheses.
// - If maxOpen < 0, there are more ')' than can be matched by '(' or '*', so the string is invalid.
// - minOpen is capped at 0 to avoid counting invalid negative open parentheses.
// - The string is valid only if minOpen == 0, meaning all '(' can be matched with ')' or '*'.
// - Edge cases:
//   - Empty string: valid (return true).
//   - Only '*': valid (can be empty, return true).
//   - Unmatched ')': maxOpen < 0, return false.
//   - Unmatched '(': minOpen > 0, return false.

// Time Complexity: O(n), where n is the length of the string
// - The algorithm iterates through the string once, performing O(1) operations per character.
// - Converting the string to a char array (in Java) is O(n), but this is typically included in the iteration.

// Space Complexity: O(1)
// - Only two integer variables (minOpen, maxOpen) are used, requiring constant extra space.
// - The input string is not modified, and no additional data structures are needed.

// Constraints:
// - 1 <= s.length <= 100
// - s consists of '(', ')', and '*'

public class Solution {
    public boolean checkValidString(String s) {
        // Initialize minimum and maximum open parentheses counts
        int minOpen = 0;  // Minimum possible open parentheses
        int maxOpen = 0;  // Maximum possible open parentheses
        
        // Iterate through each character
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Open parenthesis: increment both counters
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                // Closing parenthesis: decrement both counters
                minOpen--;
                maxOpen--;
            } else {
                // Wildcard '*': decrement minOpen (as ')'), increment maxOpen (as '(')
                minOpen--;
                maxOpen++;
            }
            
            // Too many closing parentheses: invalid
            if (maxOpen < 0) {
                return false;
            }
            
            // Prevent minOpen from being negative
            minOpen = Math.max(minOpen, 0);
        }
        
        // Valid if all open parentheses can be matched
        return minOpen == 0;
    }
}