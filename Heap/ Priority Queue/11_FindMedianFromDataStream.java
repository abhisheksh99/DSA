public class MedianFinder {
    // Problem: Find Median from Data Stream
    // Design a data structure that supports adding integers from a data stream and finding the median
    // of all elements added so far. The median is the middle value in an ordered list. If the list
    // has an even number of elements, the median is the average of the two middle values.

    // DSA Pattern: Two Heaps (Max-Heap and Min-Heap)
    // This problem is solved using two heaps to maintain the elements in a balanced way:
    // - A max-heap (lo) stores the smaller half of the numbers, with the largest of these at the root.
    // - A min-heap (hi) stores the larger half of the numbers, with the smallest of these at the root.
    // The heaps are kept balanced such that their sizes differ by at most one. The median is then either
    // the root of the larger heap (if odd total elements) or the average of the roots of both heaps
    // (if even total elements). This approach ensures efficient addition and median retrieval.

    // Approach:
    // 1. Initialize two heaps:
    //    - Max-heap (lo) for the smaller half, using a reversed comparator.
    //    - Min-heap (hi) for the larger half, using natural ordering.
    // 2. addNum(num):
    //    - Add the new number to the max-heap (lo).
    //    - Move the largest number from the max-heap to the min-heap (hi) to maintain the property
    //      that all elements in lo are less than or equal to those in hi.
    //    - If the min-heap (hi) has more elements than the max-heap (lo), move the smallest number
    //      from hi to lo to balance the sizes (lo.size >= hi.size).
    // 3. findMedian():
    //    - If lo.size > hi.size, the median is the root of the max-heap (lo.peek()).
    //    - If lo.size == hi.size, the median is the average of the roots of both heaps
    //      ((lo.peek() + hi.peek()) * 0.5).
    // 4. The balancing ensures that the max-heap has either the same number of elements as the min-heap
    //    or one more, allowing efficient median computation.

    // Key Points to Remember:
    // - The max-heap (lo) always contains the smaller half of the numbers, and the min-heap (hi)
    //   contains the larger half.
    // - The heaps are balanced to ensure lo.size >= hi.size, with at most a difference of 1.
    // - Adding a number involves inserting into the max-heap, moving the largest to the min-heap,
    //   and rebalancing if needed, ensuring the correct partitioning of numbers.
    // - The median is computed in O(1) by accessing the heap roots.
    // - Edge cases are handled naturally:
    //   - Single element: lo contains one element, hi is empty, median is lo.peek().
    //   - Even number of elements: lo and hi have equal sizes, median is the average of roots.
    // - The implementation is efficient, with O(log n) for adding numbers and O(1) for finding the median.

    // Time Complexity:
    // - addNum(num): O(log n)
    //   - Inserting into max-heap (lo.offer): O(log n), where n is the total number of elements.
    //   - Moving from max-heap to min-heap (lo.poll, hi.offer): O(log n).
    //   - Balancing (hi.poll, lo.offer): O(log n) if needed.
    //   - Total: O(log n) per addition.
    // - findMedian(): O(1)
    //   - Accessing heap roots (lo.peek, hi.peek): O(1).
    //   - Computing average (if needed): O(1).
    // - Overall, addNum is logarithmic, and findMedian is constant time.

    // Space Complexity: O(n)
    // - Max-heap (lo) and min-heap (hi) together store all n elements from the stream.
    // - Each heap stores approximately n/2 elements: O(n) total.
    // - Additional variables use O(1) space.
    // - Overall space: O(n) for the two heaps.

    private PriorityQueue<Integer> lo; // Max-heap for the smaller half of numbers
    private PriorityQueue<Integer> hi; // Min-heap for the larger half of numbers

    // Constructor: Initialize the two heaps
    public MedianFinder() {
        lo = new PriorityQueue<>((a, b) -> b - a); // Max-heap with reversed comparator
        hi = new PriorityQueue<>();                 // Min-heap with natural ordering
    }

    // Adds a number into the data structure
    public void addNum(int num) {
        // Step 1: Add the number to the max-heap (smaller half)
        lo.offer(num); // O(log n)

        // Step 2: Move the largest number from max-heap to min-heap (larger half)
        hi.offer(lo.poll()); // O(log n)

        // Step 3: Balance the heaps to ensure lo.size >= hi.size
        if (lo.size() < hi.size()) {
            lo.offer(hi.poll()); // Move smallest from min-heap to max-heap (O(log n))
        }
    }

    // Returns the median of the current data stream
    public double findMedian() {
        // If max-heap has more elements, median is its root
        if (lo.size() > hi.size()) {
            return lo.peek(); // O(1)
        }
        // If heaps have equal size, median is the average of their roots
        return (lo.peek() + hi.peek()) * 0.5; // O(1)
    }
}