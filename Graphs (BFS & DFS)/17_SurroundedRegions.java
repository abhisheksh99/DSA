public class Solution {
    // Problem: Surrounded Regions
    // Given an m x n matrix board containing 'X' and 'O', capture all regions that are surrounded by 'X'.
    // A region is captured by flipping all 'O's to 'X's in that surrounded region. A region is surrounded
    // if it is not connected to an 'O' on the boundary of the board (top, bottom, left, or right edges).

    // DSA Pattern: Depth-First Search (DFS) with Grid Traversal
    // This problem is solved using DFS to identify 'O' regions connected to the boundary. We mark these
    // boundary-connected 'O's as uncapturable, then flip all remaining 'O's to 'X' and restore the marked
    // cells to 'O'. The grid is treated as a graph where each cell is a node, and adjacent 'O' cells are
    // connected.

    // Approach:
    // 1. Check if the board is null or empty; if so, return.
    // 2. Iterate over the boundary cells (first/last row and first/last column):
    //    - If a cell contains 'O', use DFS to mark it and all connected 'O' cells as 'T' (temporary marker).
    // 3. DFS:
    //    - For a given cell (i, j), if it’s out of bounds or not 'O', return.
    //    - Mark the cell as 'T' and recursively explore all four adjacent cells (up, down, left, right).
    // 4. Iterate over the entire board:
    //    - Convert remaining 'O' cells to 'X' (these are surrounded regions).
    //    - Convert 'T' cells back to 'O' (these are boundary-connected and uncapturable).
    // 5. The board is modified in-place.

    // Key Points to Remember:
    // - A region of 'O's is surrounded if none of its cells are connected (directly or indirectly) to a
    //   boundary 'O'.
    // - Use DFS to traverse connected 'O' regions starting from the boundary to mark them as uncapturable.
    // - The temporary marker 'T' distinguishes boundary-connected 'O's from surrounded 'O's.
    // - Only boundary cells need to be checked initially, as any 'O' region touching the boundary cannot be
    //   surrounded.
    // - Edge cases:
    //   - Empty board or null board: Return immediately.
    //   - Single cell: If 'O', it’s on the boundary, so no change; if 'X', no change.
    //   - All 'X' or all 'O' board: Handle appropriately (all 'O' remains 'O' if connected to boundary).
    // - The solution modifies the board in-place, so no additional output is returned.

    // Time Complexity: O(m * n)
    // - m is the number of rows, n is the number of columns.
    // - Boundary traversal takes O(2m + 2n) to check first/last rows and columns.
    // - DFS visits each cell at most once, as each 'O' is marked 'T' and not revisited, taking O(m * n).
    // - Final board sweep to flip 'O' to 'X' and 'T' to 'O' takes O(m * n).
    // - Overall, the time complexity is O(m * n).

    // Space Complexity: O(m * n)
    // - The recursion stack for DFS can use up to O(m * n) space in the worst case (e.g., a board filled
    //   with 'O's, requiring a deep recursion).
    // - No additional data structures are used beyond the input board (modified in-place).
    // - Overall, the space complexity is O(m * n) due to the recursion stack.

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        // Step 1: Mark boundary-connected 'O's with 'T'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        // Step 2: Capture surrounded regions ('O' to 'X') and revert 'T' to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // Surrounded region
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O'; // Boundary-connected region
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        // Check bounds and ensure cell is 'O'
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'T'; // Mark as visited (boundary-connected)
        // Explore all four adjacent cells
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }
}