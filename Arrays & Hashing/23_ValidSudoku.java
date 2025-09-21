class Solution {
    // Problem: Valid Sudoku
    // Given a 9x9 Sudoku board represented as a 2D char array, determine if it is valid according
    // to Sudoku rules: each row, each column, and each of the nine 3x3 subgrids (boxes) must contain
    // the digits 1-9 without repetition. Empty cells are represented by '.', and only the filled
    // cells need to be validated.

    // DSA Pattern: Hash Set (Frequency Tracking)
    // This problem is solved using HashSets to track unique digits in each row, column, and 3x3 box.
    // HashSets provide O(1) average-time complexity for checking and adding elements, making it
    // efficient to detect duplicates in rows, columns, and boxes.

    // Approach:
    // 1. Initialize three arrays of HashSets (size 9 each) for rows, columns, and boxes to store
    //    digits encountered in each.
    // 2. Iterate through each cell in the 9x9 board using nested loops:
    //    - Skip empty cells ('.').
    //    - For a filled cell (val), check for duplicates in the corresponding row, column, and box:
    //      - If val exists in rows[i], cols[j], or boxes[idx], return false (invalid board).
    //      - Otherwise, add val to the respective HashSets.
    //    - Calculate the box index using (i / 3) * 3 + (j / 3) to map the cell to one of the
    //      nine 3x3 boxes (0 to 8).
    // 3. If no duplicates are found, return true (valid board).

    // Key Points to Remember:
    // - Use HashSets for O(1) average-time lookup and insertion to detect duplicates.
    // - Track rows, columns, and boxes separately to enforce Sudoku rules.
    // - Calculate box index carefully to map each cell to the correct 3x3 subgrid.
    // - Skip empty cells ('.') as they do not affect validity.
    // - Handle edge cases like an empty board (valid) or a board with invalid digits (handled by
    //   problem constraints: digits are 1-9 or '.').
    // - The board is fixed at 9x9, so the size is constant.
    // - Ensure no duplicate digits in any row, column, or 3x3 box for validity.

    // Time Complexity: O(1)
    // - The board is fixed at 9x9, so we iterate through 81 cells (constant).
    // - HashSet operations (contains, add) are O(1) on average.
    // - Overall, the time complexity is O(1) since the board size is fixed at 9x9 = 81 cells.
    // - For a general NxN board, it would be O(N^2), but here N = 9.

    // Space Complexity: O(1)
    // - We use three arrays of 9 HashSets, each storing up to 9 digits, resulting in O(9 * 9) = O(1)
    //   space since the board size is fixed.
    // - No additional data structures grow with input size.
    // - Overall, the space complexity is O(1) for a fixed 9x9 board.

    public boolean isValidSudoku(char[][] board) {
        // Initialize constant for board size (9x9)
        int N = 9;
        
        // Initialize arrays of HashSets for rows, columns, and 3x3 boxes
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        
        // Initialize each HashSet
        for (int i = 0; i < N; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Iterate through each cell in the 9x9 board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Get the current cell value
                char val = board[i][j];
                
                // Skip empty cells
                if (val == '.') {
                    continue;
                }

                // Check row for duplicates
                if (rows[i].contains(val)) {
                    return false;
                }
                rows[i].add(val);

                // Check column for duplicates
                if (cols[j].contains(val)) {
                    return false;
                }
                cols[j].add(val);

                // Check 3x3 box for duplicates
                int idx = (i / 3) * 3 + (j / 3); // Calculate box index (0 to 8)
                if (boxes[idx].contains(val)) {
                    return false;
                }
                boxes[idx].add(val);
            }
        }

        // If no duplicates are found, the board is valid
        return true;
    }
}