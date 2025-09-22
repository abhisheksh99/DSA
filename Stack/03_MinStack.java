class MinStack {
    // Problem: Min Stack
    // Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    // The operations are:
    // - push(x): Push element x onto the stack.
    // - pop(): Remove and return the top element from the stack.
    // - top(): Return the top element of the stack without removing it.
    // - getMin(): Return the minimum element in the stack.
    // All operations must be performed in O(1) time.

    // DSA Pattern: Stack with Auxiliary Data
    // This problem is solved using a single stack to store both the elements and the minimum values.
    // To achieve O(1) time for getMin(), we maintain the minimum value seen so far and store it on the
    // stack whenever it changes. Specifically, when pushing a value less than or equal to the current
    // minimum, we first push the current minimum onto the stack, then update the minimum and push the
    // new value. When popping, if the popped value equals the current minimum, we pop again to restore
    // the previous minimum. This ensures that the minimum is always accessible in constant time.

    // Approach:
    // 1. Initialize a stack to store elements and auxiliary minimum values, and a variable `min` to track
    //    the current minimum, initially set to Integer.MAX_VALUE.
    // 2. push(x):
    //    - If x is less than or equal to the current minimum, push the current minimum onto the stack
    //      and update min to x.
    //    - Push x onto the stack.
    // 3. pop():
    //    - Pop the top element from the stack.
    //    - If the popped element equals the current minimum, pop again to retrieve the previous minimum
    //      and update min to this value.
    // 4. top():
    //    - Return the top element of the stack using peek().
    // 5. getMin():
    //    - Return the current minimum value stored in min.
    // 6. This approach ensures all operations are O(1) by storing minimums alongside elements.

    // Key Points to Remember:
    // - The stack stores both elements and minimum values, with minimums pushed only when the minimum
    //   changes (i.e., when x <= min).
    // - When popping, if the popped element equals the current minimum, the next element on the stack is
    //   the previous minimum, ensuring correct restoration of the minimum.
    // - The min variable always holds the current minimum, allowing O(1) access via getMin().
    // - Edge cases are handled:
    //   - Empty stack: The problem assumes valid operations (e.g., pop/top called only on non-empty stack).
    //   - Single element: min is updated correctly, and pop restores min to Integer.MAX_VALUE.
    // - The approach is space-efficient, as it only stores additional elements (previous minimums) when
    //   the minimum changes, not for every push.
    // - All operations (push, pop, top, getMin) are performed in constant time.

    // Time Complexity: O(1) for all operations
    // - push(x): Two stack pushes in the worst case (min and x): O(1).
    // - pop(): One or two stack pops (element and possibly previous min): O(1).
    // - top(): Peek operation on the stack: O(1).
    // - getMin(): Accessing the min variable: O(1).
    // - All operations involve constant-time stack operations or variable access.

    // Space Complexity: O(n)
    // - n: Number of elements pushed onto the stack.
    // - The stack stores each element, plus additional minimum values when the minimum changes.
    // - In the worst case (e.g., decreasing sequence like 3, 2, 1), the stack stores 2n elements
    //   (each element and its corresponding minimum): O(n).
    // - The min variable uses O(1) space.
    // - Overall space: O(n) for the stack.

    private int min;              // Tracks the current minimum value in the stack
    private Stack<Integer> stack; // Stack to store elements and auxiliary minimums

    // Constructor: Initialize the stack and min value
    public MinStack() {
        min = Integer.MAX_VALUE; // Initialize min to maximum possible integer
        stack = new Stack<>();   // Initialize empty stack
    }

    // Push an element onto the stack
    public void push(int x) {
        // If x is less than or equal to current min, push current min first
        if (x <= min) {
            stack.push(min); // Save current min to restore later if needed (O(1))
            min = x;         // Update min to new value
        }
        // Push the element onto the stack
        stack.push(x); // O(1)
    }

    // Remove and discard the top element from the stack
    public void pop() {
        // Pop the top element
        int popped = stack.pop(); // O(1)
        // If popped element was the current min, restore previous min
        if (popped == min) {
            min = stack.pop(); // Pop and set min to previous minimum (O(1))
        }
    }

    // Return the top element of the stack
    public int top() {
        return stack.peek(); // O(1)
    }

    // Return the minimum element in the stack
    public int getMin() {
        return min; // O(1)
    }
}