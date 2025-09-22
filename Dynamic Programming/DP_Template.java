import java.util.*;

public class DPTemplate {

    /** ------------------------------------
     * 1. 1D DP TEMPLATE (e.g., Climbing Stairs)
     * Time: O(N), Space: O(N) or O(1) if optimized
     * ------------------------------------ */
    public int climbStairs(int n) {
        if (n <= 2) return n;

        // Space optimized
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    /** ------------------------------------
     * 2. 2D DP TEMPLATE (e.g., Unique Paths)
     * Time: O(M*N), Space: O(M*N) or O(N)
     * ------------------------------------ */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Base case: First row and first column = 1
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // Fill rest
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

        return dp[m - 1][n - 1];
    }

    /** ------------------------------------
     * 3. GENERAL DP TEMPLATE (Memoization)
     * Example: Fibonacci with Memo
     * Time: O(N), Space: O(N)
     * ------------------------------------ */
    Map<Integer, Integer> fibMemo = new HashMap<>();

    public int fibMemo(int n) {
        if (n <= 1) return n;
        if (fibMemo.containsKey(n)) return fibMemo.get(n);

        int result = fibMemo(n - 1) + fibMemo(n - 2);
        fibMemo.put(n, result);
        return result;
    }

    /** ------------------------------------
     * 4. GENERAL DP TEMPLATE (Tabulation)
     * Example: Fibonacci with Tabulation
     * Time: O(N), Space: O(N)
     * ------------------------------------ */
    public int fibTab(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /** ------------------------------------
     * 5. KNAPSACK DP (2D Classic)
     * Time: O(N*W), Space: O(N*W)
     * ------------------------------------ */
    public int knapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w],
                            dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    /** ------------------------------------
     * 6. LCS (Longest Common Subsequence)
     * Time: O(M*N), Space: O(M*N)
     * ------------------------------------ */
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

        return dp[m][n];
    }

    /** ------------------------------------
     * 7. COIN CHANGE (Min Coins to Make Amount)
     * Time: O(N*A), Space: O(A)
     * ------------------------------------ */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int coin : coins)
            for (int a = coin; a <= amount; a++)
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /** ------------------------------------
     * 8. DRIVER METHOD TO TEST ALL
     * ------------------------------------ */
    public static void main(String[] args) {
        DPTemplate dp = new DPTemplate();

        System.out.println("1D DP: Climb Stairs (5) = " + dp.climbStairs(5));
        System.out.println("2D DP: Unique Paths (3x3) = " + dp.uniquePaths(3, 3));
        System.out.println("Memoization: Fib(10) = " + dp.fibMemo(10));
        System.out.println("Tabulation: Fib(10) = " + dp.fibTab(10));

        int[] weights = {1, 3, 4};
        int[] values = {15, 20, 30};
        System.out.println("Knapsack: Max Value = " + dp.knapsack(weights, values, 4));

        String s1 = "abcde", s2 = "ace";
        System.out.println("LCS: " + dp.longestCommonSubsequence(s1, s2));

        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println("Coin Change: Min Coins = " + dp.coinChange(coins, amount));
    }
}
