// Problem: Non-overlapping Intervals
// Given an array of intervals where each interval is a pair [start, end], return the minimum number of intervals
// to remove to make the remaining intervals non-overlapping.

// DSA Pattern: Interval Manipulation / Greedy
// This problem is solved using a greedy approach by sorting intervals by end time and selecting the maximum number
// of non-overlapping intervals. The number of intervals to remove is the total number of intervals minus the count
// of non-overlapping intervals.

// Approach:
// 1. Get the length of the intervals array (n).
// 2. Sort the intervals by end time to prioritize intervals that end earlier, allowing more intervals to fit.
// 3. Initialize prev as 0 (index of the first interval) and count as 1 (include the first interval).
// 4. Iterate through the intervals starting from index 1:
//    - If the current interval’s start time is greater than or equal to the previous interval’s end time
//      (no overlap), update prev to the current index and increment count.
// 5. Return n - count, which represents the number of intervals to remove to eliminate all overlaps.

// Key Points to Remember:
// - Sorting by end time ensures we greedily select intervals that leave the maximum space for subsequent intervals.
// - Two intervals [a, b] and [c, d] do not overlap if c >= b; we keep the interval with the earlier end time when choosing.
// - The count variable tracks the maximum number of non-overlapping intervals possible.
// - The number of intervals to remove is the total intervals (n) minus the non-overlapping ones (count).
// - Edge cases:
//   - Single interval: no overlaps, return 0.
//   - All intervals overlap: keep one, remove the rest.
//   - No overlaps: keep all, return 0.
// - The output is the minimum number of removals to achieve non-overlapping intervals.

// Time Complexity: O(n log n), where n is the number of intervals
// - Sorting the intervals takes O(n log n).
// - Iterating through the intervals takes O(n).
// - The dominant factor is the sorting step, resulting in O(n log n).

// Space Complexity: O(1) excluding the output
// - Only a few variables (prev, count) are used, requiring O(1) extra space.
// - The sorting step may use O(log n) space for the recursion stack in some implementations, but this is typically negligible.
// - The input array is modified in-place during sorting, and no additional data structures are used.

// Constraints:
// - 1 <= intervals.length <= 10^5
// - intervals[i].length == 2
// - -10^9 <= intervals[i][0] <= intervals[i][1] <= 10^9

public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // Get number of intervals
        int n = intervals.length;
        // Sort intervals by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        // Initialize variables: prev for last selected interval, count for non-overlapping intervals
        int prev = 0;
        int count = 1;
        
        // Greedily select non-overlapping intervals
        for (int i = 1; i < n; i++) {
            // If current interval starts after previous ends, include it
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }
        
        // Return number of intervals to remove
        return n - count;
    }
}