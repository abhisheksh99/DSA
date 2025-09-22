// Problem: Count Good Nodes in Binary Tree
// Given the root of a binary tree, return the number of "good" nodes. A node is considered good if all nodes in the path
// from the root to that node have values less than or equal to the node's value.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by tracking the maximum value along the path from the root to the
// current node and counting nodes where the value is greater than or equal to this maximum.

// Approach:
// 1. Define a helper function 'countGoodNodes' that takes the current node and the maximum value seen so far on the path:
//    - If the node is null, return 0 (base case).
//    - If the node's value is greater than or equal to maxSoFar, count it as a good node and update maxSoFar.
//    - Recursively count good nodes in the left and right subtrees, passing the updated maxSoFar.
//    - Return the total count of good nodes for the current subtree.
// 2. Call the helper function with the root and Integer.MIN_VALUE (to ensure the root is always a good node).
// 3. Return the total count of good nodes.

// Key Points to Remember:
// - A node is good if its value is greater than or equal to the maximum value on the path from the root to that node.
// - The maximum value along the path is updated whenever a good node is found.
// - The recursive approach ensures that each node is evaluated with respect to the maximum value on its path from the root.
// - Edge cases:
//   - Empty tree (null root): return 0 (handled by base case).
//   - Single node: return 1 (root is always good as there's no larger value on its path).
//   - All nodes with the same value: all nodes are good (as each satisfies the condition).
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm visits each node exactly once during the recursive traversal.
// - Each node involves constant-time operations (comparisons and updates).

// Space Complexity: O(h), where h is the height of the tree
// - The recursion stack for countGoodNodes can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in the tree is in the range [1, 10^5].
// - -10^4 <= Node.val <= 10^4

public class Solution {
    public int goodNodes(TreeNode root) {
        // Start recursion with the minimum possible value to ensure root is counted
        return countGoodNodes(root, Integer.MIN_VALUE);
    }
    
    // Helper function to count good nodes recursively
    private int countGoodNodes(TreeNode node, int maxSoFar) {
        // Base case: null node contributes no good nodes
        if (node == null) {
            return 0;
        }
        
        // Initialize count for current node
        int count = 0;
        // Check if current node is good (value >= maxSoFar)
        if (node.val >= maxSoFar) {
            count = 1; // Current node is good
            maxSoFar = node.val; // Update maximum value along the path
        }
        
        // Recursively count good nodes in left and right subtrees
        count += countGoodNodes(node.left, maxSoFar);
        count += countGoodNodes(node.right, maxSoFar);
        
        // Return total count of good nodes in this subtree
        return count;
    }
}

