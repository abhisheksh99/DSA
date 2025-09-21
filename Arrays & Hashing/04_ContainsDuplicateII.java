class Solution {
    // Problem: Contains Duplicate II
    // Given an integer array nums and an integer k, return true if there are two distinct
    // indices i and j in the array such that nums[i] == nums[j] and the absolute difference
    // between i and j is at most k. Otherwise, return false.

    // DSA Pattern: Sliding Window with Hash Set
    // This problem is solved using a sliding window approach combined with a HashSet to track
    // unique elements within a window of size k. The HashSet ensures O(1) average-time complexity
    // for checking duplicates, and the sliding window maintains the constraint |i - j| <= k.

    // Approach:
    // 1. Initialize an empty HashSet to store numbers within the current window of size at most k.
    // 2. Iterate through the array:
    //    - For each number nums[i], check if it exists in the HashSet.
    //    - If it exists, a duplicate is found within the window, so return true.
    //    - Add the current number to the HashSet.
    //    - If the window size exceeds k (i.e., seen.size() > k), remove the number from the
    //      beginning of the window (nums[i - k]) to maintain the window size.
    // 3. If no duplicates are found within any window of size k, return false.

    // Key Points to Remember:
    // - Use a HashSet to track unique elements within the sliding window for O(1) lookup and insertion.
    // - Maintain the window size by removing elements when the size exceeds k.
    // - The window size is controlled by the index difference (i - (i - k) = k).
    // - Check for duplicates before adding the current number to avoid false positives.
    // - Handle edge cases like empty arrays or k = 0 (no valid window).
    // - The sliding window ensures that only elements within |i - j| <= k are considered.

    // Time Complexity: O(n)
    // - We iterate through the array once, where n is the length of nums.
    // - HashSet operations (contains, add, remove) are O(1) on average.
    // - Overall, the time complexity is O(n).

    // Space Complexity: O(min(n, k))
    // - The HashSet stores at most min(n, k) elements, as the window size is limited by k.
    // - If k >= n, the HashSet stores at most n elements.
    // - Additional space for loop variables is O(1), so the overall space complexity is O(min(n, k)).

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Initialize a HashSet to store numbers within the sliding window
        HashSet<Integer> seen = new HashSet<>();
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Check if the current number is already in the HashSet (duplicate within window)
            if (seen.contains(nums[i])) {
                return true;
            }
            // Add the current number to the HashSet
            seen.add(nums[i]);
            
            // If the window size exceeds k, remove the number at the start of the window
            if (seen.size() > k) {
                seen.remove(nums[i - k]);
            }
        }
        
        // If no duplicates are found within any window of size k, return false
        return false;
    }
}