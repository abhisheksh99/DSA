// Problem: Number of 1 Bits
// Given a 32-bit integer n, return the number of 1 bits in its binary representation
// (also known as the Hamming weight). The input n should be treated as an unsigned value,
// so negative numbers are interpreted in their two's complement form.

// DSA Pattern: Bit Manipulation
// This problem is solved using bit manipulation to count the number of 1 bits efficiently.
// The approach uses the property that n & (n-1) removes the rightmost set bit (1) in n,
// allowing us to count all 1 bits by repeating this operation until n becomes 0.

// Approach:
// 1. Initialize a counter (count) to 0 to track the number of 1 bits.
// 2. While n is not 0:
//    - Increment count to record a 1 bit.
//    - Update n using n & (n-1) to clear the rightmost 1 bit.
// 3. When n becomes 0, all 1 bits have been counted.
// 4. Return count as the result.

// Key Points to Remember:
// - The operation n & (n-1) flips the rightmost 1 bit to 0 in each iteration (e.g., 1011 & 1010 = 1010).
// - The loop runs exactly as many times as there are 1 bits in n.
// - Treating n as unsigned means we consider its 32-bit binary form, including for negative numbers
//   (e.g., -3 in two's complement is 11111111111111111111111111111101, with 30 ones).
// - The problem assumes a 32-bit integer input, but the algorithm works regardless of sign.
// - Edge cases: n = 0 (returns 0), n = -1 (all 1s, returns 32), or any positive/negative integer.
// - The approach avoids explicit bit checking (e.g., shifting and checking each bit), making it efficient.

// Time Complexity: O(k), where k is the number of 1 bits in n
// - The loop iterates exactly k times, where k is the number of 1 bits in n.
// - In the worst case (e.g., n = -1, with 32 ones), it runs 32 times, but this is still O(1) for 32-bit integers.
// - Each iteration performs O(1) operations (bitwise AND and increment).

// Space Complexity: O(1)
// - The algorithm uses only a constant amount of extra space (variable: count).
// - No additional data structures are used.
// - The output (integer result) is required by the problem.

public class Solution {
    // Treat n as an unsigned value
    public int hammingWeight(int n) {
        // Initialize counter for 1 bits
        int count = 0;
        // Continue until n becomes 0
        while (n != 0) {
            // Increment count for each 1 bit
            count++;
            // Clear the rightmost 1 bit
            n = n & (n - 1);
        }
        // Return the total number of 1 bits
        return count;
    }
}