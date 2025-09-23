// Problem: Network Delay Time
// Given a directed weighted graph represented by an array of edges (times), the number of nodes (n), and a starting node (k),
// return the time it takes for a signal to reach all nodes from node k. If it is impossible for the signal to reach all nodes, return -1.
// Each edge in times is represented as [u, v, w], where u is the source node, v is the target node, and w is the time to travel from u to v.

// DSA Pattern: Dijkstra's Algorithm / Graph Traversal
// This problem is solved using Dijkstra's algorithm to find the shortest path from the starting node to all other nodes in a weighted directed graph,
// calculating the maximum time among the shortest paths to determine the total delay.

// Approach:
// 1. Build an adjacency list representation of the graph using a HashMap, where each key is a node and its value is a list of [neighbor, weight] pairs.
// 2. Initialize an array dist to store the minimum time to reach each node, initially set to infinity except for the starting node k (dist[k] = 0).
// 3. Use a priority queue to process nodes in order of increasing distance (time) from the starting node.
// 4. For each node dequeued from the priority queue:
//    - Skip if the current time to the node exceeds the known minimum time (dist[node]).
//    - For each neighbor of the current node, calculate the new time to reach the neighbor (current time + edge weight).
//    - If the new time is less than the known time for the neighbor, update dist[neighbor] and add the neighbor to the priority queue.
// 5. After processing, find the maximum value in dist[1...n]. If any node is unreachable (dist[i] == infinity), return -1.
// 6. Return the maximum time as the result.

// Key Points to Remember:
// - The graph is directed and weighted, with edges given as [u, v, w] (from u to v with weight w).
// - Dijkstra's algorithm ensures the shortest path to each node is computed by processing nodes in order of increasing distance.
// - A priority queue optimizes the selection of the next node with the smallest current distance.
// - Edge cases:
//   - If any node is unreachable (dist[i] == infinity), return -1.
//   - If n = 1, the result is 0 (signal only needs to reach the starting node).
//   - The graph may have cycles, but Dijkstra's algorithm handles them correctly by tracking minimum distances.
// - The solution assumes 1-based node indexing (nodes from 1 to n).

// Time Complexity: O(E * log V), where V is the number of nodes (n) and E is the number of edges (times.length)
// - Building the adjacency list takes O(E).
// - Each edge is processed at most once, and each operation involves a priority queue operation (O(log V) for insertion/removal).
// - Total complexity is O(E * log V + V * log V), simplified to O(E * log V) since E >= V in a connected graph.

// Space Complexity: O(V + E)
// - The adjacency list requires O(E) space for edges and O(V) for nodes.
// - The dist array requires O(V) space.
// - The priority queue requires O(V) space in the worst case (all nodes in the queue).
// - Total space is O(V + E).

// Constraints:
// - 1 <= n <= 100
// - 1 <= k <= n
// - 1 <= times.length <= 6000
// - times[i].length == 3
// - 1 <= times[i][0], times[i][1] <= n
// - 1 <= times[i][2] <= 1000
// - All pairs (times[i][0], times[i][1]) are unique (no duplicate edges).

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list representation of the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // Initialize distances array with infinity, except for starting node
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Priority queue to store [node, time] pairs, sorted by time
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{k, 0});

        // Process nodes using Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], time = curr[1];

            // Skip if a shorter path to this node was already found
            if (time > dist[node]) continue;

            // Explore neighbors if the current node has outgoing edges
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nei = neighbor[0], t = neighbor[1];
                    // If a shorter path to the neighbor is found, update distance
                    if (dist[nei] > time + t) {
                        dist[nei] = time + t;
                        pq.offer(new int[]{nei, dist[nei]});
                    }
                }
            }
        }

        // Find the maximum time to reach any node
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1; // Node unreachable
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }
}