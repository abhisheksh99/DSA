// Problem: Pow(x, n)
// Given a double x and an integer n, compute x raised to the power n (i.e., x^n).
// The result should be accurate within the constraints of floating-point arithmetic.

// DSA Pattern: Binary Exponentiation / Divide and Conquer
// This problem is solved using the binary exponentiation technique (also known as exponentiation by squaring),
// which reduces the number of multiplications needed to compute x^n by leveraging the binary representation of n.

// Approach:
// 1. Handle the base case: if n == 0, return 1 (any number raised to 0 is 1).
// 2. Convert n to a long to handle the edge case where n is Integer.MIN_VALUE, as -n would overflow.
// 3. If n is negative, set x to 1/x and make n positive to compute the reciprocal power.
// 4. Use binary exponentiation:
//    - Initialize result = 1 and currentProduct = x.
//    - While N > 0:
//      - If N is odd (N % 2 == 1), multiply result by currentProduct.
//      - Square currentProduct (currentProduct *= currentProduct).
//      - Divide N by 2 (N /= 2).
// 5. Return the final result.

// Key Points to Remember:
// - Binary exponentiation reduces the time complexity from O(n) to O(log n) by halving the exponent in each step.
// - Converting n to long prevents overflow when n = Integer.MIN_VALUE, as -Integer.MIN_VALUE exceeds Integer.MAX_VALUE.
// - For negative n, x^n = (1/x)^(-n), which simplifies the computation.
// - Edge cases:
//   - n = 0: returns 1.
//   - n = Integer.MIN_VALUE: handled by using long.
//   - x = 1: returns 1 for any n.
//   - x = -1: returns 1 if n is even, -1 if n is odd.
//   - x = 0: returns 0 for positive n, undefined for negative n (handled as 0 in constraints).
// - Floating-point precision must be considered, but the problem assumes results are within acceptable bounds.

// Time Complexity: O(log n)
// - The while loop iterates O(log n) times, as N is halved in each iteration.
// - Each iteration performs O(1) operations (modulo, multiplication, division).

// Space Complexity: O(1)
// - Only a constant amount of extra space is used (variables: result, currentProduct, N).
// - No recursive call stack or additional data structures are needed.

// Constraints:
// - x is a double in the range [-100.0, 100.0].
// - n is a 32-bit signed integer in the range [-2^31, 2^31 - 1].

public class Solution {
    public double myPow(double x, int n) {
        // Handle base case: x^0 = 1
        if (n == 0) return 1;
        
        // Convert n to long to avoid overflow for Integer.MIN_VALUE
        long N = n;
        
        // Handle negative exponent: x^(-n) = (1/x)^n
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        // Initialize result and current product
        double result = 1;
        double currentProduct = x;
        
        // Binary exponentiation
        while (N > 0) {
            // If N is odd, multiply result by currentProduct
            if (N % 2 == 1) {
                result *= currentProduct;
            }
            // Square the current product
            currentProduct *= currentProduct;
            // Divide N by 2
            N /= 2;
        }
        
        return result;
    }
}