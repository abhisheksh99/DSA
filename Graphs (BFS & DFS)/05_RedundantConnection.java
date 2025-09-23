// Problem: Find Redundant Connection
// Given a graph represented by a list of edges, where each edge is a pair of nodes [u, v], return an edge that can be removed
// so that the resulting graph is a tree of n nodes (i.e., a connected graph with no cycles).
// If there are multiple possible edges, return the last edge in the input list that causes a cycle.

// DSA Pattern: Union-Find (Disjoint Set Union) / Graph Traversal
// This problem is solved using the Union-Find data structure to detect a cycle in the graph.
// By processing each edge and attempting to union the nodes, the first edge that connects two nodes in the same set indicates a cycle.

// Approach:
// 1. Initialize a parent array where each node is initially its own parent (self-loop, representing distinct sets).
// 2. For each edge [u, v] in the input list:
//    - Find the root (representative) of the set containing node u using the find function.
//    - Find the root of the set containing node v using the find function.
//    - If the roots are the same, the nodes are already in the same set, indicating a cycle; return the current edge.
//    - Otherwise, perform a union by setting the parent of one root to the other, merging the sets.
// 3. If no cycle is found, return an empty array (though the problem guarantees a cycle exists).
// 4. Use path compression in the find function to optimize the process by making nodes point directly to their set's root.

// Key Points to Remember:
// - The graph is undirected, and the edges form a connected graph with n nodes and n edges, guaranteeing exactly one cycle.
// - The Union-Find data structure efficiently tracks connected components and detects cycles.
// - Path compression in the find function improves performance by flattening the tree structure.
// - Edge cases:
//   - The input graph always has a cycle (n nodes and n edges), so an empty result is theoretically unreachable.
//   - Nodes are 1-indexed (from 1 to n).
// - The solution assumes the input edges are valid (nodes are within [1, n], and the graph is connected).

// Time Complexity: O(n * α(n)), where n is the number of nodes and α(n) is the inverse Ackermann function
// - The Union-Find operations (find and union) take O(α(n)) amortized time per operation with path compression.
// - Processing n edges results in O(n * α(n)), which is effectively O(n) since α(n) grows extremely slowly (nearly constant).
// - Initializing the parent array takes O(n).

// Space Complexity: O(n)
// - The parent array requires O(n) space to store the parent of each node.
// - No additional data structures are used beyond a constant amount of memory for variables.

// Constraints:
// - n == edges.length
// - 3 <= n <= 1000
// - edges[i].length == 2
// - 1 <= edges[i][0], edges[i][1] <= n
// - edges[i][0] != edges[i][1]
// - The graph is undirected (edge [u, v] implies [v, u]) and connected, with no self-loops or multiple edges.

class Solution {
    public static int[] findRedundantConnection(int[][] edges) {
        // Initialize parent array where each node is its own parent
        int[] parent = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i; // Each node starts as its own set
        }

        // Process each edge to detect a cycle
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            // Find the roots of the sets containing node1 and node2
            int root1 = find(parent, node1);
            int root2 = find(parent, node2);

            // If roots are the same, a cycle is detected; return the current edge
            if (root1 == root2) {
                return edge;
            }

            // Union the sets by setting root2's parent to root1
            parent[root2] = root1;
        }

        // If no cycle is found (should not happen per problem constraints), return empty array
        return new int[0];
    }

    // Helper function to find the root of a node's set with path compression
    private static int find(int[] parent, int node) {
        // Traverse up to the root, applying path compression
        while (node != parent[node]) {
            parent[node] = parent[parent[node]]; // Path compression: point to grandparent
            node = parent[node];
        }
        return node;
    }
}