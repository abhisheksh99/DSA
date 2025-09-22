// Problem: Same Tree
// Given the roots of two binary trees 'p' and 'q', determine if they are the same tree. Two trees are considered
// the same if they have the same structure and the nodes have identical values.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by comparing corresponding nodes of both trees and ensuring
// their values and subtrees are identical.

// Approach:
// 1. If both nodes are null, return true (base case: empty trees are the same).
// 2. If one node is null and the other is not, return false (different structures).
// 3. If the values of the current nodes differ, return false (different values).
// 4. Recursively check if the left subtrees and right subtrees are the same.
// 5. Return true only if both subtrees are identical and the current nodes have the same value.

// Key Points to Remember:
// - Two trees are the same if they have identical structures (same shape) and identical node values.
// - The recursive approach ensures that all corresponding nodes are compared for value and structure.
// - Edge cases:
//   - Both trees empty (null roots): return true.
//   - One tree empty, other not: return false.
//   - Same structure but different values: return false.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the minimum number of nodes in the two trees
// - The algorithm visits each node in both trees exactly once, stopping early if a mismatch is found.
// - Each comparison involves constant-time operations (null checks and value comparisons).

// Space Complexity: O(h), where h is the height of the shorter tree
// - The recursion stack can go as deep as the height of the shorter tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in both trees is in the range [0, 100].
// - -100 <= Node.val <= 100

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: both null nodes mean trees are identical
        if (p == null && q == null) {
            return true;
        }
        
        // Base case: one null node means trees differ
        if (p == null || q == null) {
            return false;
        }
        
        // Check if current nodes have different values
        if (p.val != q.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

