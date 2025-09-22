class Solution {
    // Problem: Median of Two Sorted Arrays
    // Given two sorted arrays nums1 and nums2 of lengths m and n respectively, find the median
    // of the two sorted arrays when merged. The arrays are sorted in ascending order, and the
    // median is the middle element (or average of two middle elements for even total length).

    // DSA Pattern: Merge Process (Two Pointers)
    // This problem is solved using a merge-like process with two pointers, similar to merging
    // two sorted arrays, but only up to the point where the median is found. By leveraging the
    // sorted property of the arrays, we can find the median without fully merging the arrays,
    // focusing on the middle position(s) of the combined sorted sequence.

    // Approach:
    // 1. Initialize variables: m (length of nums1), n (length of nums2), total (m + n).
    // 2. Initialize two pointers: i for nums1, j for nums2, and variables median1 and median2
    //    to store the last two elements processed (needed for even-length median calculation).
    // 3. Iterate up to total/2 + 1 elements to reach the median position(s):
    //    - Shift median2 to median1 to keep track of the previous element.
    //    - Compare elements at nums1[i] and nums2[j]:
    //      - If i < m and (j >= n or nums1[i] <= nums2[j]), take nums1[i] and increment i.
    //      - Otherwise, take nums2[j] and increment j.
    //    - Store the selected element in median2.
    // 4. If total is even, return the average of median1 and median2.
    // 5. If total is odd, return median2 (the middle element).

    // Key Points to Remember:
    // - The median is the middle element (odd total) or average of two middle elements (even total).
    // - Only process up to total/2 + 1 elements to find the median, avoiding a full merge.
    // - Use two pointers to simulate merging, selecting the smaller element from nums1 or nums2.
    // - Handle edge cases like one empty array (median is from the other array) or both arrays
    //   having different lengths.
    // - Ensure floating-point division (e.g., / 2.0) for the even case to get a double result.
    // - The arrays are sorted, so comparisons ensure the correct order of elements in the merged sequence.
    // - Check array bounds (i < m, j < n) to avoid index out-of-bounds errors.

    // Time Complexity: O((m + n)/2) = O(m + n)
    // - We iterate up to total/2 + 1 elements, where total = m + n (lengths of nums1 and nums2).
    // - Each iteration involves O(1) operations (comparisons, assignments, increments).
    // - Overall, the time complexity is O(m + n) in the worst case.

    // Space Complexity: O(1)
    // - We use only a constant amount of extra space for variables (m, n, total, i, j, median1, median2).
    // - No additional data structures are used, and the arrays are not modified.
    // - Overall, the space complexity is O(1).

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Initialize lengths of the arrays
        int m = nums1.length, n = nums2.length;
        // Calculate total length
        int total = m + n;
        // Initialize variables to track last two elements for median
        int median1 = 0, median2 = 0;
        // Initialize pointers for nums1 and nums2
        int i = 0, j = 0;

        // Iterate up to the median position(s) (total/2 + 1)
        for (int k = 0; k <= total / 2; k++) {
            // Shift median2 to median1 to track previous element
            median1 = median2;
            // Choose the smaller element from nums1 or nums2
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                // Take element from nums1 and increment i
                median2 = nums1[i++];
            } else {
                // Take element from nums2 and increment j
                median2 = nums2[j++];
            }
        }

        // If total length is even, return average of two middle elements
        if (total % 2 == 0) {
            return (median1 + median2) / 2.0;
        }
        // If total length is odd, return the middle element
        else {
            return median2;
        }
    }
}