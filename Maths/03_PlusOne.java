// Problem: Plus One
// Given a non-negative integer represented as a non-empty array of digits, add one to the number.
// The digits are stored such that the most significant digit is at index 0, and each element contains a single digit.
// Return the array representing the number after adding one.

// DSA Pattern: Array / Arithmetic
// This problem is solved by simulating the addition of one to a number represented as an array of digits.
// The approach processes the digits from right to left, handling carries as needed, similar to manual addition.

// Approach:
// 1. Start from the least significant digit (last index of the array).
// 2. Iterate backward through the digits:
//    - If the current digit is less than 9, increment it by 1 and return the array (no carry needed).
//    - If the current digit is 9, set it to 0 (carry over to the next digit).
// 3. If the loop completes (all digits were 9), create a new array with one extra digit:
//    - Set the most significant digit to 1, and all others remain 0 (default in Java).
// 4. Return the resulting array.

// Key Points to Remember:
// - The input array represents a non-negative integer with no leading zeros (except for 0 itself).
// - Adding 1 to a number like 999...9 results in 1000...0, requiring an extra digit.
// - The algorithm processes digits from right to left to propagate carries efficiently.
// - Edge cases:
//   - Single digit: e.g., [9] -> [1,0], [5] -> [6].
//   - Multiple digits: e.g., [1,2,3] -> [1,2,4], [9,9,9] -> [1,0,0,0].
// - The solution modifies the input array when possible to avoid extra space, except when a new array is needed.

// Time Complexity: O(n), where n is the length of the digits array
// - In the worst case, we traverse all digits once (e.g., [1,2,3] or [9,9,9]).
// - If a new array is created (e.g., for [9,9,9]), initialization is O(n+1), still O(n).
// - Each operation inside the loop (comparison, assignment) is O(1).

// Space Complexity: O(1) or O(n) depending on the case
// - In most cases, the input array is modified in place, using O(1) extra space.
// - In the worst case (all 9s), a new array of size n+1 is created, using O(n) space.
// - The output array is required by the problem and not counted as extra space.

public class Solution {
    public int[] plusOne(int[] digits) {
        // Start from the least significant digit
        for (int i = digits.length - 1; i >= 0; i--) {
            // If digit is less than 9, increment and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // If digit is 9, set to 0 and continue to next digit
            digits[i] = 0;
        }
        
        // If all digits were 9, create new array with leading 1
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;  // Leading 1, rest are 0 by default
        return newDigits;
    }
}