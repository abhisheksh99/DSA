class Solution {
    // Problem: Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements in any
    // order. The array may contain duplicates, and it is guaranteed that the answer is unique
    // (i.e., there are enough unique elements to return k of them).

    // DSA Pattern: Hash Map with Max-Heap (Priority Queue)
    // This problem is solved using a HashMap to count the frequency of each element and a max-heap
    // (PriorityQueue) to extract the k most frequent elements. The HashMap efficiently counts
    // occurrences, and the max-heap prioritizes elements with higher frequencies, allowing us to
    // retrieve the top k elements directly.

    // Approach:
    // 1. Create a HashMap to store the frequency of each number in nums.
    // 2. Iterate through nums, updating the frequency count for each element using getOrDefault.
    // 3. Create a max-heap (PriorityQueue) that compares elements based on their frequencies
    //    in descending order (higher frequency first).
    // 4. Add all unique elements from the HashMap to the max-heap.
    // 5. Poll the top k elements from the heap and store them in the result array.
    // 6. Return the array containing the k most frequent elements.

    // Key Points to Remember:
    // - Use a HashMap to count frequencies in one pass for efficiency.
    // - A max-heap allows direct extraction of the top k frequent elements without maintaining
    //   a size limit during insertion.
    // - The heap compares elements based on their frequencies using the HashMap.
    // - The order of elements in the output array does not matter, as per the problem.
    // - Handle edge cases like k == nums.length (return all elements) or arrays with duplicates.
    // - The max-heap approach is simpler than a min-heap for this problem, as it avoids the need
    //   to maintain a heap of size k during insertion.
    // - Ensure k is valid (1 <= k <= number of unique elements, guaranteed by the problem).

    // Time Complexity: O(n log n)
    // - Building the frequency HashMap takes O(n), where n is the length of nums.
    // - Adding each unique element to the max-heap takes O(log d) per element, where d is the
    //   number of unique elements (d <= n), so this step is O(n log n) in the worst case.
    // - Polling k elements from the heap takes O(k log n).
    // - Overall, the time complexity is dominated by O(n log n) due to heap operations.

    // Space Complexity: O(n)
    // - The HashMap stores up to n unique elements, using O(n) space.
    // - The max-heap stores up to d elements (where d <= n), using O(n) space in the worst case.
    // - The output array uses O(k) space, but this is required by the problem.
    // - Overall, the space complexity is O(n) due to the HashMap and heap.

    public int[] topKFrequent(int[] nums, int k) {
        // Create a HashMap to count the frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // Create a max-heap based on frequencies (descending order)
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> map.get(b) - map.get(a));

        // Add all unique elements to the max-heap
        for (int i : map.keySet()) {
            pq.offer(i);
        }

        // Extract the top k elements from the heap into the result array
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll();
        }

        // Return the k most frequent elements
        return ans;
    }
}