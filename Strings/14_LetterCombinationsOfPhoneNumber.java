public class Solution {
    // Problem: Letter Combinations of a Phone Number
    // Given a string digits containing digits from 2-9 inclusive, return all possible letter
    // combinations that the number could represent based on a phone keypad mapping. Return
    // the answer in any order. Each digit maps to a set of letters (e.g., 2 -> "abc", 3 -> "def").

    // DSA Pattern: Backtracking
    // This problem is solved using backtracking to generate all possible combinations by exploring
    // each letter mapping for each digit. A recursive approach builds combinations by appending
    // one letter at a time and backtracking when a combination is complete.

    // Approach:
    // 1. Initialize a global ArrayList res to store all valid combinations.
    // 2. Define a digit-to-character mapping array digitToChar for digits 0-9, where digits 2-9
    //    map to their respective letters (e.g., "2" -> "abc", "3" -> "def").
    // 3. Handle the edge case: if digits is empty, return the empty result list.
    // 4. Call a recursive backtrack function with:
    //    - i: current index in digits.
    //    - curStr: current combination being built.
    //    - digits: input string.
    // 5. In backtrack:
    //    - If curStr length equals digits length, add curStr to res (complete combination).
    //    - Get the letters for the current digit (digitToChar[digits.charAt(i) - '0']).
    //    - For each letter, recursively call backtrack with the next index and curStr + letter.
    // 6. Return res containing all possible combinations.

    // Key Points to Remember:
    // - Use backtracking to explore all possible letter combinations for each digit.
    // - The digitToChar array simplifies mapping digits to letters (e.g., '2' -> "abc").
    // - Handle the empty input case by returning an empty list.
    // - Each recursive call processes one digit and appends one letter to the current combination.
    // - The recursion stops when the combination length matches the input digits length.
    // - The problem assumes digits contain only 2-9, so no validation is needed for invalid digits.
    // - The output order does not matter, and all combinations are unique.

    // Time Complexity: O(4^n)
    // - n is the length of the digits string.
    // - Each digit (2-9) maps to 3 or 4 letters (e.g., 7 and 9 have 4 letters, others have 3).
    // - For each of the n digits, we explore up to 4 choices, leading to O(4^n) total combinations.
    // - String concatenation in backtracking is O(1) per call, and adding to res is O(1) amortized.
    // - Overall, the time complexity is O(4^n).

    // Space Complexity: O(n + 4^n)
    // - Recursion stack: O(n) for the deepest recursion path (one call per digit).
    // - Result list (res): stores O(4^n) combinations, each of length O(n).
    // - The digitToChar array is a fixed size (10), so O(1).
    // - Temporary strings (curStr) use O(n) space per recursive call.
    // - Overall, the space complexity is O(n + 4^n), dominated by O(4^n) for the output list.

    // Global list to store all valid combinations
    private List<String> res = new ArrayList<>();
    
    // Mapping of digits to letters (0-9), where 2-9 map to letters
    private String[] digitToChar = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        // Handle empty input case
        if (digits.isEmpty()) {
            return res;
        }
        // Start backtracking from index 0 with empty string
        backtrack(0, "", digits);
        // Return all combinations
        return res;
    }

    // Recursive helper function for backtracking
    private void backtrack(int i, String curStr, String digits) {
        // If current string length equals digits length, add to result
        if (curStr.length() == digits.length()) {
            res.add(curStr);
            return;
        }
        // Get letters for the current digit
        String chars = digitToChar[digits.charAt(i) - '0'];
        // Explore each letter for the current digit
        for (char c : chars.toCharArray()) {
            // Recursively build combination with next digit and current letter
            backtrack(i + 1, curStr + c, digits);
        }
    }
}