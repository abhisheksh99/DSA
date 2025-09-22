// Problem: Word Search II
// Given an m x n board of characters and a list of words, find all words on the board such that each word must be
// constructed from letters of sequentially adjacent cells (horizontally or vertically neighboring). The same letter cell
// may not be used more than once in a word.

// DSA Pattern: Trie + Depth-First Search (DFS) / Backtracking
// This problem is solved using a trie to store the words efficiently and DFS with backtracking to search for words on the
// board. The trie helps quickly identify valid prefixes and words, while DFS explores all possible paths on the board.

// Approach:
// 1. Build a trie from the given list of words:
//    - Each TrieNode has an array of 26 children (for lowercase letters a-z) and a string field to store a complete word.
//    - For each word, traverse character by character, creating nodes as needed, and store the word at the leaf node.
// 2. Perform DFS on each cell of the board:
//    - Start from each cell and explore all four directions (up, down, left, right).
//    - Use the trie to check if the current path forms a valid prefix or word.
//    - Mark visited cells with '#' to avoid reuse, and restore them after exploration.
// 3. During DFS:
//    - If the current cell is out of bounds, already visited, or not in the trie, return.
//    - Move to the corresponding trie node for the current character.
//    - If the current node marks a word, add it to the result and clear the word to avoid duplicates.
//    - Recursively explore neighboring cells.
// 4. Return the list of found words.

// Key Points to Remember:
// - The trie optimizes prefix checking, reducing unnecessary exploration of invalid paths.
// - The `word` field in TrieNode is set to null after adding to the result to prevent duplicate words.
// - Backtracking is achieved by marking cells as visited ('#') and restoring them after DFS.
// - Edge cases:
//   - Empty board or word list: Return an empty list.
//   - Single cell board: Only matches single-letter words.
//   - Words longer than board dimensions: Cannot be formed, handled by DFS bounds checking.
//   - Duplicate words: Prevented by clearing the `word` field in the trie.
// - The solution assumes words and board cells contain only lowercase English letters.

// Time Complexity:
// - Building the trie: O(N * M), where N is the number of words and M is the average word length.
//   - Each character of each word is processed once.
// - DFS search: O(R * C * 4 * L), where R is rows, C is columns, and L is the maximum word length.
//   - Each cell (R * C) can start a DFS.
//   - Each DFS explores up to 4 directions, with a maximum depth of L (backtracking ensures no cell is reused).
// - Total: O(N * M + R * C * 4 * L).
// - In practice, the trie prunes invalid paths, making the search more efficient.

// Space Complexity:
// - Trie: O(N * M), where N is the number of words and M is the average word length.
//   - Each word creates up to M nodes, with each node having a 26-element array.
//   - Space is optimized when words share prefixes.
// - DFS recursion stack: O(L), where L is the maximum word length (depth of recursion).
// - Result list: O(N), where N is the number of words found.
// - Total: O(N * M + L + N).

// Constraints:
// - m == board.length
// - n == board[i].length
// - 1 <= m, n <= 12
// - board[i][j] is a lowercase English letter.
// - 1 <= words.length <= 3 * 10^4
// - 1 <= words[i].length <= 10
// - words[i] consists of lowercase English letters.
// - All words are unique.

class TrieNode {
    // Array of child nodes for lowercase letters (a-z)
    TrieNode[] children = new TrieNode[26];
    // Store the complete word at the end node (null if not a word)
    String word = null;
}

public class Solution {
    // Store the board and its dimensions for easy access
    private char[][] board;
    private int rows, cols;
    // List to store found words
    private List<String> result = new ArrayList<>();

    // Main function to find all words on the board
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;

        // Build the trie from the list of words
        TrieNode root = buildTrie(words);
        // Perform DFS starting from each cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(r, c, root);
            }
        }
        return result;
    }

    // DFS to explore the board and find words using the trie
    private void dfs(int r, int c, TrieNode node) {
        // Check for invalid conditions: out of bounds, visited cell, or no trie node
        if (r < 0 || c < 0 || r >= rows || c >= cols || board[r][c] == '#' || 
            node.children[board[r][c] - 'a'] == null) {
            return;
        }

        // Get the current character and move to the corresponding trie node
        char ch = board[r][c];
        node = node.children[ch - 'a'];
        // If the node marks a word, add it to the result and clear to avoid duplicates
        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        // Mark the current cell as visited
        board[r][c] = '#';
        // Explore all four neighboring cells
        dfs(r + 1, c, node);
        dfs(r - 1, c, node);
        dfs(r, c + 1, node);
        dfs(r, c - 1, node);
        // Restore the cell after exploration (backtracking)
        board[r][c] = ch;
    }

    // Build a trie from the list of words
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        // Process each word
        for (String w : words) {
            TrieNode curr = root;
            // Process each character of the word
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                // Create a new node if it doesn't exist
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                // Move to the child node
                curr = curr.children[idx];
            }
            // Store the complete word at the leaf node
            curr.word = w;
        }
        return root;
    }
}