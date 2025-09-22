// Problem: Serialize and Deserialize Binary Tree
// Design an algorithm to serialize a binary tree into a string and deserialize that string back into the original tree structure.
// Serialization is the process of converting a data structure into a format that can be stored or transmitted.
// Deserialization is the reverse process of reconstructing the data structure from the serialized format.

// DSA Pattern: Breadth-First Search (BFS) / Queue
// This problem is solved using a level-order traversal (BFS) to serialize and deserialize the tree.
// Serialization converts the tree into a space-separated string, and deserialization reconstructs the tree from the string.

// Approach for Serialization:
// 1. If the root is null, return an empty string (base case).
// 2. Use a queue to perform a level-order traversal of the tree.
// 3. For each node:
//    - If the node is null, append "n " to the result string (indicating a null node).
//    - If the node is non-null, append its value followed by a space to the result string.
//    - Add the node's left and right children to the queue (including null children).
// 4. Return the resulting string after trimming trailing spaces.

// Approach for Deserialization:
// 1. If the input string is empty, return null (base case).
// 2. Split the input string into an array of values (split by spaces).
// 3. Create the root node using the first value in the array.
// 4. Use a queue to reconstruct the tree level by level:
//    - For each parent node dequeued, process the next two values in the array (for left and right children).
//    - If a value is not "n", create a new node with that value, assign it as the left or right child, and enqueue it.
//    - If a value is "n", skip assigning a child (it remains null).
// 5. Return the reconstructed tree's root.

// Key Points to Remember:
// - Serialization uses BFS to ensure all nodes are processed level by level, preserving the tree structure.
// - Null nodes are explicitly represented as "n" in the serialized string to maintain the tree's shape.
// - Deserialization mirrors the serialization process, using a queue to assign children to parents in level order.
// - The solution handles null nodes explicitly to ensure accurate reconstruction.
// - Edge cases:
//   - Empty tree (null root): return empty string for serialization, null for deserialization.
//   - Single node tree: serialize as a single value, deserialize back to a single node.
//   - Unbalanced or skewed trees: handled correctly by including null nodes in the string.
// - The solution assumes valid input for deserialization (a string produced by the serialize function).

// Time Complexity:
// - Serialization: O(n), where n is the number of nodes in the tree
//   - Each node is visited once during the BFS traversal.
//   - String building operations are O(1) per node.
// - Deserialization: O(n), where n is the number of nodes in the serialized string
//   - Each value in the string is processed once to reconstruct the tree.
//   - String splitting and parsing are linear in the size of the input.

// Space Complexity:
// - Serialization: O(w) + O(n) for the queue and output string, where w is the maximum width of the tree
//   - The queue holds at most the nodes at the widest level of the tree.
//   - The output string size is proportional to the number of nodes (including nulls).
// - Deserialization: O(w) + O(n) for the queue and the array of values
//   - The queue holds at most the nodes at the widest level.
//   - The array of values is proportional to the input string length.
// - In a balanced tree, w = O(n/2); in a skewed tree, w = O(1).

// Constraints:
// - The number of nodes in the tree is in the range [0, 10^4].
// - -1000 <= Node.val <= 1000

public class Codec {
    // Serializes a binary tree to a string
    public String serialize(TreeNode root) {
        // Base case: empty tree returns empty string
        if (root == null) return "";
        
        // Use a queue for level-order traversal
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        q.add(root);
        
        // Process nodes level by level
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                // Append "n" for null nodes
                res.append("n ");
                continue;
            }
            // Append node value followed by a space
            res.append(node.val + " ");
            // Add left and right children to queue (including nulls)
            q.add(node.left);
            q.add(node.right);
        }
        
        // Return the serialized string
        return res.toString();
    }

    // Deserializes a string to a binary tree
    public TreeNode deserialize(String data) {
        // Base case: empty string returns null
        if (data == "") return null;
        
        // Split the string into an array of values
        Queue<TreeNode> q = new LinkedList<>();
        String[] values = data.split(" ");
        
        // Create root node from the first value
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        q.add(root);
        
        // Process values to reconstruct the tree
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            
            // Process left child
            if (!values[i].equals("n")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            
            // Process right child (increment i to get the next value)
            if (++i < values.length && !values[i].equals("n")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        
        // Return the reconstructed tree's root
        return root;
    }
}