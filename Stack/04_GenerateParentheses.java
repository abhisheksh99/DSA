class Solution {
    // Problem: Generate Parentheses
    // Given an integer n, generate all valid combinations of n pairs of parentheses.
    // A combination is valid if it is well-formed, meaning every open parenthesis '(' has a
    // corresponding close parenthesis ')', and parentheses are properly nested.
    // Return the list of all possible valid combinations as strings.

    // DSA Pattern: Backtracking
    // This problem is solved using backtracking to explore all possible combinations of parentheses.
    // We build strings by adding '(' or ')' while ensuring the combination remains valid.
    // The backtracking approach uses two counters: one for open parentheses and one for close
    // parentheses, ensuring that we only add a close parenthesis if there are more open ones,
    // and we only add an open parenthesis if we haven't used all n open parentheses.
    // The recursion terminates when both open and close counts reach n, indicating a valid combination.

    // Approach:
    // 1. Initialize an empty list to store the result (valid combinations).
    // 2. Use a helper method `backtrack` to build combinations recursively:
    //    - Parameters: result list, current string, count of open parentheses, count of close
    //      parentheses, and the total number of pairs n.
    //    - Base case: If openCount == n and closeCount == n, add the current string to the result.
    //    - Recursive steps:
    //      - If openCount < n, add an open parenthesis '(' and recurse with incremented openCount.
    //      - If closeCount < openCount, add a close parenthesis ')' and recurse with incremented closeCount.
    // 3. Start the backtracking with an empty string and zero counts for open and close parentheses.
    // 4. Return the result list containing all valid combinations.

    // Key Points to Remember:
    // - A combination is valid if it has n open and n close parentheses, and at any point, the number
    //   of close parentheses does not exceed the number of open parentheses (closeCount <= openCount).
    // - The condition openCount < n ensures we don't add more than n open parentheses.
    // - The condition closeCount < openCount ensures we only add a close parenthesis when there is a
    //   matching open parenthesis, maintaining validity.
    // - The backtracking explores all possible valid combinations by trying both '(' and ')' at each step
    //   where allowed, ensuring all solutions are generated.
    // - Edge cases:
    //   - n = 0: Returns an empty list (handled implicitly as no combinations are generated).
    //   - n = 1: Generates ["()"].
    // - The number of valid combinations for n pairs is the nth Catalan number, C(n) = (2n)! / (n! * (n+1)!).

    // Time Complexity: O(4^n / sqrt(n))
    // - The number of valid combinations is the nth Catalan number, approximately O(4^n / sqrt(n)).
    // - For each valid combination, we perform O(n) work to build the string (adding characters and recursion).
    // - Total time: O(n * C(n)) = O(n * 4^n / sqrt(n)) = O(4^n / sqrt(n)).
    // - Each recursive call involves constant-time operations (string concatenation, list addition).
    // - The recursion tree has a branching factor of at most 2 (add '(' or ')'), but pruning via
    //   conditions reduces the number of valid paths to the Catalan number.

    // Space Complexity: O(n)
    // - Recursion stack: The maximum depth of the recursion is 2n (n open + n close parentheses), so O(n).
    // - Current string: Each string is at most 2n characters long (n '(' + n ')'): O(n).
    // - Result list: Stores C(n) combinations, each of length 2n, but this is output space and not counted
    //   in auxiliary space complexity.
    // - Auxiliary space: O(n) for the recursion stack and current string.
    // - Note: The output list space is O(n * C(n)), but this is typically excluded from auxiliary space
    //   in algorithmic analysis.

    // Main method to generate all valid combinations of n pairs of parentheses
    public List<String> generateParenthesis(int n) {
        // Initialize result list to store valid combinations
        List<String> result = new ArrayList<>();
        // Start backtracking with empty string and zero counts
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // Helper method to perform backtracking
    private void backtrack(List<String> result, String current, int openCount, int closeCount, int n) {
        // Base case: If both open and close counts equal n, we have a valid combination
        if (openCount == n && closeCount == n) {
            result.add(current); // Add to result list (O(1))
            return;
        }

        // Recursive case 1: Add an open parenthesis if we haven't used all n
        if (openCount < n) {
            backtrack(result, current + "(", openCount + 1, closeCount, n); // O(1) for concatenation
        }

        // Recursive case 2: Add a close parenthesis if valid (more open than close)
        if (closeCount < openCount) {
            backtrack(result, current + ")", openCount, closeCount + 1, n); // O(1) for concatenation
        }
    }
}