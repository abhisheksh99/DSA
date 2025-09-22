class Solution {

    public int[][] solveIntervals(int[][] intervals) {
        // Step 1: Sort the intervals by their start time.
        // This is the most crucial step for most interval problems.
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new LinkedList<>();
        
        // Handle the edge case of an empty input
        if (intervals.length == 0) {
            return merged.toArray(new int[0][]);
        }

        // Initialize with the first interval
        merged.add(intervals[0]);

        // Step 2: Iterate through the sorted intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int[] lastMerged = merged.get(merged.size() - 1);

            // Get the start and end of the current and last merged intervals
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            int lastEnd = lastMerged[1];

            // Step 3: Implement your core logic here
            // The logic depends on the specific problem.
            
            // For merging intervals:
            if (currentStart <= lastEnd) {
                // Overlap: Merge the current interval with the last one
                lastMerged[1] = Math.max(lastEnd, currentEnd);
            } else {
                // No overlap: Add the current interval as a new one
                merged.add(currentInterval);
            }
            
            // For other problems, this logic will change.
            // For example, finding non-overlapping intervals (greedy approach):
            // if (currentStart >= lastEnd) {
            //     // Add to a new result set and update the last end
            // }
        }

        // Convert the result list back to an array
        return merged.toArray(new int[merged.size()][]);
    }
}