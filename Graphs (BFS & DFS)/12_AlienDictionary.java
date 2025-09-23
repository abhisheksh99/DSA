class Solution {
    // Problem: Alien Dictionary
    // Given a list of words written in an alien language, where the words are sorted lexicographically
    // by the rules of this language, derive the order of the alphabet. Each word is a sequence of
    // lowercase letters, and the alphabet order must be valid such that for any two consecutive words,
    // the first differing characters determine the relative order of those characters in the alphabet.

    // DSA Pattern: Topological Sort with Depth-First Search (DFS)
    // This problem is solved using a graph-based approach where each character in the alien alphabet
    // is a node, and directed edges represent the order between characters. A topological sort is
    // performed to determine a valid order of the alphabet, if one exists. A cycle in the graph
    // indicates an invalid order, resulting in an empty string.

    // Approach:
    // 1. Initialize a graph (reversed adjacency list) to store character dependencies and a map to
    //    track visited nodes during DFS.
    // 2. Build the graph by:
    //    - Adding all characters from the input words to the graph.
    //    - Comparing consecutive pairs of words to identify differing characters and establish edges.
    //    - If a longer word starts with a shorter word (invalid prefix case), return an empty string.
    // 3. Perform DFS on each character to detect cycles and build the topological order:
    //    - Mark nodes as being visited (false) to detect cycles.
    //    - Recursively visit all dependent characters.
    //    - If a cycle is detected (a node is revisited while being visited), return an empty string.
    //    - Once all dependencies are resolved, mark the node as fully visited (true) and append it to
    //      the result.
    // 4. After DFS, verify that the result includes all characters (no disconnected components).
    // 5. Return the resulting string as the alien alphabet order.

    // Key Points to Remember:
    // - Use a reversed adjacency list to simplify topological sort (edges point from later to earlier
    //   characters in the order).
    // - Handle invalid cases, such as when a longer word is a prefix of a shorter word (e.g., "abc" before "ab").
    // - Use DFS to detect cycles, which indicate an invalid order (e.g., contradictory constraints).
    // - Track visited states: null (unvisited), false (being visited), true (fully visited).
    // - Ensure all characters are included in the final order, as disconnected components may indicate
    //   an incomplete solution.
    // - The result is built in reverse during DFS, as characters with no dependencies are added last.

    // Time Complexity: O(C + N * K)
    // - C is the total number of unique characters across all words (at most 26 for lowercase letters).
    // - N is the number of words, and K is the average length of words.
    // - Building the graph takes O(N * K) for iterating through word pairs and their characters.
    // - DFS visits each character and its edges once, taking O(C + E) where E is the number of edges
    //   (at most O(C^2) in a dense graph, but typically much less).
    // - Overall, the complexity is O(C + N * K) for practical cases.

    // Space Complexity: O(C)
    // - The reversedList map stores at most C characters, each with a list of dependent characters.
    // - The seen map stores at most C characters with their visited states.
    // - The result StringBuilder stores up to C characters.
    // - The recursion stack for DFS uses O(C) space in the worst case.
    // - Overall, the space complexity is O(C).

    public Map<Character, List<Character>> reversedList = new HashMap<>();
    public Map<Character, Boolean> seen = new HashMap<>();
    public StringBuilder result = new StringBuilder();
    
    public String alienOrder(String[] words) {
        // Initialize graph with all characters from input words
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reversedList.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Build graph by comparing consecutive word pairs
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check for invalid prefix case (e.g., "abc" before "ab")
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            // Compare characters to establish order
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reversedList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        
        // Perform DFS on each character to build topological order
        for (Character c : reversedList.keySet()) {
            boolean res = dfs(c);
            if (!res) return "";
        }
        
        // Ensure all characters are included in the result
        if (result.length() < reversedList.size()) {
            return "";
        }
        
        return result.toString();
    }
    
    // DFS to detect cycles and build topological order
    public boolean dfs(Character c) {
        // If node is being visited (false), a cycle is detected
        if (seen.containsKey(c)) {
            return seen.get(c);
        }
        
        // Mark node as being visited
        seen.put(c, false);
        
        // Recursively visit all dependent characters
        for (Character next : reversedList.get(c)) {
            boolean res = dfs(next);
            if (!res) return false;
        }
        
        // Mark node as fully visited and add to result
        seen.put(c, true);
        result.append(c);
        return true;
    }
}