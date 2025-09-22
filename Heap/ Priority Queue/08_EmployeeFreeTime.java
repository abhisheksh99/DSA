class Interval {
    int start; // Start time of the interval
    int end;   // End time of the interval

    // Constructor: Initialize an interval with start and end times
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    // Problem: Employee Free Time
    // Given a list of schedules for multiple employees, where each schedule is a list of intervals
    // representing working hours (with start and end times), find the common free time intervals
    // where all employees are available. Each interval is non-overlapping within a single employee's
    // schedule, and the output should be a list of intervals representing gaps where no employee is working.

    // DSA Pattern: Min-Heap (Priority Queue) and Sweep Line
    // This problem is solved using a min-heap to process all intervals in order of their start times,
    // effectively performing a sweep line approach across all employees' schedules. By sorting intervals
    // by start time and merging overlapping intervals, we can identify gaps between them, which represent
    // the common free time for all employees.

    // Approach:
    // 1. Initialize a min-heap (PriorityQueue) that sorts intervals by their start times.
    // 2. Add all intervals from all employees' schedules to the min-heap.
    // 3. Process intervals in order:
    //    - Poll the first interval as the initial `prev` interval.
    //    - For each subsequent interval (`curr`) polled from the heap:
    //      - If `prev.end < curr.start`, there is a gap between the intervals, so add a new free time
    //        interval [prev.end, curr.start] to the result.
    //      - If intervals overlap (`prev.end >= curr.start`), merge them by updating `prev.end` to
    //        the maximum of `prev.end` and `curr.end`.
    //      - Set `curr` as the new `prev` if a gap was found.
    // 4. Return the list of free time intervals.

    // Key Points to Remember:
    // - The min-heap ensures intervals are processed in ascending order of start times, allowing us to
    //   detect gaps efficiently.
    // - Overlapping intervals are merged by taking the maximum end time, ensuring we track the latest
    //   end of any active work period.
    // - A gap exists only when the previous interval's end is strictly less than the current interval's
    //   start, indicating a period where no employee is working.
    // - Edge cases are handled:
    //   - If there is only one interval, no gaps exist (empty result).
    //   - If schedules are empty, the result is empty.
    //   - If all intervals are contiguous with no gaps, the result is empty.
    // - The problem assumes valid intervals (start <= end) and non-overlapping intervals within each
    //   employee's schedule.

    // Time Complexity: O(n * log n)
    // - n: Total number of intervals across all employees' schedules.
    // - Adding all intervals to the min-heap: O(n * log n), as each insertion is O(log n).
    // - Processing intervals:
    //   - Polling n intervals from the heap: O(n * log n).
    //   - Each iteration involves constant-time comparisons and updates.
    // - Overall complexity: O(n * log n), dominated by heap operations.
    // - Note: The number of employees (m) does not directly affect complexity, as we process all intervals together.

    // Space Complexity: O(n)
    // - Min-heap stores at most n intervals at the start: O(n).
    // - Result list stores free time intervals, which is at most O(n) in the worst case (e.g., alternating
    //   work and free periods).
    // - Additional variables (prev, curr) use O(1) space.
    // - Overall space: O(n) for the heap and result list.

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // Initialize result list to store free time intervals
        List<Interval> result = new ArrayList<>();

        // Initialize min-heap to sort intervals by start time
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);

        // Step 1: Add all intervals from all schedules to the min-heap
        for (List<Interval> intervals : schedule) {
            pq.addAll(intervals); // O(n * log n) for n intervals
        }

        // Step 2: Process intervals to find gaps
        if (pq.isEmpty()) {
            return result; // Return empty list if no intervals
        }

        Interval prev = pq.poll(); // Get the first interval as the initial previous interval
        while (!pq.isEmpty()) {
            Interval curr = pq.poll(); // Get the next interval (O(log n))

            // Check for a gap between prev and curr
            if (prev.end < curr.start) {
                // Gap found: Add free time interval [prev.end, curr.start]
                result.add(new Interval(prev.end, curr.start));
                prev = curr; // Update prev to current interval
            } else {
                // Overlapping intervals: Merge by taking the maximum end time
                prev.end = Math.max(prev.end, curr.end);
            }
        }

        // Return the list of free time intervals
        return result;
    }
}