// Problem: Set Matrix Zeroes
// Given an m x n integer matrix, if an element is 0, set its entire row and column to 0. Do this in-place.

// DSA Pattern: Matrix / In-Place Manipulation
// This problem is solved by using the first row and first column of the matrix as markers to indicate which rows and columns should be set to zero, minimizing extra space. A separate flag is used to handle the first column separately to avoid conflicts.

// Approach:
// 1. Initialize a boolean flag (firstcol) to track if the first column needs to be zeroed.
// 2. Get matrix dimensions: r (rows) and c (columns).
// 3. First pass: Scan the matrix to mark rows and columns to be zeroed:
//    - Check if matrix[i][0] is 0; if so, set firstcol to true.
//    - For each element matrix[i][j] (j starting from 1), if it’s 0, mark matrix[i][0] and matrix[0][j] as 0.
// 4. Second pass: Set elements to 0 based on markers:
//    - For each cell (i,j) (i,j starting from 1), if matrix[i][0] or matrix[0][j] is 0, set matrix[i][j] to 0.
// 5. Handle the first row: If matrix[0][0] is 0, set the entire first row to 0.
// 6. Handle the first column: If firstcol is true, set the entire first column to 0.

// Key Points to Remember:
// - The first row and column are used as markers to avoid using extra space.
// - A separate firstcol flag is needed because matrix[0][0] is shared by both the first row and first column, which could cause conflicts.
// - The algorithm processes the matrix in two passes to ensure markers are set before modifying the matrix.
// - Edge cases:
//   - Single row or column: Handled correctly by the marker approach.
//   - Matrix with all zeros: All rows and columns are set to 0.
//   - No zeros: No changes to the matrix.
// - The solution is in-place, modifying the matrix directly without additional data structures.

// Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
// - First pass: Iterate through all m * n elements to set markers.
// - Second pass: Iterate through (m-1) * (n-1) elements to set zeros.
// - Setting first row and column: O(n) and O(m), respectively.
// - Total time is dominated by O(m * n).

// Space Complexity: O(1)
// - Only a single boolean variable (firstcol) is used for extra space.
// - The matrix itself is used to store markers, making the solution in-place.
// - The output is the modified matrix, as required by the problem.

// Constraints:
// - m == matrix.length, n == matrix[0].length
// - 1 <= m, n <= 200
// - -10^9 <= matrix[i][j] <= 10^9

public class Solution {
    public void setZeroes(int[][] matrix) {
        // Flag to track if first column needs to be zeroed
        boolean firstcol = false;
        // Get matrix dimensions
        int r = matrix.length;
        int c = matrix[0].length;
        
        // First pass: Mark rows and columns to be zeroed
        for (int i = 0; i < r; i++) {
            // Check if first column has a zero
            if (matrix[i][0] == 0) {
                firstcol = true;
            }
            // Check other columns for zeros
            for (int j = 1; j < c; j++) {
                if (matrix[i][j] == 0) {
                    // Mark first cell of row and column
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Second pass: Set zeros based on markers
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Handle first row if needed
        if (matrix[0][0] == 0) {
            for (int j = 0; j < c; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Handle first column if needed
        if (firstcol) {
            for (int i = 0; i < r; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}