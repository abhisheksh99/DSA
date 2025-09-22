class Solution {
    // Problem: Last Stone Weight
    // Given an array of integers representing the weights of stones, simulate a process where
    // you repeatedly pick the two heaviest stones, smash them together, and handle the result:
    // - If the stones have weights x and y (x <= y), after smashing, both stones are destroyed,
    //   and if x != y, a new stone of weight y - x is added back.
    // - Continue this process until at most one stone remains.
    // - Return the weight of the remaining stone (or 0 if no stones remain).

    // DSA Pattern: Max-Heap (Priority Queue)
    // This problem is solved using a max-heap to efficiently retrieve the two heaviest stones
    // in each iteration. A max-heap ensures that the largest element is always at the root,
    // allowing constant-time access to the heaviest stones. After smashing, we add the difference
    // back to the heap if the stones are not equal, continuing until at most one stone remains.

    // Approach:
    // 1. Initialize a max-heap (PriorityQueue with reversed comparator to prioritize larger values).
    // 2. Add all stone weights from the input array to the max-heap.
    // 3. While the heap has at least two stones:
    //    - Extract the two heaviest stones (y and x, where x <= y).
    //    - If x != y, compute the difference (y - x) and add it back to the heap.
    //    - If x == y, both stones are destroyed, and nothing is added back.
    // 4. After the loop, return the weight of the remaining stone (if any) or 0 if the heap is empty.

    // Key Points to Remember:
    // - The max-heap is implemented using a PriorityQueue with a custom comparator (b - a) to
    //   prioritize larger weights, simulating a max-heap.
    // - The heap ensures O(log n) time for adding and removing elements, making it efficient for
    //   repeatedly accessing the heaviest stones.
    // - The condition x != y ensures that equal-weight stones destroy each other completely,
    //   while unequal weights result in a new stone of weight y - x.
    // - The loop continues as long as there are at least two stones (size > 1).
    // - Edge cases are handled naturally:
    //   - If the input array is empty, the heap will be empty, returning 0.
    //   - If only one stone exists, the loop is skipped, and the stone's weight is returned.
    // - The final check for an empty heap ensures the correct return value when no stones remain.

    // Time Complexity: O(n * log n)
    // - Initializing the max-heap: O(n), where n is the number of stones (adding n elements, each taking O(log n)).
    // - Main loop: In the worst case, we perform up to n-1 smash operations (each stone is smashed at most once).
    //   - Each operation involves two poll operations (O(log n)) and one potential add operation (O(log n)).
    //   - Total loop complexity: O(n * log n) for up to n iterations, each with O(log n) operations.
    // - Overall complexity: O(n * log n) due to heap operations dominating the process.

    // Space Complexity: O(n)
    // - The max-heap stores at most n elements initially (all stones).
    // - As stones are smashed, the heap size decreases, so space usage never exceeds O(n).
    // - No additional data structures are used beyond the heap.

    public int lastStoneWeight(int[] stones) {
        // Initialize a max-heap using PriorityQueue with reversed comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all stone weights to the max-heap
        for (int stone : stones) {
            maxHeap.add(stone); // O(log n) per insertion
        }

        // Continue smashing while there are at least two stones
        while (maxHeap.size() > 1) {
            // Extract the two heaviest stones (y is the largest, x is the second largest)
            int y = maxHeap.poll(); // O(log n)
            int x = maxHeap.poll(); // O(log n)

            // If stones have different weights, add the difference back to the heap
            if (x != y) {
                maxHeap.add(y - x); // O(log n)
            }
            // If x == y, both stones are destroyed, and nothing is added back
        }

        // Return the remaining stone's weight or 0 if no stones remain
        return maxHeap.isEmpty() ? 0 : maxHeap.poll(); // O(1) or O(log n) for poll
    }
}