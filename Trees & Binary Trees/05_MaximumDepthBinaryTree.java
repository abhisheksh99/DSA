// Problem: Maximum Depth of Binary Tree
// Given the root of a binary tree, return its maximum depth. The maximum depth is the number of nodes along the
// longest path from the root node to the farthest leaf node.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by computing the depth of the left and right subtrees and
// returning the maximum of the two plus one for the current node.

// Approach:
// 1. If the root is null, return 0 (base case for an empty tree).
// 2. Recursively compute the depth of the left subtree.
// 3. Recursively compute the depth of the right subtree.
// 4. Return the maximum of the left and right depths plus 1 (to account for the current node).
// 5. The recursion processes all nodes to find the longest path to a leaf.

// Key Points to Remember:
// - The depth of a tree is the number of nodes in the longest path from the root to a leaf.
// - Each recursive call computes the depth of a subtree, and the maximum is taken at each node.
// - Edge cases:
//   - Empty tree (null root): return 0.
//   - Single node: return 1 (no children, depth is 1).
//   - Skewed tree: depth equals the number of nodes in the single path.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm visits each node exactly once during the recursive traversal.
// - Each node involves constant-time operations (max comparison and addition).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [0, 10^4].
// - -100 <= Node.val <= 100

public class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: return 0 for empty tree
        if (root == null) {
            return 0;
        }
        
        // Recursively compute depths of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Return the maximum depth plus 1 for the current node
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

