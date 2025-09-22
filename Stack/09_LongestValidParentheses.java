public class Solution {
    // Problem: Longest Valid Parentheses
    // Given a string s containing only the characters '(' and ')', find the length of the longest
    // valid (well-formed) parentheses substring. A valid parentheses substring is one where every
    // '(' has a matching ')', and they are properly nested. Return the length as an integer.

    // DSA Pattern: Stack
    // This problem is solved using a stack to track the indices of parentheses to identify valid
    // substrings. The stack stores indices of open parentheses '(' and bases for valid substrings.
    // By maintaining a base index (-1 initially or the index of the last unmatched ')'), we can
    // calculate the length of valid substrings when a matching ')' is found. The stack ensures we
    // efficiently track the start of potential valid substrings and compute their lengths.

    // Approach:
    // 1. Initialize a stack with -1 as the base index for the first valid substring.
    // 2. Initialize maxLength to track the length of the longest valid parentheses substring.
    // 3. Iterate through the string:
    //    - For each '(', push its index onto the stack.
    //    - For each ')':
    //      - Pop the top index from the stack (either a '(' or a base index).
    //      - If the stack becomes empty, push the current index as the new base for the next valid substring.
    //      - If the stack is not empty, calculate the length of the current valid substring as
    //        i - stack.peek(), where i is the current index and stack.peek() is the index of the
    //        previous unmatched '(' or base. Update maxLength if this length is greater.
    // 4. Return maxLength as the result.

    // Key Points to Remember:
    // - The stack stores indices of '(' and base indices for valid substrings, with -1 as the initial base.
    // - When a ')' matches a '(', popping reveals the base of the current valid substring, and the length
    //   is calculated as the difference between the current index and the new top of the stack.
    // - If a ')' has no matching '(', the stack becomes empty, and the current index becomes the new base.
    // - The base index (-1 or index of unmatched ')') ensures correct length calculation for nested or
    //   consecutive valid substrings.
    // - Edge cases are handled:
    //   - Empty string: Returns 0 (no valid substring).
    //   - No valid substrings (e.g., ")))"): Returns 0.
    //   - Single valid pair (e.g., "()"): Returns 2.
    // - The approach efficiently tracks the longest valid substring by updating maxLength only when a
    //   valid substring is found.

    // Time Complexity: O(n)
    // - n: Length of the input string s.
    // - Iterating through the string: O(n).
    // - Stack operations (push, pop, peek): O(1) per operation.
    // - Each character is processed once, with at most one push and one pop per character.
    // - Updating maxLength: O(1) per valid substring end.
    // - Total complexity: O(n), as all operations are linear.

    // Space Complexity: O(n)
    // - Stack stores at most n/2 + 1 indices in the worst case (e.g., all '(' followed by all ')'): O(n).
    // - Additional variables (maxLength, i) use O(1) space.
    // - Overall space: O(n) due to the stack.

    public int longestValidParentheses(String s) {
        // Initialize variables
        int maxLength = 0; // Tracks the length of the longest valid parentheses substring
        Stack<Integer> stack = new Stack<>(); // Stack to store indices of '(' and bases
        stack.push(-1); // Initial base for the first valid substring

        // Step 1: Process each character in the string
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Push index of open parenthesis
                stack.push(i); // O(1)
            } else {
                // Pop top index for closing parenthesis
                stack.pop(); // O(1)

                if (stack.isEmpty()) {
                    // No matching '(', set current index as new base
                    stack.push(i); // O(1)
                } else {
                    // Valid substring found, calculate length and update maxLength
                    maxLength = Math.max(maxLength, i - stack.peek()); // O(1)
                }
            }
        }

        // Step 2: Return the length of the longest valid substring
        return maxLength;
    }
}