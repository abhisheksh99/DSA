class Solution {
    // Problem: 3Sum
    // Given an integer array nums, return all unique triplets [nums[i], nums[j], nums[k]] such
    // that i != j != k and nums[i] + nums[j] + nums[k] == 0. The solution set must not contain
    // duplicate triplets.

    // DSA Pattern: Two Pointers with Sorting
    // This problem is solved using a combination of sorting and the two-pointer technique. Sorting
    // the array allows us to efficiently find triplets summing to zero and avoid duplicates. The
    // problem is reduced to finding two numbers that sum to a target (-nums[i]) for each fixed i,
    // using two pointers to search the remaining sorted subarray.

    // Approach:
    // 1. Sort the array to enable the two-pointer technique and handle duplicates easily.
    // 2. Initialize an empty list to store the result triplets.
    // 3. Iterate through the array with index i (up to nums.length - 2 to leave room for two more elements):
    //    - Skip if nums[i] > 0, as the sum cannot be zero with positive numbers in a sorted array.
    //    - Skip duplicate nums[i] to avoid duplicate triplets (check if nums[i] == nums[i-1]).
    //    - Call twoSum2 to find pairs (nums[left], nums[right]) that sum to -nums[i].
    // 4. In twoSum2:
    //    - Use two pointers (left = i + 1, right = nums.length - 1) to find pairs.
    //    - Compute the sum: nums[i] + nums[left] + nums[right].
    //    - If sum == 0, add the triplet to the result and skip duplicates for left and right.
    //    - If sum < 0, increment left to increase the sum.
    //    - If sum > 0, decrement right to decrease the sum.
    // 5. Return the list of unique triplets.

    // Key Points to Remember:
    // - Sorting is crucial to use two pointers and handle duplicates efficiently.
    // - Skip nums[i] > 0, as positive numbers cannot form a triplet summing to zero in a sorted array.
    // - Avoid duplicate triplets by skipping duplicate values for i, left, and right.
    // - Use two pointers to solve the two-sum subproblem for each fixed i, reducing complexity.
    // - Ensure i != left != right by setting left = i + 1 and right = nums.length - 1.
    // - Handle edge cases like arrays with fewer than 3 elements (implicitly handled by loop bounds).
    // - Increment/decrement pointers after adding a triplet to avoid using the same elements.

    // Time Complexity: O(n^2)
    // - Sorting the array takes O(n log n), where n is the length of nums.
    // - The outer loop runs O(n) times, and for each i, the twoSum2 function uses two pointers,
    //   which takes O(n) in the worst case, leading to O(n * n) = O(n^2).
    // - Overall, the time complexity is dominated by O(n^2).

    // Space Complexity: O(1) or O(n) depending on the sorting implementation
    // - The result list is required by the problem and not counted as extra space.
    // - The two-pointer variables (left, right) and loop variables use O(1) space.
    // - Sorting may use O(1) space (in-place sorting like heapsort) or O(n) space (e.g., merge sort).
    // - Excluding the output, the space complexity is O(1) for in-place sorting or O(n) otherwise.

    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to enable two-pointer technique and handle duplicates
        Arrays.sort(nums);
        // Initialize result list to store triplets
        List<List<Integer>> result = new ArrayList<>();

        // Iterate through the array, fixing the first element of the triplet
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            // Skip duplicate nums[i] to avoid duplicate triplets
            if (i == 0 || nums[i] != nums[i - 1]) {
                // Call twoSum2 to find pairs that sum to -nums[i]
                twoSum2(nums, i, result);
            }
        }

        // Return the list of unique triplets
        return result;
    }

    // Helper function to find two numbers that sum to -nums[i]
    void twoSum2(int[] nums, int i, List<List<Integer>> result) {
        // Initialize left pointer after i
        int left = i + 1;
        // Initialize right pointer at the end
        int right = nums.length - 1;

        // Use two pointers to find pairs
        while (left < right) {
            // Calculate the sum of the triplet
            int sum = nums[i] + nums[left] + nums[right];

            // If sum is zero, add the triplet to the result
            if (sum == 0) {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                // Increment left and decrement right
                left++;
                right--;
                // Skip duplicate nums[left] to avoid duplicate triplets
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
            // If sum is less than zero, increment left to increase the sum
            else if (sum < 0) {
                left++;
            }
            // If sum is greater than zero, decrement right to decrease the sum
            else {
                right--;
            }
        }
    }
}