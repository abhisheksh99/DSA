class MaxStack {
    // Problem: Max Stack
    // Design a stack that supports the following operations in O(1) time for push, pop, top, and peekMax,
    // and O(n) time for popMax:
    // - push(x): Push element x onto the stack.
    // - pop(): Remove and return the top element from the stack.
    // - top(): Return the top element of the stack without removing it.
    // - peekMax(): Return the maximum element in the stack without removing it.
    // - popMax(): Remove and return the maximum element from the stack.
    // The stack must maintain the maximum element efficiently and handle all operations correctly.

    // DSA Pattern: Two Stacks (Main Stack and Auxiliary Max Stack)
    // This problem is solved using two stacks:
    // - A main stack to store all elements in the order they are pushed.
    // - An auxiliary max stack to track the maximum element at each push operation.
    // The max stack stores the maximum value seen up to each push, allowing O(1) access to the current
    // maximum. For popMax, we use a temporary buffer stack to preserve other elements while removing the
    // maximum, then restore the stack and max stack accordingly.

    // Approach:
    // 1. Initialize two stacks:
    //    - stack: Stores all elements in the order they are pushed.
    //    - maxStack: Stores the maximum value seen at each push operation.
    // 2. push(x):
    //    - Push x onto the main stack.
    //    - Compute the current maximum (either x or the previous maximum from maxStack.peek()) and push
    //      it onto maxStack.
    // 3. pop():
    //    - Pop the top element from both the main stack and maxStack (to keep them synchronized).
    //    - Return the popped element from the main stack.
    // 4. top():
    //    - Return the top element of the main stack using peek().
    // 5. peekMax():
    //    - Return the top element of maxStack, which is the current maximum.
    // 6. popMax():
    //    - Get the maximum value using peekMax().
    //    - Use a temporary buffer stack to store elements popped from the main stack until the maximum
    //      element is reached.
    //    - Pop the maximum element from both stacks.
    //    - Restore the buffered elements by pushing them back onto the stack (which updates maxStack).
    //    - Return the maximum value.
    // 7. This approach ensures O(1) time for push, pop, top, and peekMax, with O(n) for popMax due to
    //    the need to reorganize the stack.

    // Key Points to Remember:
    // - The maxStack mirrors the main stack, storing the maximum value at each push operation, ensuring
    //   O(1) access to the current maximum via peekMax().
    // - For popMax, elements above the maximum are temporarily moved to a buffer stack, the maximum is
    //   removed, and then elements are restored, maintaining the stack's order and maxStack's consistency.
    // - The maxStack is kept synchronized with the main stack, so each pop operation removes both the
    //   element and its corresponding maximum.
    // - Edge cases are handled:
    //   - Empty stack: The problem assumes valid operations (pop/top/peekMax/popMax called on non-empty stack).
    //   - Single element: maxStack and stack each have one element, and operations work correctly.
    //   - Duplicate maximums: popMax removes the most recent maximum, as it is at the top of the stack.
    // - The implementation is efficient for most operations, with popMax being the only operation requiring
    //   O(n) time due to stack reorganization.

    // Time Complexity:
    // - push(x): O(1)
    //   - Pushing to stack and maxStack: O(1).
    //   - Computing max (Math.max): O(1).
    // - pop(): O(1)
    //   - Popping from stack and maxStack: O(1).
    // - top(): O(1)
    //   - Peeking at stack: O(1).
    // - peekMax(): O(1)
    //   - Peeking at maxStack: O(1).
    // - popMax(): O(n)
    //   - Popping elements to buffer until max is reached: O(n) in worst case (max at bottom).
    //   - Popping max: O(1).
    //   - Restoring elements by pushing back: O(n) in worst case, as each push is O(1).
    // - Most operations are constant time, except popMax, which requires linear time.

    // Space Complexity: O(n)
    // - n: Number of elements in the stack.
    // - Main stack stores all elements: O(n).
    // - maxStack stores one maximum per element: O(n).
    // - Buffer stack in popMax stores at most n-1 elements temporarily: O(n).
    // - Overall space: O(n) for the two stacks and temporary buffer.

    private Stack<Integer> stack;    // Main stack to store elements
    private Stack<Integer> maxStack; // Auxiliary stack to track maximums at each push

    // Constructor: Initialize the main stack and max stack
    public MaxStack() {
        stack = new Stack<>();    // Initialize empty main stack
        maxStack = new Stack<>(); // Initialize empty max stack
    }

    // Push an element onto the stack
    public void push(int x) {
        // Compute the current maximum (x or previous max)
        int max = maxStack.isEmpty() ? x : Math.max(maxStack.peek(), x); // O(1)
        stack.push(x);       // Push element to main stack (O(1))
        maxStack.push(max);  // Push current maximum to max stack (O(1))
    }

    // Remove and return the top element from the stack
    public int pop() {
        maxStack.pop();      // Remove corresponding maximum from max stack (O(1))
        return stack.pop();  // Remove and return top element from main stack (O(1))
    }

    // Return the top element of the stack without removing it
    public int top() {
        return stack.peek(); // O(1)
    }

    // Return the maximum element in the stack without removing it
    public int peekMax() {
        return maxStack.peek(); // O(1)
    }

    // Remove and return the maximum element from the stack
    public int popMax() {
        int max = peekMax(); // Get the current maximum (O(1))
        Stack<Integer> buffer = new Stack<>(); // Temporary stack to store elements

        // Pop elements until the maximum is reached
        while (top() != max) {
            buffer.push(pop()); // Move non-max elements to buffer (O(1) per pop)
        }

        // Remove the maximum element
        pop(); // O(1)

        // Restore elements from buffer to main stack
        while (!buffer.isEmpty()) {
            push(buffer.pop()); // Push back elements, updating maxStack (O(1) per push)
        }

        return max; // Return the maximum
    }
}