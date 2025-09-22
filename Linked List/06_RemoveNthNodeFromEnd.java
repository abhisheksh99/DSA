class Solution {
    // Problem: Remove Nth Node From End of List
    // Given the head of a singly linked list and an integer n, remove the nth node from
    // the end of the list and return the head of the modified list. The list has at least
    // n nodes, and n is a positive integer. The list may contain one or more nodes.

    // DSA Pattern: Two-Pointer Technique (Fast and Slow Pointers)
    // This problem is solved using two pointers (front and back) to locate the node to be
    // removed. By maintaining a gap of n nodes between the pointers and moving them together,
    // the back pointer will point to the node just before the one to be removed when the front
    // pointer reaches the end. A dummy node simplifies edge cases, such as removing the head.

    // Approach:
    // 1. Initialize a dummy node pointing to the head to handle edge cases (e.g., removing
    //    the first node).
    // 2. Initialize two pointers, front and back, starting at the dummy node.
    // 3. Move the front pointer n+1 steps ahead to create a gap of n nodes between front and back.
    // 4. Move both pointers one step at a time until front reaches the end (null).
    //    - When front is null, back points to the node just before the nth node from the end.
    // 5. Remove the nth node by updating back.next to skip the next node (back.next = back.next.next).
    // 6. Return dummy.next as the head of the modified list.

    // Key Points to Remember:
    // - The dummy node simplifies handling edge cases, such as removing the head node.
    // - The front pointer moves n+1 steps initially to ensure the gap is correct (n nodes
    //   between back.next and front).
    // - When front reaches null, back.next is the node to remove, so back.next = back.next.next
    //   skips it.
    // - The problem guarantees n is valid (1 ≤ n ≤ length of list), so no additional validation
    //   is needed.
    // - Edge cases include lists with one node (n=1 removes the only node) or removing the head.
    // - The algorithm modifies the list in-place by adjusting pointers.
    // - The result is a valid linked list with the nth node from the end removed.

    // Time Complexity: O(L)
    // - L is the length of the linked list.
    // - Moving the front pointer n+1 steps takes O(n) time, where n ≤ L.
    // - Moving both pointers until front reaches null takes O(L - n) time.
    // - Total time is O(L), as we traverse the list at most once.
    // - Other operations (pointer updates) are O(1).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (dummy node, front, and back
    //   pointers).
    // - The list is modified in-place, with no additional data structures.
    // - The output (returned head) is required by the problem.

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Initialize a dummy node to handle edge cases (e.g., removing the head)
        ListNode dummy = new ListNode(0); // Value 0 is arbitrary, as dummy.next is returned
        dummy.next = head;

        // Initialize two pointers starting at the dummy node
        ListNode front = dummy;
        ListNode back = dummy;

        // Move front pointer n+1 steps ahead to create a gap of n nodes
        for (int i = 0; i <= n; i++) {
            front = front.next;
        }

        // Move both pointers until front reaches the end (null)
        while (front != null) {
            front = front.next;
            back = back.next;
        }

        // Remove the nth node from the end by skipping it
        back.next = back.next.next;

        // Return the head of the modified list (skip dummy node)
        return dummy.next;
    }
}