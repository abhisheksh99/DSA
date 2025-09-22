class Solution {
    // Problem: Reverse Nodes in k-Group
    // Given the head of a linked list and an integer k, reverse every k nodes in the list.
    // If the number of nodes is not a multiple of k, the remaining nodes should remain as is.
    // The linked list may be empty or contain one or more nodes, and k is a positive integer.
    // DSA Pattern: Linked List Manipulation
    // This problem is solved using an iterative approach that reverses groups of k nodes
    // while maintaining proper connections between groups. A dummy node is used to simplify
    // edge cases, and pointers are updated to reverse each group in-place.
    // Approach:
    // 1. Use a dummy node to handle edge cases (e.g., reversing the first group).
    // 2. Count the total number of nodes to determine how many k-groups can be reversed.
    // 3. For each k-group:
    //    - Identify the current group’s start (curr) and next node (nex).
    //    - Reverse k nodes by updating pointers iteratively.
    //    - Connect the reversed group to the previous and next parts of the list.
    //    - Move the pre pointer to the end of the reversed group for the next iteration.
    // 4. Repeat until there are fewer than k nodes left.
    // Key Points to Remember:
    // - The dummy node simplifies handling the head of the list.
    // - Count nodes first to ensure only groups of k nodes are reversed.
    // - Use three pointers (pre, curr, nex) to track positions during reversal.
    // - Each reversal updates pointers to reverse the links within the group.
    // - The remaining nodes (less than k) are left unchanged.
    // - Edge cases include empty lists, k=1, or lists shorter than k.
    // - The algorithm modifies the list in-place.
    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - Counting nodes takes O(n).
    // - Each node is processed once during reversal (O(n)).
    // - Total time is O(n).
    // Space Complexity: O(1)
    // - Only a constant amount of extra space is used for pointers (dummy, pre, curr, nex).
    // - The list is modified in-place, and no recursion or additional data structures are used.
    public ListNode reverseKGroup(ListNode head, int k) {
        // Base cases: if list is empty or k=1, no reversal needed
        if (head == null || k == 1) {
            return head;
        }
        
        // Initialize dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Initialize pointers: pre (before group), curr (current group start), nex (next node)
        ListNode pre = dummy, curr = dummy, nex = dummy;
        int count = 0;
        
        // Count total number of nodes
        while (curr.next != null) {
            curr = curr.next;
            count++;
        }
        
        // Process each k-group while there are enough nodes
        while (count >= k) {
            curr = pre.next; // Start of current group
            nex = curr.next; // Next node to process
            
            // Reverse k nodes
            for (int i = 1; i < k; i++) {
                curr.next = nex.next; // Disconnect current node
                nex.next = pre.next;  // Insert nex at the start of the group
                pre.next = nex;       // Update group's head
                nex = curr.next;      // Move to next node
            }
            
            // Move pre to the end of the reversed group
            pre = curr;
            count -= k; // Reduce count by k for processed nodes
        }
        
        // Return the head of the modified list
        return dummy.next;
    }
}