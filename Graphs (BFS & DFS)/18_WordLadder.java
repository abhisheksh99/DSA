public class Solution {
    // Problem: Word Ladder
    // Given two words (beginWord and endWord) and a dictionary (wordList), find the length of the shortest transformation
    // sequence from beginWord to endWord, where each transformed word must exist in the wordList, and only one letter
    // can be changed at a time. Return 0 if no such transformation sequence exists. The length is the number of words
    // in the sequence, including beginWord and endWord.

    // DSA Pattern: Bidirectional Breadth-First Search (BFS)
    // This problem is solved using bidirectional BFS to find the shortest path between beginWord and endWord in a graph
    // where nodes are words, and edges exist between words differing by exactly one letter. Bidirectional BFS starts
    // from both beginWord and endWord, meeting in the middle, which is more efficient than single-ended BFS for large graphs.

    // Approach:
    // 1. Convert wordList to a HashSet for O(1) lookups and check if endWord is in it; if not, return 0.
    // 2. Initialize two sets: beginSet (starting with beginWord) and endSet (starting with endWord).
    // 3. Initialize a visited set to track explored words and avoid cycles.
    // 4. Perform bidirectional BFS:
    //    - Choose the smaller set (beginSet or endSet) to expand for efficiency.
    //    - For each word in the chosen set, generate all possible one-letter transformations.
    //    - If a transformed word is in the opposite set, a path is found; return the current length + 1.
    //    - If a transformed word is in wordSet and unvisited, add it to a temporary set and mark it visited.
    //    - Update the chosen set with the temporary set and increment the length.
    // 5. If either set becomes empty without finding a path, return 0 (no transformation sequence exists).

    // Key Points to Remember:
    // - Each word transformation changes exactly one letter, and the resulting word must be in wordList.
    // - Bidirectional BFS reduces time complexity by exploring from both ends, meeting in the middle.
    // - Always expand the smaller set to minimize the number of transformations checked.
    // - Use a HashSet for wordList to enable O(1) lookups.
    // - The visited set prevents revisiting words, avoiding infinite loops.
    // - Edge cases:
    //   - If endWord is not in wordList, return 0.
    //   - If beginWord equals endWord, return 1 if in wordList, else 0.
    //   - Empty wordList or no valid path: return 0.
    // - The length includes both beginWord and endWord (e.g., ["hit" -> "hot" -> "dot"] has length 3).

    // Time Complexity: O(N * 26 * L)
    // - N is the number of words in wordList, L is the length of each word.
    // - Converting wordList to a HashSet takes O(N).
    // - For each word in BFS, we try 26 letters for each of L positions, generating O(26 * L) transformations.
    // - Bidirectional BFS significantly reduces the number of nodes explored compared to single-ended BFS.
    // - In the worst case, we process O(N) words, each requiring O(26 * L) checks.
    // - HashSet operations (contains, add) are O(1) on average.
    // - Overall, the time complexity is approximately O(N * 26 * L) for sparse graphs, but bidirectional search makes it faster in practice.

    // Space Complexity: O(N + L)
    // - The wordSet stores N words: O(N).
    // - The beginSet, endSet, and visited sets store up to O(N) words in the worst case.
    // - The temporary set (temp) stores up to O(N) words.
    // - The char array for word transformations uses O(L) space.
    // - Overall, the space complexity is O(N + L).

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        // Initialize sets for bidirectional BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1; // Length of transformation sequence

        // Bidirectional BFS
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller set for efficiency
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            // Store next level of words to explore
            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                // Try changing each position to every letter
                for (int i = 0; i < chs.length; i++) {
                    char old = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        // If target is in endSet, a path is found
                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        // Add unvisited valid words to the next level
                        if (!visited.contains(target) && wordSet.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                    }
                    chs[i] = old; // Restore original character
                }
            }
            beginSet = temp; // Move to next level
            len++;
        }

        return 0; // No transformation sequence found
    }
}