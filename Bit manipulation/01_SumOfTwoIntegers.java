// Problem: Add Two Integers
// Given two integers a and b, return their sum without using the '+' or '-' operators.
// The integers can be positive, negative, or zero, and the solution must handle all cases correctly.

// DSA Pattern: Bit Manipulation
// This problem is solved using bit manipulation to perform addition without arithmetic operators.
// The approach simulates how addition works at the bit level, using bitwise XOR (^) for sum
// and bitwise AND (&) with left shift (<<) for carry.

// Approach:
// 1. Use a while loop to continue until there is no carry (b becomes 0).
// 2. In each iteration:
//    - Compute the sum of a and b without carry using XOR: temp = a ^ b.
//    - Compute the carry using AND and left shift: carry = (a & b) << 1.
//    - Update a to the sum (temp) and b to the carry.
// 3. When b becomes 0, the loop ends, and a contains the final sum.
// 4. Return a as the result.

// Key Points to Remember:
// - XOR (^) computes the sum of bits without considering carry (e.g., 1 ^ 1 = 0, 1 ^ 0 = 1).
// - AND (&) with left shift (<< 1) computes the carry (e.g., 1 & 1 = 1, shifted left for carry).
// - The loop iterates until no carry remains (b == 0), ensuring all bits are processed.
// - The approach handles positive, negative, and zero inputs correctly due to two's complement representation.
// - No arithmetic operators (+, -) are used, adhering to the problem constraints.
// - Edge cases (e.g., a = 0, b = 0, or negative numbers) are handled naturally by bit operations.

// Time Complexity: O(log n)
// - n is the maximum number of bits in the input integers (typically 32 for int).
// - The while loop iterates at most log n times, as each iteration shifts the carry left,
//   reducing the number of significant bits in b until it becomes 0.
// - Each iteration performs O(1) bitwise operations (XOR, AND, shift).

// Space Complexity: O(1)
// - The algorithm uses only a constant amount of extra space (variables: temp, carry).
// - No additional data structures are used.
// - The output (integer result) is required by the problem.

class Solution {
    public int getSum(int a, int b) {
        // Continue until there is no carry
        while (b != 0) {
            // Compute sum without carry using XOR
            int temp = a ^ b;
            // Compute carry using AND and left shift
            int carry = (a & b) << 1;
            // Update a to the sum and b to the carry
            a = temp;
            b = carry;
        }
        // Return the final sum
        return a;
    }
}