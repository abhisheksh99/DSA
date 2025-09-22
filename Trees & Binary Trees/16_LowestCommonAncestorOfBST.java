// Problem: Lowest Common Ancestor of a Binary Tree
// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. The LCA is defined
// between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node
// to be a descendant of itself). All Node.val are unique in the tree.

// DSA Pattern: Recursion / Divide and Conquer
// This problem is solved using a recursive approach by traversing the tree and checking if both nodes are in
// different subtrees or if one is the current node, determining the LCA at each level.

// Approach:
// 1. If the current node is null, return null (base case: no common ancestor found).
// 2. If the current node is either p or q, return the current node (one of the target nodes is found).
// 3. Recursively search for p and q in the left and right subtrees.
// 4. If both left and right subtrees return non-null results, the current node is the LCA (p and q are in different subtrees).
// 5. If only one subtree returns a non-null result, return that result (both p and q are in the same subtree).
// 6. Return null if neither subtree contains p or q.

// Key Points to Remember:
// - The LCA is the first common ancestor encountered when traversing from the root to both nodes.
// - The recursive approach leverages the divide-and-conquer strategy: check current node, then recurse on subtrees.
// - If both target nodes are found in different subtrees, the current node is their lowest common ancestor.
// - If one target node is the current node, it serves as the LCA regardless of where the other node is.
// - Edge cases:
//   - Empty tree (null root): return null.
//   - One node is the root: return the root if the other node is in its subtree.
//   - p or q is the LCA: return that node.
// - The solution assumes unique node values and that p and q exist in the tree.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single traversal, visiting each node at most once.
// - Each recursive call involves constant-time operations (null checks and comparisons).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [2, 10^5].
// - -10^9 <= Node.val, p.val, q.val <= 10^9
// - All Node.val are unique.
// - p != q
// - p and q exist in the tree.

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: empty tree has no LCA
        if (root == null) return null;
        
        // If current node is p or q, return it (LCA found)
        if (root == p || root == q) return root;
        
        // Recursively search for p and q in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // If both subtrees contain one of the target nodes, current node is LCA
        if (left != null && right != null) return root;
        
        // Return the non-null result (both nodes in one subtree) or null
        return left == null ? right : left;
    }
}

