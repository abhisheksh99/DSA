// Problem: Meeting Rooms II
// Given an array of meeting time intervals where each interval is a pair [start, end], return the minimum number
// of meeting rooms required to schedule all meetings without conflicts.

// DSA Pattern: Interval Manipulation / Sweep Line
// This problem is solved using a sweep line approach by separating start and end times, sorting them, and tracking
// the maximum number of overlapping meetings to determine the number of rooms needed.

// Approach:
// 1. If the intervals array is empty, return 0 (no rooms needed).
// 2. Create two arrays: start (containing all start times) and end (containing all end times).
// 3. Populate start and end arrays from the intervals array.
// 4. Sort both start and end arrays independently.
// 5. Initialize pointers startptr and endptr to track positions in start and end arrays, and result to track the
//    current number of rooms in use.
// 6. While startptr is within the start array:
//    - If the current start time (start[startptr]) is greater than or equal to the current end time (end[endptr]),
//      a meeting has ended, so decrement result and increment endptr.
//    - A new meeting is starting, so increment result and startptr.
// 7. The maximum value of result during the process represents the minimum number of rooms needed.
// 8. Return result.

// Key Points to Remember:
// - Sorting start and end times separately allows us to process events (meeting starts and ends) in chronological order.
// - When a start time is encountered, a room is needed (increment result).
// - When an end time is encountered before a start time, a room is freed (decrement result).
// - The maximum value of result at any point is the minimum number of rooms required.
// - Edge cases:
//   - Empty intervals array: return 0.
//   - Single interval: return 1.
//   - Non-overlapping intervals: return 1.
//   - All meetings at the same time: return number of intervals.
// - The solution assumes valid intervals (start < end).

// Time Complexity: O(n log n), where n is the number of intervals
// - Creating and populating start and end arrays takes O(n).
// - Sorting start and end arrays takes O(n log n).
// - The while loop iterates at most 2n times (n start times + n end times), which is O(n).
// - The dominant factor is the sorting step, resulting in O(n log n).

// Space Complexity: O(n)
// - Two arrays (start and end) of size n are used, requiring O(n) space.
// - No other significant data structures are used.
// - The output (an integer) is required by the problem and not counted as extra space.

// Constraints:
// - 0 <= intervals.length <= 10^4
// - intervals[i].length == 2
// - 0 <= intervals[i][0] < intervals[i][1] <= 10^6

public class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // Handle empty input
        if (intervals.length == 0) {
            return 0;
        }
        
        // Initialize arrays for start and end times
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        
        // Populate start and end arrays
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        // Sort start and end times
        Arrays.sort(start);
        Arrays.sort(end);
        
        // Initialize pointers and result
        int startptr = 0;
        int endptr = 0;
        int result = 0;
        
        // Process events using sweep line
        while (startptr < start.length) {
            // If a meeting ends before or when the next starts, free a room
            if (start[startptr] >= end[endptr]) {
                result--;
                endptr++;
            }
            // A new meeting starts, use a room
            result++;
            startptr++;
        }
        
        return result;
    }
}