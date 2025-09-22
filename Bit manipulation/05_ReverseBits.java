// Problem: Reverse Bits
// Given a 32-bit integer n, reverse its bits and return the result. The input n should be
// treated as an unsigned value, so negative numbers are interpreted in their two's complement
// form, and the output is a 32-bit unsigned integer.

// DSA Pattern: Bit Manipulation
// This problem is solved using bit manipulation to reverse the order of bits in the input
// integer. The approach processes each bit of the input from least significant to most
// significant, building the reversed result by shifting and setting bits.

// Approach:
// 1. Initialize a variable reverse to 0 to store the reversed bits.
// 2. Iterate 32 times (since n is a 32-bit integer):
//    - Left shift reverse by 1 to make space for the next bit.
//    - Extract the least significant bit of n using n & 1 and add it to reverse using OR (|).
//    - Right shift n by 1 to process the next bit.
// 3. After 32 iterations, reverse contains the bits of n in reversed order.
// 4. Return reverse as the result.

// Key Points to Remember:
// - The input n is treated as an unsigned 32-bit integer, so we process all 32 bits regardless of sign.
// - Left shifting reverse (<< 1) moves existing bits left and adds a 0 at the least significant position.
// - n & 1 extracts the least significant bit (0 or 1) of n.
// - n >> 1 shifts n right to process the next bit, effectively discarding the least significant bit.
// - The loop runs exactly 32 times for a 32-bit integer.
// - Edge cases: n = 0 (all 0s, returns 0), n = -1 (all 1s, returns 0xFFFFFFFF), or any 32-bit integer.
// - The result is an unsigned 32-bit integer, interpreted as a signed int in Java.

// Time Complexity: O(1)
// - The loop iterates exactly 32 times, as the input is always a 32-bit integer.
// - Each iteration performs O(1) operations (shift, AND, OR).
// - Total time is constant, as the number of iterations is fixed regardless of input.

// Space Complexity: O(1)
// - The algorithm uses only a constant amount of extra space (variables: reverse, i).
// - No additional data structures are used.
// - The output (integer result) is required by the problem.

// Example:
// Input: n = 00000010100101000001111010011100 (unsigned)
// Output: 00111001011110000010100101000000 (unsigned)
// Explanation: The bits are reversed, e.g., the first bit becomes the last, the second becomes
// the second-to-last, and so on.

public class Solution {
    // Treat n as an unsigned value
    public int reverseBits(int n) {
        // Initialize result to store reversed bits
        int reverse = 0;
        // Process all 32 bits
        for (int i = 0; i < 32; i++) {
            // Left shift reverse to make space for the next bit
            reverse = reverse << 1;
            // Add the least significant bit of n to reverse
            reverse = reverse | (n & 1);
            // Right shift n to process the next bit
            n = n >> 1;
        }
        // Return the reversed bits
        return reverse;
    }
}