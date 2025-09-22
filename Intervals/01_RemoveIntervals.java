// Problem: Remove Interval
// Given a sorted list of non-overlapping intervals and an interval to be removed, return the list of intervals after removing the specified interval.
// Each interval is a pair [start, end] where start <= end, and the intervals in the input are sorted by start time and non-overlapping.
// The output should contain the remaining intervals after removing all parts that overlap with toBeRemoved.

// DSA Pattern: Interval Manipulation / Sweep Line
// This problem is solved by iterating through the intervals and determining how each one interacts with the interval to be removed.
// For each interval, we check if it is completely outside the removal interval or overlaps, and split the interval if necessary.

// Approach:
// 1. Initialize an empty list result to store the output intervals.
// 2. Iterate through each interval in intervals:
//    - If the interval is completely outside toBeRemoved (interval ends before toBeRemoved starts or starts after toBeRemoved ends),
//      add it to result as is.
//    - If the interval overlaps with toBeRemoved:
//      - If interval[0] < toBeRemoved[0], add the non-overlapping left part [interval[0], toBeRemoved[0]] to result.
//      - If interval[1] > toBeRemoved[1], add the non-overlapping right part [toBeRemoved[1], interval[1]] to result.
// 3. Return the result list containing the remaining intervals.

// Key Points to Remember:
// - Intervals are sorted and non-overlapping, simplifying the logic as we only need to compare each interval with toBeRemoved.
// - An interval overlaps with toBeRemoved if it is not completely to the left (interval[1] < toBeRemoved[0]) or to the right (interval[0] > toBeRemoved[1]).
// - Overlapping intervals may need to be split into two parts: one before toBeRemoved and one after.
// - Edge cases:
//   - Interval completely inside toBeRemoved: no part is added to result.
//   - Interval completely outside toBeRemoved: added as is.
//   - toBeRemoved completely outside all intervals: all intervals are added unchanged.
//   - Empty intervals list: return empty list.
// - The output intervals remain sorted and non-overlapping due to the input properties and the way splits are handled.

// Time Complexity: O(n), where n is the number of intervals
// - The algorithm iterates through the intervals array exactly once.
// - Each iteration performs O(1) operations: comparisons, list creation, and adding to result.
// - Converting int[] to List<Integer> and adding to result is O(1) per interval.

// Space Complexity: O(n) for the output list
// - The result list stores at most 2n intervals (if every interval is split into two parts).
// - The output list is required by the problem and not counted as extra space.
// - Excluding the output, the algorithm uses O(1) extra space (no additional data structures).

// Constraints:
// - 1 <= intervals.length <= 10^4
// - intervals[i].length == 2
// - toBeRemoved.length == 2
// - -10^9 <= intervals[i][0] <= intervals[i][1] <= 10^9
// - -10^9 <= toBeRemoved[0] <= toBeRemoved[1] <= 10^9
// - Intervals are sorted by start time and non-overlapping.

public class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        // Initialize result list
        List<List<Integer>> result = new ArrayList<>();
        
        // Iterate through each interval
        for (int[] interval : intervals) {
            // Case 1: No overlap (interval is completely before or after toBeRemoved)
            if (interval[0] > toBeRemoved[1] || interval[1] < toBeRemoved[0]) {
                result.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // Case 2: Overlap exists, add non-overlapping parts
                // Add left non-overlapping part if it exists
                if (interval[0] < toBeRemoved[0]) {
                    result.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                // Add right non-overlapping part if it exists
                if (interval[1] > toBeRemoved[1]) {
                    result.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        
        return result;
    }
}