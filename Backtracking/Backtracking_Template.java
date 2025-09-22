import java.util.*;

public class BacktrackingTemplate {

    /** ------------------------------------------------------------
     * 1. SUBSETS (POWER SET)
     * Time: O(2^n), Space: O(n)
     * ------------------------------------------------------------ */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrackSubsets(int start, int[] nums, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path)); // copy current subset
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                        // choose
            backtrackSubsets(i + 1, nums, path, res); // explore
            path.remove(path.size() - 1);             // un-choose (backtrack)
        }
    }

    /** ------------------------------------------------------------
     * 2. PERMUTATIONS
     * Time: O(n!), Space: O(n)
     * ------------------------------------------------------------ */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrackPermute(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrackPermute(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);

            backtrackPermute(nums, used, path, res);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /** ------------------------------------------------------------
     * 3. COMBINATIONS (e.g., choose k elements)
     * Time: O(C(n, k)), Space: O(k)
     * ------------------------------------------------------------ */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombine(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrackCombine(int start, int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrackCombine(i + 1, n, k, path, res);
            path.remove(path.size() - 1);
        }
    }

    /** ------------------------------------------------------------
     * 4. N-QUEENS
     * Time: O(n!), Space: O(n^2)
     * ------------------------------------------------------------ */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');

        backtrackNQueens(0, board, result);
        return result;
    }

    private void backtrackNQueens(int row, char[][] board, List<List<String>> res) {
        if (row == board.length) {
            res.add(construct(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                backtrackNQueens(row + 1, board, res);
                board[row][col] = '.'; // backtrack
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        // check column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;

        // check diagonals
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board)
            result.add(new String(row));
        return result;
    }

    /** ------------------------------------------------------------
     * 5. PALINDROME PARTITIONING
     * Time: O(2^n), Space: O(n)
     * ------------------------------------------------------------ */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrackPartition(0, s, new ArrayList<>(), result);
        return result;
    }

    private void backtrackPartition(int start, String s, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String substr = s.substring(start, end);
            if (isPalindrome(substr)) {
                path.add(substr);
                backtrackPartition(end, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r)
            if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }

    /** ------------------------------------------------------------
     * 6. DRIVER METHOD TO TEST ALL
     * ------------------------------------------------------------ */
    public static void main(String[] args) {
        BacktrackingTemplate bt = new BacktrackingTemplate();

        System.out.println("Subsets:");
        System.out.println(bt.subsets(new int[]{1, 2, 3}));

        System.out.println("\nPermutations:");
        System.out.println(bt.permute(new int[]{1, 2, 3}));

        System.out.println("\nCombinations (4 choose 2):");
        System.out.println(bt.combine(4, 2));

        System.out.println("\nN-Queens (n = 4):");
        List<List<String>> nQueens = bt.solveNQueens(4);
        for (List<String> board : nQueens) {
            board.forEach(System.out::println);
            System.out.println();
        }

        System.out.println("\nPalindrome Partitioning:");
        System.out.println(bt.partition("aab"));
    }
}
