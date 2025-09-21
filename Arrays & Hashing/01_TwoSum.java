class Solution {
    // Problem: Two Sum
    // Given an array of integers nums and an integer target, return indices of the two numbers 
    // such that they add up to the target. Assume exactly one solution exists.

    // DSA Pattern: Hash Table (Two-pointer or Hashing pattern)
    // This problem is solved using a Hash Table to achieve efficient lookup of complements.
    // The Hash Table stores each number and its index, allowing us to check if the complement
    // (target - nums[i]) exists in O(1) time.

    // Approach:
    // 1. Use a HashMap to store numbers as keys and their indices as values.
    // 2. Iterate through the array once.
    // 3. For each number, calculate its complement (target - nums[i]).
    // 4. Check if the complement exists in the HashMap:
    //    - If found, return the indices of the complement and the current number.
    //    - If not, add the current number and its index to the HashMap.
    // 5. If no solution is found, return an empty array.

    // Key Points to Remember:
    // - Use a HashMap for O(1) lookup time when checking for the complement.
    // - Store indices as values in the HashMap since the problem asks for indices, not numbers.
    // - Handle the case where no solution exists by returning an empty array.
    // - Iterate through the array only once to optimize performance.
    // - Ensure the complement is checked before adding the current number to avoid using the same element twice.

    // Time Complexity: O(n)
    // - We iterate through the array once, where n is the length of nums.
    // - HashMap operations (put, containsKey, get) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(n)
    // - The HashMap stores at most n key-value pairs (one for each number in the array).
    // - Additional space for the output array is O(1), so the overall space complexity is O(n).
    public int[] twoSum(int[] nums, int target) {
        // Initialize a HashMap to store number-to-index mappings
        HashMap<Integer, Integer> map = new HashMap<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement needed to reach the target
            int complement = target - nums[i];
            
            // Check if the complement exists in the HashMap
            if (map.containsKey(complement)) {
                // If found, return the indices of the complement and current number
                return new int[] { map.get(complement), i };
            }
            
            // If complement not found, add the current number and its index to the HashMap
            map.put(nums[i], i);
        }

        // If no solution is found, return an empty array
        return new int[] {};
    }
}