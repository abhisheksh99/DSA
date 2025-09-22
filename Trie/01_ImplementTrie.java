// Problem: Implement Trie (Prefix Tree)
// Implement a trie (prefix tree) data structure that supports inserting words, searching for words, and checking if any
// word in the trie starts with a given prefix. A trie is a tree-like structure where each node represents a character in a word.

// DSA Pattern: Trie / Prefix Tree
// This problem is solved using a trie data structure, which efficiently stores and retrieves strings by organizing characters
// in a tree where each path from the root to a node represents a prefix or word.

// Approach for TrieNode:
// 1. Each TrieNode contains an array of links to child nodes (one for each lowercase letter, a-z).
// 2. A boolean flag `isEnd` indicates whether the node marks the end of a word.
// 3. Methods:
//    - `containsKey(char)`: Check if a child node exists for a given character.
//    - `get(char)`: Retrieve the child node for a given character.
//    - `put(char, TrieNode)`: Add a child node for a given character.
//    - `setEnd()`: Mark the node as the end of a word.
//    - `isEnd()`: Check if the node marks the end of a word.

// Approach for Trie:
// 1. Initialize the trie with an empty root node.
// 2. `insert(String)`:
//    - Traverse the word character by character, starting from the root.
//    - For each character, if no node exists for it, create a new node and link it.
//    - Move to the child node and continue until the word is fully processed.
//    - Mark the final node as the end of a word.
// 3. `searchPrefix(String)`:
//    - Traverse the word character by character, following the corresponding child nodes.
//    - If a character is not found, return null (prefix not present).
//    - Return the node corresponding to the last character of the prefix.
// 4. `search(String)`:
//    - Use `searchPrefix` to find the node for the word.
//    - Return true if the node exists and is marked as the end of a word.
// 5. `startsWith(String)`:
//    - Use `searchPrefix` to find the node for the prefix.
//    - Return true if the node exists (prefix is present).

// Key Points to Remember:
// - The trie assumes lowercase English letters (a-z), with a fixed array size of 26 for simplicity.
// - Each node represents a single character, and paths from the root represent prefixes or words.
// - The `isEnd` flag distinguishes whether a node completes a word.
// - Edge cases:
//   - Empty string: `search` and `startsWith` return false for empty input (no node reached).
//   - Single character: Handled by creating a single child node.
//   - Non-existent word/prefix: `searchPrefix` returns null, leading to false for `search` or `startsWith`.
// - The trie is space-efficient for words sharing common prefixes, as they reuse the same nodes.

// Time Complexity:
// - Insert: O(m), where m is the length of the word
//   - Each character is processed once to traverse or create nodes.
// - Search: O(m), where m is the length of the word
//   - Each character is checked to traverse to the corresponding node.
// - StartsWith: O(m), where m is the length of the prefix
//   - Similar to search, but only checks for prefix existence.

// Space Complexity:
// - O(ALPHABET_SIZE * N * M) in the worst case, where N is the number of words and M is the average word length
//   - Each node has up to 26 links (for a-z), and each word creates up to M nodes.
//   - Space is optimized when words share prefixes, reducing the number of nodes.
// - The `TrieNode` array uses O(26) = O(1) space per node.

// Constraints:
// - Words and prefixes consist of lowercase English letters (a-z).
// - 1 <= word.length, prefix.length <= 2000
// - All input strings are non-empty and contain only valid lowercase letters.

class TrieNode {
    // Array of links to child nodes (one for each lowercase letter)
    private TrieNode[] links;

    // Size of the alphabet (lowercase a-z)
    private final int R = 26;

    // Flag to mark the end of a word
    private boolean isEnd;

    // Initialize a TrieNode with an empty array of links
    public TrieNode() {
        links = new TrieNode[R];
    }

    // Check if a child node exists for the given character
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    // Get the child node for the given character
    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    // Add a child node for the given character
    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    // Mark this node as the end of a word
    public void setEnd() {
        isEnd = true;
    }

    // Check if this node marks the end of a word
    public boolean isEnd() {
        return isEnd;
    }
}

public class Trie {
    // Root node of the trie
    private TrieNode root;

    // Initialize the trie with an empty root node
    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        // Process each character of the word
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            // If no node exists for the character, create one
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            // Move to the child node
            node = node.get(currentChar);
        }
        // Mark the final node as the end of the word
        node.setEnd();
    }

    // Helper function to find the node corresponding to a prefix
    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        // Process each character of the word/prefix
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            // If the character exists, move to the child node
            if (node.containsKey(currentChar)) {
                node = node.get(currentChar);
            } else {
                // Prefix not found, return null
                return null;
            }
        }
        return node;
    }

    // Search for a word in the trie
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        // Return true if the node exists and marks the end of a word
        return node != null && node.isEnd();
    }

    // Check if any word in the trie starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        // Return true if the prefix exists (node is not null)
        return node != null;
    }
}