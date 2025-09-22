// Problem: Construct Binary Tree from Preorder and Inorder Traversal
// Given two integer arrays 'preorder' and 'inorder' representing the preorder and inorder traversals of a binary tree,
// construct and return the binary tree. The tree has no duplicate values.

// DSA Pattern: Recursion / Divide and Conquer
// This problem is solved using a recursive approach by leveraging the properties of preorder and inorder traversals
// to identify the root and recursively construct the left and right subtrees.

// Approach:
// 1. Initialize a global variable 'preorderIndex' to track the current index in the preorder array.
// 2. Create a HashMap 'inorderIndexMap' to store the indices of values in the inorder array for O(1) lookup.
// 3. Populate the HashMap with value-to-index mappings from the inorder array.
// 4. Define a helper function 'buildTreeHelper' that takes the preorder array and the left and right bounds of the
//    current inorder subarray:
//    - If left > right, return null (base case: no nodes to process).
//    - Get the root value from the current preorderIndex and increment preorderIndex.
//    - Create a new TreeNode with the root value.
//    - Find the root's index in the inorder array using the HashMap.
//    - Recursively construct the left subtree using the inorder subarray from left to inorderRootIndex - 1.
//    - Recursively construct the right subtree using the inorder subarray from inorderRootIndex + 1 to right.
//    - Return the root node.
// 5. Call the helper function with the initial bounds [0, inorder.length - 1] and return the result.

// Key Points to Remember:
// - Preorder traversal gives the root first, then left subtree, then right subtree.
// - Inorder traversal gives left subtree, root, then right subtree.
// - The root's index in the inorder array splits the array into left and right subtrees.
// - The HashMap optimizes the lookup of the root's index in the inorder array.
// - Edge cases:
//   - Empty arrays: return null (handled by constraints ensuring valid input).
//   - Single node: return a tree with one node.
//   - Skewed tree: handled by recursive splitting.
// - The solution assumes no duplicate values in the tree and valid input arrays.

// Time Complexity: O(n), where n is the number of nodes in the tree
// - Building the HashMap takes O(n).
// - The recursive helper function processes each node exactly once, with O(1) operations per node (HashMap lookup and node creation).
// - Total time is O(n) for both the HashMap construction and tree construction.

// Space Complexity: O(n)
// - The HashMap stores n entries for the inorder array.
// - The recursion stack can go as deep as the height of the tree, which is O(h) where h is O(log n) for a balanced tree or O(n) for a skewed tree.
// - The overall space complexity is O(n) due to the HashMap.

// Constraints:
// - 1 <= preorder.length == inorder.length <= 3000
// - preorder and inorder consist of unique values.
// - Each value of inorder also appears in preorder.
// - preorder and inorder are valid traversals of a binary tree.
// - -3000 <= preorder[i], inorder[i] <= 3000

public class Solution {
    // Global variable to track the current index in preorder array
    private Map<Integer, Integer> inorderIndexMap;
    private int preorderIndex;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Initialize preorder index and HashMap
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        
        // Populate HashMap with inorder value-to-index mappings
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        // Build the tree recursively
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }
    
    // Helper function to recursively construct the tree
    private TreeNode buildTreeHelper(int[] preorder, int left, int right) {
        // Base case: no nodes to process
        if (left > right) return null;
        
        // Get root value from preorder and increment index
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        
        // Find root's index in inorder array
        int inorderRootIndex = inorderIndexMap.get(rootValue);
        
        // Recursively build left and right subtrees
        root.left = buildTreeHelper(preorder, left, inorderRootIndex - 1);
        root.right = buildTreeHelper(preorder, inorderRootIndex + 1, right);
        
        // Return the constructed subtree
        return root;
    }
}

