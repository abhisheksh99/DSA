class Solution {
    // Problem: Linked List Cycle
    // Given the head of a singly linked list, determine if the list contains a cycle.
    // A cycle exists if a node can be reached again by following the next pointers.
    // Return true if there is a cycle, otherwise return false. The linked list may be
    // empty or contain one or more nodes.

    // DSA Pattern: Floyd’s Cycle-Finding Algorithm (Two-Pointer Technique)
    // This problem is solved using Floyd’s Tortoise and Hare algorithm, which uses two
    // pointers moving at different speeds (slow moves one step, fast moves two steps).
    // If the pointers meet, a cycle exists; otherwise, the fast pointer will reach the
    // end of the list, indicating no cycle. This approach detects cycles efficiently
    // without using extra space.

    // Approach:
    // 1. Handle the base case: if the list is empty (head == null), return false as no cycle is possible.
    // 2. Initialize two pointers:
    //    - slow: moves one node at a time.
    //    - fast: moves two nodes at a time.
    // 3. While fast and fast.next are not null (ensuring we can move fast two steps):
    //    - Move fast two steps and slow one step.
    //    - If fast meets slow (fast == slow), a cycle exists, so return true.
    // 4. If fast reaches the end (null or fast.next is null), no cycle exists, so return false.

    // Key Points to Remember:
    // - Floyd’s algorithm detects a cycle by exploiting the fact that the fast pointer will
    //   eventually meet the slow pointer inside a cycle if one exists.
    // - The fast pointer moves twice as fast, ensuring it catches up to slow in O(n) steps if a cycle is present.
    // - Check fast.next != null to avoid NullPointerException when moving fast two steps.
    // - The algorithm handles edge cases: empty list, single node, or no cycle.
    // - No cycle exists if the fast pointer reaches the end of the list.
    // - The solution uses constant extra space, making it efficient for large lists.
    // - The problem assumes the list nodes contain integer values, but the cycle detection
    //   only depends on the next pointers.

    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - If there is no cycle, the fast pointer reaches the end in O(n) steps.
    // - If there is a cycle, the fast pointer catches the slow pointer in O(n) steps, as the
    //   relative speed is one step per iteration, and the cycle length is at most n.
    // - Each operation (pointer updates, comparisons) is O(1).
    // - Total time is O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only two pointers (slow and fast), regardless of list size.
    // - No additional data structures are used, and the list is not modified.
    // - The output (boolean result) is required by the problem.

    public boolean hasCycle(ListNode head) {
        // Handle base case: empty list has no cycle
        if (head == null) {
            return false;
        }

        // Initialize two pointers: slow moves one step, fast moves two steps
        ListNode fast = head;
        ListNode slow = head;

        // Traverse the list while fast and fast.next are not null
        while (fast != null && fast.next != null) {
            // Move fast pointer two steps
            fast = fast.next.next;
            // Move slow pointer one step
            slow = slow.next;

            // If fast meets slow, a cycle exists
            if (fast == slow) {
                return true;
            }
        }

        // If fast reaches the end, no cycle exists
        return false;
    }
}