// Problem: Rotate Image
// Given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise) in-place.
// The matrix must be modified directly without using another 2D array.

// DSA Pattern: Matrix / In-Place Manipulation
// This problem is solved by rotating the matrix in-place using a layer-by-layer approach.
// Each layer of the matrix is rotated by swapping elements in a four-way cycle, moving elements
// clockwise through the positions (i,j) -> (j,n-1-i) -> (n-1-i,n-1-j) -> (n-1-j,i) -> (i,j).

// Approach:
// 1. Determine the size of the matrix (n x n).
// 2. Iterate through the matrix layers from the outermost to the innermost:
//    - For each layer, use indices i (row) from 0 to (n+1)/2 and j (column) from 0 to n/2.
//    - For each position (i,j) in the current layer:
//      - Store the value at (n-1-j,i) in a temporary variable.
//      - Perform a four-way swap to rotate the elements:
//        - Move (n-1-i,n-1-j) to (n-1-j,i).
//        - Move (j,n-1-i) to (n-1-i,n-1-j).
//        - Move (i,j) to (j,n-1-i).
//        - Move the temporary value to (i,j).
// 3. The process rotates each layer by 90 degrees clockwise in-place.

// Key Points to Remember:
// - The matrix is n x n, so rows and columns are equal in length.
// - In-place rotation means no extra matrix is used; only a constant amount of extra space (temp variable).
// - The outer loop runs to (n+1)/2 to cover all layers (e.g., for n=3, only i=0 is needed; for n=4, i=0,1).
// - The inner loop runs to n/2 to process elements in each layer, avoiding double rotations.
// - The four-way swap ensures each element moves to its correct position in the 90-degree rotation.
// - Edge cases:
//   - n = 1: No rotation needed (single element).
//   - n = 2: One four-way swap.
//   - n = 3: One layer with multiple swaps.
// - The algorithm works for both odd and even n.

// Time Complexity: O(n^2), where n is the size of the matrix
// - The algorithm processes each element in the n x n matrix exactly once.
// - The outer loop runs approximately n/2 times, and the inner loop runs n/2 times per layer.
// - Total iterations are proportional to n^2 / 4, which is O(n^2).
// - Each swap operation is O(1).

// Space Complexity: O(1)
// - Only a single temporary variable (temp) is used for swapping.
// - The rotation is performed in-place, modifying the input matrix directly.
// - No additional data structures are used.

// Constraints:
// - matrix is an n x n matrix (1 <= n <= 20).
// - Elements are integers in the range [-1000, 1000].

public class Solution {
    public void rotate(int[][] matrix) {
        // Get matrix size
        int n = matrix.length;
        // Iterate through layers
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // Store bottom-left element
                int temp = matrix[n - 1 - j][i];
                // Move bottom-right to bottom-left
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                // Move top-right to bottom-right
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                // Move top-left to top-right
                matrix[j][n - 1 - i] = matrix[i][j];
                // Move temp (bottom-left) to top-left
                matrix[i][j] = temp;
            }
        }
    }
}