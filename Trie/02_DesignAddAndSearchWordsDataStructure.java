// Problem: Design Add and Search Words Data Structure
// Design a data structure that supports adding words and searching for words, where a search can include a '.' character
// to represent any single letter. The data structure should efficiently store words and handle pattern-based searches.

// DSA Pattern: Trie / Prefix Tree with Backtracking
// This problem is solved using a trie data structure to store words, with a recursive search function to handle the '.' wildcard.
// The trie organizes characters in a tree, and the wildcard requires exploring all possible child nodes at that position.

// Approach for TrieNode:
// 1. Each TrieNode contains a HashMap mapping characters to child nodes (flexible for any character set).
// 2. A boolean flag `word` indicates whether the node marks the end of a complete word.
// 3. The node is initialized with an empty HashMap and `word` set to false.

// Approach for WordDictionary:
// 1. Initialize the trie with an empty root node.
// 2. `addWord(String)`:
//    - Traverse the word character by character, starting from the root.
//    - For each character, if no node exists for it, create a new node and add it to the parent's HashMap.
//    - Move to the child node and continue until the word is fully processed.
//    - Mark the final node as the end of a word (`word = true`).
// 3. `search(String)`:
//    - Call the helper function `searchInNode` starting from the root node.
// 4. `searchInNode(String, TrieNode)`:
//    - For each character in the word:
//      - If the character is not '.', check if it exists in the current node's children. If not, return false.
//      - If the character is '.', recursively search all child nodes for the remaining substring.
//      - If the character exists, move to the corresponding child node.
//    - After processing all characters, return true if the final node marks a word's end.
// 5. The search handles '.' by exploring all possible child nodes, effectively performing a depth-first search for valid paths.

// Key Points to Remember:
// - The trie uses a HashMap instead of a fixed-size array to support a flexible character set (not limited to lowercase a-z).
// - The '.' wildcard requires checking all child nodes at that position, which is handled recursively.
// - The `word` flag distinguishes whether a node completes a word.
// - Edge cases:
//   - Empty word: `search` returns false (no characters to process, and root is not a word).
//   - Single character: Handled by creating/checking a single child node.
//   - Word with only '.': Explores all possible child nodes at each position.
//   - Non-existent word: Returns false if any character (or pattern) doesn't match.
// - The solution assumes words consist of lowercase letters and '.' for searches.

// Time Complexity:
// - addWord: O(m), where m is the length of the word
//   - Each character is processed once to traverse or create nodes.
// - search: O(m * 26^k) in the worst case, where m is the length of the word and k is the number of '.' characters
//   - For each '.', the algorithm explores up to 26 possible child nodes (assuming lowercase letters).
//   - For non-'.' characters, it takes O(1) to check and move to the next node.
// - In practice, the search is faster when there are fewer '.' characters or fewer child nodes.

// Space Complexity:
// - O(N * M), where N is the number of words and M is the average word length
//   - Each word creates up to M nodes, with each node storing a HashMap of child links.
//   - Space is optimized when words share common prefixes, reusing nodes.
// - The recursion stack for search can go up to O(m) depth for a word of length m.

// Constraints:
// - 1 <= word.length <= 25
// - Words in addWord consist of lowercase English letters.
// - Words in search consist of lowercase English letters and '.'.
// - All input strings are non-empty.

class TrieNode {
    // HashMap to store child nodes for each character
    Map<Character, TrieNode> children = new HashMap<>();
    // Flag to mark the end of a complete word
    boolean word = false;

    // Initialize an empty TrieNode
    public TrieNode() {}
}

public class WordDictionary {
    // Root node of the trie
    TrieNode trie;

    // Initialize the trie with an empty root node
    public WordDictionary() {
        trie = new TrieNode();
    }
    
    // Add a word to the trie
    public void addWord(String word) {
        TrieNode node = trie;
        // Process each character of the word
        for (char ch : word.toCharArray()) {
            // If no node exists for the character, create one
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            // Move to the child node
            node = node.children.get(ch);
        }
        // Mark the final node as the end of a word
        node.word = true;
    }
    
    // Helper function to search for a word or pattern starting from a given node
    public boolean searchInNode(String word, TrieNode node) {
        // Process each character of the word
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // If the character is not found in the current node's children
            if (!node.children.containsKey(ch)) {
                // If the character is '.', try all possible child nodes
                if (ch == '.') {
                    for (char x : node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        // Recursively search the remaining substring from each child
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                // Character not found and not '.', return false
                return false;
            } else {
                // Move to the child node for the current character
                node = node.children.get(ch);
            }
        }
        // Return true if the final node marks the end of a word
        return node.word;
    }
    
    // Search for a word or pattern in the trie
    public boolean search(String word) {
        return searchInNode(word, trie);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */