// Problem: Insert Interval
// Given a sorted list of non-overlapping intervals and a new interval, insert the new interval into the list
// and merge overlapping intervals. Return the resulting list of merged intervals.
// Each interval is a pair [start, end] where start <= end, and the input intervals are sorted by start time.

// DSA Pattern: Interval Manipulation / Merge Intervals
// This problem is solved by adding the new interval to the list, sorting by start time, and then merging overlapping
// intervals using a linear scan, similar to the Merge Intervals problem.

// Approach:
// 1. Convert the input intervals array to a list and add the newInterval.
// 2. Sort the list of intervals by start time to ensure all intervals are in order.
// 3. Initialize a result list to store merged intervals and set current to the first interval.
// 4. Iterate through the remaining intervals in the sorted list:
//    - If the current interval's end time is greater than or equal to the next interval's start time
//      (i.e., they overlap), merge them by updating current's end time to the maximum of both end times.
//    - Otherwise, add current to the result list and update current to the next interval.
// 5. After the loop, add the final current interval to the result list.
// 6. Convert the result list to an array and return it.

// Key Points to Remember:
// - The input intervals are sorted and non-overlapping, but adding newInterval requires sorting again.
// - Two intervals [a, b] and [c, d] overlap if b >= c; they are merged into [min(a, c), max(b, d)].
// - The sorting step ensures all intervals are processed in order of start time, simplifying the merge logic.
// - Edge cases:
//   - Empty intervals list: return [newInterval].
//   - newInterval does not overlap with any interval: inserted and returned as is.
//   - newInterval overlaps with one or more intervals: merged appropriately.
//   - newInterval extends beyond all intervals: handled by sorting and merging.
// - The result intervals are non-overlapping and sorted by start time.

// Time Complexity: O(n log n), where n is the number of intervals
// - Converting the input array to a list takes O(n).
// - Adding newInterval is O(1).
// - Sorting the list takes O(n log n).
// - Merging intervals takes O(n) for the linear scan.
// - Converting the result list to an array is O(n).
// - The dominant factor is the sorting step, resulting in O(n log n).

// Space Complexity: O(n) for the output
// - The intervalList stores n + 1 intervals (input intervals plus newInterval).
// - The result list stores up to n + 1 merged intervals.
// - The output array is required by the problem and not counted as extra space.
// - Excluding the output, the space used is O(n) for the intervalList and result list.

// Constraints:
// - 0 <= intervals.length <= 10^4
// - intervals[i].length == 2
// - newInterval.length == 2
// - -10^9 <= intervals[i][0] <= intervals[i][1] <= 10^9
// - -10^9 <= newInterval[0] <= newInterval[1] <= 10^9
// - Intervals are sorted by start time and non-overlapping.

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Convert intervals to list and add newInterval
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        intervalList.add(newInterval);
        // Sort intervals by start time
        Collections.sort(intervalList, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Initialize result list and current interval
        List<int[]> res = new ArrayList<>();
        int[] current = intervalList.get(0);
        
        // Merge overlapping intervals
        for (int i = 1; i < intervalList.size(); i++) {
            int[] interval = intervalList.get(i);
            // If current interval overlaps with next, merge by updating end time
            if (current[1] >= interval[0]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                // No overlap, add current to result and move to next interval
                res.add(current);
                current = interval;
            }
        }
        
        // Add the last interval
        res.add(current);
        
        // Convert result list to array
        return res.toArray(new int[res.size()][]);
    }
}