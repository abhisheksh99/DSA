class Solution {
    // Problem: Merge Sorted Array
    // Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
    // nums1 has a length of m + n, where m is the number of elements in nums1 and n is the number
    // of elements in nums2. The last n positions in nums1 are filled with zeros and can be used
    // to store the merged result. The arrays are sorted in ascending order.

    // DSA Pattern: Two Pointers (Reverse Merge)
    // This problem is solved using a two-pointer technique, merging the arrays from the end to the
    // beginning. By working backwards, we avoid overwriting elements in nums1 that still need to be
    // compared, utilizing the extra space at the end of nums1.

    // Approach:
    // 1. Initialize three pointers:
    //    - p1 at the last element of nums1's valid elements (m - 1).
    //    - p2 at the last element of nums2 (n - 1).
    //    - i at the last position of nums1 (m + n - 1).
    // 2. While p2 >= 0 (i.e., there are elements left in nums2):
    //    - If p1 >= 0 and nums1[p1] > nums2[p2], place nums1[p1] at nums1[i], decrement p1 and i.
    //    - Otherwise, place nums2[p2] at nums1[i], decrement p2 and i.
    // 3. Continue until all elements from nums2 are merged. Elements from nums1 (if any remain) are
    //    already in their correct sorted positions.

    // Key Points to Remember:
    // - Merge from the end to the beginning to utilize the extra space in nums1 and avoid overwriting.
    // - Use two pointers (p1, p2) to track the current elements in nums1 and nums2.
    // - Compare the largest remaining elements from both arrays to place the larger one at the end.
    // - No need to handle remaining nums1 elements, as they are already in place if p2 reaches -1.
    // - Handle edge cases like m = 0 (nums1 is empty, copy nums2) or n = 0 (nums1 is unchanged).
    // - The arrays are sorted in ascending order, so comparisons ensure the merged array is sorted.

    // Time Complexity: O(n + m)
    // - We iterate through all elements of nums2 (n iterations) and potentially all elements of nums1
    //   (m iterations), depending on the comparisons.
    // - Each iteration involves O(1) operations (comparisons, assignments).
    // - Overall, the time complexity is O(n + m), where m and n are the lengths of nums1 and nums2.

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for pointers (p1, p2, i).
    // - The merging is done in-place in nums1, so no additional data structures are needed.
    // - Overall, the space complexity is O(1).

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointer p1 at the last valid element of nums1
        int p1 = m - 1;
        // Initialize pointer p2 at the last element of nums2
        int p2 = n - 1;
        // Initialize pointer i at the last position of nums1
        int i = m + n - 1;

        // Merge arrays from the end while there are elements in nums2
        while (p2 >= 0) {
            // If there are elements in nums1 and nums1[p1] > nums2[p2], place nums1[p1] at nums1[i]
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1];
                p1--;
            }
            // Otherwise, place nums2[p2] at nums1[i]
            else {
                nums1[i] = nums2[p2];
                p2--;
            }
            // Move to the next position to fill in nums1
            i--;
        }
        // No need to handle remaining nums1 elements, as they are already in place
    }
}