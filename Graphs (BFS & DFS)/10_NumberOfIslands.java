// Problem: Number of Islands
// Given an m x n 2D binary grid representing a map of '1's (land) and '0's (water), return the number of islands.
// An island is a group of '1's connected 4-directionally (up, down, left, right) and surrounded by water.

// DSA Pattern: Depth-First Search (DFS) / Graph Traversal
// This problem is solved using DFS to explore and count connected components of land cells ('1's) in the grid.
// Each unvisited land cell triggers a DFS to mark all connected land cells as visited, incrementing the island count.

// Approach:
// 1. If the grid is null or empty, return 0 (no islands possible).
// 2. Initialize class variables for the grid, number of rows, and number of columns for easy access in DFS.
// 3. Iterate through each cell in the grid:
//    - If a cell contains a '1' (land), perform DFS to mark all connected land cells as visited (set to '0').
//    - Increment the island count for each new island found.
// 4. In the DFS function:
//    - Check if the current cell is out of bounds or is water ('0'); if so, return.
//    - Mark the current cell as visited by setting it to '0'.
//    - Recursively explore the 4-directional neighbors (up, down, left, right).
// 5. Return the total number of islands found.

// Key Points to Remember:
// - An island is a group of '1's connected 4-directionally (not diagonally).
// - DFS is used to explore all connected land cells in an island, marking them as visited to avoid revisiting.
// - The grid is modified in-place to mark visited cells (set to '0'), eliminating the need for a separate visited array.
// - Edge cases:
//   - Empty grid or no land cells: return 0.
//   - Single cell with '1': return 1.
//   - Grid with no islands: return 0.
// - The solution assumes a rectangular grid where each row has the same number of columns.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - The algorithm visits each cell at most once, either during the initial grid traversal or during DFS.
// - Each DFS operation involves constant-time checks (bounds and value checks).

// Space Complexity: O(m * n) in the worst case
// - The recursion stack for DFS can go as deep as O(m * n) in the worst case (e.g., a grid full of '1's).
// - No additional data structures are used beyond the recursion stack, as the grid is modified in-place.

// Constraints:
// - m == grid.length
// - n == grid[i].length
// - 1 <= m, n <= 300
// - grid[i][j] is '0' or '1'

class Solution {
    private int rows, cols;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        // Handle base case: null or empty grid
        if (grid == null || grid.length == 0) return 0;

        // Initialize class variables for grid dimensions and grid reference
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        int islandCount = 0;

        // Iterate through each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If the cell is land, perform DFS and increment island count
                if (grid[r][c] == '1') {
                    dfs(r, c);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    // Helper function to perform DFS and mark connected land cells as visited
    private void dfs(int r, int c) {
        // Base case: out of bounds or water cell
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') return;

        // Mark the current cell as visited
        grid[r][c] = '0';

        // Recursively explore 4-directional neighbors
        dfs(r + 1, c); // Down
        dfs(r - 1, c); // Up
        dfs(r, c + 1); // Right
        dfs(r, c - 1); // Left
    }
}