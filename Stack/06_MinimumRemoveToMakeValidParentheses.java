public class Solution {
    // Problem: Minimum Remove to Make Valid Parentheses
    // Given a string s containing lowercase letters and parentheses ('(' and ')'), remove the minimum
    // number of parentheses to make the string valid. A string is valid if it is empty, contains only
    // lowercase letters, or is a well-formed sequence of parentheses where every '(' has a matching ')'
    // and vice versa. Return any valid string after removing the minimum number of parentheses.

    // DSA Pattern: Stack and HashSet
    // This problem is solved using a stack to track the indices of open parentheses ('(') and a HashSet
    // to store the indices of parentheses to be removed. The stack helps identify unmatched parentheses
    // by keeping track of '(' indices and pairing them with ')' when encountered. Unmatched ')' are
    // immediately marked for removal, and any '(' left on the stack after processing are also marked.
    // Finally, a valid string is built by excluding the marked indices.

    // Approach:
    // 1. Initialize a HashSet to store indices of parentheses to be removed and a stack to track indices
    //    of open parentheses.
    // 2. Iterate through the string:
    //    - For each '(', push its index onto the stack.
    //    - For each ')', if the stack is not empty, pop the top index (matching the ')'); otherwise,
    //      add the ')' index to the removeIndices set, as it is unmatched.
    // 3. After the loop, any indices remaining in the stack represent unmatched '(' and are added to
    //    removeIndices.
    // 4. Build the result string using a StringBuilder, including only characters whose indices are not
    //    in removeIndices.
    // 5. Return the resulting valid string.

    // Key Points to Remember:
    // - A valid string must have matching parentheses, meaning every '(' has a corresponding ')', and
    //   the number of ')' never exceeds the number of '(' at any point.
    // - The stack tracks indices of '(' to pair with ')', allowing us to identify unmatched parentheses.
    // - Unmatched ')' are detected when a ')' is encountered with an empty stack.
    // - Unmatched '(' are those left in the stack after processing the string.
    // - The HashSet efficiently tracks indices to be removed, allowing O(1) lookup during string construction.
    // - Edge cases are handled:
    //   - Empty string: Returns empty string.
    //   - No parentheses: Returns the input string unchanged.
    //   - Only unmatched parentheses: Removes all parentheses.
    // - The approach ensures the minimum number of removals by only removing unmatched parentheses.

    // Time Complexity: O(n)
    // - n: Length of the input string s.
    // - Iterating through the string to process parentheses: O(n).
    //   - Stack operations (push, pop): O(1) per operation.
    //   - HashSet operations (add): O(1) per operation.
    // - Processing remaining stack elements: O(n) in the worst case (all '(' are unmatched).
    // - Building the result string with StringBuilder: O(n).
    // - Total complexity: O(n), as all operations are linear.

    // Space Complexity: O(n)
    // - Stack stores at most n/2 indices in the worst case (e.g., all '('): O(n).
    // - HashSet stores at most n indices in the worst case (e.g., all parentheses unmatched): O(n).
    // - StringBuilder stores up to n characters for the result: O(n).
    // - Overall space: O(n) for the stack, HashSet, and StringBuilder.

    public String minRemoveToMakeValid(String s) {
        // Step 1: Initialize data structures
        Set<Integer> removeIndices = new HashSet<>(); // Stores indices of parentheses to remove
        Stack<Integer> stack = new Stack<>();         // Tracks indices of open parentheses

        // Step 2: Process the string to identify unmatched parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i); // Push index of open parenthesis (O(1))
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    removeIndices.add(i); // Unmatched ')', mark for removal (O(1))
                } else {
                    stack.pop(); // Match with top '(', pop it (O(1))
                }
            }
        }

        // Step 3: Add indices of unmatched open parentheses to removeIndices
        while (!stack.isEmpty()) {
            removeIndices.add(stack.pop()); // O(1) per pop
        }

        // Step 4: Build the result string, excluding marked indices
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removeIndices.contains(i)) {
                result.append(s.charAt(i)); // Append valid characters (O(1))
            }
        }

        // Step 5: Return the valid string
        return result.toString(); // O(1)
    }
}