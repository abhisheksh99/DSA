class Solution {
    // Problem: Copy List with Random Pointer
    // Given the head of a linked list where each node contains a value, a next pointer,
    // and a random pointer that can point to any node in the list or null, create a deep
    // copy of the list. The deep copy should consist of new nodes with the same values,
    // and the next and random pointers in the copied list should point to nodes in the
    // copied list, not the original list. The list may be empty or contain one or more nodes.
    // DSA Pattern: Linked List Manipulation with Hashing
    // This problem is solved using a recursive approach with a hash map to track already
    // copied nodes, avoiding infinite recursion due to random pointers. For each node, we
    // create a new node, store it in the hash map, and recursively copy its next and random
    // pointers, reusing nodes from the hash map if already copied.
    // Approach:
    // 1. Use a hash map to store mappings from original nodes to their copies.
    // 2. Define a recursive function copyRandomList that processes each node:
    //    - Base case: If the node is null, return null.
    //    - If the node is already in the hash map, return its copy.
    //    - Create a new node with the same value and null pointers.
    //    - Store the original-to-copy mapping in the hash map.
    //    - Recursively copy the next and random pointers.
    //    - Assign the copied next and random pointers to the new node.
    // 3. Return the copied node.
    // Key Points to Remember:
    // - The hash map prevents redundant node creation and handles cycles caused by random pointers.
    // - Each node in the original list maps to a new node in the copied list.
    // - The deep copy ensures no pointers in the new list point to original list nodes.
    // - Edge cases include empty lists (null head), single nodes, or lists with cycles via random pointers.
    // - The algorithm creates new nodes and modifies pointers in the copied list.
    // - The hash map ensures O(1) lookup for already copied nodes.
    // Time Complexity: O(n)
    // - n is the number of nodes in the linked list.
    // - Each node is visited at most once during the recursive traversal.
    // - Hash map operations (put and get) are O(1) on average.
    // - Total time is O(n).
    // Space Complexity: O(n)
    // - The hash map stores a mapping for each of the n nodes, using O(n) space.
    // - The recursion stack can go up to O(n) in the worst case (e.g., a linear list).
    // - Total space is O(n).

    // Hash map to store mappings from original nodes to their copies
    private HashMap<Node, Node> visitedNode = new HashMap<>();

    // Recursive function to create a deep copy of the linked list
    public Node copyRandomList(Node head) {
        // Base case: if the node is null, return null
        if (head == null) {
            return null;
        }

        // If the node is already copied, return its copy from the hash map
        if (visitedNode.containsKey(head)) {
            return visitedNode.get(head);
        }

        // Create a new node with the same value and null pointers
        Node node = new Node(head.val, null, null);

        // Store the mapping of original node to its copy
        visitedNode.put(head, node);

        // Recursively copy the next and random pointers
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);

        // Return the copied node
        return node;
    }
}