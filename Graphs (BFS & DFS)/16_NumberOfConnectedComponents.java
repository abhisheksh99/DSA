class Solution {
    // Problem: Number of Connected Components in an Undirected Graph
    // Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
    // count the number of connected components in the graph. A connected component is a group of nodes
    // where each node is reachable from any other node in the group via edges.

    // DSA Pattern: Depth-First Search (DFS) with Adjacency List
    // This problem is solved using DFS to explore all nodes in each connected component. An adjacency list
    // represents the undirected graph, and a visited array tracks explored nodes. Each unvisited node triggers
    // a DFS to mark all nodes in its component, incrementing the component count.

    // Approach:
    // 1. Initialize an adjacency list for n nodes and populate it with undirected edges (add both directions).
    // 2. Initialize a visited array to track explored nodes (0 = unvisited, 1 = visited).
    // 3. Iterate through all nodes (0 to n-1):
    //    - If a node is unvisited, increment the component counter and perform DFS from that node.
    // 4. In DFS:
    //    - Mark the current node as visited.
    //    - Recursively visit all unvisited neighbors in the adjacency list.
    // 5. Return the total number of connected components.

    // Key Points to Remember:
    // - The graph is undirected, so each edge [u, v] is added to both u’s and v’s adjacency lists.
    // - DFS ensures all nodes in a connected component are visited before moving to the next component.
    // - Each node is visited exactly once, and each edge is explored at most twice (once per direction).
    // - Edge cases:
    //   - n = 0: No components (return 0).
    //   - No edges: Each node is its own component (return n).
    //   - Fully connected graph: One component (return 1).
    // - The visited array prevents revisiting nodes, avoiding infinite loops in the undirected graph.
    // - Use an adjacency list for efficient neighbor traversal, especially in sparse graphs.

    // Time Complexity: O(n + e)
    // - n is the number of nodes, and e is the number of edges.
    // - Building the adjacency list takes O(e) time, as each edge is added to two lists.
    // - DFS visits each node exactly once (O(n)) and explores each edge at most twice (O(e)).
    // - Overall, the time complexity is O(n + e).

    // Space Complexity: O(n + e)
    // - The adjacency list uses O(n + e) space (n for the list array, e for the edges).
    // - The visited array uses O(n) space.
    // - The recursion stack for DFS uses O(n) space in the worst case (e.g., a linear graph).
    // - Overall, the space complexity is O(n + e).

    public int countComponents(int n, int[][] edges) {
        int counter = 0;
        int[] visited = new int[n];

        // Initialize adjacency list for each node
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        // Build undirected graph by adding edges in both directions
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }

        // Count connected components using DFS
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                counter++;
                dfs(adjList, visited, i);
            }
        }

        return counter;
    }

    private void dfs(List<Integer>[] adjList, int[] visited, int node) {
        // Mark current node as visited
        visited[node] = 1;

        // Explore all unvisited neighbors
        for (int i = 0; i < adjList[node].size(); i++) {
            if (visited[adjList[node].get(i)] == 0) {
                dfs(adjList, visited, adjList[node].get(i));
            }
        }
    }
}