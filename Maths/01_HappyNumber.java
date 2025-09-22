// Problem: Happy Number
// Given a positive integer n, determine if it is a happy number. A happy number is a number
// defined by the following process: Starting with any positive integer, replace the number by
// the sum of the squares of its digits, and repeat the process until the number equals 1
// (where it will stay), or it loops endlessly in a cycle that does not include 1. Those
// numbers for which this process ends in 1 are happy numbers.

// DSA Pattern: Hash Set / Cycle Detection
// This problem is solved using a hash set to detect cycles in the sequence of numbers generated
// by summing the squares of digits. If the number becomes 1, it is happy; if a cycle is detected,
// it is not happy.

// Approach:
// 1. Initialize a HashSet to store numbers seen during the process.
// 2. While n is not 1 and has not been seen before:
//    - Add n to the set of seen numbers.
//    - Compute the sum of the squares of n's digits using a helper function.
//    - Update n to the new sum.
// 3. After the loop, return true if n equals 1 (happy number), or false if a cycle is detected.
// 4. The helper function getSumOfSquares extracts each digit, squares it, and sums the squares.

// Key Points to Remember:
// - A happy number will eventually reach 1, while an unhappy number will enter a cycle.
// - The HashSet is used to detect cycles by checking if a number has been seen before.
// - The getSumOfSquares function processes digits by extracting them via modulo (%) and division (/).
// - Edge cases: n = 1 (happy, returns true), n = 7 (happy), n = 4 (unhappy, enters cycle 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4).
// - The algorithm handles any positive integer input as specified.

// Time Complexity: O(log n) on average
// - Each iteration reduces n by summing the squares of its digits, which typically reduces the number of digits.
// - For most inputs, the number of iterations is logarithmic because the sum of squares tends to produce smaller numbers.
// - In the worst case (e.g., cycles), the number of iterations is bounded by the size of the cycle, but this is typically small.
// - The getSumOfSquares function processes each digit in O(log n) (since the number of digits is logarithmic in n).

// Space Complexity: O(log n)
// - The HashSet stores numbers seen during the process.
// - The number of unique sums encountered is typically logarithmic, as numbers tend to shrink or enter a small cycle.
// - In practice, the space used is small, as cycles or convergence to 1 happen quickly.

public class Solution {
    public boolean isHappy(int n) {
        // Initialize a HashSet to track seen numbers
        Set<Integer> seenNumbers = new HashSet<>();
        
        // Continue until n is 1 (happy) or a cycle is detected
        while (n != 1 && !seenNumbers.contains(n)) {
            // Add current number to seen set
            seenNumbers.add(n);
            // Compute sum of squares of digits
            n = getSumOfSquares(n);
        }
        
        // Return true if n is 1, false if a cycle is detected
        return n == 1;
    }
    
    // Helper function to compute sum of squares of digits
    private int getSumOfSquares(int n) {
        int sum = 0;
        // Extract each digit and sum its square
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}