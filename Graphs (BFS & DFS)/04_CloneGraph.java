// Problem: Clone Graph
// Given a reference to a node in a connected undirected graph, return a deep copy (clone) of the graph.
// Each node in the graph contains a value (int) and a list of its neighbors (List<Node>).
// The graph is represented as an adjacency list, and all nodes are reachable from the given node.

// DSA Pattern: Depth-First Search (DFS) / Graph Traversal
// This problem is solved using a recursive DFS approach to traverse the graph and create a deep copy of each node and its neighbors.
// A HashMap is used to track visited nodes to avoid cycles and ensure each node is cloned only once.

// Approach:
// 1. If the input node is null, return null (empty graph).
// 2. Use a HashMap to store mappings from original nodes to their clones to handle cycles and avoid redundant cloning.
// 3. For the given node:
//    - If it has already been cloned (exists in the HashMap), return the cloned node.
//    - Otherwise, create a new node with the same value and an empty neighbor list.
//    - Add the mapping of the original node to the cloned node in the HashMap.
//    - Recursively clone each neighbor of the current node and add the cloned neighbors to the cloned node's neighbor list.
// 4. Return the cloned node, which serves as the entry point to the cloned graph.

// Key Points to Remember:
// - The graph is undirected, connected, and may contain cycles, so a HashMap is essential to avoid infinite recursion.
// - Each node must be cloned exactly once, with its value and neighbors copied correctly.
// - The cloned graph must be a deep copy, meaning no references to the original graph's nodes are retained.
// - Edge cases:
//   - Null input node: return null.
//   - Single node with no neighbors: return a new node with the same value and empty neighbor list.
//   - Graph with cycles: HashMap ensures each node is processed only once.
// - The solution assumes a Node class with an integer value (val) and a list of neighbors (List<Node> neighbors).

// Time Complexity: O(V + E), where V is the number of nodes and E is the number of edges
// - Each node is visited and cloned exactly once, taking O(V) time.
// - Each edge is processed when copying the neighbor lists, taking O(E) time.
// - HashMap operations (get/put) are O(1) on average.

// Space Complexity: O(V)
// - The HashMap stores a mapping for each node, requiring O(V) space.
// - The recursion stack can go as deep as O(V) in the worst case (e.g., a linear graph).
// - The cloned graph itself is not counted as extra space since it is part of the output.

// Constraints:
// - The number of nodes in the graph is in the range [0, 100].
// - 1 <= Node.val <= 100
// - Each node value is unique.
// - The graph is connected and undirected, with no self-loops or multiple edges between the same pair of nodes.

class Solution {
    // HashMap to store mappings from original nodes to their clones
    HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        // Handle base case: null input node
        if (node == null) {
            return node;
        }

        // If node has already been cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new node with the same value and empty neighbor list
        Node cloneNode = new Node(node.val, new ArrayList<>());

        // Map the original node to its clone
        visited.put(node, cloneNode);

        // Recursively clone each neighbor and add to the cloned node's neighbor list
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        // Return the cloned node
        return cloneNode;
    }
}