// Problem: Pacific Atlantic Water Flow
// Given an m x n rectangular island represented by a 2D array heights, where heights[i][j] represents the height of the cell (i, j),
// return a list of grid coordinates where water can flow to both the Pacific and Atlantic oceans.
// Water can flow from a cell to an ocean or an adjacent cell if the adjacent cell's height is less than or equal to the current cell's height.
// The Pacific Ocean touches the left and top edges, and the Atlantic Ocean touches the right and bottom edges.

// DSA Pattern: Depth-First Search (DFS) / Graph Traversal
// This problem is solved using DFS to determine which cells can flow to the Pacific and Atlantic oceans.
// By starting DFS from the ocean borders and flowing "uphill" (to cells with equal or greater height), we identify reachable cells.

// Approach:
// 1. If the grid is empty or null, return an empty list.
// 2. Create two boolean arrays to track cells that can reach the Pacific and Atlantic oceans.
// 3. Perform DFS starting from:
//    - Pacific borders: left column (j=0) and top row (i=0).
//    - Atlantic borders: right column (j=col-1) and bottom row (i=row-1).
// 4. In the DFS function:
//    - Mark the current cell as reachable in the corresponding boolean array.
//    - Explore 4-directional neighbors (up, down, left, right).
//    - For each neighbor, proceed only if:
//      - It is within bounds.
//      - It has not been visited (not marked reachable).
//      - Its height is greater than or equal to the current cell's height (water flows to equal or higher cells).
//    - Recursively apply DFS to valid neighbors.
// 5. Iterate through the grid to find cells that are reachable by both oceans (Pacific and Atlantic).
// 6. Return the list of coordinates for cells that can flow to both oceans.

// Key Points to Remember:
// - Water flows from a cell to an adjacent cell if the adjacent cell's height is less than or equal to the current cell's height.
// - Starting DFS from the ocean borders and flowing "uphill" simplifies the problem by simulating reverse flow.
// - Two separate DFS traversals are needed: one for the Pacific and one for the Atlantic.
// - A cell must be marked in both boolean arrays to be included in the result.
// - Edge cases:
//   - Empty grid: return empty list.
//   - Single cell: check if it can reach both oceans (Pacific and Atlantic).
//   - No cells reachable by both oceans: return empty list.
// - The solution assumes a rectangular grid where each row has the same number of columns.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - Each cell is visited at most once per DFS traversal (Pacific and Atlantic), so each DFS takes O(m * n).
// - Initial border traversals take O(m + n) to start DFS from border cells.
// - Final traversal to collect results takes O(m * n).
// - Total complexity is O(m * n).

// Space Complexity: O(m * n)
// - The two boolean arrays (pacificReachable and atlanticReachable) require O(m * n) space.
// - The recursion stack for DFS can go as deep as O(m * n) in the worst case (e.g., a linear path through the grid).
// - The output list requires O(k) space, where k is the number of cells that can reach both oceans (k <= m * n).
// - The directions array requires O(1) space.

// Constraints:
// - m == heights.length
// - n == heights[i].length
// - 1 <= m, n <= 200
// - 0 <= heights[i][j] <= 10^5

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // Handle base case: empty or null grid
        if (heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int row = heights.length;
        int col = heights[0].length;

        // Arrays to track cells reachable by Pacific and Atlantic oceans
        boolean[][] pacificReachable = new boolean[row][col];
        boolean[][] atlanticReachable = new boolean[row][col];

        // Start DFS from Pacific borders: left column and top row
        for (int i = 0; i < row; i++) {
            dfs(i, 0, pacificReachable, heights);
            dfs(i, col - 1, atlanticReachable, heights);
        }

        // Start DFS from Atlantic borders: top row and bottom row
        for (int i = 0; i < col; i++) {
            dfs(0, i, pacificReachable, heights);
            dfs(row - 1, i, atlanticReachable, heights);
        }

        // Collect cells that can reach both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    // Helper function to perform DFS and mark reachable cells
    private void dfs(int row, int col, boolean[][] reachable, int[][] heights) {
        // Define 4-directional movements (right, down, up, left)
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        // Mark the current cell as reachable
        reachable[row][col] = true;

        // Explore all 4-directional neighbors
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Skip if out of bounds
            if (newRow < 0 || newRow >= heights.length || newCol < 0 || newCol >= heights[0].length) {
                continue;
            }

            // Skip if already visited
            if (reachable[newRow][newCol]) {
                continue;
            }

            // Skip if the neighbor's height is less than the current cell's height
            if (heights[newRow][newCol] < heights[row][col]) {
                continue;
            }

            // Recursively explore the valid neighbor
            dfs(newRow, newCol, reachable, heights);
        }
    }
}