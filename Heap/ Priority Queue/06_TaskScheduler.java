class Solution {
    // Problem: Task Scheduler
    // Given a char array tasks representing tasks to be executed by a CPU and an integer n representing
    // the cooling period between same tasks, return the least number of time units required to complete
    // all tasks. Each task takes one time unit, and the CPU can either execute a task or be idle in each
    // unit. If a task is executed, the same task cannot be executed again until n time units have passed.

    // DSA Pattern: Max-Heap and Greedy
    // This problem is solved using a max-heap to prioritize tasks with the highest remaining frequency and
    // a greedy approach to schedule tasks in cycles of n+1 time units. The max-heap allows efficient
    // selection of the most frequent tasks, and the cycle-based approach ensures the cooling period is
    // respected while minimizing idle time. Tasks are processed in batches, and their frequencies are
    // updated and reinserted into the heap if they still need to be executed.

    // Approach:
    // 1. Count the frequency of each task using a HashMap.
    // 2. Build a max-heap containing the frequencies of all tasks.
    // 3. Process tasks in cycles:
    //    - For each cycle of n+1 time units, extract up to n+1 tasks from the max-heap (most frequent first).
    //    - Decrease the frequency of each extracted task by 1 and collect them in a temporary list.
    //    - Reinsert tasks with remaining frequency (>0) back into the heap.
    //    - Update the total time: If the heap is empty, add the number of tasks processed in the cycle
    //      (no idle time needed); otherwise, add n+1 (full cycle, including potential idle time).
    // 4. Continue until the heap is empty (all tasks are completed).
    // 5. Return the total time units required.

    // Key Points to Remember:
    // - The max-heap ensures tasks with the highest frequency are scheduled first, minimizing idle time.
    // - Each cycle processes up to n+1 tasks to respect the cooling period, as the same task cannot
    //   appear more than once in a cycle of n+1 time units.
    // - If fewer than n+1 tasks are available in a cycle, idle time may be needed, but we only count
    //   actual tasks processed when the heap is empty at the end.
    // - The HashMap efficiently counts task frequencies, and the max-heap handles scheduling.
    // - Edge cases are handled:
    //   - If n = 0, tasks can be executed consecutively, and the result is the number of tasks.
    //   - If tasks have the same frequency, the heap ensures they are processed in a valid order.
    // - The formula for minimum time is max(tasks.length, (maxFreq - 1) * (n + 1) + numMaxFreqTasks),
    //   but this implementation directly simulates the process for clarity.

    // Time Complexity: O(t * log m)
    // - Building the frequency map: O(t), where t is the number of tasks.
    // - Adding frequencies to the max-heap: O(m * log m), where m is the number of unique tasks (m <= 26 for letters A-Z).
    // - Main loop:
    //   - Each cycle extracts up to n+1 tasks (O(log m) per poll) and reinserts up to n+1 tasks (O(log m) per add).
    //   - In the worst case, we process all tasks, and each task is polled and reinserted O(t/m) times.
    //   - Total heap operations: O(t * log m), as each task is processed multiple times, and heap operations are O(log m).
    // - Overall complexity: O(t * log m), dominated by heap operations, where m is typically small (e.g., 26 for letters).

    // Space Complexity: O(m)
    // - HashMap stores frequencies for m unique tasks: O(m).
    // - Max-heap stores at most m frequencies: O(m).
    // - Temporary list in each cycle stores up to n+1 frequencies: O(n), but typically small.
    // - Overall space: O(m), where m <= 26 for letters A-Z, making it effectively constant in many cases.

    public int leastInterval(char[] tasks, int n) {
        // Step 1: Count the frequency of each task
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char task : tasks) {
            freqMap.put(task, freqMap.getOrDefault(task, 0) + 1); // O(1) per task
        }

        // Step 2: Build a max-heap based on task frequencies
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(freqMap.values()); // O(m * log m), where m is number of unique tasks

        // Step 3: Process tasks in cycles
        int time = 0; // Total time units required
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>(); // Store frequencies of tasks in current cycle
            // Process up to n+1 tasks in one cycle
            for (int i = 0; i < n + 1; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.poll()); // Extract task frequency (O(log m))
                }
            }

            // Decrease frequency and reinsert tasks with remaining frequency
            for (int freq : temp) {
                if (--freq > 0) {
                    maxHeap.add(freq); // Reinsert if task still needs execution (O(log m))
                }
            }

            // Step 4: Update time
            // If heap is empty, add number of tasks processed; otherwise, add full cycle (n+1)
            time += maxHeap.isEmpty() ? temp.size() : n + 1;
        }

        // Return total time units required
        return time;
    }
}