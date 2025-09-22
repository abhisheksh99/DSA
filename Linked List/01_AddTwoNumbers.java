
class Solution {
    // Problem: Add Two Numbers
    // Given two non-empty linked lists l1 and l2 representing non-negative integers, where
    // each node contains a single digit and the digits are stored in reverse order, add the
    // two numbers and return the sum as a linked list in the same format. The numbers do not
    // contain leading zeros, except for the number 0 itself.

    // DSA Pattern: Linked List Traversal and Arithmetic
    // This problem is solved by simulating the addition of two numbers digit by digit, similar
    // to manual addition. We traverse both linked lists simultaneously, summing corresponding
    // digits along with any carry from the previous addition. A dummy node is used to simplify
    // the construction of the result list, and a carry variable tracks overflow for each digit.

    // Approach:
    // 1. Initialize a dummy node to simplify list construction and a pointer (ans) to build
    //    the result list.
    // 2. Initialize a carry variable to handle overflow from digit sums.
    // 3. While there are digits in l1, l2, or a non-zero carry:
    //    - Get the current digit from l1 (or 0 if l1 is null).
    //    - Get the current digit from l2 (or 0 if l2 is null).
    //    - Compute the sum = carry + x + y.
    //    - Update carry = sum / 10 (integer division for carry-over).
    //    - Create a new node with sum % 10 (current digit) and append it to the result.
    //    - Move to the next nodes in l1 and l2 if available.
    // 4. Return the next node of the dummy node as the head of the result list.

    // Key Points to Remember:
    // - The digits are stored in reverse order, making it easier to process from least to most
    //   significant digit.
    // - Use a dummy node to avoid special handling for the head of the result list.
    // - Handle cases where lists are of different lengths by using 0 for missing digits.
    // - The carry must be processed even after both lists are exhausted (e.g., for cases like
    //   999 + 1 = 1000).
    // - Each node in the result contains a single digit (0–9), computed as sum % 10.
    // - The problem assumes non-empty lists and non-negative integers.
    // - Edge cases include lists of different lengths or cases with carry propagation.

    // Time Complexity: O(max(N, M))
    // - N and M are the lengths of the input linked lists l1 and l2, respectively.
    // - The algorithm traverses each list at most once, processing each node in the longer list.
    // - Each operation (summing digits, updating carry, creating nodes) is O(1).
    // - Total time is O(max(N, M)), accounting for the longer list and any final carry.

    // Space Complexity: O(max(N, M))
    // - The space is used for the output linked list, which has at most max(N, M) + 1 nodes
    //   (due to a possible carry creating an extra digit, e.g., 999 + 1 = 1000).
    // - Excluding the output, the algorithm uses O(1) extra space (dummy node, ans pointer,
    //   carry, and temporary variables).
    // - The output list is required by the problem.

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Initialize a dummy node to simplify result list construction
        ListNode dummy = new ListNode(0);
        // Pointer to build the result list
        ListNode ans = dummy;
        // Initialize carry to handle overflow from digit sums
        int carry = 0;

        // Continue while there are digits in l1, l2, or a non-zero carry
        while (l1 != null || l2 != null || carry != 0) {
            // Get digit from l1 (or 0 if l1 is null)
            int x = (l1 != null) ? l1.val : 0;
            // Get digit from l2 (or 0 if l2 is null)
            int y = (l2 != null) ? l2.val : 0;
            // Compute sum of digits and carry
            int sum = carry + x + y;

            // Update carry for the next digit
            carry = sum / 10;

            // Create a new node with the current digit (sum % 10)
            ans.next = new ListNode(sum % 10);
            // Move to the next node in the result list
            ans = ans.next;

            // Move to the next node in l1 if available
            if (l1 != null) {
                l1 = l1.next;
            }
            // Move to the next node in l2 if available
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // Return the head of the result list (skip dummy node)
        return dummy.next;
    }
}