class Solution {
    // Problem: Merge Two Sorted Lists
    // Given the heads of two sorted singly linked lists list1 and list2, merge them into a
    // single sorted linked list in ascending order and return the head of the merged list.
    // Each list is sorted in non-decreasing order, may be empty, and contains integer values.

    // DSA Pattern: Two-Pointer Linked List Merging
    // This problem is solved by comparing nodes from both lists and building a new sorted
    // linked list by selecting the smaller value at each step. A dummy node simplifies the
    // construction of the merged list, and a pointer tracks the current node being built.
    // After processing both lists, any remaining nodes from either list are appended.

    // Approach:
    // 1. Initialize a dummy node to simplify list construction and a pointer (merge) to
    //    build the merged list.
    // 2. While both list1 and list2 have nodes:
    //    - Compare the values of the current nodes in list1 and list2.
    //    - Append the node with the smaller value to the merged list.
    //    - Advance the pointer of the list from which the node was taken.
    //    - Move the merge pointer to the newly added node.
    // 3. After the loop, append any remaining nodes from list1 or list2 (if any).
    // 4. Return the next node of the dummy node as the head of the merged list.

    // Key Points to Remember:
    // - Use a dummy node to avoid special handling for the head of the merged list.
    // - Compare node values to ensure the merged list remains sorted in ascending order.
    // - Handle cases where one list is exhausted before the other by appending remaining nodes.
    // - Edge cases include empty lists (list1 or list2 is null) or lists of different lengths.
    // - The lists are sorted in non-decreasing order, so equal values can be handled by
    //   choosing either node (here, list1 is chosen when values are equal).
    // - The algorithm modifies pointers but reuses existing nodes without creating new ones.
    // - The result is a single sorted linked list containing all nodes from both inputs.

    // Time Complexity: O(N + M)
    // - N and M are the lengths of list1 and list2, respectively.
    // - The algorithm processes each node in both lists exactly once, comparing and linking them.
    // - Each operation (comparisons, pointer updates) is O(1).
    // - Total time is O(N + M), where N + M is the total number of nodes.

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (dummy node and merge pointer).
    // - The merged list is constructed by reusing nodes from list1 and list2, with no additional
    //   data structures.
    // - The output (returned head) is required by the problem.

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Initialize a dummy node to simplify merged list construction
        ListNode dummy = new ListNode(0); // Value 0 is arbitrary, as dummy.next is returned
        // Pointer to build the merged list
        ListNode merge = dummy;

        // Process both lists while both have nodes
        while (list1 != null && list2 != null) {
            // Compare values and append the smaller node to the merged list
            if (list1.val <= list2.val) {
                merge.next = list1; // Append node from list1
                list1 = list1.next; // Move to the next node in list1
            } else {
                merge.next = list2; // Append node from list2
                list2 = list2.next; // Move to the next node in list2
            }
            // Move the merge pointer to the newly added node
            merge = merge.next;
        }

        // Append any remaining nodes from list1 or list2
        merge.next = (list1 != null) ? list1 : list2;

        // Return the head of the merged list (skip dummy node)
        return dummy.next;
    }
}