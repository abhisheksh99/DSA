class Solution {
    // Problem: Next Permutation
    // Given an array of integers nums representing a permutation, modify the array in-place to
    // generate the next lexicographically greater permutation. If no such permutation exists
    // (i.e., the array is the last permutation), rearrange it to the lexicographically smallest
    // permutation (sorted in ascending order).

    // DSA Pattern: Array Manipulation / Permutation Generation
    // This problem is solved using a standard algorithm for generating the next permutation in
    // lexicographical order. It involves finding the first pair of elements from the right that
    // are in increasing order, swapping the first element with the smallest element to its right
    // that is greater, and reversing the remaining elements to get the smallest possible suffix.

    // Approach:
    // 1. Traverse the array from right to left to find the first index i where nums[i] < nums[i+1]
    //    (the first "dip" indicating a non-decreasing sequence breaks).
    // 2. If such an i exists (i >= 0):
    //    - Find the smallest element to the right of i (at index j) where nums[j] > nums[i].
    //    - Swap nums[i] and nums[j] to create a larger prefix.
    // 3. Reverse the subarray from i+1 to the end to make the suffix as small as possible
    //    (lexicographically smallest).
    // 4. If no such i exists (array is in descending order), reverse the entire array to get
    //    the smallest permutation.

    // Key Points to Remember:
    // - The next permutation is the lexicographically next greater arrangement of numbers.
    // - Find the first pair from the right where nums[i] < nums[i+1] to identify the point to
    //   increase the permutation.
    // - Swap with the smallest number to the right that is greater than nums[i] to ensure the
    //   smallest possible increase in the prefix.
    // - Reverse the suffix (from i+1 to end) to get the smallest possible suffix.
    // - If the array is in descending order (last permutation), reverse it to get the first
    //   permutation (ascending order).
    // - Perform all operations in-place to meet the problem's requirements.
    // - Handle edge cases like single-element arrays (no change) or two-element arrays (swap if needed).

    // Time Complexity: O(n)
    // - Finding the first dip (i) takes O(n) in the worst case.
    // - Finding the smallest number to swap (j) takes O(n) in the worst case.
    // - Reversing the suffix takes O(n/2) = O(n).
    // - Swapping is O(1).
    // - Overall, the time complexity is O(n), where n is the length of nums.

    // Space Complexity: O(1)
    // - All operations (swap, reverse) are done in-place using only a constant amount of extra
    //   space for variables (i, j, temp).
    // - No additional data structures are used, so the space complexity is O(1).

    public void nextPermutation(int[] nums) {
        // Initialize i to find the first index from the right where nums[i] < nums[i+1]
        int i = nums.length - 2;

        // Find the first decreasing element from the right
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }

        // If a dip is found (i >= 0), find the smallest number to swap
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the smallest number to the right of i that is greater than nums[i]
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap nums[i] and nums[j] to increase the prefix
            swap(nums, i, j);
        }

        // Reverse the subarray from i+1 to the end to get the smallest suffix
        reverse(nums, i + 1);
    }

    // Helper function to swap two elements in the array
    public void swap(int[] nums, int i, int j) {
        // Store nums[i] in temp
        int temp = nums[i];
        // Assign nums[j] to nums[i]
        nums[i] = nums[j];
        // Assign temp to nums[j]
        nums[j] = temp;
    }

    // Helper function to reverse the subarray from index i to the end
    public void reverse(int[] nums, int i) {
        // Initialize j at the end of the array
        int j = nums.length - 1;

        // Swap elements from i to j until they meet
        while (i < j) {
            swap(nums, j, i);
            i++;
            j--;
        }
    }
}