class Solution {
    // Problem: Swim in Rising Water
    // Given an n x n grid where each cell grid[i][j] represents the elevation at position (i, j), find the
    // least time required to swim from the top-left cell (0, 0) to the bottom-right cell (n-1, n-1). You can
    // swim to adjacent cells (up, down, left, right) if the elevation is at most the current time t. The time
    // required is the maximum elevation encountered along the path.

    // DSA Pattern: Binary Search + Depth-First Search (DFS)
    // This problem is solved using binary search to find the minimum time (maximum elevation) required to reach
    // the destination, combined with DFS to check if a path exists for a given time. The grid is treated as a
    // graph where edges connect adjacent cells, and we can only traverse cells with elevation <= time.

    // Approach:
    // 1. Use binary search to find the minimum time t that allows a path from (0, 0) to (n-1, n-1):
    //    - The search range is from grid[0][0] (minimum possible time) to n*n-1 (maximum possible elevation).
    //    - For each mid time, check if a path exists using DFS.
    //    - If a path exists, try a lower time (right = mid); otherwise, try a higher time (left = mid + 1).
    // 2. DFS:
    //    - Start from (0, 0) and explore adjacent cells (up, down, left, right).
    //    - Skip cells that are out of bounds, already visited, or have elevation > time.
    //    - Return true if (n-1, n-1) is reached; otherwise, return false.
    //    - Use a visited array to avoid revisiting cells.
    // 3. Return the minimum time found by binary search.

    // Key Points to Remember:
    // - The time required is the maximum elevation along the path, so we binary search over possible times.
    // - DFS checks if a valid path exists for a given time t by ensuring all cells in the path have elevation <= t.
    // - The grid is a directed graph where edges exist between adjacent cells if their elevations allow passage.
    // - Binary search optimizes the solution by reducing the number of DFS calls.
    // - Edge cases:
    //   - n = 1: Return grid[0][0] (no movement needed).
    //   - High elevations: Maximum elevation is n*n-1 (since grid values are 0 to n*n-1).
    //   - No path exists: Input guarantees a valid path, but DFS handles validation.
    // - The visited array prevents cycles and ensures each cell is processed once per DFS.

    // Time Complexity: O(n^2 * log(n^2))
    // - n is the size of the grid (n x n).
    // - Binary search range is O(n^2), so it takes O(log(n^2)) = O(2 * log n) iterations.
    // - For each binary search iteration, DFS explores up to O(n^2) cells in the worst case.
    // - Total time is O(n^2 * log(n^2)).
    // - Checking grid[i][j] <= time and visited array operations are O(1).

    // Space Complexity: O(n^2)
    // - The visited array uses O(n^2) space.
    // - The recursion stack for DFS uses O(n^2) space in the worst case (e.g., a spiral path).
    // - No additional data structures are used beyond the input grid.
    // - Overall, the space complexity is O(n^2).

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int left = grid[0][0], right = n * n - 1;

        // Binary search to find the minimum time
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canReach(grid, mid)) {
                right = mid; // Try a lower time
            } else {
                left = mid + 1; // Need a higher time
            }
        }

        return left;
    }

    private boolean canReach(int[][] grid, int time) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        return dfs(grid, visited, 0, 0, time);
    }

    private boolean dfs(int[][] grid, boolean[][] visited, int i, int j, int time) {
        int n = grid.length;

        // Check bounds, visited status, and elevation constraint
        if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j] || grid[i][j] > time) {
            return false;
        }

        // Reached destination
        if (i == n - 1 && j == n - 1) {
            return true;
        }

        visited[i][j] = true;

        // Explore all four directions
        return dfs(grid, visited, i + 1, j, time) ||
               dfs(grid, visited, i - 1, j, time) ||
               dfs(grid, visited, i, j + 1, time) ||
               dfs(grid, visited, i, j - 1, time);
    }
}