public class Solution {
    // Problem: Sliding Window Maximum
    // Given an array of integers nums and an integer k, return an array of the maximum values
    // for each sliding window of size k in the array. The sliding window moves one position to the
    // right at each step, and there are n - k + 1 windows, where n is the length of the array.
    // The output array should contain the maximum element in each window, in order.

    // DSA Pattern: Monotonic Deque
    // This problem is solved using a double-ended queue (Deque) to maintain a monotonically decreasing
    // sequence of indices, ensuring the maximum element in the current window is always at the front.
    // The deque stores indices of elements in the window, where the corresponding values are in
    // decreasing order. This allows efficient removal of out-of-window indices and smaller values
    // that cannot be the maximum, achieving O(1) operations per element after initial setup.

    // Approach:
    // 1. Handle edge cases: If the input array is null, empty, or k <= 0, return an empty array.
    // 2. Initialize a result array of size n - k + 1 to store the maximum of each window.
    // 3. Initialize a Deque to store indices of potential maximum elements in the current window.
    // 4. Iterate through the array (index i from 0 to n-1):
    //    - Remove indices from the front of the deque that are outside the current window (i - k + 1).
    //    - Remove indices from the back of the deque if their corresponding values are less than nums[i],
    //      as they cannot be the maximum in the current or future windows.
    //    - Add the current index i to the back of the deque.
    //    - If i >= k - 1 (i.e., a full window of size k is formed), add the value at the front of the
    //      deque (nums[deque.peek()]) to the result array, as it is the maximum for the current window.
    // 5. Return the result array containing the maximums of all windows.

    // Key Points to Remember:
    // - The deque maintains indices in a monotonically decreasing order of their corresponding values,
    //   ensuring the front always holds the index of the maximum element in the current window.
    // - Indices are removed from the front if they fall outside the current window (i - k + 1).
    // - Indices are removed from the back if their values are smaller than the current element, as they
    //   cannot be the maximum in the current or future windows.
    // - Each element is added and removed from the deque at most once, making the amortized time per
    //   operation O(1).
    // - Edge cases are handled:
    //   - Empty array, null array, or invalid k (k <= 0): Return empty array.
    //   - k = 1: Each window contains one element, and the result is the input array.
    //   - k = n: Only one window, and the result contains the maximum of the entire array.
    // - The deque ensures efficient tracking of the maximum without explicitly comparing all elements in
    //   each window.

    // Time Complexity: O(n)
    // - n: Length of the input array nums.
    // - Iterating through the array: O(n).
    // - Each element is pushed to and popped from the deque at most once:
    //   - Adding an index (offer): O(1).
    //   - Removing indices from front (poll): O(1) amortized, as each index is removed at most once.
    //   - Removing indices from back (pollLast): O(1) amortized, as each index is removed at most once.
    // - Total complexity: O(n), as each operation (push/pop) is amortized O(1) over n iterations.
    // - Result array initialization and population: O(n - k + 1), which is O(n).

    // Space Complexity: O(n)
    // - Deque stores at most k indices in the worst case (when elements are in increasing order within
    //   a window): O(k).
    // - Result array stores n - k + 1 elements: O(n).
    // - Overall space: O(n) due to the result array, as k <= n.
    // - Additional variables (i, n) use O(1) space.

    public int[] maxSlidingWindow(int[] nums, int k) {
        // Step 1: Handle edge cases (null array, empty array, or invalid k)
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0]; // Return empty array
        }

        // Step 2: Initialize result array and deque
        int n = nums.length;
        int[] result = new int[n - k + 1]; // Array to store maximums of each window
        Deque<Integer> deque = new LinkedList<>(); // Deque to store indices of potential maximums

        // Step 3: Process each element in the array
        for (int i = 0; i < n; i++) {
            // Remove indices that are out of the current window (i - k + 1)
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll(); // Remove front index (O(1))
            }

            // Remove indices whose corresponding values are less than nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast(); // Remove back index (O(1))
            }

            // Add the current index to the deque
            deque.offer(i); // Add index to back (O(1))

            // Step 4: Add maximum to result for windows of size k
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peek()]; // Maximum is at front of deque
            }
        }

        // Step 5: Return the result array
        return result;
    }
}