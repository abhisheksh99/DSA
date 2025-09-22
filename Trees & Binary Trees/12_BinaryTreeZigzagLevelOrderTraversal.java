// Problem: Binary Tree Zigzag Level Order Traversal
// Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. The traversal alternates
// between left-to-right and right-to-left for each level, starting with left-to-right for the first level.

// DSA Pattern: Breadth-First Search / Level Order Traversal
// This problem is solved using a breadth-first search (BFS) approach with a queue to process nodes level by level,
// using a boolean flag to alternate the order of node values (left-to-right or right-to-left) for each level.

// Approach:
// 1. If the root is null, return an empty list of lists.
// 2. Initialize a result list to store the node values grouped by level.
// 3. Use a queue for level-order traversal, with null as a delimiter to mark the end of each level:
//    - Add the root and a null delimiter to the queue.
//    - Initialize a list to store the current level's node values.
//    - Use a boolean flag 'isLeftToRight' to track the order (true for left-to-right, false for right-to-left).
// 4. While the queue is not empty:
//    - Remove the next node from the queue.
//    - If the node is not null:
//      - Add its value to the level list (at the end for left-to-right, at the start for right-to-left).
//      - Add its left and right children to the queue (if they exist).
//    - If the node is null (end of level):
//      - Add the current level list to the result.
//      - Reset the level list for the next level.
//      - If the queue is not empty, add a null delimiter for the next level.
//      - Toggle the order flag for the next level.
// 5. Return the result list containing the zigzag level order traversal.

// Key Points to Remember:
// - Zigzag traversal alternates between left-to-right (level 0, 2, 4, ...) and right-to-left (level 1, 3, 5, ...).
// - The queue ensures nodes are processed level by level, with null delimiters to separate levels.
// - A LinkedList is used for the level list to efficiently add elements at the start or end based on the order.
// - Edge cases:
//   - Empty tree (null root): return an empty list of lists.
//   - Single node: return a list with one inner list containing the node's value.
//   - Skewed tree: each level contains one node, with alternating order.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a level-order traversal, visiting each node exactly once.
// - Each node involves constant-time operations (queue and list operations).

// Space Complexity: O(w), where w is the maximum width of the tree
// - The queue stores at most the nodes at the widest level of the tree, plus one null delimiter.
// - The level list stores at most the nodes at the current level.
// - In a balanced tree, w = O(n/2) = O(n); in a skewed tree, w = O(1).
// - The output list is not counted as extra space (per standard convention).

// Constraints:
// - The number of nodes in the tree is in the range [0, 2000].
// - -100 <= Node.val <= 100

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Handle base case: empty tree
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        
        // Initialize result list for zigzag level order traversal
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // Initialize queue for level-order traversal with null delimiter
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);
        queue.addLast(null);
        
        // Initialize list for current level's node values
        LinkedList<Integer> levelList = new LinkedList<Integer>();
        // Flag to track traversal order (true: left-to-right, false: right-to-left)
        boolean isLeftToRight = true;
        
        // Process nodes level by level
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.pollFirst();
            if (currentNode != null) {
                // Add node value based on current level's order
                if (isLeftToRight) {
                    levelList.addLast(currentNode.val);
                } else {
                    levelList.addFirst(currentNode.val);
                }
                
                // Add child nodes to queue
                if (currentNode.left != null) {
                    queue.addLast(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.addLast(currentNode.right);
                }
            } else {
                // End of current level: add level list to result
                result.add(levelList);
                // Reset level list for next level
                levelList = new LinkedList<Integer>();
                // Add delimiter for next level if queue is not empty
                if (!queue.isEmpty()) {
                    queue.addLast(null);
                }
                // Toggle traversal order for next level
                isLeftToRight = !isLeftToRight;
            }
        }
        
        // Return the zigzag level order traversal result
        return result;
    }
}

