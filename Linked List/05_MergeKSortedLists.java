class Solution {
    // Problem: Merge k Sorted Lists
    // Given an array of k linked lists, where each list is sorted in ascending order, merge
    // all the linked lists into one sorted linked list and return its head. The lists may
    // be empty, and the array may contain zero or more lists.

    // DSA Pattern: Priority Queue (Min-Heap)
    // This problem is solved using a min-heap to efficiently select the smallest node from
    // the heads of the k lists at each step. By maintaining only the current node from each
    // list in the heap, we can repeatedly extract the smallest node, add it to the result
    // list, and insert the next node from the same list into the heap. A dummy node
    // simplifies the construction of the merged list.

    // Approach:
    // 1. Handle the base case: if the input array is empty or null, return null.
    // 2. Initialize a min-heap to store ListNode objects, sorted by their values.
    // 3. Add the head node of each non-null list to the min-heap.
    // 4. Initialize a dummy node and a pointer (merge) to build the result list.
    // 5. While the min-heap is not empty:
    //    - Extract the node with the smallest value from the heap.
    //    - Append it to the result list.
    //    - If the extracted node has a next node, add that next node to the heap.
    //    - Move the merge pointer to the newly added node.
    // 6. Return the next node of the dummy node as the head of the merged list.

    // Key Points to Remember:
    // - Use a min-heap to always select the smallest node among the current heads of the lists.
    // - Store ListNode objects in the heap (not just values) to track their position in the lists.
    // - A dummy node simplifies the construction of the merged list by avoiding special cases for the head.
    // - Handle edge cases: empty array, all lists empty, or some lists empty.
    // - The heap contains at most k nodes at any time (one from each list), optimizing space.
    // - The lists are sorted in ascending order, so the heap ensures the merged list is sorted.
    // - The algorithm reuses existing nodes without creating new ones, except for the dummy node.

    // Time Complexity: O(N * log k)
    // - N is the total number of nodes across all lists, and k is the number of lists.
    // - Initializing the heap with up to k nodes (one per list) takes O(k * log k).
    // - Each node is inserted into and removed from the heap once, with each heap operation
    //   taking O(log k).
    // - Processing all N nodes involves N insertions and N removals, totaling O(N * log k).
    // - Other operations (pointer updates, list traversal) are O(1) per node.
    // - Total time is O(N * log k).

    // Space Complexity: O(k)
    // - The min-heap stores at most k nodes at any time (one from each list), so O(k) space.
    // - The dummy node and merge pointer use O(1) extra space.
    // - The output list reuses existing nodes from the input lists, so it’s not counted in
    //   extra space (required by the problem).
    // - Total extra space is O(k), excluding the output.

    public ListNode mergeKLists(ListNode[] lists) {
        // Handle base case: if array is null or empty, return null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Initialize a min-heap to store nodes, sorted by their values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head node of each non-null list to the min-heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // Initialize a dummy node to simplify result list construction
        ListNode dummy = new ListNode(0); // Value 0 is arbitrary, as dummy.next is returned
        // Pointer to build the merged list
        ListNode merge = dummy;

        // Process nodes from the min-heap until it’s empty
        while (!minHeap.isEmpty()) {
            // Extract the node with the smallest value
            ListNode smallest = minHeap.remove();
            // Append it to the result list
            merge.next = smallest;
            // Move the merge pointer to the newly added node
            merge = merge.next;

            // If the extracted node has a next node, add it to the heap
            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        // Return the head of the merged list (skip dummy node)
        return dummy.next;
    }
}