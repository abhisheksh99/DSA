// Problem: Gas Station
// Given two integer arrays gas and cost of length n, where gas[i] is the amount of gas at station i and cost[i] is the cost
// to travel from station i to i+1, determine the starting station index from which a car can complete a circular tour.
// Return -1 if no such starting point exists. The car starts with an empty tank and can only travel clockwise.

// DSA Pattern: Greedy / Single Pass
// This problem is solved using a greedy approach by tracking the net gas balance and determining if a valid starting point exists.
// If the total gas is less than the total cost, no solution is possible. Otherwise, we find the starting point by resetting
// the balance whenever it becomes negative.

// Approach:
// 1. Check if the total gas available (sum of gas) is less than the total cost (sum of cost). If so, return -1 (no solution).
// 2. Initialize total to track the net gas balance and res to store the potential starting index (initially 0).
// 3. Iterate through each station (index i):
//    - Add the net gas at the current station (gas[i] - cost[i]) to total.
//    - If total becomes negative, the current starting point (res) is invalid:
//      - Reset total to 0 (start fresh from the next station).
//      - Update res to i + 1 (try the next station as the starting point).
// 4. Return res, which is the valid starting index if the total gas is sufficient.

// Key Points to Remember:
// - The circular tour requires completing a full cycle, returning to the starting station with non-negative gas.
// - If total gas < total cost, no starting point allows a complete circuit (return -1).
// - A negative total at any point indicates the current starting point fails, so try the next station.
// - The greedy approach works because if a starting point fails, no station up to the failure point can be a valid start.
// - If total gas >= total cost, there is exactly one valid starting point (proven by the problem's constraints).
// - Edge cases:
//   - Single station: Check if gas[0] >= cost[0].
//   - All gas = 0: Return -1 (no travel possible).
//   - All cost = 0: Return 0 (any starting point works if gas is sufficient).

// Time Complexity: O(n), where n is the length of the gas or cost array
// - Computing the sum of gas and cost takes O(n).
// - The single loop through the arrays takes O(n).
// - Each operation inside the loop is O(1).
// - Total time is O(n).

// Space Complexity: O(1)
// - Only a few variables (total, res) are used, requiring constant extra space.
// - The input arrays are not modified, and no additional data structures are needed.

// Constraints:
// - gas.length == cost.length
// - 1 <= gas.length <= 10^5
// - 0 <= gas[i], cost[i] <= 10^9

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Check if total gas is less than total cost
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1; // No solution possible
        }
        
        // Track net gas balance and starting index
        int total = 0;
        int res = 0;
        
        // Iterate through stations
        for (int i = 0; i < gas.length; i++) {
            // Add net gas (gas - cost) at current station
            total += (gas[i] - cost[i]);
            
            // If total becomes negative, current starting point fails
            if (total < 0) {
                total = 0; // Reset total
                res = i + 1; // Try next station as starting point
            }
        }
        
        // Return the valid starting index
        return res;
    }
}