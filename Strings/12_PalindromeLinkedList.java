/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // Problem: Palindrome Linked List
    // Given the head of a singly linked list, determine if it is a palindrome. A linked list
    // is a palindrome if it reads the same forward and backward (e.g., 1->2->2->1 is a palindrome).

    // DSA Pattern: Two Pointers with Array
    // This solution converts the linked list to an ArrayList to store node values, then uses
    // a two-pointer technique to check if the list is a palindrome by comparing elements from
    // both ends. The array conversion simplifies palindrome checking but uses extra space.

    // Approach:
    // 1. Initialize an ArrayList to store the values of the linked list nodes.
    // 2. Traverse the linked list, adding each node's value to the ArrayList.
    // 3. Use two pointers (left at start, right at end) to compare elements:
    //    - While left < right, check if values at left and right match.
    //    - If they match, move left forward and right backward.
    //    - If they don't match, the loop will exit early.
    // 4. Return true if left >= right (all elements matched), false otherwise.

    // Key Points to Remember:
    // - Convert the linked list to an ArrayList for easier palindrome checking.
    // - Use two pointers to compare elements from both ends, similar to string palindrome checks.
    // - Handle edge cases: empty list (valid palindrome), single node (valid), or odd/even length lists.
    // - The condition left >= right accounts for both odd and even-length lists:
    //   - Odd length: pointers meet at the middle (left == right).
    //   - Even length: pointers cross (left > right).
    // - This approach trades space for simplicity; an O(1) space solution exists (e.g., reverse second half).

    // Time Complexity: O(n)
    // - Traversing the linked list to populate the ArrayList takes O(n), where n is the number of nodes.
    // - Checking the palindrome with two pointers takes O(n/2) = O(n).
    // - ArrayList operations (add, get) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(n)
    // - The ArrayList stores all n node values, using O(n) space.
    // - Other variables (left, right) use O(1) space.
    // - Overall, the space complexity is O(n).

    public boolean isPalindrome(ListNode head) {
        // Initialize ArrayList to store linked list values
        List<Integer> list = new ArrayList<>();
        
        // Traverse linked list and add values to ArrayList
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        
        // Initialize two pointers for palindrome check
        int left = 0;
        int right = list.size() - 1;
        
        // Compare elements from both ends
        while (left < right && list.get(left) == list.get(right)) {
            left++;
            right--;
        }
        
        // Return true if all elements matched (left >= right), false otherwise
        return left >= right;
    }
}