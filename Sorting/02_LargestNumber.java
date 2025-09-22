class Solution {
    // Problem: Largest Number
    // Given an array of non-negative integers nums, arrange them to form the largest possible
    // number and return it as a string. The result may be very large, so return it as a string
    // instead of an integer. The array is non-empty and contains non-negative integers.

    // DSA Pattern: String Manipulation and Custom Sorting
    // This problem is solved by converting integers to strings and sorting them with a custom
    // comparator to form the largest possible number. The comparator concatenates pairs of
    // numbers in different orders (a+b vs b+a) and sorts based on which order produces the
    // larger result. Special handling is needed for cases where the largest number starts with
    // zero (e.g., [0,0] results in "0").

    // Approach:
    // 1. Convert each integer in nums to a string and store in an array.
    // 2. Sort the string array using a custom comparator:
    //    - For two strings a and b, compare the concatenated strings "a+b" and "b+a".
    //    - Sort in descending order so that the larger concatenation comes first.
    // 3. Handle the edge case: if the first string after sorting is "0", return "0"
    //    (e.g., when all numbers are 0).
    // 4. Concatenate all strings in the sorted array using a StringBuilder to form the result.
    // 5. Return the final string.

    // Key Points to Remember:
    // - Converting numbers to strings allows concatenation to form the largest number.
    // - The custom comparator (b+a).compareTo(a+b) ensures numbers are ordered to maximize
    //   the result (e.g., for 3 and 30, "330" > "303", so 3 comes before 30).
    // - Sorting in descending order based on concatenation ensures the largest number.
    // - If the first string is "0" after sorting, the entire result is "0" (handles cases like [0,0]).
    // - Use StringBuilder for efficient string concatenation.
    // - The input array is non-empty, and numbers are non-negative, simplifying edge cases.
    // - The output is a string to handle potentially large numbers.

    // Time Complexity: O(n * log n * m)
    // - n is the length of the input array nums.
    // - Converting integers to strings is O(n * m), where m is the average number of digits
    //   per number (bounded by log of the maximum integer).
    // - Sorting the array takes O(n * log n) comparisons, and each comparison involves
    //   concatenating and comparing strings, which is O(m) in the worst case.
    // - Concatenating strings using StringBuilder is O(n * m).
    // - Overall, the dominant term is O(n * log n * m) due to sorting with string comparisons.
    // - Edge case checks (e.g., first string is "0") are O(1).

    // Space Complexity: O(n * m)
    // - The string array stores n strings, each of average length m, so O(n * m).
    // - The StringBuilder stores the final result, which is O(n * m) in the worst case.
    // - Additional space for sorting (e.g., Arrays.sort) is O(log n) for the recursion stack.
    // - Excluding the output, the space complexity is O(n * m) for the string array.

    public String largestNumber(int[] nums) {
        // Initialize array to store numbers as strings
        String[] array = new String[nums.length];

        // Convert each integer to a string
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }

        // Sort strings using a custom comparator to maximize the concatenated result
        Arrays.sort(array, (a, b) -> (b + a).compareTo(a + b));

        // If the first string is "0", the result is "0" (handles cases like [0,0])
        if (array[0].equals("0")) {
            return "0";
        }

        // Build the largest number by concatenating sorted strings
        StringBuilder largest = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            largest.append(array[i]);
        }

        // Return the final string
        return largest.toString();
    }
}