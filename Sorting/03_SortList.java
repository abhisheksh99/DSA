public class Solution {
    // Problem: Sort List
    // Given the head of a linked list, sort the list in ascending order and return the sorted
    // list. The list contains nodes with integer values, and the sorting must be done in
    // O(n log n) time complexity with O(1) extra space (excluding recursion stack space).
    // The linked list may be empty or contain one or more nodes.

    // DSA Pattern: Divide and Conquer (Merge Sort)
    // This problem is solved using a merge sort algorithm adapted for linked lists. The list
    // is recursively split into two halves until single nodes or empty lists remain, then
    // sorted halves are merged back together. The merge process combines two sorted lists
    // into a single sorted list, and the splitting uses a slow-fast pointer technique to
    // find the middle of the list efficiently.

    // Approach:
    // 1. Handle base cases: if the list is empty (head == null) or has one node (head.next == null),
    //    return the head as it is already sorted.
    // 2. Split the list into two halves:
    //    - Use a slow-fast pointer technique to find the middle node.
    //    - Break the list into two parts by setting the next pointer of the node before the
    //      middle to null.
    // 3. Recursively sort the left half (from head to before middle) and the right half
    //    (from middle to end).
    // 4. Merge the two sorted halves into a single sorted list using a dummy node to simplify
    //    pointer manipulation.
    // 5. Return the head of the sorted list.

    // Key Points to Remember:
    // - Merge sort is used to achieve O(n log n) time complexity, suitable for linked lists.
    // - The slow-fast pointer technique (one step vs. two steps) finds the middle node efficiently.
    // - Splitting the list requires breaking the link before the middle node.
    // - Merging two sorted lists involves comparing node values and building a new list.
    // - A dummy node simplifies the merge process by avoiding edge cases for the head.
    // - Handle remaining nodes in either list during merging by appending them directly.
    // - The algorithm modifies the list in-place, except for the recursion stack.

    // Time Complexity: O(n log n)
    // - n is the number of nodes in the linked list.
    // - Splitting the list into halves takes O(log n) levels of recursion.
    // - At each level, the getMid function traverses O(n) nodes to find the middle, and the
    //   merge function processes O(n) nodes to combine the lists.
    // - Total time is O(n) per level * O(log n) levels = O(n log n).
    // - Base case checks are O(1).

    // Space Complexity: O(log n)
    // - The recursion stack for the divide-and-conquer approach uses O(log n) space due to
    //   the recursive calls for splitting the list.
    // - The merge function uses O(1) extra space (excluding the output) with a dummy node
    //   and pointers.
    // - No additional data structures grow with input size, but the recursion stack is
    //   considered in space complexity.
    // - The problem requires in-place sorting, and the output list reuses existing nodes.

    public ListNode sortList(ListNode head) {
        // Handle base cases: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves by finding the middle
        ListNode mid = getMid(head);
        // Recursively sort the first half (from head to before middle)
        ListNode left = sortList(head);
        // Recursively sort the second half (from middle to end)
        ListNode right = sortList(mid);
        // Merge the sorted halves
        return merge(left, right);
    }

    // Function to find the middle of the list using slow-fast pointer technique
    private ListNode getMid(ListNode head) {
        // Initialize prev to track the node before the middle
        ListNode prev = null;
        // Use slow (prev) and fast (head) pointers
        while (head != null && head.next != null) {
            // Move prev one step (or set to head initially)
            prev = (prev == null) ? head : prev.next;
            // Move head two steps
            head = head.next.next;
        }
        // Middle node is the next node after prev
        ListNode mid = prev.next;
        // Break the list into two halves by setting prev.next to null
        prev.next = null;
        return mid;
    }

    // Function to merge two sorted lists into one sorted list
    private ListNode merge(ListNode list1, ListNode list2) {
        // Initialize a dummy node to simplify merging
        ListNode dummy = new ListNode(0);
        // Tail pointer to build the merged list
        ListNode tail = dummy;

        // Compare nodes from both lists and merge in sorted order
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                // Add smaller node from list1 to the result
                tail.next = list1;
                list1 = list1.next;
            } else {
                // Add smaller or equal node from list2 to the result
                tail.next = list2;
                list2 = list2.next;
            }
            // Move tail to the newly added node
            tail = tail.next;
        }

        // Append any remaining nodes from list1 or list2
        tail.next = (list1 != null) ? list1 : list2;

        // Return the head of the merged list (skip dummy node)
        return dummy.next;
    }
}