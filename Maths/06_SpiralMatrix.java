// Problem: Spiral Matrix
// Given an m x n matrix, return all elements of the matrix in spiral order (clockwise starting from the top-left corner).
// The spiral order traverses the matrix layer by layer, moving right, down, left, and up, spiraling inward.

// DSA Pattern: Matrix / Simulation
// This problem is solved by simulating the spiral traversal of the matrix using a direction array to guide movement.
// A visited marker is used to track processed elements, and direction changes are managed to maintain the spiral pattern.

// Approach:
// 1. Initialize variables:
//    - rows (m) and columns (n) to store matrix dimensions.
//    - visited marker (101, outside the constraint range [-100, 100]) to mark processed cells.
//    - row and col to track the current position, starting at (0,0).
//    - directions array to represent movements: right (0,1), down (1,0), left (0,-1), up (-1,0).
//    - currentdirection to track the current movement direction (0 to 3).
//    - changedirection to count consecutive direction changes (stops when 2 consecutive changes occur).
//    - ans list to store the spiral order elements.
// 2. Add the first element (matrix[0][0]) to ans and mark it as visited.
// 3. While fewer than 2 consecutive direction changes have occurred:
//    - Move in the current direction if the next position is valid (within bounds and not visited):
//      - Update row and col using the current direction.
//      - Add the new element to ans and mark it as visited.
//      - Reset changedirection to 0 (valid move made).
//    - If the move is invalid, change direction (currentdirection = (currentdirection + 1) % 4) and increment changedirection.
// 4. Stop when changedirection reaches 2 (indicating no valid moves in two consecutive directions).
// 5. Return the ans list containing the spiral order.

// Key Points to Remember:
// - The spiral pattern follows right -> down -> left -> up, repeating inward.
// - The visited marker (101) is safe since matrix elements are in [-100, 100].
// - The changedirection counter detects when the spiral is complete (no valid moves in two consecutive directions).
// - The directions array simplifies movement logic, and modulo 4 ensures cyclic direction changes.
// - Edge cases:
//   - 1x1 matrix: returns single element.
//   - 1xn or mx1 matrix: linear traversal (right or down).
//   - Empty matrix: not applicable (constraints ensure m,n >= 1).
// - The algorithm modifies the input matrix to mark visited cells but does not use extra space beyond the output list.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - Each cell in the m x n matrix is visited exactly once.
// - Each iteration performs O(1) operations (boundary checks, list addition, matrix update).
// - Total operations are proportional to m * n.

// Space Complexity: O(m * n) for the output list, O(1) extra space
// - The ans list stores m * n elements, as required by the problem (not counted as extra space).
// - The directions array and other variables (row, col, etc.) use O(1) space.
// - The input matrix is modified in-place to mark visited cells, avoiding additional data structures.

// Constraints:
// - m == matrix.length, n == matrix[i].length
// - 1 <= m, n <= 10
// - -100 <= matrix[i][j] <= 100

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Initialize dimensions
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Marker for visited cells
        int visited = 101;
        // Current position
        int row = 0, col = 0;
        // Directions: right, down, left, up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Track current direction and consecutive direction changes
        int currentdirection = 0, changedirection = 0;
        // Result list
        List<Integer> ans = new ArrayList<>();
        
        // Add first element and mark as visited
        ans.add(matrix[0][0]);
        matrix[0][0] = visited;
        
        // Continue until two consecutive direction changes
        while (changedirection < 2) {
            // Move in current direction if valid
            while (row + directions[currentdirection][0] >= 0 &&
                   row + directions[currentdirection][0] < rows &&
                   col + directions[currentdirection][1] >= 0 &&
                   col + directions[currentdirection][1] < columns &&
                   matrix[row + directions[currentdirection][0]][col + directions[currentdirection][1]] != visited) {
                // Reset direction change counter
                changedirection = 0;
                // Update position
                row += directions[currentdirection][0];
                col += directions[currentdirection][1];
                // Add element and mark as visited
                ans.add(matrix[row][col]);
                matrix[row][col] = visited;
            }
            // Change direction and increment change counter
            currentdirection = (currentdirection + 1) % 4;
            changedirection++;
        }
        
        return ans;
    }
}