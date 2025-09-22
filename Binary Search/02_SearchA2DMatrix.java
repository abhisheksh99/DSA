class Solution {
    // Problem: Search a 2D Matrix
    // Given an m x n matrix where each row is sorted in ascending order and the first
    // integer of each row is greater than the last integer of the previous row, search
    // for a target integer in the matrix. Return true if the target is found, or false
    // otherwise. The matrix is non-empty, and all elements are integers.

    // DSA Pattern: Binary Search
    // This problem is solved using binary search by treating the 2D matrix as a flattened
    // sorted array. Since the matrix rows are sorted and each row's first element is greater
    // than the previous row's last element, the entire matrix can be mapped to a 1D sorted
    // array of size m * n. Binary search is applied to this virtual array, converting 1D
    // indices to 2D row and column indices for element access.

    // Approach:
    // 1. Get the dimensions of the matrix: m (rows) and n (columns).
    // 2. Initialize two pointers for binary search: left (0) and right (m * n - 1).
    // 3. While left <= right:
    //    - Calculate the middle index mid = left + (right - left) / 2.
    //    - Convert mid to 2D coordinates: row = mid / n, column = mid % n.
    //    - Compare the element at matrix[row][column] with the target:
    //      - If equal, return true (target found).
    //      - If less than target, search the right half by setting left = mid + 1.
    //      - If greater than target, search the left half by setting right = mid - 1.
    // 4. If the loop ends without finding the target, return false.

    // Key Points to Remember:
    // - The matrix's sorted property (row-wise and row-to-row) allows it to be treated as a
    //   flattened sorted array of size m * n.
    // - Convert 1D index mid to 2D coordinates using row = mid / n and column = mid % n.
    // - Use left + (right - left) / 2 to calculate mid to avoid integer overflow.
    // - The binary search halves the search space in each iteration, ensuring efficiency.
    // - The problem assumes the matrix is non-empty and rows have consistent length.
    // - Handle edge cases: empty matrix (not applicable due to problem constraints) or
    //   target not present.
    // - The algorithm accesses matrix elements directly without flattening the matrix.

    // Time Complexity: O(log(m * n))
    // - m is the number of rows, and n is the number of columns in the matrix.
    // - Binary search on a virtual array of size m * n requires O(log(m * n)) iterations.
    // - Each iteration performs O(1) operations (index calculations and comparisons).
    // - Total time is O(log(m * n)).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (variables: left, right,
    //   mid, and mid_val).
    // - No additional data structures are used, and the matrix is not modified.
    // - The output (boolean result) is required by the problem.

    public boolean searchMatrix(int[][] matrix, int target) {
        // Get matrix dimensions: m rows, n columns
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize binary search pointers for the virtual flattened array
        int left = 0, right = m * n - 1;

        // Perform binary search
        while (left <= right) {
            // Calculate middle index, avoiding integer overflow
            int mid = left + (right - left) / 2;

            // Convert 1D mid index to 2D row and column indices
            int mid_val = matrix[mid / n][mid % n];

            // If target is found, return true
            if (mid_val == target) {
                return true;
            }
            // If middle value is less than target, search right half
            else if (mid_val < target) {
                left = mid + 1;
            }
            // If middle value is greater than target, search left half
            else {
                right = mid - 1;
            }
        }

        // If target is not found, return false
        return false;
    }
}