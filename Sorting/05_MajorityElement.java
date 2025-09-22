class Solution {
    // Problem: Majority Element
    // Given an array nums of size n, return the majority element. The majority element is
    // the element that appears more than ⌊n/2⌋ times. You may assume that the majority
    // element always exists in the array, and the array is non-empty.

    // DSA Pattern: Boyer-Moore Voting Algorithm
    // This problem is solved using the Boyer-Moore Voting Algorithm, which finds the majority
    // element in a single pass. The algorithm maintains a candidate and a count, incrementing
    // the count when the same element is encountered and decrementing it for different
    // elements. When the count reaches zero, a new candidate is chosen. Since the majority
    // element appears more than n/2 times, it will always be the final candidate.

    // Approach:
    // 1. Initialize a count variable to 0 and a candidate variable to store the potential
    //    majority element.
    // 2. Iterate through the array:
    //    - If count is 0, set the current element as the new candidate.
    //    - If the current element matches the candidate, increment the count.
    //    - If the current element differs from the candidate, decrement the count.
    // 3. After the loop, return the candidate, which is guaranteed to be the majority element
    //    due to the problem's assumption.

    // Key Points to Remember:
    // - The Boyer-Moore Voting Algorithm leverages the fact that the majority element appears
    //   more than n/2 times, ensuring it "survives" the counting process.
    // - When count reaches 0, any element can be chosen as a new candidate, as the majority
    //   element will eventually dominate.
    // - The algorithm does not require sorting or additional data structures, making it
    //   efficient.
    // - The problem guarantees a majority element exists, so no validation is needed.
    // - The algorithm works in a single pass, processing each element exactly once.
    // - The approach is in-place and uses constant extra space.

    // Time Complexity: O(n)
    // - n is the length of the input array nums.
    // - The algorithm performs a single pass through the array, processing each element once.
    // - Each operation (comparisons, increments, decrements) is O(1).
    // - Total time is O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only two variables (count and candidate), regardless of input size.
    // - No additional data structures are used, making it constant space.
    // - The output (returned candidate) is required by the problem.

    public int majorityElement(int[] nums) {
        // Initialize count to track candidate occurrences and candidate to store the element
        int count = 0;
        int candidate = 0;

        // Iterate through the array to find the majority candidate
        for (int num : nums) {
            // If count is zero, select the current element as the new candidate
            if (count == 0) {
                candidate = num;
            }

            // Increment count if current element matches candidate, else decrement
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Return the candidate, guaranteed to be the majority element
        return candidate;
    }
}