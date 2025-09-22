// Problem: Binary Tree Right Side View
// Given the root of a binary tree, return a list of the rightmost node values at each level of the tree,
// as seen from the right side of the tree.

// DSA Pattern: Breadth-First Search / Level Order Traversal
// This problem is solved using a breadth-first search (BFS) approach by performing a level-order traversal
// and collecting the last node value at each level.

// Approach:
// 1. Initialize an empty list 'result' to store the rightmost node values.
// 2. If the root is null, return the empty list (base case).
// 3. Use a queue to perform level-order traversal:
//    - Add the root to the queue.
//    - While the queue is not empty:
//      - Determine the size of the current level (number of nodes in the queue).
//      - Process all nodes at the current level:
//        - Remove the next node from the queue.
//        - If it's the last node of the level (i == levelSize - 1), add its value to the result.
//        - Add the node's left and right children to the queue (if they exist).
// 4. Return the result list containing the rightmost node values.

// Key Points to Remember:
// - The right side view consists of the rightmost node at each level of the tree.
// - BFS ensures nodes are processed level by level, and the last node at each level is the rightmost.
// - The queue maintains the order of nodes to be processed, with children added in left-to-right order.
// - Edge cases:
//   - Empty tree (null root): return an empty list.
//   - Single node: return a list with that node's value.
//   - Skewed tree: return a list of all node values in the single path.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - The algorithm performs a level-order traversal, visiting each node exactly once.
// - Each node involves constant-time operations (queue operations and list additions).

// Space Complexity: O(w), where w is the maximum width of the tree
// - The queue stores at most the nodes at the widest level of the tree.
// - In a balanced tree, w = O(n/2) = O(n); in a skewed tree, w = O(1).
// - The output list is not counted as extra space (per standard convention).

// Constraints:
// - The number of nodes in the tree is in the range [0, 100].
// - -100 <= Node.val <= 100

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // Initialize result list for rightmost node values
        List<Integer> result = new ArrayList<>();
        // Handle base case: empty tree
        if (root == null) {
            return result;
        }
        
        // Initialize queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Process nodes level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // Add the last node's value of the level to the result
                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }
                
                // Add child nodes to the queue for the next level
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        
        // Return the list of rightmost node values
        return result;
    }
}

