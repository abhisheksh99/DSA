class Solution {
    // Problem: Daily Temperatures
    // Given an array of integers representing daily temperatures, return an array where each element
    // answer[i] represents the number of days until a warmer day occurs (i.e., the first day after
    // day i where temperatures[j] > temperatures[i]). If no warmer day exists for a given day, set
    // answer[i] to 0.

    // DSA Pattern: Monotonic Stack
    // This problem is solved using a monotonic stack to maintain a decreasing sequence of indices
    // corresponding to temperatures. The stack stores indices of days with temperatures in decreasing
    // order, allowing us to efficiently find the next warmer day for each day. When a warmer day is
    // encountered, we pop indices from the stack and compute the difference in days, updating the
    // result array accordingly.

    // Approach:
    // 1. Initialize a result array `answer` of size n (where n is the length of the input array) to
    //    store the number of days until a warmer day, initially set to 0.
    // 2. Initialize a stack to store indices of days with temperatures in decreasing order.
    // 3. Iterate through the array (index i from 0 to n-1):
    //    - While the stack is not empty and the current temperature (temperatures[i]) is greater than
    //      the temperature at the index at the top of the stack (temperatures[stack.peek()]):
    //      - Pop the top index from the stack.
    //      - Calculate the number of days until the warmer day as i - popped_index.
    //      - Store this difference in answer[popped_index].
    //    - Push the current index i onto the stack.
    // 4. Return the result array, where unpopped indices (days with no warmer day) remain 0.

    // Key Points to Remember:
    // - The stack maintains indices in a monotonically decreasing order of their corresponding
    //   temperatures, ensuring that the top of the stack always points to a day that is waiting for
    //   a warmer day.
    // - When a warmer day is found (temperatures[i] > temperatures[stack.peek()]), the top index is
    //   popped, and the day difference is computed, as the current day i is the first warmer day for
    //   the popped day.
    // - Each index is pushed and popped at most once, making stack operations amortized O(1).
    // - Days with no warmer day (remaining in the stack) automatically have answer[i] = 0, as the
    //   result array is initialized to 0.
    // - Edge cases are handled:
    //   - If the array has one element, the result is [0] (no future day exists).
    //   - If temperatures are non-increasing, all results are 0 (no warmer days).
    // - The implementation is efficient, avoiding the need to check future days explicitly for each day.

    // Time Complexity: O(n)
    // - n: Length of the input array temperatures.
    // - Iterating through the array: O(n).
    // - Each index is pushed onto the stack once (O(1)) and popped at most once (O(1)).
    // - Total stack operations: O(n) amortized, as each element is processed at most twice (push and pop).
    // - Result array initialization: O(n).
    // - Overall complexity: O(n), as all operations are linear or amortized linear.

    // Space Complexity: O(n)
    // - Stack stores at most n indices in the worst case (e.g., when temperatures are in decreasing
    //   order): O(n).
    // - Result array stores n elements: O(n).
    // - Additional variables (i, n) use O(1) space.
    // - Overall space: O(n) due to the stack and result array.

    public int[] dailyTemperatures(int[] temperatures) {
        // Step 1: Initialize variables
        int n = temperatures.length; // Length of input array
        int[] answer = new int[n];   // Result array initialized to 0
        Stack<Integer> stack = new Stack<>(); // Stack to store indices of days

        // Step 2: Process each day
        for (int i = 0; i < n; i++) {
            // While stack is not empty and current temperature is warmer than the temperature
            // at the index at the top of the stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop(); // Pop the index of a day waiting for a warmer day
                answer[index] = i - index; // Calculate days until warmer day
            }
            // Push current index onto the stack
            stack.push(i); // O(1)
        }

        // Step 3: Return the result array
        return answer;
    }
}