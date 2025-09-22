class Solution {
    // Problem: Reverse Linked List
    // Given the head of a singly linked list, reverse the list and return the new head.
    // The linked list may be empty or contain one or more nodes, and each node contains
    // an integer value.

    // DSA Pattern: Iterative Linked List Reversal
    // This problem is solved iteratively by adjusting the next pointers of each node to
    // point to the previous node, effectively reversing the list's direction. We use three
    // pointers (prev, curr, and temp) to keep track of the previous node, current node, and
    // next node during traversal, updating links in-place to achieve the reversal.

    // Approach:
    // 1. Initialize two pointers:
    //    - prev: starts as null, will eventually become the new head.
    //    - curr: starts at the head of the list.
    // 2. While curr is not null:
    //    - Save the next node (temp = curr.next) to avoid losing the rest of the list.
    //    - Reverse the link by setting curr.next to prev.
    //    - Move prev to curr (prev becomes the current node).
    //    - Move curr to temp (advance to the next node).
    // 3. After the loop, prev points to the new head of the reversed list.
    // 4. Return prev as the head of the reversed list.

    // Key Points to Remember:
    // - The iterative approach reverses the list in-place by adjusting pointers.
    // - Use temp to store the next node before modifying curr.next to prevent losing the list.
    // - The prev pointer starts as null to handle the new tail (original head) correctly.
    // - Handle edge cases: empty list (return null) or single node (return head unchanged).
    // - The algorithm processes each node exactly once, updating its next pointer.
    // - The final head is prev, as it points to the last node processed (original tail).

    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - The algorithm traverses the list once, processing each node exactly once.
    // - Each operation (pointer updates) within the loop is O(1).
    // - Total time is O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (three pointers: prev, curr,
    //   and temp).
    // - The list is modified in-place, with no additional data structures.
    // - The output (returned head) is required by the problem.

    public ListNode reverseList(ListNode head) {
        // Initialize prev as null (will become the new head) and curr as the current head
        ListNode prev = null;
        ListNode curr = head;

        // Traverse the list until curr reaches the end
        while (curr != null) {
            // Save the next node to avoid losing the rest of the list
            ListNode temp = curr.next;
            // Reverse the link by pointing curr.next to the previous node
            curr.next = prev;
            // Move prev to the current node
            prev = curr;
            // Move curr to the next node (saved in temp)
            curr = temp;
        }

        // Return prev, which is the new head of the reversed list
        return prev;
    }
}