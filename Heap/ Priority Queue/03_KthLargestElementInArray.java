public class Solution {
    // Problem: Kth Largest Element in an Array
    // Given an integer array nums and an integer k, return the kth largest element in the array.
    // The kth largest element is the kth element in the sorted array (in descending order).
    // Note that it is the kth largest element in the sorted order, not the kth distinct element.

    // DSA Pattern: Min-Heap (Priority Queue)
    // This problem is solved using a min-heap to efficiently track the k largest elements in the array.
    // A min-heap maintains at most k elements, with the smallest of these at the root. By keeping the
    // heap size at k, the root will be the kth largest element after processing all numbers. This approach
    // avoids sorting the entire array, which would be less efficient.

    // Approach:
    // 1. Initialize a min-heap (PriorityQueue with natural ordering for minimum values at the root).
    // 2. Iterate through the array:
    //    - Add each number to the min-heap.
    //    - If the heap size exceeds k, remove the smallest element (root) to maintain a size of k.
    // 3. After processing all numbers, the root of the min-heap is the kth largest element.
    // 4. Return the root of the heap using peek().

    // Key Points to Remember:
    // - The min-heap ensures that the smallest of the k largest elements is at the root, making it
    //   easy to discard smaller elements when the heap size exceeds k.
    // - The heap never stores more than k elements, ensuring space efficiency.
    // - Duplicates are handled naturally, as the heap compares values and keeps the k largest.
    // - Edge cases are handled implicitly:
    //   - If k equals the array length, the heap contains all elements, and the root is the smallest.
    //   - If k = 1, the heap contains the largest element after processing.
    // - The implementation is efficient, avoiding full sorting (O(n * log n)) by using a heap.

    // Time Complexity: O(n * log k)
    // - Adding each of the n elements to the min-heap takes O(log k) per insertion.
    // - Removing the smallest element (poll) when the heap size exceeds k takes O(log k).
    // - Total complexity: O(n * log k), as we process n elements, and each operation (add or poll) is O(log k).
    // - This is more efficient than sorting the entire array (O(n * log n)) when k is significantly smaller than n.

    // Space Complexity: O(k)
    // - The min-heap stores at most k elements at any time.
    // - No additional data structures are used beyond the heap.
    // - The space usage is independent of the input array size n, depending only on k.

    public int findKthLargest(int[] nums, int k) {
        // Initialize a min-heap to store the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process each number in the input array
        for (int num : nums) {
            minHeap.add(num); // Add the number to the heap (O(log k))
            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the root (smallest element, O(log k))
            }
        }

        // Return the root of the heap, which is the kth largest element
        return minHeap.peek(); // O(1)
    }
}