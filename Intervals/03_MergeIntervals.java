// Problem: Merge Intervals
// Given an array of intervals where each interval is a pair [start, end], merge all overlapping intervals
// and return an array of non-overlapping intervals that cover all the intervals in the input.
// The intervals are not guaranteed to be sorted.

// DSA Pattern: Interval Manipulation / Sorting
// This problem is solved by sorting the intervals by start time and then merging overlapping intervals
// in a single pass. A linked list is used to efficiently build the result by updating the last interval.

// Approach:
// 1. Sort the intervals array by start time to ensure intervals are processed in order.
// 2. Initialize a LinkedList to store the merged intervals.
// 3. Iterate through each interval in the sorted array:
//    - If the result list is empty or the last interval in the result does not overlap with the current interval
//      (i.e., last interval's end time < current interval's start time), add the current interval to the result.
//    - Otherwise, merge the current interval with the last interval in the result by updating the end time
//      to the maximum of the two end times.
// 4. Convert the LinkedList to an array and return it.

// Key Points to Remember:
// - Two intervals [a, b] and [c, d] overlap if b >= c; they are merged into [min(a, c), max(b, d)].
// - Sorting ensures intervals are processed in order, making it easy to merge consecutive overlapping intervals.
// - Using a LinkedList allows efficient updates to the last interval’s end time without resizing.
// - Edge cases:
//   - Empty intervals array: return empty array.
//   - Single interval: return it as is.
//   - Non-overlapping intervals: return all intervals unchanged.
//   - All intervals overlap: merge into a single interval.
// - The output intervals are non-overlapping and sorted by start time.

// Time Complexity: O(n log n), where n is the number of intervals
// - Sorting the intervals takes O(n log n).
// - Iterating through the intervals and merging takes O(n).
// - Converting the LinkedList to an array takes O(n).
// - The dominant factor is the sorting step, resulting in O(n log n).

// Space Complexity: O(n) for the output
// - The LinkedList stores up to n merged intervals.
// - The output array is required by the problem and not counted as extra space.
// - Excluding the output, the space used is O(n) for the LinkedList.
// - The sorting step may use O(log n) space for the recursion stack in some implementations, but this is typically negligible.

// Constraints:
// - 1 <= intervals.length <= 10^4
// - intervals[i].length == 2
// - 0 <= intervals[i][0] <= intervals[i][1] <= 10^9

public class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // Initialize LinkedList for merged intervals
        LinkedList<int[]> ans = new LinkedList<>();
        
        // Process each interval
        for (int[] interval : intervals) {
            // If list is empty or no overlap with last interval, add current interval
            if (ans.isEmpty() || ans.getLast()[1] < interval[0]) {
                ans.add(interval);
            } else {
                // Overlap exists, merge by updating end time of last interval
                ans.getLast()[1] = Math.max(ans.getLast()[1], interval[1]);
            }
        }
        
        // Convert LinkedList to array
        return ans.toArray(new int[ans.size()][]);
    }
}