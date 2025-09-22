// Problem: Multiply Strings
// Given two non-negative integers represented as strings, return their product as a string.
// The numbers can be arbitrarily large, and the solution must not use built-in BigInteger libraries or convert inputs to integers directly.

// DSA Pattern: String / Arithmetic Simulation
// This problem is solved by simulating the manual multiplication process, similar to how we multiply numbers by hand.
// Each digit of num1 is multiplied by each digit of num2, and the results are accumulated in an array to handle carries.

// Approach:
// 1. Initialize an array pos of size m + n (where m and n are lengths of num1 and num2) to store the digits of the product.
// 2. Iterate through num1 and num2 from right to left (least significant digits):
//    - For each pair of digits (num1[i], num2[j]):
//      - Compute their product: (num1[i] - '0') * (num2[j] - '0').
//      - Add the product to the appropriate position in pos:
//        - The units place goes to pos[i + j + 1].
//        - The tens place (carry) goes to pos[i + j].
//      - Update pos[i + j + 1] with the units digit (sum % 10) and add the carry (sum / 10) to pos[i + j].
// 3. Build the result string using a StringBuilder:
//    - Iterate through pos, appending non-zero digits (skip leading zeros).
//    - If the result is empty (all zeros), return "0".
// 4. Return the final string.

// Key Points to Remember:
// - The pos array has size m + n because the maximum number of digits in the product is m + n (e.g., 99 * 99 = 9801, 2 + 2 = 4 digits).
// - Positions i + j and i + j + 1 are used because multiplying digits at indices i and j contributes to these positions in the result.
// - Subtracting '0' converts a character digit to its integer value (e.g., '5' - '0' = 5).
// - Leading zeros are skipped to ensure the result is valid (e.g., "0012" becomes "12").
// - Edge cases:
//   - If either num1 or num2 is "0", the result is "0".
//   - Single-digit numbers: e.g., "2" * "3" = "6".
//   - Large numbers: handled by the array-based approach.
// - The algorithm avoids direct integer conversion, adhering to the problem constraints.

// Time Complexity: O(m * n), where m and n are the lengths of num1 and num2
// - The nested loops iterate m * n times to process each pair of digits.
// - Each iteration performs O(1) operations (multiplication, addition, modulo).
// - Building the result string takes O(m + n) time, which is dominated by O(m * n).

// Space Complexity: O(m + n)
// - The pos array uses O(m + n) space to store the digits of the product.
// - The StringBuilder uses O(m + n) space for the result string.
// - The output string is required by the problem and not counted as extra space.

// Constraints:
// - 1 <= num1.length, num2.length <= 200
// - num1 and num2 consist of digits only (0-9).
// - No leading zeros except for the number 0 itself.

public class Solution {
    public String multiply(String num1, String num2) {
        // Get lengths of input strings
        int m = num1.length(), n = num2.length();
        // Initialize array to store digit products
        int[] pos = new int[m + n];
        
        // Multiply each digit pair and accumulate results
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Compute product of digits
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // Positions for tens and units digits
                int p1 = i + j, p2 = i + j + 1;
                // Add product to units position
                int sum = mul + pos[p2];
                // Update tens position with carry
                pos[p1] += sum / 10;
                // Update units position with remainder
                pos[p2] = sum % 10;
            }
        }
        
        // Build result string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        
        // Return "0" if result is empty, otherwise return string
        return sb.length() == 0 ? "0" : sb.toString();
    }
}