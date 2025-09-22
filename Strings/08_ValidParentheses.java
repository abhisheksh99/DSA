class Solution {
    // Problem: Valid Parentheses
    // Given a string s containing only the characters '(', ')', '{', '}', '[', and ']',
    // determine if the string is valid. A string is valid if:
    // - Open brackets are closed by the same type of brackets.
    // - Open brackets are closed in the correct order.
    // - Every close bracket has a corresponding open bracket.

    // DSA Pattern: Stack
    // This problem is solved using a stack to track open brackets and ensure they are closed
    // in the correct order. By pushing open brackets onto the stack and checking if closing
    // brackets match the most recent open bracket, we can validate the string efficiently.

    // Approach:
    // 1. Initialize an empty stack to store open brackets.
    // 2. Iterate through each character in the string:
    //    - If the character is an open bracket ('(', '[', '{'), push it onto the stack.
    //    - If the character is a closing bracket (')', ']', '}'):
    //      - Check if the stack is empty (no matching open bracket), return false.
    //      - Pop the top bracket from the stack and check if it matches the current closing
    //        bracket. If not, return false.
    // 3. After the loop, check if the stack is empty:
    //    - If empty, all brackets were matched correctly, return true.
    //    - If not empty, there are unmatched open brackets, return false.

    // Key Points to Remember:
    // - Use a stack to track the order of open brackets and ensure proper matching.
    // - Check for empty stack before popping to handle cases with unmatched closing brackets.
    // - Match each closing bracket with the most recent open bracket (top of stack).
    // - Handle edge cases: empty string (valid), single character (invalid), or unbalanced brackets.
    // - The input contains only parentheses characters, simplifying the validation logic.
    // - Ensure all open brackets are closed in the correct order (LIFO principle).
    // - The final stack must be empty for the string to be valid.

    // Time Complexity: O(n)
    // - Iterate through the string once, where n is the length of s.
    // - Stack operations (push, pop, isEmpty) are O(1).
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(n)
    // - The stack stores at most n/2 open brackets in the worst case (e.g., "(((((").
    // - No other data structures are used beyond the stack.
    // - Overall, the space complexity is O(n).

    public boolean isValid(String s) {
        // Initialize a stack to store open brackets
        Stack<Character> stack = new Stack<>();
        
        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // If character is an open bracket, push it onto the stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } 
            // If character is a closing bracket
            else {
                // If stack is empty, no matching open bracket, return false
                if (stack.isEmpty()) {
                    return false;
                }
                // Pop the top open bracket
                char top = stack.pop();
                // Check if the closing bracket matches the top open bracket
                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }
        
        // Return true if stack is empty (all brackets matched), false otherwise
        return stack.isEmpty();
    }
}