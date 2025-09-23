class Solution {
    // Problem: Number of Provinces
    // Given an n x n matrix isConnected where isConnected[i][j] == 1 indicates a direct connection between cities i and j,
    // and isConnected[i][j] == 0 indicates no direct connection, find the number of provinces. A province is a group of
    // directly or indirectly connected cities, representing a connected component in an undirected graph.

    // DSA Pattern: Breadth-First Search (BFS) with Adjacency Matrix
    // This problem is solved using BFS to identify connected components in the graph. Each unvisited city triggers a BFS
    // to mark all connected cities as visited, incrementing the province count for each new component found.
    // The adjacency matrix is used directly to check connections, avoiding the need for an explicit adjacency list.

    // Approach:
    // 1. Initialize a boolean array visited of size n to track visited cities.
    // 2. Iterate through each city (0 to n-1):
    //    - If a city is unvisited, perform BFS starting from it and increment the province count.
    // 3. In BFS:
    //    - Initialize a queue, add the starting city, and mark it as visited.
    //    - While the queue is not empty, dequeue a city and explore all other cities (0 to n-1).
    //    - For each city i, if isConnected[city][i] == 1 and i is unvisited, mark it as visited and enqueue it.
    // 4. Return the total number of provinces (connected components).

    // Key Points to Remember:
    // - The input matrix is symmetric (isConnected[i][j] == isConnected[j][i]) since the graph is undirected.
    // - Self-loops (isConnected[i][i]) are typically 1 but do not affect the algorithm as they are not enqueued.
    // - BFS ensures all cities in a connected component are visited before moving to the next component.
    // - Each node is visited exactly once, and each edge is checked at most twice (in both directions).
    // - Edge cases:
    //   - n = 1: If isConnected[0][0] == 1 or 0, it’s one province (single node).
    //   - Fully disconnected graph (all 0s except possibly diagonals): n provinces.
    //   - Fully connected graph: 1 province.
    // - The adjacency matrix allows O(1) checks for connections, but requires O(n) per node to scan all possible neighbors.

    // Time Complexity: O(n^2)
    // - n is the number of cities (size of the isConnected matrix).
    // - The outer loop runs O(n) times to check each city.
    // - BFS visits each node exactly once, and for each node, it checks all n possible neighbors in the matrix.
    // - Total edge checks are O(n^2) since each of the n nodes scans n entries in the matrix.
    // - Overall, the time complexity is O(n^2).

    // Space Complexity: O(n)
    // - The visited array uses O(n) space to track visited cities.
    // - The queue in BFS uses O(n) space in the worst case (e.g., all nodes in one component).
    // - No additional space for the graph since the input matrix is used directly.
    // - Overall, the space complexity is O(n).

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void bfs(int[][] isConnected, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[city][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}