public class Solution {
    // Problem: Minimum Cost to Connect Points
    // Given an array of points on a 2D plane, where points[i] = [xi, yi], find the minimum cost to connect
    // all points using a minimum spanning tree (MST), where the cost of connecting two points is their
    // Manhattan distance (|xi - xj| + |yi - yj|). Return the total cost of the MST.

    // DSA Pattern: Minimum Spanning Tree (Prim's Algorithm)
    // This problem is solved using Prim's algorithm to find the MST of a complete graph where nodes are points,
    // and edge weights are Manhattan distances. We greedily select the point with the minimum connection cost
    // to the current MST, updating distances to remaining points.

    // Approach:
    // 1. Initialize a boolean array inMST to track points included in the MST.
    // 2. Initialize an array minDist to store the minimum cost to connect each point to the MST, starting
    //    with Integer.MAX_VALUE except for the first point (minDist[0] = 0).
    // 3. For each of n iterations:
    //    - Find the unvisited point with the smallest minDist value (next point to add to MST).
    //    - Mark this point as included in the MST and add its minDist to the total cost.
    //    - Update minDist for all unvisited points by calculating the Manhattan distance to the newly added point.
    // 4. Return the total cost of the MST.
    // 5. Use a helper function to compute the Manhattan distance between two points.

    // Key Points to Remember:
    // - The graph is complete (every pair of points has an edge with weight equal to Manhattan distance).
    // - Prim's algorithm ensures the minimum total cost by always choosing the cheapest edge to connect a new point.
    // - The inMST array prevents revisiting points, ensuring no cycles in the MST.
    // - The minDist array tracks the cheapest known edge to connect each point to the MST.
    // - Start from an arbitrary point (here, point 0) as all points must be connected.
    // - Edge cases:
    //   - n = 1: No edges needed, return 0.
    //   - n = 2: Cost is the Manhattan distance between the two points.
    //   - Points may have the same coordinates (distance 0), which is valid.
    // - The algorithm processes each point exactly once and updates distances efficiently.

    // Time Complexity: O(n^2)
    // - n is the number of points.
    // - The outer loop runs n times to select each point for the MST.
    // - In each iteration, finding the minimum distance point takes O(n).
    // - Updating minDist for remaining points takes O(n) per iteration (compute Manhattan distance).
    // - Total time is O(n * n) = O(n^2).
    // - Note: Using a priority queue could reduce this to O(n^2 * log n) for sparse updates, but the dense graph
    //   makes the array-based approach reasonable.

    // Space Complexity: O(n)
    // - The inMST array uses O(n) space.
    // - The minDist array uses O(n) space.
    // - No additional data structures are used beyond a few variables.
    // - Overall, the space complexity is O(n).

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        // Track whether a point is included in MST
        boolean[] inMST = new boolean[n];
        // Minimum cost to connect each point to MST (initialize with max values)
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0; // Start from point 0 with cost 0

        int result = 0;

        // Build MST by selecting n points
        for (int i = 0; i < n; i++) {
            int currPoint = -1;
            int currMinDist = Integer.MAX_VALUE;

            // Pick the unvisited point with the smallest distance
            for (int j = 0; j < n; j++) {
                if (!inMST[j] && minDist[j] < currMinDist) {
                    currMinDist = minDist[j];
                    currPoint = j;
                }
            }

            // Add selected point to MST
            inMST[currPoint] = true;
            result += currMinDist;

            // Update minimum distances for unvisited points
            for (int j = 0; j < n; j++) {
                if (!inMST[j]) {
                    int dist = manhattanDistance(points[currPoint], points[j]);
                    if (dist < minDist[j]) {
                        minDist[j] = dist;
                    }
                }
            }
        }

        return result;
    }

    // Helper function to compute Manhattan distance between two points
    private int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}