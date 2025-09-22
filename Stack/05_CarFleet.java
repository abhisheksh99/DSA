class Solution {
    // Problem: Car Fleet
    // Given a target distance (target) and two arrays, position and speed, where position[i] is the
    // starting position of the i-th car and speed[i] is its speed (in units per hour), determine the
    // number of car fleets that will arrive at the target. A car fleet is a group of cars that arrive
    // at the target at the same time because they catch up to each other and travel together at the speed
    // of the slowest car in the group. Cars can catch up to form a fleet but cannot pass each other.

    // DSA Pattern: Stack and Sorting
    // This problem is solved by sorting cars by their starting positions in descending order and using
    // a stack to track the arrival times of car fleets. Each car's arrival time is calculated as
    // (target - position) / speed. Cars that catch up to each other form a single fleet, which is
    // represented by the arrival time of the leading (slowest) car in the group. A monotonic stack is
    // used to keep track of fleets by comparing arrival times, ensuring that only the latest arrival
    // times (indicating distinct fleets) are retained.

    // Approach:
    // 1. Create a 2D array to store each car's position and speed as a pair [position[i], speed[i]].
    // 2. Populate the array using the input position and speed arrays.
    // 3. Sort the cars array in descending order by position, so we process cars from right to left
    //    (closest to the target first).
    // 4. Initialize a stack to store the arrival times of car fleets.
    // 5. For each car:
    //    - Calculate its arrival time as (target - position) / speed (using double for precision).
    //    - If the stack is empty or the car's arrival time is greater than the top of the stack, push
    //      the arrival time, as it represents a new fleet (the car won't catch up to the fleet ahead).
    //    - If the arrival time is less than or equal to the top of the stack, the car will catch up to
    //      the existing fleet and arrive at the same time, so no new fleet is formed.
    // 6. Return the size of the stack, which represents the number of distinct car fleets.

    // Key Points to Remember:
    // - Sorting by position in descending order ensures we process cars from right to left, as cars
    //   closer to the target (higher position) lead potential fleets.
    // - A car catches up to the car ahead if its arrival time is less than or equal to the arrival time
    //   of the leading car, forming a single fleet with the same arrival time.
    // - The stack maintains a monotonically increasing sequence of arrival times, where each element
    //   represents the arrival time of a distinct fleet.
    // - Using double for arrival time calculations ensures precision, as division may result in non-integer
    //   values.
    // - Edge cases are handled:
    //   - If n = 0, return 0 (no cars, no fleets).
    //   - If n = 1, return 1 (single car forms one fleet).
    //   - If all cars have the same arrival time, they form one fleet.
    // - The stack only stores arrival times for cars that lead new fleets, ignoring cars that catch up.

    // Time Complexity: O(n * log n)
    // - n: Number of cars (length of position or speed arrays).
    // - Creating and populating the cars array: O(n).
    // - Sorting the cars array by position: O(n * log n).
    // - Processing each car:
    //   - Calculating arrival time: O(1).
    //   - Stack operations (push, peek): O(1).
    // - Total complexity: O(n * log n), dominated by the sorting step.
    // - Stack operations are O(n) total, as each car is pushed at most once.

    // Space Complexity: O(n)
    // - Cars array: O(n) to store n pairs of [position, speed].
    // - Stack: O(n) in the worst case (e.g., when each car forms a separate fleet, such as when
    //   positions are increasing and speeds ensure distinct arrival times).
    // - Result (number of fleets) and temporary variables: O(1).
    // - Overall space: O(n) due to the cars array and stack.

    public int carFleet(int target, int[] position, int[] speed) {
        // Step 1: Create and populate the cars array with position and speed pairs
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i]; // Store position
            cars[i][1] = speed[i];    // Store speed
        }

        // Step 2: Sort cars by position in descending order
        Arrays.sort(cars, (a, b) -> b[0] - a[0]); // O(n * log n)

        // Step 3: Initialize stack to store arrival times of fleets
        Stack<Double> st = new Stack<>();

        // Step 4: Process each car from right to left
        for (int[] car : cars) {
            // Calculate time to reach target: (target - position) / speed
            double timeTaken = (double) (target - car[0]) / car[1]; // Use double for precision

            // If stack is empty or current car arrives later than the top fleet, it's a new fleet
            if (st.isEmpty() || timeTaken > st.peek()) {
                st.push(timeTaken); // Push arrival time (O(1))
            }
            // If timeTaken <= st.peek(), car catches up to existing fleet, no new fleet formed
        }

        // Step 5: Return the number of fleets (size of stack)
        return st.size(); // O(1)
    }
}