class Solution {
    // Problem: Contains Duplicate
    // Given an integer array nums, return true if any value appears at least twice in the array,
    // and return false if every element is distinct.

    // DSA Pattern: Hash Table (Hash Set)
    // This problem is solved using a HashSet to track unique elements. The HashSet allows for O(1)
    // average-time complexity for checking if an element has been seen before, making it efficient
    // for detecting duplicates.

    // Approach:
    // 1. Initialize an empty HashSet to store numbers seen during iteration.
    // 2. Iterate through the array once:
    //    - For each number, check if it already exists in the HashSet.
    //    - If it exists, a duplicate is found, so return true.
    //    - If not, add the number to the HashSet.
    // 3. If the loop completes without finding a duplicate, return false.

    // Key Points to Remember:
    // - Use a HashSet for O(1) average-time complexity for lookup and insertion.
    // - Check for duplicates before adding to the HashSet to ensure correct logic.
    // - The HashSet only stores unique elements, so it’s ideal for this problem.
    // - Handle edge cases like an empty array or single-element array (no duplicates possible).
    // - Early return on finding a duplicate optimizes performance.

    // Time Complexity: O(n)
    // - We iterate through the array once, where n is the length of nums.
    // - HashSet operations (contains, add) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(n)
    // - The HashSet stores at most n unique elements in the worst case (no duplicates).
    // - Additional space for loop variables is O(1), so the overall space complexity is O(n).

    public boolean containsDuplicate(int[] nums) {
        // Initialize a HashSet to store unique numbers
        HashSet<Integer> seen = new HashSet<>();
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Check if the current number is already in the HashSet
            if (seen.contains(nums[i])) {
                // If found, a duplicate exists, so return true
                return true;
            }
            // If not a duplicate, add the number to the HashSet
            seen.add(nums[i]);
        }
        
        // If no duplicates are found, return false
        return false;
    }
}