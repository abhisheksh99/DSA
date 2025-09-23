public class Solution extends Relation {
    // Problem: Find the Celebrity
    // In a party of n people, a celebrity is defined as someone who is known by everyone else,
    // but knows no one themselves. Given a function knows(a, b) that returns true if person a
    // knows person b, find the celebrity's index (0 to n-1) or return -1 if no celebrity exists.

    // DSA Pattern: Logical Deduction with Graph
    // This problem is solved using a two-step approach. First, we find a potential celebrity candidate
    // by iteratively eliminating people who cannot be the celebrity. Then, we verify if the candidate
    // satisfies the celebrity condition (everyone knows them, and they know no one).

    // Approach:
    // 1. Initialize a candidate as person 0.
    // 2. Iterate through all people (0 to n-1):
    //    - If the current candidate knows person i, update the candidate to i (since a celebrity cannot know anyone).
    // 3. After the loop, verify if the candidate is a celebrity:
    //    - Check that the candidate knows no one (except themselves).
    //    - Check that everyone else knows the candidate.
    // 4. Return the candidate's index if they are a celebrity; otherwise, return -1.

    // Key Points to Remember:
    // - The knows(a, b) function acts like an adjacency matrix for a directed graph, where knows(a, b) = true
    //   means there is an edge from a to b.
    // - A celebrity has incoming edges from all other nodes (everyone knows them) and no outgoing edges
    //   (they know no one).
    // - The first loop eliminates non-celebrities: if knows(candidate, i) is true, candidate cannot be the celebrity
    //   (since they know someone), so i becomes the new candidate.
    // - After the loop, the candidate is the only person who could be the celebrity, as all others have been ruled out.
    // - The isCelebrity check ensures the candidate meets both conditions: no outgoing edges (knows no one)
    //   and all incoming edges (known by everyone).
    // - Edge cases:
    //   - n = 1: The single person is a celebrity if they don’t know themselves (often assumed false).
    //   - No celebrity exists: Return -1 if the candidate fails the isCelebrity check.
    // - The algorithm makes at most 3n API calls to knows(): n-1 in the first loop, and up to 2n in the verification.

    // Time Complexity: O(n)
    // - The first loop iterates n times, making one knows() call per iteration: O(n).
    // - The isCelebrity function iterates n times, making up to two knows() calls per iteration (knows(i, j) and knows(j, i)): O(n).
    // - Overall, the total number of API calls is at most 3n, so the time complexity is O(n).

    // Space Complexity: O(1)
    // - Only a constant amount of extra space is used (numOfPeople, celebCandiate, and loop variables).
    // - No additional data structures are needed.
    // - Overall, the space complexity is O(1).

    private int numOfPeople;

    public int findCelebrity(int n) {
        numOfPeople = n;
        int celebCandidate = 0;

        // Step 1: Find potential celebrity candidate
        for (int i = 0; i < n; i++) {
            if (knows(celebCandidate, i)) {
                celebCandidate = i; // If candidate knows i, i becomes the new candidate
            }
        }

        // Step 2: Verify if the candidate is a celebrity
        if (isCelebrity(celebCandidate)) {
            return celebCandidate;
        }

        return -1; // No celebrity exists
    }

    private boolean isCelebrity(int i) {
        // Check if i is a celebrity: i knows no one, and everyone knows i
        for (int j = 0; j < numOfPeople; j++) {
            if (i == j) {
                continue; // Skip self
            }
            if (knows(i, j) || !knows(j, i)) {
                return false; // i knows someone or someone doesn't know i
            }
        }

        return true; // i satisfies celebrity conditions
    }
}