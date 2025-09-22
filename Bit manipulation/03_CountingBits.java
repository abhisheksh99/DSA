// Problem: Counting Bits
// Given a non-negative integer n, return an array of length n+1 where each element at index i
// represents the number of 1 bits in the binary representation of i (Hamming weight of i).

// DSA Pattern: Bit Manipulation with Dynamic Programming
// This problem is solved using bit manipulation combined with a dynamic programming approach.
// The key insight is that the number of 1 bits in a number i can be derived from the number of
// 1 bits in i & (i-1) plus 1, since i & (i-1) removes the rightmost 1 bit from i.

// Approach:
// 1. Initialize an array ans of size n+1 to store the count of 1 bits for each number from 0 to n.
// 2. Set ans[0] = 0, as the binary representation of 0 has no 1 bits.
// 3. Iterate from i = 1 to n:
//    - Compute ans[i] as ans[i & (i-1)] + 1, where i & (i-1) gives the number obtained by
//      removing the rightmost 1 bit from i, and we add 1 to account for the removed bit.
// 4. Return the ans array.

// Key Points to Remember:
// - The operation i & (i-1) removes the rightmost 1 bit (e.g., for i = 5 (101), i & (i-1) = 4 (100)).
// - This creates a recurrence relation: the number of 1 bits in i equals the number of 1 bits
//   in i & (i-1) plus 1, leveraging previously computed results in ans.
// - The approach is dynamic programming because ans[i] depends on ans[j] where j = i & (i-1).
// - The problem assumes n is non-negative, and the output array has size n+1.
// - Edge cases: n = 0 (returns [0]), n = 1 (returns [0, 1]), or larger n.
// - The solution is efficient as it avoids explicit bit counting for each number.

// Time Complexity: O(n)
// - The loop iterates from 1 to n, performing O(1) operations per iteration (bitwise AND and array access).
// - Total time is O(n), as each number from 1 to n is processed exactly once.
// - Note that this is more efficient than computing the Hamming weight for each i independently.

// Space Complexity: O(n)
// - The algorithm uses an array ans of size n+1 to store the results.
// - No additional data structures are used beyond the output array.
// - The output array is required by the problem.

// Example:
// Input: n = 5
// Output: [0, 1, 1, 2, 1, 2]
// Explanation:
// - 0 (000) -> 0 ones
// - 1 (001) -> 1 one
// - 2 (010) -> 1 one
// - 3 (011) -> 2 ones
// - 4 (100) -> 1 one
// - 5 (101) -> 2 ones

class Solution {
    public int[] countBits(int n) {
        // Initialize result array of size n+1
        int[] ans = new int[n + 1];
        // Base case: 0 has no 1 bits
        ans[0] = 0;
        // Compute number of 1 bits for each i from 1 to n
        for (int i = 1; i <= n; i++) {
            // Use the number of 1 bits in i & (i-1) plus 1
            ans[i] = ans[i & (i - 1)] + 1;
        }
        // Return the result array
        return ans;
    }
}