// Problem: Meeting Rooms
// Given an array of meeting time intervals where each interval is a pair [start, end], determine if a person
// can attend all meetings without conflicts. A conflict occurs if any two meetings overlap.

// DSA Pattern: Interval Manipulation / Sorting
// This problem is solved by sorting the intervals by start time and checking for any overlaps.
// If any meeting's end time is greater than the next meeting's start time, there is a conflict.

// Approach:
// 1. Sort the intervals array by start time to ensure meetings are in chronological order.
// 2. Iterate through the sorted intervals from index 0 to n-2:
//    - For each interval, check if its end time (intervals[i][1]) is greater than the next interval's start time (intervals[i+1][0]).
//    - If true, there is an overlap, so return false (cannot attend all meetings).
// 3. If no overlaps are found, return true (can attend all meetings).

// Key Points to Remember:
// - Two intervals [a, b] and [c, d] overlap if b > c (end time of first exceeds start time of second).
// - Sorting by start time ensures we only need to check consecutive intervals for overlaps.
// - Edge cases:
//   - Empty intervals array: return true (no meetings, no conflicts).
//   - Single interval: return true (no conflicts possible).
//   - Intervals with same start time: overlap depends on end times.
// - The solution assumes valid intervals (start <= end).

// Time Complexity: O(n log n), where n is the number of intervals
// - Sorting the intervals takes O(n log n).
// - Iterating through the intervals takes O(n).
// - The dominant factor is the sorting step, resulting in O(n log n).

// Space Complexity: O(1) excluding the input
// - No additional data structures are used beyond a few variables.
// - The sorting step may use O(log n) space for the recursion stack in some implementations, but this is typically negligible.
// - The input array is modified in-place during sorting.

// Constraints:
// - 0 <= intervals.length <= 10^4
// - intervals[i].length == 2
// - 0 <= intervals[i][0] <= intervals[i][1] <= 10^6

public class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Check for any overlapping meetings
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false; // Overlap found, cannot attend all meetings
            }
        }
        
        return true; // No overlaps, can attend all meetings
    }
}