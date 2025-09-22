class Solution {
    // Problem: Reorder List
    // Given the head of a singly linked list, reorder it in-place such that the nodes are
    // rearranged as L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ..., where L0 is the first node,
    // Ln is the last node, L1 is the second node, and so on. The list may be empty or
    // contain one or more nodes.

    // DSA Pattern: Linked List Manipulation (Two-Pointer and Reversal)
    // This problem is solved in three steps: (1) find the middle of the list using the
    // slow-fast pointer technique, (2) reverse the second half of the list, and (3) merge
    // the first half and the reversed second half alternately. The reordering is done
    // in-place by adjusting pointers, ensuring the nodes are interleaved as required.

    // Approach:
    // 1. Handle the base case: if the list is empty or has one node, return as no reordering is needed.
    // 2. Find the middle of the list using slow and fast pointers:
    //    - Slow moves one step, fast moves two steps; when fast reaches the end, slow is at the middle.
    // 3. Reverse the second half of the list (starting from slow):
    //    - Use three pointers (prev, curr, temp) to reverse links iteratively.
    // 4. Merge the first half (from head) and the reversed second half (from prev) alternately:
    //    - Take a node from the first half, then a node from the second half, and adjust pointers.
    //    - Continue until the second half is exhausted (second.next == null).
    // 5. The list is modified in-place, and no return is needed (void method).

    // Key Points to Remember:
    // - The slow-fast pointer technique finds the middle node; for even-length lists, slow points
    //   to the first node of the second half.
    // - Reversing the second half ensures the last node becomes the head of the second half.
    // - The merge step interleaves nodes: first node from the first half, first node from the
    //   reversed second half, and so on.
    // - Use temporary pointers to preserve next nodes during reversal and merging.
    // - Edge cases include empty lists, single-node lists, or lists with two nodes.
    // - The problem guarantees no cycles, and the list can be modified in-place.
    // - The loop in the merge step stops when second.next is null to avoid redundant linking.

    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - Finding the middle takes O(n/2) = O(n) steps using the slow-fast pointer technique.
    // - Reversing the second half takes O(n/2) = O(n) steps.
    // - Merging the two halves takes O(n/2) = O(n) steps.
    // - Each operation (pointer updates) is O(1).
    // - Total time is O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (pointers: slow, fast, prev,
    //   curr, temp, first, second).
    // - The list is modified in-place, with no additional data structures.
    // - The problem requires no output (void method).

    public void reorderList(ListNode head) {
        // Handle base case: empty list or single node requires no reordering
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the list using slow and fast pointers
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // Moves one step
            fast = fast.next.next; // Moves two steps
        }

        // Step 2: Reverse the second half of the list starting from slow
        ListNode prev = null, curr = slow, temp;
        while (curr != null) {
            // Save the next node
            temp = curr.next;
            // Reverse the link
            curr.next = prev;
            // Move prev and curr forward
            prev = curr;
            curr = temp;
        }

        // Step 3: Merge the first half (head) and reversed second half (prev) alternately
        ListNode first = head, second = prev;
        while (second.next != null) {
            // Save the next node of the first half
            temp = first.next;
            // Link the current node from the first half to the second half
            first.next = second;
            // Move to the next node in the first half
            first = temp;

            // Save the next node of the second half
            temp = second.next;
            // Link the current node from the second half to the first half
            second.next = first;
            // Move to the next node in the second half
            second = temp;
        }
    }
}