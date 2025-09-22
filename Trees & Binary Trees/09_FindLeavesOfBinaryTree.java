// Problem: Find Leaves of Binary Tree
// Given a binary tree, collect all nodes as if the leaves are removed layer by layer until the tree is empty,
// and return a list of lists where each inner list contains the values of nodes removed in one step.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive depth-first search approach by computing the height of each node
// (distance from the farthest leaf) and grouping nodes by their height in the result list.

// Approach:
// 1. Initialize an empty list of lists to store the result, where each inner list contains nodes at the same height.
// 2. Use a helper function 'dfs' that:
//    - Returns -1 for a null node (base case, one less than a leaf's height).
//    - Recursively computes the height of the left and right subtrees.
//    - Determines the current node's height as the maximum of left and right heights plus 1.
//    - Adds the current node's value to the result list at the index corresponding to its height.
//    - Ensures the result list is large enough by adding a new inner list if needed.
// 3. Call dfs on the root and return the result list.

// Key Points to Remember:
// - The height of a node is defined as the number of edges from the node to its farthest leaf.
// - Leaves have a height of 0, their parents have height 1, and so on.
// - Nodes with the same height are removed in the same step, forming one inner list in the result.
// - The recursive approach ensures all nodes are processed, and their values are grouped by height.
// - Edge cases:
//   - Empty tree (null root): return an empty list of lists.
//   - Single node: return [[node.val]].
//   - Linear tree: each node forms its own list, in order from leaf to root.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single post-order traversal, visiting each node exactly once.
// - Each node involves constant-time operations (height calculation and list addition).

// Space Complexity: O(h) excluding the output, where h is the height of the tree
// - The recursion stack for dfs can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - The output list is not counted as extra space (per standard convention).
// - No additional data structures are used beyond the recursion stack and output.

// Constraints:
// - The number of nodes in the tree is in the range [1, 1000].
// - 0 <= Node.val <= 1000

public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // Initialize result list to store nodes grouped by height
        List<List<Integer>> result = new ArrayList<>();
        // Perform DFS to populate result
        dfs(root, result);
        // Return the list of lists
        return result;
    }
    
    // Helper function to compute height and group nodes
    private int dfs(TreeNode node, List<List<Integer>> result) {
        // Base case: null node has height -1
        if (node == null) {
            return -1;
        }
        
        // Recursively compute heights of left and right subtrees
        int leftHeight = dfs(node.left, result);
        int rightHeight = dfs(node.right, result);
        
        // Current node's height is max of left and right heights plus 1
        int currentHeight = Math.max(leftHeight, rightHeight) + 1;
        
        // Ensure result list has enough inner lists
        if (result.size() == currentHeight) {
            result.add(new ArrayList<>());
        }
        
        // Add current node's value to the list at its height
        result.get(currentHeight).add(node.val);
        
        // Return the height of the current node
        return currentHeight;
    }
}

