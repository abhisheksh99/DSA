class KthLargest {
    // Problem: Kth Largest Element in a Stream
    // Design a class to find the kth largest element in a stream of numbers. The class should
    // support the following operations:
    // - KthLargest(k, nums): Constructor, initializes the class with integer k and an array of initial numbers.
    // - add(val): Adds a new value to the stream and returns the kth largest element in the stream.

    // DSA Pattern: Min-Heap (Priority Queue)
    // This problem is solved using a min-heap to maintain the k largest elements seen so far.
    // The min-heap stores at most k elements, with the smallest of these at the root. This ensures
    // that the kth largest element is always at the root of the heap (accessible in O(1) time).
    // When a new value is added, we compare it with the heap's root to decide whether to include it.

    // Approach:
    // 1. Constructor (KthLargest):
    //    - Initialize a min-heap with capacity k to store the k largest elements.
    //    - Set the value of k to track the desired kth largest element.
    //    - Iterate through the initial array of numbers and call add() for each number to populate the heap.
    // 2. add(val):
    //    - If the heap has fewer than k elements, add the new value directly to the heap.
    //    - If the heap has k elements and the new value is larger than the smallest element (heap root),
    //      remove the smallest element and add the new value.
    //    - Return the smallest element in the heap (root), which is the kth largest element in the stream.
    
    // Key Points to Remember:
    // - The min-heap ensures the smallest of the k largest elements is at the root, making it easy to
    //   check if a new value should replace the smallest element.
    // - The heap never stores more than k elements, maintaining space efficiency.
    // - If the stream has fewer than k elements, the heap will contain all elements, and the root is still
    //   the kth largest (or smallest of the available elements).
    // - The min-heap handles duplicates correctly, as it compares values and keeps the k largest.
    // - The implementation is efficient, with O(log k) for insertions and O(1) for retrieving the kth largest.
    
    // Time Complexity:
    // - Constructor: O(n * log k), where n is the length of the input array nums.
    //   - Each call to add() takes O(log k), and we process n elements.
    // - add(val): O(log k)
    //   - Inserting into the heap (offer) or removing the root (poll) takes O(log k).
    //   - Accessing the root (peek) is O(1).
    // - Overall, the add operation is efficient for streaming data, as it only depends on k, not the stream size.
    
    // Space Complexity: O(k)
    // - The min-heap stores at most k elements at any time.
    // - Additional variables (k) use constant space.
    // - No other data structures are used beyond the heap.

    private PriorityQueue<Integer> minHeap; // Min-heap to store the k largest elements
    private int k;                         // The kth largest element to track

    // Constructor: Initializes the class with k and an array of initial numbers
    public KthLargest(int k, int[] nums) {
        this.k = k;                        // Store the value of k
        this.minHeap = new PriorityQueue<>(k); // Initialize min-heap with capacity k

        // Add each number from the initial array to the heap
        for (int num : nums) {
            add(num);                      // Use add() to process each number
        }
    }

    // Add a new value to the stream and return the kth largest element
    public int add(int val) {
        // If the heap has fewer than k elements, add the new value
        if (minHeap.size() < k) {
            minHeap.offer(val);            // Insert value into the heap (O(log k))
        }
        // If the heap has k elements and the new value is larger than the smallest
        else if (val > minHeap.peek()) {
            minHeap.poll();                // Remove the smallest element (O(log k))
            minHeap.offer(val);            // Add the new value (O(log k))
        }

        // Return the smallest element in the heap (kth largest in the stream)
        return minHeap.peek();             // Access root in O(1)
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */