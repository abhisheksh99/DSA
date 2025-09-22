class Solution {
    // Problem: Open the Lock
    // Given a lock with 4 circular wheels, each with 10 slots (0 to 9), the lock starts at "0000".
    // Each move consists of turning one wheel one slot up or down (e.g., 0 to 1 or 0 to 9).
    // A list of deadend combinations is provided, which cannot be used. Find the minimum number
    // of moves required to reach the target combination. Return -1 if the target is unreachable.
    // The lock cannot stop on a deadend combination, and each combination is a string of 4 digits.

    // DSA Pattern: Breadth-First Search (BFS)
    // This problem is solved using BFS to find the shortest path (minimum number of moves) from
    // the starting combination "0000" to the target combination. Each combination is treated as a
    // node in a graph, with edges representing valid one-move transitions (turning one wheel up or
    // down). BFS ensures the first time we reach the target, we have used the minimum number of
    // moves. A queue tracks combinations to explore, and a visited set avoids revisiting combinations.
    // Deadends are checked to skip forbidden states.

    // Approach:
    // 1. Convert the deadends array to a HashSet for O(1) lookup.
    // 2. Check if the starting combination "0000" is a deadend. If so, return -1.
    // 3. Initialize a queue with the starting combination "0000" and 0 moves, and a visited set
    //    with "0000" to avoid cycles.
    // 4. While the queue is not empty:
    //    - Dequeue the current combination and its move count.
    //    - If the current combination equals the target, return the move count.
    //    - For each of the 4 wheels:
    //      - For each possible move (up by 1 or down by 1):
    //        - Calculate the new digit using modulo arithmetic to handle wrap-around (e.g., 0 to 9 or 9 to 0).
    //        - Create a new combination by replacing the current digit.
    //        - If the new combination is not visited and not a deadend, add it to the queue with move count + 1
    //          and mark it as visited.
    // 5. If the queue empties without finding the target, return -1 (target is unreachable).

    // Key Points to Remember:
    // - Each combination is a 4-digit string, treated as a node in a graph with 10,000 possible states (0000 to 9999).
    // - Each state has 8 neighbors (4 wheels × 2 directions: up or down).
    // - BFS guarantees the shortest path (minimum moves) because it explores states level by level.
    // - The deadend set prevents moving to forbidden combinations, and the visited set prevents revisiting states.
    // - Modulo arithmetic ((digit + delta + 10) % 10) handles wheel wrap-around (e.g., 0 - 1 = 9, 9 + 1 = 0).
    // - The starting combination "0000" must be checked against deadends before starting BFS.
    // - If the target is a deadend or unreachable (e.g., blocked by deadends), return -1.
    // - String manipulation is used to generate new combinations efficiently.

    // Time Complexity: O(10^4) = O(10000)
    // - There are 10^4 = 10,000 possible combinations (4 wheels, each with 10 digits).
    // - Each combination has 8 neighbors (4 wheels × 2 directions), so we generate O(8 × 10,000) = O(80,000) edges.
    // - BFS visits each combination at most once, and checking deadends/visited is O(1) with HashSet.
    // - String operations (substring, concatenation) are O(1) since the string length is fixed at 4.
    // - Initializing the deadend set is O(d), where d is the length of deadends, but this is dominated by the BFS.
    // - Total time is O(10,000) for visiting all states, assuming constant-time operations per state.

    // Space Complexity: O(10^4) = O(10000)
    // - The queue stores at most O(10,000) combinations in the worst case (all possible states).
    // - The visited set stores up to O(10,000) combinations.
    // - The deadend set stores O(d) combinations, where d is the length of deadends (d ≤ 10,000).
    // - Each combination is a 4-character string, which is O(1) space per combination.
    // - Total space is O(10,000) for the queue and sets, dominated by the number of possible states.

    public int openLock(String[] deadends, String target) {
        // Convert deadends array to HashSet for O(1) lookup
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        
        // Check if starting combination "0000" is a deadend
        if (deadendSet.contains("0000")) {
            return -1; // Cannot start from a deadend
        }
        
        // Initialize queue for BFS with starting combination and move count
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>("0000", 0));
        
        // Initialize visited set to avoid revisiting combinations
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        
        // Perform BFS
        while (!queue.isEmpty()) {
            // Dequeue current combination and move count
            Pair<String, Integer> current = queue.poll();
            String currentCombination = current.getKey();
            int moves = current.getValue();
            
            // If target is reached, return minimum moves
            if (currentCombination.equals(target)) {
                return moves;
            }
            
            // Try rotating each of the 4 wheels up or down
            for (int i = 0; i < 4; i++) {
                for (int delta : new int[]{-1, 1}) {
                    // Calculate new digit with wrap-around (e.g., 0 - 1 = 9, 9 + 1 = 0)
                    int newDigit = (currentCombination.charAt(i) - '0' + delta + 10) % 10;
                    // Create new combination by replacing the i-th digit
                    String newCombination = currentCombination.substring(0, i) +
                                           newDigit +
                                           currentCombination.substring(i + 1);
                    
                    // If new combination is not visited and not a deadend, explore it
                    if (!visited.contains(newCombination) && !deadendSet.contains(newCombination)) {
                        visited.add(newCombination); // Mark as visited
                        queue.offer(new Pair<>(newCombination, moves + 1)); // Add to queue with incremented moves
                    }
                }
            }
        }
        
        // Target is unreachable
        return -1;
    }
}