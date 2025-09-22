// Problem: Kth Smallest Element in a BST
// Given the root of a binary search tree (BST) and an integer k, return the kth smallest value (1-indexed) of all
// the values of the nodes in the tree.

// DSA Pattern: Recursion / Inorder Traversal
// This problem is solved using an inorder traversal of the BST, which visits nodes in ascending order, tracking the
// number of nodes visited to find the kth smallest value.

// Approach:
// 1. Initialize two global variables:
//    - count: tracks the number of nodes visited during inorder traversal.
//    - result: stores the value of the kth smallest node.
// 2. Define a helper function 'inorder' that performs an inorder traversal:
//    - If the node is null, return (base case).
//    - Recursively traverse the left subtree.
//    - Increment count and check if it equals k; if so, set result to the current node's value and return.
//    - Recursively traverse the right subtree.
// 3. Call the inorder function with the root and k, then return the result.

// Key Points to Remember:
// - In a BST, an inorder traversal (left, root, right) visits nodes in ascending order of their values.
// - The kth node visited during inorder traversal has the kth smallest value (1-indexed).
// - Early termination is possible once the kth node is found, avoiding unnecessary traversal.
// - Edge cases:
//   - Single node: if k=1, return the node's value.
//   - k equals the number of nodes: return the largest value in the BST.
//   - Empty tree: not applicable due to constraints (k is valid).
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - In the worst case, the algorithm traverses all nodes (if k equals the number of nodes).
// - In the best case (k is small), it terminates early after visiting O(k + h) nodes, where h is the tree height.
// - Each node involves constant-time operations (count increment and comparison).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for inorder traversal can go as deep as the height of the tree.
// - In a balanced BST, h = O(log n); in a skewed BST, h = O(n).
// - The global variables count and result use O(1) space.

// Constraints:
// - The number of nodes in the tree is in the range [1, 10^4].
// - 1 <= k <= number of nodes
// - -10^4 <= Node.val <= 10^4

public class Solution {
    // Global variables to track visited nodes and kth smallest value
    private int count = 0;
    private int result = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        // Perform inorder traversal to find kth smallest value
        inorder(root, k);
        // Return the kth smallest value
        return result;
    }
    
    // Helper function for inorder traversal
    private void inorder(TreeNode node, int k) {
        // Base case: null node
        if (node == null) return;
        
        // Traverse left subtree
        inorder(node.left, k);
        
        // Process current node
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        // Traverse right subtree
        inorder(node.right, k);
    }
}

