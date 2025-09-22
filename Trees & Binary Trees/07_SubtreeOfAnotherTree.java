// Problem: Subtree of Another Tree
// Given the roots of two binary trees 'root' and 'subRoot', determine if there is a subtree of 'root' that is identical
// to the tree rooted at 'subRoot'. Two trees are identical if they have the same structure and node values.

// DSA Pattern: Recursion / Depth-First Search
// This problem is solved using a recursive approach by checking if the tree rooted at 'subRoot' matches the tree rooted
// at each node in 'root', or any of its subtrees, using a helper function to check for identical trees.

// Approach:
// 1. Define a helper function 'isSameTree' to check if two trees are identical:
//    - If both nodes are null, return true (base case: empty trees are identical).
//    - If one node is null and the other is not, return false (different structures).
//    - If the values of the current nodes differ, return false.
//    - Recursively check if the left and right subtrees are identical.
// 2. In the main function 'isSubtree':
//    - If the root is null, return false (no subtree can exist).
//    - Check if the tree rooted at the current node is identical to 'subRoot' using isSameTree.
//    - If not, recursively check the left and right subtrees of 'root'.
// 3. Return true if any subtree matches 'subRoot', false otherwise.

// Key Points to Remember:
// - A subtree of 'root' is a tree rooted at any node in 'root' or its descendants.
// - The isSameTree helper ensures exact matches in structure and values.
// - The recursive approach checks every possible subtree of 'root' to find a match with 'subRoot'.
// - Edge cases:
//   - Empty 'root': return false (no subtrees exist).
//   - Empty 'subRoot': typically true (empty tree is a subtree), but constraints ensure non-empty 'subRoot'.
//   - Single node trees: check if they match exactly.
// - The solution assumes a standard TreeNode class with value and left/right child pointers.

// Time Complexity: O(m * n), where m is the number of nodes in 'root' and n is the number of nodes in 'subRoot'
// - For each node in 'root' (O(m)), we may call isSameTree, which takes O(n) to compare 'subRoot' with the subtree.
// - In the worst case (e.g., all nodes in 'root' have the same value as 'subRoot's root), we check each node.

// Space Complexity: O(h), where h is the height of 'root'
// - The recursion stack for isSubtree and isSameTree can go as deep as the height of 'root'.
// - In a balanced tree, h = O(log m); in a skewed tree, h = O(m).
// - No additional data structures are used beyond the recursion stack.

// Constraints:
// - The number of nodes in 'root' is in the range [1, 2000].
// - The number of nodes in 'subRoot' is in the range [1, 1000].
// - -10^4 <= root.val, subRoot.val <= 10^4

public class Solution {
    // Helper function to check if two trees are identical
    private boolean isSameTree(TreeNode s, TreeNode t) {
        // Base case: both null nodes mean trees are identical
        if (s == null && t == null) {
            return true;
        }
        
        // Base case: one null node means trees differ
        if (s == null || t == null) {
            return false;
        }
        
        // Check if current nodes have different values
        if (s.val != t.val) {
            return false;
        }
        
        // Recursively check left and right subtrees
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: null root cannot contain a subtree
        if (root == null) {
            return false;
        }
        
        // Check if current tree is identical to subRoot
        if (isSameTree(root, subRoot)) {
            return true;
        }
        
        // Recursively check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}

