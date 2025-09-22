// Problem: Binary Tree Level Order Traversal
// Given the root of a binary tree, return the level order traversal of its nodes' values (i.e., from left to right,
// level by level) as a list of lists, where each inner list contains the node values at a given level.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive depth-first search approach by tracking the level of each node and
// grouping node values by their level in the result list.

// Approach:
// 1. Initialize a global list of lists 'result' to store the node values grouped by level.
// 2. Define a helper function 'levelOrderHelper' that takes a node and its level:
//    - If the result list does not have a list for the current level, add a new empty list.
//    - Add the current node's value to the list corresponding to its level.
//    - Recursively process the left and right children, incrementing the level by 1.
// 3. In the main function:
//    - If the root is null, return the empty result list.
//    - Call the helper function with the root at level 0.
// 4. Return the result list containing the level order traversal.

// Key Points to Remember:
// - Level order traversal processes nodes level by level, from left to right.
// - The recursive approach uses the level parameter to group nodes into the correct inner list.
// - Each level corresponds to an index in the result list, and nodes are added in left-to-right order.
// - Edge cases:
//   - Empty tree (null root): return an empty list of lists.
//   - Single node: return a list containing a single inner list with the node's value.
//   - Skewed tree: each level contains one node.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a single traversal, visiting each node exactly once.
// - Each node involves constant-time operations (list access and addition).

// Space Complexity: O(h) excluding the output, where h is the height of the tree
// - The recursion stack for levelOrderHelper can go as deep as the height of the tree.
// - In a balanced tree, h = O(log n); in a skewed tree, h = O(n).
// - The output list is not counted as extra space (per standard convention).
// - The global result list is part of the output.

// Constraints:
// - The number of nodes in the tree is in the range [0, 2000].
// - -100 <= Node.val <= 100

public class Solution {
    // Global list to store level order traversal result
    List<List<Integer>> result = new ArrayList<>();
    
    // Helper function to perform recursive level order traversal
    private void levelOrderHelper(TreeNode node, int level) {
        // Ensure the result list has a list for the current level
        if (result.size() == level) {
            result.add(new ArrayList<Integer>());
        }
        
        // Add current node's value to the corresponding level
        result.get(level).add(node.val);
        
        // Recursively process left and right children
        if (node.left != null) {
            levelOrderHelper(node.left, level + 1);
        }
        if (node.right != null) {
            levelOrderHelper(node.right, level + 1);
        }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Handle base case: empty tree
        if (root == null) {
            return result;
        }
        
        // Start recursive traversal from level 0
        levelOrderHelper(root, 0);
        
        // Return the level order traversal result
        return result;
    }
}

