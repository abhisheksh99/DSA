// Problem: Invert Binary Tree
// Given the root of a binary tree, invert the tree by swapping every left and right child of all nodes,
// and return the root of the inverted tree.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by swapping the left and right children of each node
// and recursively inverting the left and right subtrees.

// Approach:
// 1. If the root is null, return null (base case).
// 2. Recursively invert the right subtree and store the result.
// 3. Recursively invert the left subtree and store the result.
// 4. Swap the left and right children of the current node.
// 5. Return the root of the inverted tree.

// Key Points to Remember:
// - Inverting a binary tree means swapping the left and right child pointers for every node.
// - The recursive approach ensures that all nodes in the tree are processed, from leaves to the root.
// - The structure of the tree remains the same, but left and right children are swapped.
// - Edge cases:
//   - Empty tree (null root): return null.
//   - Single node: return the node (no children to swap).
//   - Leaf nodes: swapping null children has no effect.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm visits each node exactly once to swap its children.
// - Each node involves constant-time operations (swapping pointers).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [0, 100].
// - -100 <= Node.val <= 100

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base case: return null for empty tree
        if (root == null) {
            return null;
        }
        
        // Recursively invert right and left subtrees
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        
        // Swap left and right children
        root.left = right;
        root.right = left;
        
        // Return the root of the inverted tree
        return root;
    }
}

