class Solution {
    // Problem: Predict Party Victory
    // Given a string representing a senate where each character is either 'R' (Radiant) or 'D' (Dire),
    // determine which party will win by eliminating the other. Each senator can ban one senator from
    // the opposing party in each round, and senators act in order of their index in the string. A
    // senator who is not banned can participate in future rounds. The process continues until one
    // party has no senators left. Return "Radiant" or "Dire" based on which party wins.

    // DSA Pattern: Queue-Based Simulation
    // This problem is solved using two queues to simulate the voting process. Each queue stores the
    // indices of senators from one party (Radiant or Dire). In each round, the senator with the lower
    // index acts first, banning an opposing senator. The winning senator is re-queued with an index
    // incremented by the senate size to participate in a future round. This continues until one party's
    // queue is empty, indicating the other party wins. The queue-based approach ensures senators act in
    // order and tracks their ability to vote in future rounds.

    // Approach:
    // 1. Initialize two queues: one for Radiant ('R') indices and one for Dire ('D') indices.
    // 2. Populate the queues by iterating through the senate string, adding each senator's index to
    //    the appropriate queue based on their party.
    // 3. Simulate rounds while both queues are non-empty:
    //    - Dequeue one index from each queue (representing one Radiant and one Dire senator).
    //    - Compare the indices: the senator with the smaller index acts first and bans the other.
    //    - The winning senator (with the smaller index) is re-queued with their index increased by
    //      the senate size (n) to indicate they can vote in a future round.
    // 4. Once one queue is empty, return the winning party: "Radiant" if the Radiant queue is non-empty,
    //    or "Dire" if the Dire queue is non-empty.

    // Key Points to Remember:
    // - The senate string represents senators in order of their voting priority (lower index acts first).
    // - Each round, one senator from each party is considered, and the one with the lower index bans the other.
    // - Re-queuing a senator with index + n simulates their participation in a future round, preserving
    //   their relative order in the next cycle.
    // - The process ends when one party has no senators left (their queue is empty).
    // - The simulation assumes each senator bans an opponent in the same round, ensuring a fair elimination process.
    // - The solution handles edge cases: if one party has no senators initially, the other wins immediately.
    // - The queue-based approach avoids modifying the input string and efficiently tracks active senators.

    // Time Complexity: O(n)
    // - Let n be the length of the senate string (number of senators).
    // - Initializing the queues takes O(n) to iterate through the string and enqueue indices.
    // - Each senator can be banned at most once, so there are at most n eliminations.
    // - Each round dequeues two senators (O(1)) and enqueues one (O(1)), so each round is O(1).
    // - Since there are at most n rounds (each senator is banned or survives), the simulation takes O(n).
    // - Total time is O(n) for initialization plus O(n) for the simulation, resulting in O(n).

    // Space Complexity: O(n)
    // - Two queues store indices of senators, with a total of n indices initially (O(n) space).
    // - Each round removes two indices and adds one, so the total number of indices in the queues
    //   decreases over time, never exceeding O(n).
    // - No additional data structures are used beyond the queues.
    // - Total space is O(n) for the queues.

    public String predictPartyVictory(String senate) {
        // Get the number of senators
        int n = senate.length();
        
        // Initialize queues for Radiant and Dire senator indices
        Queue<Integer> r = new ArrayDeque<>();
        Queue<Integer> d = new ArrayDeque<>();

        // Populate queues with senator indices based on party
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                r.offer(i); // Add Radiant senator index
            } else {
                d.offer(i); // Add Dire senator index
            }
        }

        // Simulate rounds until one party is eliminated
        while (!r.isEmpty() && !d.isEmpty()) {
            // Get the next senator from each party
            int ri = r.poll(); // Radiant senator index
            int di = d.poll(); // Dire senator index
            
            // Senator with lower index acts first and bans the other
            if (ri < di) {
                // Radiant senator bans Dire senator
                r.offer(ri + n); // Re-queue Radiant senator for next round
            } else {
                // Dire senator bans Radiant senator
                d.offer(di + n); // Re-queue Dire senator for next round
            }
        }

        // Return the winning party
        return r.isEmpty() ? "Dire" : "Radiant";
    }
}