public class Solution {
    // Problem: Fizz Buzz
    // Given an integer n, return a string list result where:
    // - result[i] = "FizzBuzz" if i is divisible by both 3 and 5,
    // - result[i] = "Fizz" if i is divisible by 3,
    // - result[i] = "Buzz" if i is divisible by 5,
    // - result[i] = i (as a string) if none of the above conditions are true.
    // The list should contain answers for numbers from 1 to n inclusive.

    // DSA Pattern: Iteration with Conditionals
    // This problem is solved using a straightforward iteration over the numbers from 1 to n,
    // applying conditional checks to determine the appropriate string for each number based
    // on divisibility rules. The results are collected in a list.

    // Approach:
    // 1. Initialize an empty ArrayList to store the result strings.
    // 2. Iterate from i = 1 to n:
    //    - Check if i is divisible by both 3 and 5 (i % 3 == 0 && i % 5 == 0), add "FizzBuzz".
    //    - Else, check if i is divisible by 3 (i % 3 == 0), add "Fizz".
    //    - Else, check if i is divisible by 5 (i % 5 == 0), add "Buzz".
    //    - Otherwise, add i as a string using Integer.toString(i).
    // 3. Return the result list containing the Fizz Buzz sequence.

    // Key Points to Remember:
    // - Check divisibility by both 3 and 5 first to avoid overlap with individual checks.
    // - Use else-if to ensure only one condition is applied per number.
    // - Convert i to a string for non-divisible cases using Integer.toString.
    // - Handle edge cases like n = 0 (empty list, but problem guarantees n >= 1).
    // - The order of conditions is important: check "FizzBuzz" before "Fizz" or "Buzz".
    // - The output list must include results for all numbers from 1 to n inclusive.
    // - Use ArrayList for dynamic list growth and O(1) amortized add operations.

    // Time Complexity: O(n)
    // - Iterate through numbers 1 to n, performing O(1) operations per number (modulo, string
    //   conversion, list add).
    // - Overall, the time complexity is O(n), where n is the input integer.

    // Space Complexity: O(n)
    // - The result ArrayList stores n strings, using O(n) space.
    // - Each string is of constant length ("Fizz", "Buzz", "FizzBuzz", or a number string),
    //   but the list size dominates.
    // - No additional data structures are used beyond the output list.
    // - Overall, the space complexity is O(n), excluding the output.

    public List<String> fizzBuzz(int n) {
        // Initialize ArrayList to store the result
        List<String> result = new ArrayList<>();
        
        // Iterate from 1 to n inclusive
        for (int i = 1; i <= n; i++) {
            // Check if divisible by both 3 and 5
            if (i % 3 == 0 && i % 5 == 0) {
                // Number is divisible by both 3 and 5
                result.add("FizzBuzz");
            } 
            // Check if divisible by 3
            else if (i % 3 == 0) {
                // Number is divisible by 3
                result.add("Fizz");
            } 
            // Check if divisible by 5
            else if (i % 5 == 0) {
                // Number is divisible by 5
                result.add("Buzz");
            } 
            // Number is not divisible by 3 or 5
            else {
                result.add(Integer.toString(i));
            }
        }
        
        // Return the Fizz Buzz sequence
        return result;
    }
}