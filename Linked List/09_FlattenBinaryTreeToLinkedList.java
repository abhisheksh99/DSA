class Solution {
    // Problem: Flatten Binary Tree to Linked List
    // Given the root of a binary tree, flatten it into a "linked list" in-place, where
    // the right child pointer points to the next node in the list, and the left child
    // pointer is set to null. The linked list should follow the pre-order traversal order
    // (root, left, right) of the original tree. The tree may be empty or contain one or
    // more nodes.

    // DSA Pattern: Recursive Tree Traversal
    // This problem is solved using a recursive approach that flattens the tree by
    // reordering the nodes in pre-order traversal (root, left, right). For each node,
    // we recursively flatten the left and right subtrees, then reattach the flattened
    // left subtree to the right of the current node and append the flattened right
    // subtree to the end of the flattened left subtree. The left child is set to null.

    // Approach:
    // 1. Define a helper function flattenToLinkedList that returns the tail of the
    //    flattened subtree (last node in pre-order).
    // 2. Base cases:
    //    - If the node is null, return null.
    //    - If the node is a leaf (no left or right child), return the node itself.
    // 3. For a non-leaf node:
    //    - Recursively flatten the left subtree, getting its tail (leftTail).
    //    - Recursively flatten the right subtree, getting its tail (rightTail).
    //    - If leftTail exists, connect it to the current node’s right child, move the
    //      left subtree to the right, and set the left child to null.
    //    - Return the tail of the flattened tree (rightTail if it exists, else leftTail).
    // 4. The main flatten function calls the helper on the root, performing the operation
    //    in-place (no return value needed).

    // Key Points to Remember:
    // - The flattened list follows pre-order traversal: root, left subtree, right subtree.
    // - The left child of each node is set to null, and the right child forms the linked list.
    // - The helper function returns the tail of the flattened subtree to facilitate connecting
    //   the right subtree.
    // - Use recursion to process subtrees, then rewire pointers to form the linked list.
    // - Edge cases include empty trees (null root), single nodes, or trees with only one child.
    // - The algorithm modifies the tree in-place, reusing existing nodes.
    // - The tail of the flattened tree is either the rightTail (if right subtree exists) or
    //   leftTail (if only left subtree exists).

    // Time Complexity: O(n)
    // - n is the number of nodes in the binary tree.
    // - Each node is visited exactly once during the recursive pre-order traversal.
    // - Operations per node (pointer updates) are O(1).
    // - Total time is O(n).

    // Space Complexity: O(h)
    // - h is the height of the binary tree.
    // - The recursion stack can go as deep as the height of the tree (h), which is O(h).
    // - In the worst case (skewed tree), h = n; in a balanced tree, h = log n.
    // - No additional data structures are used, and the tree is modified in-place.
    // - The problem requires no output (void method).

    // Helper function to flatten the tree and return the tail of the flattened list
    private TreeNode flattenToLinkedList(TreeNode node) {
        // Base case: if node is null, return null
        if (node == null) {
            return null;
        }

        // Base case: if node is a leaf (no children), return the node itself
        if (node.left == null && node.right == null) {
            return node;
        }

        // Recursively flatten the left and right subtrees
        TreeNode leftTail = flattenToLinkedList(node.left);
        TreeNode rightTail = flattenToLinkedList(node.right);

        // If the left subtree exists, rewire the connections
        if (leftTail != null) {
            // Connect the tail of the left subtree to the right child
            leftTail.right = node.right;
            // Move the flattened left subtree to the right
            node.right = node.left;
            // Set the left child to null
            node.left = null;
        }

        // Return the tail of the flattened tree (rightTail if exists, else leftTail)
        return rightTail == null ? leftTail : rightTail;
    }

    // Main function to flatten the binary tree in-place
    public void flatten(TreeNode root) {
        // Call the helper function to perform the flattening
        flattenToLinkedList(root);
    }
}