// Problem: Symmetric Tree
// Given the root of a binary tree, determine if it is a mirror of itself (i.e., symmetric around its center).
// A tree is symmetric if the left and right subtrees are mirror images of each other.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by comparing the left and right subtrees to check if they are
// mirror images, ensuring that corresponding nodes have equal values and mirrored structures.

// Approach:
// 1. If the root is null, return true (an empty tree is symmetric).
// 2. Use a helper function isMirror to compare two subtrees (left and right):
//    - If both nodes are null, return true (symmetric base case).
//    - If one node is null and the other is not, return false (asymmetric).
//    - Check if the current nodes have equal values and their subtrees are mirrored:
//      - Left node's left subtree mirrors right node's right subtree.
//      - Left node's right subtree mirrors right node's left subtree.
// 3. Call isMirror with the root's left and right children and return the result.

// Key Points to Remember:
// - A tree is symmetric if its left and right subtrees are mirror images, meaning:
//   - Corresponding nodes have the same value.
//   - The left subtree of one mirrors the right subtree of the other, and vice versa.
// - The recursive approach ensures that all corresponding pairs of nodes are compared.
// - Edge cases:
//   - Empty tree (null root): return true.
//   - Single node with no children: return true.
//   - Unbalanced or unequal subtrees: return false.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm visits each node exactly once during the recursive comparison of the left and right subtrees.
// - Each comparison involves constant-time operations (null checks and value comparisons).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for isMirror can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [0, 1000].
// - -100 <= Node.val <= 100

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        // Handle base case: empty tree is symmetric
        if (root == null) return true;
        
        // Check if left and right subtrees are mirrors
        return isMirror(root.left, root.right);
    }
    
    // Helper function to check if two subtrees are mirrors
    private boolean isMirror(TreeNode left, TreeNode right) {
        // Base case: both null nodes are symmetric
        if (left == null && right == null) return true;
        // Base case: one null node means asymmetry
        if (left == null || right == null) return false;
        
        // Check if current nodes have same value and subtrees are mirrored
        return (left.val == right.val)
            && isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}

