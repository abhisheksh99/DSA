import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
        children = new TrieNode[26]; // a-z
        isEnd = false;
    }
}

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** -----------------------
     * 1. INSERT WORD
     * Time: O(L), Space: O(1) per call
     * ----------------------- */
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    /** -----------------------
     * 2. SEARCH EXACT WORD
     * Time: O(L), Space: O(1)
     * ----------------------- */
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEnd;
    }

    /** -----------------------
     * 3. STARTSWITH PREFIX
     * Time: O(L), Space: O(1)
     * ----------------------- */
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    /** -----------------------
     * 4. DELETE WORD (Optional Advanced)
     * Time: O(L), Space: O(L) recursion stack
     * ----------------------- */
    public void delete(String word) {
        deleteHelper(root, word, 0);
    }

    private boolean deleteHelper(TrieNode node, String word, int depth) {
        if (node == null) return false;

        if (depth == word.length()) {
            if (!node.isEnd) return false;
            node.isEnd = false;

            // If no children, we can delete this node
            return isEmpty(node);
        }

        int idx = word.charAt(depth) - 'a';
        if (deleteHelper(node.children[idx], word, depth + 1)) {
            node.children[idx] = null;
            return !node.isEnd && isEmpty(node);
        }

        return false;
    }

    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children)
            if (child != null) return false;
        return true;
    }

    /** -----------------------
     * 5. BACKTRACKING EXAMPLE: Word Search on Grid
     * Time: O(M * N * 4^L), Space: O(L) for path
     * ----------------------- */
    public List<String> findWords(char[][] board, String[] words) {
        // Build Trie from word list
        for (String word : words) insert(word);

        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        // Try DFS from every cell
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, i, j, root, "", visited, result);

        return new ArrayList<>(result);
    }

    private void dfs(char[][] board, int row, int col, TrieNode node, String path,
                     boolean[][] visited, Set<String> result) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col])
            return;

        char ch = board[row][col];
        TrieNode next = node.children[ch - 'a'];
        if (next == null) return;

        path += ch;
        if (next.isEnd) result.add(path);

        visited[row][col] = true;
        dfs(board, row + 1, col, next, path, visited, result);
        dfs(board, row - 1, col, next, path, visited, result);
        dfs(board, row, col + 1, next, path, visited, result);
        dfs(board, row, col - 1, next, path, visited, result);
        visited[row][col] = false;
    }

    /** -----------------------
     * 6. TRAVERSE HELPER
     * ----------------------- */
    private TrieNode traverse(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null)
                return null;
            node = node.children[idx];
        }
        return node;
    }

    /** -----------------------
     * 7. DRIVER
     * ----------------------- */
    public static void main(String[] args) {
        Trie trie = new Trie();

        System.out.println("INSERTING WORDS:");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apex");

        System.out.println("\nSEARCH TESTS:");
        System.out.println("search('apple') = " + trie.search("apple")); // true
        System.out.println("search('app') = " + trie.search("app"));     // true
        System.out.println("search('ap') = " + trie.search("ap"));       // false

        System.out.println("\nPREFIX TESTS:");
        System.out.println("startsWith('ap') = " + trie.startsWith("ap"));   // true
        System.out.println("startsWith('bat') = " + trie.startsWith("bat")); // false

        System.out.println("\nDELETE TEST:");
        trie.delete("app");
        System.out.println("search('app') = " + trie.search("app"));     // false
        System.out.println("startsWith('app') = " + trie.startsWith("app")); // true

        System.out.println("\nBACKTRACKING (WORD SEARCH) TEST:");
        char[][] board = {
            {'a','p','p','l'},
            {'b','a','t','e'},
            {'x','e','x','z'}
        };
        String[] words = {"apple", "app", "bat", "apex", "axe"};
        List<String> foundWords = trie.findWords(board, words);
        System.out.println("Words Found: " + foundWords);
    }
}
