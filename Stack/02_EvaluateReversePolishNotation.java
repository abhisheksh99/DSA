class Solution {
    // Problem: Evaluate Reverse Polish Notation
    // Given an array of strings representing an arithmetic expression in Reverse Polish Notation (RPN),
    // evaluate the expression and return the result as an integer. RPN is a postfix notation where
    // operators follow their operands (e.g., ["2", "1", "+", "3", "*"] evaluates to (2 + 1) * 3 = 9).
    // The input contains numbers (as strings) and operators ("+", "-", "*", "/"). Division is integer
    // division, truncating toward zero, and the input is guaranteed to be valid (no division by zero).

    // DSA Pattern: Stack
    // This problem is solved using a stack to process the RPN expression. Operands are pushed onto the
    // stack, and when an operator is encountered, the top two operands are popped, the operation is
    // performed, and the result is pushed back onto the stack. This continues until all tokens are
    // processed, and the final result is the single value left on the stack.

    // Approach:
    // 1. Initialize an empty stack to store operands (integers).
    // 2. Iterate through each token in the input array:
    //    - If the token is an operator ("+", "-", "*", "/"):
    //      - Pop the top two operands from the stack (in correct order for subtraction and division).
    //      - Perform the operation and push the result back onto the stack.
    //    - If the token is a number (string representation), parse it to an integer and push it onto the stack.
    // 3. After processing all tokens, the stack contains one element, which is the result of the expression.
    // 4. Pop and return the final result from the stack.

    // Key Points to Remember:
    // - RPN eliminates the need for parentheses, as the order of operations is determined by the position
    //   of operators and operands.
    // - For subtraction (b - a) and division (b / a), the order of popping matters: the first popped operand
    //   (a) is the second operand, and the second popped operand (b) is the first operand.
    // - The stack ensures that operands are processed in the correct order, with each operator consuming
    //   exactly two operands and producing one result.
    // - The input is guaranteed to be valid, so no edge cases like invalid tokens, empty arrays, or division
    //   by zero need to be handled explicitly.
    // - Integer division truncates toward zero (handled automatically by Java's / operator for integers).
    // - Each token is processed exactly once, and stack operations are O(1).

    // Time Complexity: O(n)
    // - n: Length of the input array tokens.
    // - Iterating through the array: O(n).
    // - For each token:
    //   - If it's a number, parsing and pushing to the stack: O(1).
    //   - If it's an operator, popping two operands, performing the operation, and pushing the result: O(1).
    // - Total complexity: O(n), as each token is processed with constant-time operations.
    // - Stack operations (push, pop) are O(1) per operation.

    // Space Complexity: O(n)
    // - Stack stores at most n/2 elements in the worst case (e.g., when all tokens are numbers until the
    //   last token is an operator): O(n).
    // - Additional variables (loop variable, temporary operands) use O(1) space.
    // - Overall space: O(n) due to the stack.

    public int evalRPN(String[] tokens) {
        // Step 1: Initialize a stack to store operands
        Stack<Integer> stack = new Stack<>();

        // Step 2: Process each token in the input array
        for (String c : tokens) {
            if (c.equals("+")) {
                // Addition: Pop two operands, add them, and push result
                stack.push(stack.pop() + stack.pop()); // O(1)
            } else if (c.equals("-")) {
                // Subtraction: Pop two operands, subtract in correct order (b - a)
                int a = stack.pop(); // Second operand
                int b = stack.pop(); // First operand
                stack.push(b - a);   // Push result (O(1))
            } else if (c.equals("*")) {
                // Multiplication: Pop two operands, multiply, and push result
                stack.push(stack.pop() * stack.pop()); // O(1)
            } else if (c.equals("/")) {
                // Division: Pop two operands, divide in correct order (b / a)
                int a = stack.pop(); // Second operand
                int b = stack.pop(); // First operand
                stack.push(b / a);   // Push result (integer division, O(1))
            } else {
                // Number: Parse string to integer and push to stack
                stack.push(Integer.parseInt(c)); // O(1)
            }
        }

        // Step 3: Return the final result from the stack
        return stack.pop(); // O(1)
    }
}