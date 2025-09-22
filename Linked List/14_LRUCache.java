public class LRUCache {
    // Problem: LRU Cache
    // Design and implement a Least Recently Used (LRU) cache that supports get and put
    // operations. The cache has a fixed capacity, and when it is full, the least recently
    // used item is removed before adding a new item. The get operation retrieves the value
    // associated with a key if it exists, otherwise returns -1. The put operation inserts
    // or updates a key-value pair. Both operations should be performed in O(1) time.
    // DSA Pattern: Doubly Linked List + Hash Map
    // This problem is solved using a combination of a doubly linked list and a hash map.
    // The doubly linked list maintains the order of elements (most recently used to least
    // recently used), and the hash map provides O(1) access to nodes by key. A dummy head
    // and tail node simplify list operations.
    // Approach:
    // 1. Define a Node class with key, value, prev, and next pointers for the doubly linked list.
    // 2. Initialize the cache with:
    //    - A fixed capacity.
    //    - A hash map to store key-to-node mappings.
    //    - Dummy head and tail nodes to simplify list operations.
    // 3. For get(key):
    //    - If the key exists in the hash map, retrieve the node, move it to the front
    //      (most recently used), and return its value.
    //    - Otherwise, return -1.
    // 4. For put(key, value):
    //    - If the key exists, update the node’s value and move it to the front.
    //    - If the key doesn’t exist:
    //      - Create a new node and add it to the front.
    //      - Add the key-node pair to the hash map.
    //      - If the cache is at capacity, remove the least recently used node (before tail)
    //        from both the list and hash map.
    // 5. Helper methods:
    //    - remove(node): Removes a node from the doubly linked list by updating pointers.
    //    - insertAtHead(node): Inserts a node right after the head (most recently used position).
    // Key Points to Remember:
    // - The doubly linked list ensures O(1) insertion and removal by maintaining prev/next pointers.
    // - The hash map ensures O(1) lookup for keys.
    // - Dummy head and tail nodes eliminate edge cases when adding/removing nodes at the ends.
    // - Moving a node to the front (after head) marks it as most recently used.
    // - The least recently used node is always before the tail.
    // - Edge cases include empty cache, capacity of 1, or keys not present in the cache.
    // - All operations (get, put) are performed in O(1) time.
    // Time Complexity: O(1)
    // - get: O(1) for hash map lookup, node removal, and insertion.
    // - put: O(1) for hash map operations, node removal, and insertion.
    // - Helper methods (remove, insertAtHead) are O(1) due to direct pointer updates.
    // Space Complexity: O(capacity)
    // - The hash map stores at most 'capacity' key-node pairs.
    // - The doubly linked list stores at most 'capacity' nodes.
    // - Total space is O(capacity) for the hash map and linked list.

    // Node class for the doubly linked list
    private static class Node {
        int key;    // Key for hash map mapping
        int value;  // Value stored in the node
        Node prev;  // Pointer to previous node
        Node next;  // Pointer to next node
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;          // Maximum number of items in the cache
    private final HashMap<Integer, Node> map; // Maps keys to nodes for O(1) lookup
    private final Node head;             // Dummy head node for the doubly linked list
    private final Node tail;             // Dummy tail node for the doubly linked list

    // Initialize the LRU Cache with given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);     // Dummy head node
        this.tail = new Node(0, 0);     // Dummy tail node
        head.next = tail;               // Connect head to tail
        tail.prev = head;               // Connect tail to head
    }

    // Get the value associated with the key, or -1 if not found
    public int get(int key) {
        // Check if the key exists in the hash map
        if (!map.containsKey(key)) {
            return -1;
        }
        // Move the accessed node to the front (most recently used)
        Node node = map.get(key);
        remove(node);
        insertAtHead(node);
        return node.value;
    }

    // Insert or update a key-value pair in the cache
    public void put(int key, int value) {
        // If key exists, update its value and move to front
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtHead(node);
        } else {
            // If cache is at capacity, remove the least recently used node
            if (map.size() == capacity) {
                map.remove(tail.prev.key);  // Remove from hash map
                remove(tail.prev);          // Remove from list
            }
            // Create and insert new node
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insertAtHead(newNode);
        }
    }

    // Helper method to remove a node from the doubly linked list
    private void remove(Node node) {
        node.prev.next = node.next;     // Update previous node's next pointer
        node.next.prev = node.prev;     // Update next node's prev pointer
    }

    // Helper method to insert a node right after the head
    private void insertAtHead(Node node) {
        node.next = head.next;          // Connect node to current head.next
        node.next.prev = node;          // Update prev of head.next to node
        head.next = node;               // Connect head to node
        node.prev = head;               // Connect node to head
    }
}