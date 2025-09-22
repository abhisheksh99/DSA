// Problem: Diameter of Binary Tree
// Given the root of a binary tree, return the length of the diameter of the tree. The diameter is the length of the
// longest path between any two nodes in the tree, measured by the number of edges between them. This path may or may
// not pass through the root.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by computing the height of each subtree and tracking the maximum
// diameter (sum of left and right subtree heights) across all nodes.

// Approach:
// 1. Use a global variable 'maxDiameter' to track the maximum diameter found during traversal.
// 2. Define a helper function 'getHeight' that:
//    - Returns 0 for a null node (base case).
//    - Recursively computes the height of the left and right subtrees.
//    - Updates 'maxDiameter' with the maximum of the current maxDiameter and the sum of left and right heights.
//    - Returns the height of the current node (1 + max of left and right heights).
// 3. Call getHeight on the root to populate maxDiameter.
// 4. Return maxDiameter as the result.

// Key Points to Remember:
// - The diameter is the number of edges in the longest path between two nodes, which equals the sum of the heights of
//   the left and right subtrees for some node.
// - The path may not pass through the root, so we check all nodes.
// - The height of a node is the number of edges in the longest path from the node to a leaf.
// - Edge cases:
//   - Empty tree (null root): return 0.
//   - Single node: return 0 (no edges).
//   - Single path (skewed tree): diameter is the number of edges in the path.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single post-order traversal, visiting each node exactly once.
// - Each node involves constant-time operations (comparisons and updates).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for getHeight can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - The global variable maxDiameter uses O(1) space.

// Constraints:
// - The number of nodes in the tree is in the range [0, 10^4].
// - -100 <= Node.val <= 100

public class Solution {
    // Global variable to track the maximum diameter
    int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        // Compute heights and update maxDiameter
        getHeight(root);
        // Return the maximum diameter
        return maxDiameter;
    }
    
    // Helper function to compute the height of a subtree
    private int getHeight(TreeNode node) {
        // Base case: null node has height 0
        if (node == null) return 0;
        
        // Recursively compute heights of left and right subtrees
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        
        // Update maxDiameter with the sum of left and right heights
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        
        // Return the height of the current node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}

