class Solution {
    // Problem: Middle of the Linked List
    // Given the head of a singly linked list, return the middle node of the list.
    // If there are two middle nodes (i.e., the list has an even number of nodes),
    // return the second middle node. The list may contain one or more nodes, and
    // is guaranteed to be non-empty.

    // DSA Pattern: Two-Pointer Technique (Slow and Fast Pointers)
    // This problem is solved using the slow and fast pointer technique (also known as
    // the tortoise and hare algorithm). The slow pointer moves one step at a time, while
    // the fast pointer moves two steps. When the fast pointer reaches the end of the list,
    // the slow pointer will be at the middle node. This approach efficiently finds the
    // middle in a single pass without needing to know the list's length.

    // Approach:
    // 1. Initialize two pointers, slow and fast, both starting at the head of the list.
    // 2. While fast is not null and fast.next is not null:
    //    - Move slow one step (slow = slow.next).
    //    - Move fast two steps (fast = fast.next.next).
    // 3. When the loop ends (fast is null or fast.next is null), slow points to the
    //    middle node.
    // 4. Return the slow pointer as the middle node.

    // Key Points to Remember:
    // - The fast pointer moves twice as fast as the slow pointer, ensuring slow reaches
    //   the middle when fast reaches the end.
    // - For even-length lists, the second middle node is returned (e.g., in [1,2,3,4],
    //   node 3 is returned).
    // - Check fast.next != null to avoid NullPointerException when moving fast two steps.
    // - The problem guarantees a non-empty list, so no null head check is needed.
    // - Edge cases include lists with one node (returns head) or two nodes (returns second node).
    // - The algorithm is in-place and does not modify the list structure.
    // - The solution handles both odd and even-length lists correctly.

    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - The fast pointer traverses the list at double speed, reaching the end in O(n/2) steps.
    // - Each operation (pointer updates) is O(1).
    // - Total time is O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only two pointers (slow and fast), regardless of list size.
    // - No additional data structures are used, and the list is not modified.
    // - The output (returned middle node) is required by the problem.

    public ListNode middleNode(ListNode head) {
        // Initialize slow and fast pointers starting at the head
        ListNode slow = head, fast = head;

        // Move slow one step and fast two steps until fast reaches the end
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer one step
            fast = fast.next.next; // Move fast pointer two steps
        }

        // Slow pointer is at the middle node
        return slow;
    }
}