// Problem: Rotting Oranges
// Given a m x n grid where each cell can be 0 (empty), 1 (fresh orange), or 2 (rotten orange), return the minimum number of minutes
// required for all fresh oranges to become rotten. A rotten orange can rot adjacent fresh oranges (up, down, left, right) in one minute.
// If it is impossible for all fresh oranges to rot, return -1.

// DSA Pattern: Breadth-First Search (BFS) / Graph Traversal
// This problem is solved using BFS to simulate the rotting process level by level, where each level represents one minute.
// Rotten oranges spread to adjacent fresh oranges simultaneously, making BFS ideal for tracking the minimum time.

// Approach:
// 1. If the grid is null or empty, return -1 (invalid input).
// 2. Initialize a queue to store the coordinates of rotten oranges and count the number of fresh oranges.
// 3. Iterate through the grid to:
//    - Count fresh oranges (cells with value 1).
//    - Enqueue the coordinates of rotten oranges (cells with value 2).
// 4. If there are no fresh oranges, return 0 (no rotting needed).
// 5. Perform BFS:
//    - Process all rotten oranges at the current level (queue size).
//    - For each rotten orange, check its 4-directional neighbors (up, down, left, right).
//    - If a neighbor is a fresh orange (1), mark it as rotten (2), decrement the fresh count, and enqueue its coordinates.
//    - After processing each level, increment the minute counter.
// 6. After BFS, check if all fresh oranges have rotted (freshCount == 0).
//    - If yes, return minutes - 1 (subtract 1 to account for the extra increment in the last iteration).
//    - If no, return -1 (some fresh oranges remain unreachable).
// 7. Use a directions array to simplify 4-directional neighbor checks.

// Key Points to Remember:
// - Each rotten orange can rot adjacent fresh oranges in one minute, and the process happens simultaneously (BFS ensures level-by-level processing).
// - The grid is modified in-place to mark fresh oranges as rotten (1 → 2).
// - Edge cases:
//   - No fresh oranges: return 0.
//   - No rotten oranges and fresh oranges exist: return -1 (impossible to rot all).
//   - Empty grid or invalid input: return -1.
//   - Single cell with no fresh oranges: return 0.
// - The solution assumes a rectangular grid where each row has the same number of columns.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - Initial grid traversal to count fresh oranges and enqueue rotten oranges takes O(m * n).
// - BFS visits each cell at most once (when it turns from fresh to rotten), taking O(m * n).
// - Each neighbor check is constant time (4 directions), and queue operations are efficient.

// Space Complexity: O(m * n) in the worst case
// - The queue may store up to O(m * n) cells if most cells are rotten at the start (e.g., grid full of 2s).
// - Additional space includes the directions array (O(1)) and variables (O(1)).
// - The grid is modified in-place, so no extra grid storage is needed.

// Constraints:
// - m == grid.length
// - n == grid[i].length
// - 1 <= m, n <= 10
// - grid[i][j] is 0, 1, or 2

public class Solution {
    public int orangesRotting(int[][] grid) {
        // Handle base case: null or empty grid
        if (grid == null || grid.length == 0) return -1;

        int m = grid.length, n = grid[0].length;
        int freshCount = 0;
        Queue<int[]> rottenQueue = new LinkedList<>();

        // Count fresh oranges and enqueue rotten oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    rottenQueue.offer(new int[]{i, j});
                }
            }
        }

        // If no fresh oranges, no rotting is needed
        if (freshCount == 0) return 0;

        int minutes = 0;
        // Define 4-directional movements (down, up, right, left)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Perform BFS to simulate rotting process
        while (!rottenQueue.isEmpty()) {
            int size = rottenQueue.size();
            // Process all rotten oranges at the current minute
            for (int i = 0; i < size; i++) {
                int[] rotten = rottenQueue.poll();
                // Check 4-directional neighbors
                for (int[] dir : directions) {
                    int x = rotten[0] + dir[0];
                    int y = rotten[1] + dir[1];
                    // If neighbor is a fresh orange, rot it and enqueue
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2; // Mark as rotten
                        freshCount--;
                        rottenQueue.offer(new int[]{x, y});
                    }
                }
            }
            minutes++; // Increment time after processing a level
        }

        // Return minutes - 1 if all oranges rotted, else -1
        return freshCount == 0 ? minutes - 1 : -1;
    }
}