// Problem: Merge Triplets to Form Target Triplet
// Given an array of triplets 'triplets' where each triplet contains three integers, and a target triplet 'target',
// determine if it is possible to choose some triplets and merge them (by taking the maximum of each position)
// to form the target triplet. A triplet can be used only if all its values are less than or equal to the corresponding
// values in the target triplet.

// DSA Pattern: Greedy
// This problem is solved using a greedy approach by iterating through the triplets and tracking the maximum values for
// each position that can contribute to forming the target triplet.

// Approach:
// 1. Initialize an array 'maxValues' to store the maximum values for each position (x, y, z) of the triplet.
// 2. Iterate through each triplet in 'triplets':
//    - Check if the triplet can contribute to the target by ensuring all its values are less than or equal to the
//      corresponding values in the target triplet.
//    - If valid, update 'maxValues' by taking the maximum of the current maxValues and the triplet's values for each position.
// 3. Check if the resulting 'maxValues' array matches the target triplet exactly.
// 4. Return true if all positions match, false otherwise.

// Key Points to Remember:
// - A triplet can only contribute to the solution if all its values are less than or equal to the target's corresponding values.
// - The greedy approach works because we only need to find the maximum possible value for each position that doesn't exceed
//   the target, ensuring we can form the target triplet by merging valid triplets.
// - Edge cases:
//   - If no triplet is valid (all exceed target in at least one position): return false.
//   - If target cannot be formed (maxValues don't match target): return false.
//   - Single triplet: check if it matches the target exactly (if valid).
// - The solution assumes positive integers in the triplets and target arrays.

// Time Complexity: O(n), where n is the length of triplets
// - The algorithm iterates through each triplet exactly once, performing constant-time operations (comparisons and max updates)
//   for each triplet.

// Space Complexity: O(1)
// - Only a fixed-size array 'maxValues' of length 3 is used, requiring constant extra space.
// - No additional data structures are needed beyond the input arrays.

// Constraints:
// - 1 <= triplets.length <= 10^5
// - triplets[i].length == 3
// - target.length == 3
// - 1 <= triplets[i][j], target[j] <= 10^9

public class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // Initialize the max values for each position in the triplet
        int[] maxValues = new int[3];
        
        // Iterate through each triplet
        for (int[] triplet : triplets) {
            // Check if the triplet can contribute to the target
            if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {
                // Update the max values for each position
                maxValues[0] = Math.max(maxValues[0], triplet[0]);
                maxValues[1] = Math.max(maxValues[1], triplet[1]);
                maxValues[2] = Math.max(maxValues[2], triplet[2]);
            }
        }
        
        // Check if the max values match the target triplet
        return maxValues[0] == target[0] && maxValues[1] == target[1] && maxValues[2] == target[2];
    }
}