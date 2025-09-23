// Problem: Walls and Gates
// Given a m x n 2D grid initialized with three possible values:
// - -1: A wall or obstacle
// - 0: A gate
// - Integer.MAX_VALUE: An empty room
// Fill each empty room with the distance to its nearest gate (measured as the number of steps in a 4-directional path).
// If a room cannot be reached by any gate, it should remain Integer.MAX_VALUE.

// DSA Pattern: Breadth-First Search (BFS) / Graph Traversal
// This problem is solved using BFS starting from all gates simultaneously to compute the shortest distance to each empty room.
// BFS ensures that rooms are filled with the minimum distance from the nearest gate.

// Approach:
// 1. If the grid is null or empty, return immediately (no processing needed).
// 2. Initialize a queue to store the coordinates of gates (cells with value 0).
// 3. Iterate through the grid to find and enqueue all gates.
// 4. Perform BFS starting from all gates:
//    - Dequeue a cell (starting with a gate or a processed room).
//    - For each of its 4-directional neighbors (up, down, left, right):
//      - Check if the neighbor is within bounds and is an empty room (Integer.MAX_VALUE).
//      - If valid, update the neighbor's distance as the current cell's distance + 1.
//      - Enqueue the neighbor to process its neighbors in the next iteration.
// 5. Modify the grid in-place to store the shortest distance to each empty room from the nearest gate.

// Key Points to Remember:
// - The grid contains three types of cells: gates (0), walls (-1), and empty rooms (Integer.MAX_VALUE).
// - BFS from all gates simultaneously ensures the shortest distance to each room, as closer gates are processed first.
// - The grid is modified in-place, and only empty rooms are updated.
// - Edge cases:
//   - Empty grid or null grid: return immediately.
//   - No gates: no changes to the grid (all empty rooms remain Integer.MAX_VALUE).
//   - No empty rooms: no updates needed after finding gates.
// - The solution assumes a rectangular grid where each row has the same number of columns.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - Initial grid traversal to find gates takes O(m * n).
// - BFS visits each cell at most once (when its distance is first updated), taking O(m * n).
// - Each neighbor check is constant time (4 directions), and queue operations are efficient.

// Space Complexity: O(m * n) in the worst case
// - The queue may store up to O(m * n) cells in the worst case (e.g., grid with many gates and few walls).
// - The directions array requires O(1) space.
// - The grid is modified in-place, so no extra grid storage is needed.

// Constraints:
// - m == rooms.length
// - n == rooms[i].length
// - 1 <= m, n <= 250
// - rooms[i][j] is either -1, 0, or Integer.MAX_VALUE (2^31 - 1)

class Solution {
    public void wallsAndGates(int[][] rooms) {
        // Handle base case: null or empty grid
        if (rooms == null || rooms.length == 0) return;

        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();

        // Enqueue all gates (cells with value 0)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        // Define 4-directional movements (down, up, right, left)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Perform BFS starting from all gates simultaneously
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0], col = point[1];

            // Check all 4-directional neighbors
            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];

                // Skip if out of bounds or not an empty room
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != Integer.MAX_VALUE) {
                    continue;
                }

                // Update distance to nearest gate and enqueue the room
                rooms[r][c] = rooms[row][col] + 1;
                queue.offer(new int[] {r, c});
            }
        }
    }
}