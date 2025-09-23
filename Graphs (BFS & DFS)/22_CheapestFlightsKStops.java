public class Solution {
    // Problem: Cheapest Flights Within K Stops
    // Given n cities (labeled 0 to n-1), a list of flights (each as [from, to, price]), a source city (src),
    // a destination city (dst), and a maximum number of stops (k), find the cheapest price to travel from
    // src to dst using at most k stops. Return -1 if no such route exists.

    // DSA Pattern: Bellman-Ford Algorithm (Modified)
    // This problem is solved using a modified Bellman-Ford algorithm to find the shortest path (cheapest price)
    // from src to dst with at most k stops (i.e., k+1 edges). The algorithm iteratively relaxes edges up to
    // k+1 times to account for the stop constraint, using a temporary array to store updated costs.

    // Approach:
    // 1. Initialize a costs array with Integer.MAX_VALUE for all cities, except costs[src] = 0.
    // 2. For each of k+1 iterations (to allow up to k stops):
    //    - Clone the current costs array to store new costs for this iteration.
    //    - For each flight [u, v, cost], if u is reachable (costs[u] != Integer.MAX_VALUE),
    //      update the cost to v as min(current cost to v, cost to u + flight cost).
    //    - Update the costs array with the new values after each iteration.
    // 3. After k+1 iterations, check costs[dst]:
    //    - If costs[dst] is Integer.MAX_VALUE, return -1 (no route exists).
    //    - Otherwise, return costs[dst] as the cheapest price.

    // Key Points to Remember:
    // - The graph is directed and weighted, with edges representing flights and weights as prices.
    // - At most k stops means a path can have up to k+1 edges (k intermediate nodes).
    // - Bellman-Ford is suitable here because it handles edge relaxation with a specific number of edges.
    // - Cloning the costs array ensures we only use costs from the previous iteration, avoiding in-iteration updates.
    // - Edge cases:
    //   - k = 0: Only direct flights from src to dst are allowed.
    //   - No route exists: Return -1 if dst is unreachable.
    //   - src = dst: Return 0 if no stops needed, but check if k allows reaching dst via other paths.
    // - The algorithm avoids cycles by limiting to k+1 iterations, and negative weights are not a concern (prices are non-negative).

    // Time Complexity: O(k * E)
    // - k is the maximum number of stops, E is the number of flights (edges).
    // - The outer loop runs k+1 times.
    // - In each iteration, we process all E flights to relax edges.
    // - Cloning the costs array takes O(n) per iteration, but since k * E dominates (E can be up to n*(n-1)),
    //   the total time is O(k * E).
    // - In practice, E is often much smaller than n^2, making this efficient.

    // Space Complexity: O(n)
    // - The costs array uses O(n) space, where n is the number of cities.
    // - The temporary array (temp) uses O(n) space.
    // - No additional data structures are used beyond a few variables.
    // - Overall, the space complexity is O(n).

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Initialize costs array with maximum values
        int[] costs = new int[n];
        java.util.Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0; // Source has cost 0

        // Iterate up to k+1 times to allow k stops
        for (int i = 0; i <= k; i++) {
            int[] temp = costs.clone(); // Clone costs for this iteration
            for (int[] flight : flights) {
                int u = flight[0], v = flight[1], cost = flight[2];
                if (costs[u] == Integer.MAX_VALUE) continue; // Skip if source u is unreachable
                if (temp[v] > costs[u] + cost) {
                    temp[v] = costs[u] + cost; // Update cost to v
                }
            }
            costs = temp; // Update costs for next iteration
        }

        // Return -1 if dst is unreachable, else return cheapest cost
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
}