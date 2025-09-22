class Solution {
    // Problem: K Closest Points to Origin
    // Given an array of points where points[i] = [x_i, y_i] represents a point on the X-Y plane
    // and an integer k, return the k points that are closest to the origin (0, 0).
    // The distance between a point (x, y) and the origin is the Euclidean distance: sqrt(x^2 + y^2).
    // The k closest points can be returned in any order, and the answer is guaranteed to be unique
    // (except for the order).

    // DSA Pattern: Min-Heap (Priority Queue)
    // This problem is solved using a min-heap to efficiently track the k points with the smallest
    // Euclidean distances to the origin. The min-heap stores at most k points, with the point
    // having the largest distance among them at the root (using a custom comparator). By keeping
    // the heap size at k, we ensure that after processing all points, the heap contains the k
    // closest points. This approach avoids sorting the entire array of points.

    // Approach:
    // 1. Initialize a min-heap (PriorityQueue) with a custom comparator that compares points based
    //    on their Euclidean distance to the origin (x^2 + y^2). The comparator is reversed to make
    //    the heap act as a min-heap for distances, so the largest distance is at the root.
    // 2. Iterate through the points array:
    //    - Add each point (as an array [x, y]) to the min-heap.
    //    - If the heap size exceeds k, remove the point with the largest distance (root).
    // 3. After processing all points, the heap contains the k closest points.
    // 4. Convert the heap to an array of size k x 2 and return it.

    // Key Points to Remember:
    // - The Euclidean distance is computed as x^2 + y^2 (square root is not needed since it preserves
    //   the order for comparison).
    // - The min-heap is implemented using a PriorityQueue with a comparator that calculates
    //   (b[0]^2 + b[1]^2) - (a[0]^2 + a[1]^2) to prioritize points with smaller distances.
    // - The heap never stores more than k points, ensuring space efficiency.
    // - The output array is of size k x 2, where each element is a point [x_i, y_i].
    // - Edge cases are handled implicitly:
    //   - If k equals the number of points, the heap contains all points.
    //   - If k = 1, the heap contains the single closest point.
    // - The implementation is efficient, avoiding full sorting (O(n * log n)) by using a heap.

    // Time Complexity: O(n * log k)
    // - Adding each of the n points to the min-heap takes O(log k) per insertion.
    // - Removing the root (point with the largest distance) when the heap size exceeds k takes O(log k).
    // - Total complexity: O(n * log k), as we process n points, and each operation (add or poll) is O(log k).
    // - Converting the heap to an array at the end takes O(k).
    // - This is more efficient than sorting all points (O(n * log n)) when k is significantly smaller than n.

    // Space Complexity: O(k)
    // - The min-heap stores at most k points at any time, where each point is an array of two integers.
    // - The output array requires O(k) space to store k points.
    // - No additional data structures are used beyond the heap and the output array.

    public int[][] kClosest(int[][] points, int k) {
        // Initialize a min-heap with a comparator based on Euclidean distance
        // Comparator: (b[0]^2 + b[1]^2) - (a[0]^2 + a[1]^2) ensures smaller distances are prioritized
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        // Process each point in the input array
        for (int[] point : points) {
            pq.add(point); // Add the point to the heap (O(log k))
            // If heap size exceeds k, remove the point with the largest distance
            if (pq.size() > k) {
                pq.poll(); // Remove the root (O(log k))
            }
        }

        // Convert the heap to an array of k points and return
        return pq.toArray(new int[k][2]); // O(k)
    }
}