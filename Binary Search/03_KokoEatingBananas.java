class Solution {
    // Problem: Koko Eating Bananas
    // Given an array piles where piles[i] represents the number of bananas in the i-th pile,
    // and an integer h representing the number of hours available, find the minimum eating
    // speed (bananas per hour) that allows Koko to eat all bananas within h hours. Koko can
    // eat at most one pile per hour, and the number of bananas eaten from a pile must be an
    // integer. The array is non-empty, and all values are positive integers.

    // DSA Pattern: Binary Search
    // This problem is solved using binary search to find the minimum eating speed. The eating
    // speed must be between 1 and the size of the largest pile (maximum bananas in any pile).
    // For each potential speed, we calculate the total hours needed to eat all piles and
    // check if it’s within h hours. Binary search narrows down the speed by adjusting the
    // search range based on whether the current speed is sufficient.

    // Approach:
    // 1. Initialize the search range:
    //    - left = 1 (minimum possible speed).
    //    - right = maximum pile size (since Koko cannot eat faster than the largest pile in one hour).
    // 2. Perform binary search while left < right:
    //    - Calculate the middle speed mid = left + (right - left) / 2.
    //    - Use a helper function canFinish to check if Koko can eat all piles at speed mid
    //      within h hours.
    //    - If possible (hours <= h), the speed is sufficient, so try a lower speed by setting
    //      right = mid.
    //    - If not possible (hours > h), the speed is too low, so increase it by setting
    //      left = mid + 1.
    // 3. Return left, which is the minimum speed that allows all piles to be eaten in h hours.
    // 4. The canFinish helper calculates total hours by summing ceil(pile / speed) for each pile.

    // Key Points to Remember:
    // - The eating speed must be an integer, and Koko eats one pile per hour.
    // - Binary search is used to find the smallest speed that satisfies the time constraint.
    // - The search range is from 1 to the maximum pile size, as eating faster than the largest
    //   pile is unnecessary.
    // - Use ceil(pile / speed) to calculate hours per pile, as partial hours count as a full hour.
    // - The problem guarantees a non-empty array and positive integers, simplifying edge cases.
    // - The binary search ensures we find the minimum speed efficiently.
    // - When right = mid, the search continues to find the smallest possible speed.

    // Time Complexity: O(n * log M)
    // - n is the length of the piles array.
    // - M is the maximum pile size (maximum value in piles).
    // - Binary search over the range [1, M] takes O(log M) iterations.
    // - For each iteration, the canFinish function iterates over the array to compute hours,
    //   taking O(n) time.
    // - Total time is O(n * log M).
    // - Finding the maximum pile size initially takes O(n).

    // Space Complexity: O(1)
    // - The algorithm uses only a constant amount of extra space (variables: left, right,
    //   mid, and hours).
    // - No additional data structures are used, and the input array is not modified.
    // - The output (returned speed) is required by the problem.

    public static int minEatingSpeed(int[] piles, int h) {
        // Initialize search range: left is minimum speed (1), right is maximum pile size
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile); // Set right to the largest pile size
        }

        // Perform binary search to find the minimum eating speed
        while (left < right) {
            // Calculate middle speed, avoiding integer overflow
            int mid = left + (right - left) / 2;

            // Check if Koko can finish all piles at this speed within h hours
            if (canFinish(piles, mid, h)) {
                // If possible, try a lower speed to find the minimum
                right = mid;
            } else {
                // If not possible, increase the speed
                left = mid + 1;
            }
        }

        // Return the minimum speed that works
        return left;
    }

    // Helper function to check if Koko can eat all piles at given speed within h hours
    private static boolean canFinish(int[] piles, int speed, int h) {
        // Calculate total hours needed to eat all piles at the given speed
        int hours = 0;
        for (int pile : piles) {
            // Use ceil to account for partial hours (each pile takes at least one hour)
            hours += Math.ceil((double) pile / speed);
        }
        // Return true if total hours is within the allowed time
        return hours <= h;
    }
}