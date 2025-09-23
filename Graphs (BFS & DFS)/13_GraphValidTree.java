class Solution {
    // Problem: Graph Valid Tree
    // Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
    // determine if these edges form a valid tree. A valid tree is a connected graph with no cycles and
    // exactly n-1 edges, where n is the number of nodes.

    // DSA Pattern: Depth-First Search (DFS) with Adjacency List
    // This problem is solved using a graph traversal approach (DFS) to check for connectivity and
    // absence of cycles. An adjacency list represents the undirected graph, and a stack-based DFS
    // ensures all nodes are visited. The edge count (n-1) and full connectivity (all nodes visited)
    // confirm a valid tree.

    // Approach:
    // 1. Check if the number of edges is exactly n-1; if not, it cannot be a valid tree.
    // 2. Build an adjacency list to represent the undirected graph by adding both directions of each edge.
    // 3. Perform DFS starting from node 0:
    //    - Use a stack to explore nodes and a HashSet to track visited nodes.
    //    - For each node, explore unvisited neighbors, mark them as visited, and push them onto the stack.
    //    - Skip neighbors that have already been visited to avoid detecting false cycles.
    // 4. After DFS, check if the number of visited nodes equals n (ensuring connectivity).
    // 5. Return true if all conditions are met (n-1 edges and all nodes connected); otherwise, return false.

    // Key Points to Remember:
    // - A valid tree must have exactly n-1 edges (fewer means disconnected, more implies a cycle).
    // - Use an adjacency list for efficient neighbor lookup in the undirected graph.
    // - DFS ensures we can check connectivity and implicitly detect cycles (since visited nodes are skipped).
    // - The HashSet prevents revisiting nodes, avoiding false cycle detection in undirected graphs.
    // - Start DFS from node 0, as the graph must be connected for a valid tree.
    // - If the visited set size equals n, all nodes are connected, confirming a single component.
    // - Edge case: If n=1 and no edges, it is a valid tree (single node).

    // Time Complexity: O(N + E)
    // - N is the number of nodes, and E is the number of edges (E = N-1 for a valid tree).
    // - Building the adjacency list takes O(E) = O(N-1).
    // - DFS visits each node and its edges once, taking O(N + E) = O(N) since E = N-1.
    // - HashSet operations (add, contains) are O(1) on average.
    // - Overall, the time complexity is O(N).

    // Space Complexity: O(N)
    // - The adjacency list stores O(E) = O(N-1) edges, as each edge is stored twice (undirected).
    // - The HashSet for visited nodes stores up to N nodes.
    // - The stack for DFS uses up to O(N) space in the worst case (e.g., a linear graph).
    // - Overall, the space complexity is O(N).

    public boolean validTree(int n, int[][] edges) {
        // Check if the number of edges is exactly n-1
        if (edges.length != n - 1) {
            return false;
        }
        
        // Initialize adjacency list for the graph
        List<List<Integer>> adjancencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjancencyList.add(new ArrayList<>());
        }
        
        // Build undirected graph by adding edges in both directions
        for (int[] edge : edges) {
            adjancencyList.get(edge[0]).add(edge[1]);
            adjancencyList.get(edge[1]).add(edge[0]);
        }
        
        // Initialize stack for DFS and set for tracking visited nodes
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        
        // Start DFS from node 0
        stack.push(0);
        visited.add(0);
        
        // Perform DFS to explore all connected nodes
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjancencyList.get(node)) {
                if (visited.contains(neighbour)) {
                    continue; // Skip visited neighbors to avoid cycle misdetection
                }
                visited.add(neighbour);
                stack.push(neighbour);
            }
        }
        
        // Check if all nodes were visited (graph is connected)
        if (visited.size() == n) {
            return true;
        }
        return false;
    }
}