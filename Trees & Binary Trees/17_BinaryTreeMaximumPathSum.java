// Problem: Binary Tree Maximum Path Sum
// Given a non-empty binary tree, find the maximum path sum. A path sum is the sum of the values of the nodes in a path.
// A path is any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
// The path must contain at least one node and does not need to go through the root.

// DSA Pattern: Recursion / Divide and Conquer
// This problem is solved using a recursive approach by calculating the maximum path sum that includes each node as
// a potential "turning point" and tracking the global maximum path sum found so far.

// Approach:
// 1. Use a helper function `maxGain` to compute the maximum gain (path sum) from a node through one of its subtrees.
// 2. For each node:
//    - If the node is null, return 0 (base case: no contribution to path sum).
//    - Recursively compute the maximum gain from the left and right subtrees, taking 0 if negative (to exclude negative contributions).
//    - Calculate the maximum path sum through the current node (node value + left gain + right gain) and update the global maximum.
//    - Return the maximum gain for the current node (node value + max of left or right gain) to be used by the parent.
// 3. The main function calls `maxGain` and returns the global maximum path sum stored in `maxSum`.

// Key Points to Remember:
// - A path can start and end at any node, and it does not need to pass through the root.
// - For each node, we consider it as the "turning point" of a path (including both left and right subtrees).
// - Negative contributions from subtrees are excluded by taking the maximum of the gain and 0.
// - The global maximum path sum (`maxSum`) is updated at each node to track the highest path sum encountered.
// - Edge cases:
//   - Single node: The path sum is the node's value.
//   - Negative values: The path can exclude negative subtree sums by choosing 0.
//   - Empty tree: Not applicable due to the problem constraint (non-empty tree).
// - The solution assumes the tree has at least one node.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single traversal, visiting each node exactly once.
// - Each recursive call involves constant-time operations (comparisons and arithmetic).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - A single global variable (`maxSum`) is used to track the maximum path sum.

// Constraints:
// - The number of nodes in the tree is in the range [1, 3 * 10^4].
// - -10^4 <= Node.val <= 10^4

public class Solution {
    // Global variable to track the maximum path sum found
    int maxSum = Integer.MIN_VALUE;
    
    // Helper function to compute the maximum gain from a node through one of its subtrees
    public int maxGain(TreeNode node) {
        // Base case: null node contributes 0 to the path sum
        if (node == null) {
            return 0;
        }
        
        // Recursively compute the maximum gain from left and right subtrees
        // Use 0 if the gain is negative to exclude unprofitable paths
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        
        // Calculate the maximum path sum through the current node
        int priceNewPath = node.val + leftGain + rightGain;
        
        // Update the global maximum path sum if the current path is larger
        maxSum = Math.max(maxSum, priceNewPath);
        
        // Return the maximum gain for the current node to be used by the parent
        return node.val + Math.max(leftGain, rightGain);
    }
    
    // Main function to initiate the recursion and return the maximum path sum
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
}