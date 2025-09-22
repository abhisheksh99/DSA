// Problem: Validate Binary Search Tree
// Given the root of a binary tree, determine if it is a valid binary search tree (BST). A valid BST is defined as follows:
// - The left subtree of a node contains only nodes with values less than the node's value.
// - The right subtree of a node contains only nodes with values greater than the node's value.
// - Both the left and right subtrees must also be valid BSTs.

// DSA Pattern: Recursion / Inorder Traversal
// This problem is solved using an inorder traversal, which visits nodes in ascending order in a BST. By tracking the
// previously visited node's value, we can ensure each node's value is greater than the previous one.

// Approach:
// 1. Initialize a global variable 'prev' to track the value of the previously visited node (initially null).
// 2. Define a helper function 'inorder' that performs an inorder traversal:
//    - If the node is null, return true (base case: empty tree is a valid BST).
//    - Recursively validate the left subtree.
//    - Check if the current node's value is less than or equal to the previous node's value (if prev is not null);
//      if so, return false (violates BST property).
//    - Update prev to the current node's value.
//    - Recursively validate the right subtree.
// 3. Call the inorder function with the root and return the result.

// Key Points to Remember:
// - In a BST, an inorder traversal visits nodes in ascending order.
// - By comparing each node's value with the previous node's value, we ensure the BST property (left < node < right).
// - The prev variable tracks the last visited node's value to enforce strict inequality (node.val > prev).
// - Edge cases:
//   - Empty tree (null root): return true.
//   - Single node: return true (no subtrees to violate BST properties).
//   - Duplicate values: not allowed in a valid BST (node.val <= prev is false).
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single inorder traversal, visiting each node exactly once.
// - Each node involves constant-time operations (comparisons and assignments).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for inorder traversal can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - The global prev variable uses O(1) space.

// Constraints:
// - The number of nodes in the tree is in the range [1, 10^4].
// - -2^31 <= Node.val <= 2^31 - 1

public class Solution {
    // Global variable to track the previous node's value
    private Integer prev;
    
    public boolean isValidBST(TreeNode root) {
        // Initialize prev and start inorder traversal
        prev = null;
        return inorder(root);
    }
    
    // Helper function for inorder traversal
    public boolean inorder(TreeNode root) {
        // Base case: empty tree is valid
        if (root == null) {
            return true;
        }
        
        // Validate left subtree
        if (!inorder(root.left)) {
            return false;
        }
        
        // Check BST property: current value must be greater than previous
        if (prev != null && root.val <= prev) {
            return false;
        }
        // Update prev to current node's value
        prev = root.val;
        
        // Validate right subtree
        return inorder(root.right);
    }
}

