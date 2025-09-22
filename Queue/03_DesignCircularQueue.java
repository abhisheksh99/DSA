class MyCircularQueue {
    // Problem: Design Circular Queue
    // Design a circular queue data structure that supports the following operations:
    // - MyCircularQueue(k): Constructor, initializes a queue of size k.
    // - enQueue(value): Inserts an element into the queue. Returns true if successful, false if full.
    // - deQueue(): Removes an element from the front of the queue. Returns true if successful, false if empty.
    // - Front(): Returns the front element of the queue, or -1 if empty.
    // - Rear(): Returns the last element of the queue, or -1 if empty.
    // - isEmpty(): Returns true if the queue is empty, false otherwise.
    // - isFull(): Returns true if the queue is full, false otherwise.

    // DSA Pattern: Circular Buffer (Array-Based Queue)
    // This problem is solved using a circular buffer implemented with an array, where the
    // queue wraps around to the beginning when the end is reached. This allows efficient
    // use of space by reusing slots after elements are dequeued. The implementation tracks
    // the head (front of queue), size (number of elements), and capacity, using modulo
    // arithmetic to handle wrap-around indices.

    // Approach:
    // 1. Constructor (MyCircularQueue):
    //    - Initialize an array of size k to store elements.
    //    - Set capacity to k, head to 0 (index of front), and size to 0 (empty queue).
    // 2. enQueue(value):
    //    - Check if the queue is full (size == capacity). If full, return false.
    //    - Compute the tail index using (head + size) % capacity.
    //    - Insert the value at the tail index, increment size, and return true.
    // 3. deQueue():
    //    - Check if the queue is empty (size == 0). If empty, return false.
    //    - Move head to the next index using (head + 1) % capacity, decrement size, and return true.
    // 4. Front():
    //    - If empty, return -1.
    //    - Otherwise, return the element at the head index.
    // 5. Rear():
    //    - If empty, return -1.
    //    - Compute the tail index using (head + size - 1) % capacity and return the element.
    // 6. isEmpty():
    //    - Return true if size is 0, false otherwise.
    // 7. isFull():
    //    - Return true if size equals capacity, false otherwise.

    // Key Points to Remember:
    // - The circular queue uses an array with modulo arithmetic to wrap indices around,
    //   ensuring efficient space usage compared to a linear queue.
    // - The head pointer tracks the front of the queue, and the tail is computed as
    //   (head + size) % capacity to handle wrap-around.
    // - Size tracks the number of elements, avoiding the need to track tail explicitly.
    // - Modulo operations (% capacity) ensure indices stay within array bounds.
    // - All operations handle edge cases: empty queue, full queue, single element, or wrap-around.
    // - The queue does not store null values; -1 is used as a sentinel for empty Front()/Rear() calls.
    // - The implementation is efficient, with all operations running in constant time.

    // Time Complexity: O(1) for all operations
    // - Constructor: Initializes array and variables in O(1) (array allocation is considered O(1) for fixed size k).
    // - enQueue: Performs a constant number of operations (check, index calculation, assignment).
    // - deQueue: Performs a constant number of operations (check, index update, decrement).
    // - Front: Constant-time array access or check.
    // - Rear: Constant-time index calculation and array access.
    // - isEmpty: Constant-time size check.
    // - isFull: Constant-time size comparison.
    // - All operations involve basic arithmetic or array access, which are O(1).

    // Space Complexity: O(k)
    // - The array of size k is the primary storage, where k is the capacity specified in the constructor.
    // - Additional variables (head, size, cap) use constant space.
    // - No additional data structures are used beyond the fixed-size array.

    private final int[] data; // Array to store queue elements
    private final int cap;    // Capacity of the queue
    private int head;         // Index of the front element
    private int size;         // Current number of elements in the queue

    // Constructor: Initializes a circular queue of size k
    public MyCircularQueue(int k) {
        data = new int[k];    // Allocate array of size k
        cap = k;              // Set capacity
        head = 0;             // Initialize head at index 0
        size = 0;             // Initialize queue as empty
    }

    // Insert an element at the rear of the queue
    public boolean enQueue(int value) {
        // Check if queue is full
        if (isFull()) {
            return false;     // Cannot insert if full
        }
        // Calculate tail index using modulo for wrap-around
        int tail = (head + size) % cap;
        data[tail] = value;   // Insert value at tail
        size++;               // Increment size
        return true;          // Insertion successful
    }

    // Remove an element from the front of the queue
    public boolean deQueue() {
        // Check if queue is empty
        if (isEmpty()) {
            return false;     // Cannot remove if empty
        }
        // Move head to next index using modulo for wrap-around
        head = (head + 1) % cap;
        size--;               // Decrement size
        return true;          // Removal successful
    }

    // Get the front element of the queue
    public int Front() {
        // Return -1 if queue is empty
        return isEmpty() ? -1 : data[head];
    }

    // Get the rear element of the queue
    public int Rear() {
        // Return -1 if queue is empty
        if (isEmpty()) {
            return -1;
        }
        // Calculate tail index using modulo for wrap-around
        int tail = (head + size - 1) % cap;
        return data[tail];    // Return element at tail
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;     // True if size is 0
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == cap;   // True if size equals capacity
    }
}