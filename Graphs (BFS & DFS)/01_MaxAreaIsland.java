// Problem: Max Area of Island
// Given an m x n binary grid representing a map of '1's (land) and '0's (water), return the maximum area of an island.
// An island is a group of '1's connected 4-directionally (up, down, left, right). The area is the number of cells in the island.

// DSA Pattern: Depth-First Search (DFS) / Graph Traversal
// This problem is solved using a depth-first search (DFS) approach to explore connected land cells (1s) in the grid,
// calculating the area of each island and tracking the maximum area found.

// Approach:
// 1. If the grid is null or empty, return 0 (no islands possible).
// 2. Iterate through each cell in the grid using nested loops:
//    - If a cell contains a '1' (land), perform DFS to calculate the area of the island starting from that cell.
//    - Update the maximum area if the current island's area is larger.
// 3. In the DFS helper function:
//    - Check if the current cell is out of bounds or is water (0); if so, return 0.
//    - Mark the current cell as visited by setting it to 0 to avoid revisiting.
//    - Count the current cell (area = 1) and recursively explore its 4-directional neighbors (up, down, left, right).
//    - Sum the areas from all valid neighbors and return the total area of the island.
// 4. Return the maximum area found after checking all cells.

// Key Points to Remember:
// - An island is a group of '1's connected 4-directionally (not diagonally).
// - DFS is used to explore all connected land cells in an island, marking them as visited to avoid cycles.
// - The grid is modified in-place to mark visited cells (set to 0), eliminating the need for a separate visited array.
// - Edge cases:
//   - Empty grid or no land cells: return 0.
//   - Single cell with '1': return 1.
//   - Multiple islands: return the area of the largest island.
// - The solution assumes a rectangular grid where each row has the same number of columns.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - The algorithm visits each cell at most once, either during the initial grid traversal or during DFS.
// - Each DFS operation involves constant-time checks (bounds and value checks).

// Space Complexity: O(m * n) in the worst case due to the recursion stack
// - The recursion stack can go as deep as the size of the largest island (up to m * n in a grid full of 1s).
// - No additional data structures are used beyond the recursion stack, as the grid is modified in-place.

// Constraints:
// - m == grid.length
// - n == grid[i].length
// - 1 <= m, n <= 50
// - grid[i][j] is either 0 or 1

public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // Handle base case: null or empty grid
        if (grid == null || grid.length == 0) return 0;
        
        int maxArea = 0;
        // Iterate through each cell in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // If the cell is land, calculate the island's area using DFS
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
    
    // Helper function to perform DFS and calculate the area of an island
    private int dfs(int[][] grid, int i, int j) {
        // Base case: out of bounds or water cell
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }
        
        // Mark the current cell as visited
        grid[i][j] = 0;
        // Count the current cell
        int area = 1;
        
        // Recursively explore 4-directional neighbors
        area += dfs(grid, i + 1, j); // Down
        area += dfs(grid, i - 1, j); // Up
        area += dfs(grid, i, j + 1); // Right
        area += dfs(grid, i, j - 1); // Left
        
        return area;
    }
}