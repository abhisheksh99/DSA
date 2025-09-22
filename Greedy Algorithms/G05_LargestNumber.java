// Problem: Largest Number
// Given an array of integers 'nums', return the largest possible number that can be formed by concatenating the numbers
// in any order. The result should be returned as a string to handle potentially large numbers.

// DSA Pattern: Greedy / Custom Sorting
// This problem is solved using a greedy approach by converting numbers to strings and sorting them with a custom comparator
// that compares concatenated pairs to determine the optimal order for forming the largest number.

// Approach:
// 1. Convert each integer in 'nums' to a string and store in an array.
// 2. Sort the string array using a custom comparator that compares two strings 'a' and 'b' by concatenating them in both
//    orders (a+b vs b+a) and choosing the order that produces the larger result.
// 3. Handle the edge case where the largest number starts with "0" (e.g., input [0,0]), which means the result is "0".
// 4. Concatenate all strings in the sorted array using a StringBuilder to form the final result.
// 5. Return the concatenated string.

// Key Points to Remember:
// - Converting numbers to strings allows concatenation and comparison as strings.
// - The custom comparator (b+a).compareTo(a+b) ensures that strings are sorted in descending order based on which concatenation
//   produces a larger number (e.g., "3"+"30" = "330" vs "30"+"3" = "303", so "3" comes first).
// - If the first string after sorting is "0", the entire result is "0" because all numbers must be zero.
// - Edge cases:
//   - Empty array: not applicable due to constraints.
//   - Single number: return that number as a string.
//   - All zeros: return "0".
//   - Large numbers: handled by using strings to avoid integer overflow.
// - The solution assumes non-negative integers in the input array.

// Time Complexity: O(n log n * k), where n is the length of nums and k is the average length of numbers as strings
// - Converting integers to strings takes O(n * k).
// - Sorting the string array takes O(n log n), with each comparison taking O(k) for string concatenation and comparison.
// - Building the final string with StringBuilder takes O(n * k).
// - The dominant factor is O(n log n * k) due to sorting.

// Space Complexity: O(n * k)
// - The string array stores n strings, each of average length k, requiring O(n * k) space.
// - The StringBuilder requires O(n * k) space for the final result.
// - Additional O(log n) space may be used for the sorting recursion stack, but this is negligible.

// Constraints:
// - 1 <= nums.length <= 100
// - 0 <= nums[i] <= 10^9

public class Solution {
    public String largestNumber(int[] nums) {
        // Convert integers to strings
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        
        // Sort strings using custom comparator
        Arrays.sort(array, (a, b) -> (b + a).compareTo(a + b));
        
        // Handle edge case where result is "0"
        if (array[0].equals("0")) {
            return "0";
        }
        
        // Build the largest number
        StringBuilder largest = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            largest.append(array[i]);
        }
        
        // Return the final string
        return largest.toString();
    }
}