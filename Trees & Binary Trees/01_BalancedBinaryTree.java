// Problem: Balanced Binary Tree
// Given a binary tree, determine if it is height-balanced. A height-balanced binary tree is a binary tree in which
// the depth of the two subtrees of every node never differs by more than one.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by computing the height of each subtree and checking if the
// difference in heights of left and right subtrees is at most 1 for every node.

// Approach:
// 1. If the root is null, return true (an empty tree is balanced).
// 2. For each node:
//    - Compute the height of the left subtree using a helper function getHeight.
//    - Compute the height of the right subtree using getHeight.
//    - Check if the absolute difference in heights is greater than 1; if so, return false.
//    - Recursively check if the left and right subtrees are balanced.
// 3. The getHeight helper function:
//    - Returns 0 for a null node.
//    - Recursively computes the height of left and right subtrees.
//    - Returns the maximum of the two heights plus 1.
// 4. Return true if all nodes satisfy the balance condition, false otherwise.

// Key Points to Remember:
// - A tree is balanced if the height difference between left and right subtrees of every node is at most 1.
// - The height of a node is the number of edges on the longest path from the node to a leaf.
// - Recursion ensures that every node is checked for balance and height difference.
// - Edge cases:
//   - Empty tree (null root): return true.
//   - Single node: return true (no subtrees to compare).
//   - Unbalanced subtree: detected by height difference or recursive checks.
// - The solution assumes a standard TreeNode class with left and right child pointers.

// Time Complexity: O(n log n) in the worst case, where n is the number of nodes
// - For each node, getHeight is called, which traverses to the leaves, taking O(n) time.
// - The isBalanced function is called for each node, leading to O(n) calls.
// - In a balanced tree, the height is O(log n), so the total time is O(n log n).
// - In a skewed tree, the time approaches O(n^2) due to repeated height calculations.

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for getHeight and isBalanced can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [0, 10^4].
// - -10^8 <= Node.val <= 10^8

public class Solution {
    public boolean isBalanced(TreeNode root) {
        // Handle base case: empty tree is balanced
        if (root == null) {
            return true;
        }
        
        // Compute heights of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        
        // Check if height difference exceeds 1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        // Recursively check if left and right subtrees are balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    // Helper function to compute the height of a subtree
    private int getHeight(TreeNode node) {
        // Base case: null node has height 0
        if (node == null) {
            return 0;
        }
        
        // Recursively compute heights of left and right subtrees
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        
        // Return the maximum height plus 1
        return Math.max(leftHeight, rightHeight) + 1;
    }
}