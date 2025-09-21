// Patterns.java: Collection of algorithmic patterns with time and space complexities
import java.util.*;

// Class to encapsulate all algorithmic patterns
public class Patterns {
    
    // 1. Fast/Slow Pointers (Detect Cycle in LinkedList)
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(1), as we use only two pointers
    }
    
    // 2. Overlapping Intervals (Merge Intervals)
    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        result.add(current);
        
        for (int[] interval : intervals) {
            if (current[1] >= interval[0]) {
                current[1] = Math.max(current[1], interval[1]);
            } else {
                current = interval;
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
        // Time Complexity: O(n log n) due to sorting
        // Space Complexity: O(n) for the result list
    }
    
    // 3. Prefix Sum (Subarray Sum Equals K)
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            count += prefixSum.getOrDefault(sum - k, 0);
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }
        return count;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(n) for the hash map
    }
    
    // 4. Sliding Window (Longest Substring Without Repeating Characters)
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, end);
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
        // Time Complexity: O(n), where n is the length of the string
        // Space Complexity: O(min(m, n)), where m is the size of the charset
    }
    
    // 5. Two Pointers (Remove Duplicates from Sorted Array)
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int write = 1;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[read - 1]) {
                nums[write++] = nums[read];
            }
        }
        return write;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(1), as we modify the array in-place
    }
    
    // 6. Cyclic Sort (Find Missing Number)
    public int missingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i];
            if (nums[i] < nums.length && nums[i] != nums[correct]) {
                swap(nums, i, correct);
            } else {
                i++;
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(1), as we modify the array in-place
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // 7. LinkedList Reversal
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(1), as we use constant extra space
    }
    
    // 8. Matrix Manipulation (Spiral Matrix)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) result.add(matrix[top][i]);
            top++;
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) result.add(matrix[bottom][i]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
                left++;
            }
        }
        return result;
        // Time Complexity: O(m * n), where m and n are matrix dimensions
        // Space Complexity: O(1), excluding the output list
    }
    
    // 9. Binary Tree Traversals (Level Order, Inorder, Preorder, Postorder)
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
    
    // Level Order Traversal (BFS)
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(w), where w is the maximum width of the tree
    }
    
    // Inorder Traversal - Recursive (Left -> Root -> Right)
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (recursion stack)
    }
    
    private void inorderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderRecursive(node.left, result);
        result.add(node.val);
        inorderRecursive(node.right, result);
    }
    
    // Inorder Traversal - Iterative (Left -> Root -> Right)
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (stack size)
    }
    
    // Preorder Traversal - Recursive (Root -> Left -> Right)
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (recursion stack)
    }
    
    private void preorderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);
        preorderRecursive(node.left, result);
        preorderRecursive(node.right, result);
    }
    
    // Preorder Traversal - Iterative (Root -> Left -> Right)
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (stack size)
    }
    
    // Postorder Traversal - Recursive (Left -> Right -> Root)
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderRecursive(root, result);
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (recursion stack)
    }
    
    private void postorderRecursive(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorderRecursive(node.left, result);
        postorderRecursive(node.right, result);
        result.add(node.val);
    }
    
    // Postorder Traversal - Iterative (Left -> Right -> Root)
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree (stack size)
    }
    
    // 10. Backtracking (Permutations)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
        // Time Complexity: O(n!), where n is the length of nums
        // Space Complexity: O(n) for the recursion stack
    }
    
    private void backtrack(int[] nums, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == nums.length) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int num : nums) {
            if (!curr.contains(num)) {
                curr.add(num);
                backtrack(nums, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }
    
    // 11. Modified Binary Search (Search in Rotated Sorted Array)
    public int searchRotated(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
        // Time Complexity: O(log n), where n is the length of nums
        // Space Complexity: O(1)
    }
    
    // 12. TopK, K-way Merge, Two Heaps (Find Kth Largest Element)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return minHeap.peek();
        // Time Complexity: O(n log k), where n is the length of nums
        // Space Complexity: O(k) for the heap
    }
    
    // 13. Monotonic Stack (Next Greater Element)
    public int[] nextGreaterElement(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return result;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(n) for the stack
    }
    
    // 14. Binary Tree Patterns (Maximum Depth)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        // Time Complexity: O(n), where n is the number of nodes
        // Space Complexity: O(h), where h is the height of the tree
    }
    
    // 15. Greedy (Jump Game)
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length && i <= maxReach; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }
        return false;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(1)
    }
    
    // 16. Graphs - Topological Sort
    public int[] topologicalSort(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) if (indegree[i] == 0) queue.offer(i);
        int[] result = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index++] = node;
            for (int neighbor : adj.get(node)) {
                if (--indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }
        return index == n ? result : new int[0];
        // Time Complexity: O(V + E), where V is vertices and E is edges
        // Space Complexity: O(V + E) for adjacency list and queue
    }
    
    // 17. Graphs - Union Find (Number of Provinces)
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int provinces = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int rootI = find(parent, i);
                    int rootJ = find(parent, j);
                    if (rootI != rootJ) {
                        parent[rootI] = rootJ;
                        provinces--;
                    }
                }
            }
        }
        return provinces;
        // Time Complexity: O(n^2 * α(n)), where α is the inverse Ackermann function
        // Space Complexity: O(n) for the parent array
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }
    
    // 18. Graphs - Dijkstra's Algorithm (Shortest Path)
    public int[] dijkstra(int n, int[][] edges, int src) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0], d = curr[1];
            if (d > dist[node]) continue;
            for (int[] neighbor : adj.get(node)) {
                int next = neighbor[0], weight = neighbor[1];
                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }
        return dist;
        // Time Complexity: O((V + E) log V), where V is vertices and E is edges
        // Space Complexity: O(V + E) for adjacency list and priority queue
    }
    
    // 19. Graphs - Kruskal's Algorithm (Minimum Spanning Tree)
    public int kruskal(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int mstWeight = 0, edgesUsed = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int rootU = find(parent, u), rootV = find(parent, v);
            if (rootU != rootV) {
                parent[rootU] = rootV;
                mstWeight += w;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }
        return edgesUsed == n - 1 ? mstWeight : -1;
        // Time Complexity: O(E log E), where E is the number of edges
        // Space Complexity: O(V) for the parent array
    }
    
    // 20. Graphs - Breadth-First Search (Shortest Path in Unweighted Graph)
    public int[] bfsShortestPath(int n, int[][] edges, int src) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // Assuming undirected graph
        }
        
        // BFS
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) { // Not visited
                    dist[neighbor] = dist[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        return dist;
        // Time Complexity: O(V + E), where V is vertices and E is edges
        // Space Complexity: O(V + E) for adjacency list and queue
    }
    
    // 21. Graphs - Depth-First Search (Cycle Detection in Directed Graph)
    public boolean hasCycleDFS(int n, int[][] edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        
        // DFS with visited and recursion stack
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfsCycleDetect(i, adj, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
        // Time Complexity: O(V + E), where V is vertices and E is edges
        // Space Complexity: O(V + E) for adjacency list and recursion stack
    }
    
    private boolean dfsCycleDetect(int node, List<List<Integer>> adj, boolean[] visited, boolean[] recStack) {
        visited[node] = true;
        recStack[node] = true;
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfsCycleDetect(neighbor, adj, visited, recStack)) {
                    return true;
                }
            } else if (recStack[neighbor]) {
                return true; // Cycle detected
            }
        }
        
        recStack[node] = false;
        return false;
    }
    
    // 22. Dynamic Programming
    // Fibonacci - Memoization (Top-Down)
    public long fibMemoization(int n) {
        Map<Integer, Long> memo = new HashMap<>();
        return fibMemoHelper(n, memo);
        // Time Complexity: O(n), as each value is computed once
        // Space Complexity: O(n) for the memoization map and recursion stack
    }
    
    private long fibMemoHelper(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        long result = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    // Fibonacci - Tabulation (Bottom-Up)
    public long fibTabulation(int n) {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
        // Time Complexity: O(n), as we iterate from 2 to n
        // Space Complexity: O(n) for the DP array
    }
    
    // Longest Common Subsequence - Tabulation (Bottom-Up)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
        // Time Complexity: O(m * n), where m and n are lengths of the strings
        // Space Complexity: O(m * n) for the DP table
    }
    
    // 23. Trie (Prefix Tree)
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    
    public class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.isEndOfWord = true;
        }
        
        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.get(c);
                if (node == null) return false;
            }
            return node.isEndOfWord;
        }
        
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                node = node.children.get(c);
                if (node == null) return false;
            }
            return true;
        }
        // Time Complexity: O(m) for insert, search, and startsWith, where m is the length of the word/prefix
        // Space Complexity: O(ALPHABET_SIZE * N * M) worst case, where N is number of words, M is average word length
    }
    
    // 24. Bit Manipulation (Single Number)
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
        // Time Complexity: O(n), where n is the length of nums
        // Space Complexity: O(1), as we use a single variable
    }
    
    // 25. Recursion Template (Subsets)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        recursiveHelperSubsets(nums, 0, new ArrayList<>(), result);
        return result;
        // Time Complexity: O(2^n), where n is the length of nums (each element can be included or excluded)
        // Space Complexity: O(n) for the recursion stack
    }
    
    private void recursiveHelperSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // Base case: reached end of array
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Include current element
        current.add(nums[index]);
        recursiveHelperSubsets(nums, index + 1, current, result);
        current.remove(current.size() - 1);
        
        // Exclude current element
        recursiveHelperSubsets(nums, index + 1, current, result);
    }
    
    // 26. Greedy with Priority Queue (Meeting Rooms II)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // Min heap to track end times of meetings
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!endTimes.isEmpty() && endTimes.peek() <= interval[0]) {
                // Room is free, reuse it
                endTimes.poll();
            }
            // Add current meeting's end time
            endTimes.offer(interval[1]);
        }
        return endTimes.size();
        // Time Complexity: O(n log n), where n is the number of intervals (due to sorting and heap operations)
        // Space Complexity: O(n) for the priority queue
    }
    
    // 27. Divide and Conquer (Merge k Sorted Lists)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKListsHelper(lists, 0, lists.length - 1);
        // Time Complexity: O(n log k), where n is the total number of nodes, k is the number of lists
        // Space Complexity: O(log k) for the recursion stack
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        // Base case: single list
        if (start == end) return lists[start];
        // Base case: empty range
        if (start > end) return null;
        // Divide: split into two halves
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        // Conquer: merge two sorted lists
        return mergeTwoLists(left, right);
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
    
    // 28. Sliding Window with Variable Size (Minimum Window Substring)
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        // Frequency map for characters in t
        Map<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }
        
        // Sliding window variables
        int required = tFreq.size();
        int formed = 0;
        Map<Character, Integer> windowFreq = new HashMap<>();
        int left = 0, minLen = Integer.MAX_VALUE, minLeft = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
            
            // Check if current character forms a match
            if (tFreq.containsKey(c) && windowFreq.get(c).equals(tFreq.get(c))) {
                formed++;
            }
            
            // Shrink window from left
            while (left <= right && formed == required) {
                c = s.charAt(left);
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                
                windowFreq.put(c, windowFreq.get(c) - 1);
                if (tFreq.containsKey(c) && windowFreq.get(c) < tFreq.get(c)) {
                    formed--;
                }
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
        // Time Complexity: O(s + t), where s and t are lengths of the input strings
        // Space Complexity: O(s + t) for the frequency maps
    }
    
    // 29. Segment Tree (Range Sum Query - Mutable)
    public class NumArray {
        private int[] segmentTree;
        private int n;
        
        public NumArray(int[] nums) {
            if (nums.length == 0) return;
            n = nums.length;
            segmentTree = new int[4 * n]; // Max size needed for segment tree
            buildTree(nums, 0, 0, n - 1);
        }
        
        private void buildTree(int[] nums, int node, int start, int end) {
            if (start == end) {
                segmentTree[node] = nums[start];
                return;
            }
            int mid = start + (end - start) / 2;
            buildTree(nums, 2 * node + 1, start, mid);
            buildTree(nums, 2 * node + 2, mid + 1, end);
            segmentTree[node] = segmentTree[2 * node + 1] + segmentTree[2 * node + 2];
        }
        
        public void update(int index, int val) {
            updateTree(0, 0, n - 1, index, val);
        }
        
        private void updateTree(int node, int start, int end, int index, int val) {
            if (start == end) {
                segmentTree[node] = val;
                return;
            }
            int mid = start + (end - start) / 2;
            if (index <= mid) {
                updateTree(2 * node + 1, start, mid, index, val);
            } else {
                updateTree(2 * node + 2, mid + 1, end, index, val);
            }
            segmentTree[node] = segmentTree[2 * node + 1] + segmentTree[2 * node + 2];
        }
        
        public int sumRange(int left, int right) {
            return queryTree(0, 0, n - 1, left, right);
        }
        
        private int queryTree(int node, int start, int end, int left, int right) {
            if (right < start || left > end) return 0;
            if (left <= start && right >= end) return segmentTree[node];
            int mid = start + (end - start) / 2;
            int leftSum = queryTree(2 * node + 1, start, mid, left, right);
            int rightSum = queryTree(2 * node + 2, mid + 1, end, left, right);
            return leftSum + rightSum;
        }
        // Time Complexity: O(n) for building, O(log n) for update and query
        // Space Complexity: O(n) for the segment tree
    }
    
    // 30. Sorting Algorithms
    // Bubble Sort
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        // Time Complexity: O(n^2)
        // Space Complexity: O(1)
    }
    
    // Selection Sort
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            swap(arr, i, minIdx);
        }
        // Time Complexity: O(n^2)
        // Space Complexity: O(1)
    }
    
    // Insertion Sort
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        // Time Complexity: O(n^2)
        // Space Complexity: O(1)
    }
    
    // Merge Sort
    public void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
        // Time Complexity: O(n log n)
        // Space Complexity: O(n)
    }
    
    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
    
    // Quick Sort
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        // Time Complexity: O(n log n) average, O(n^2) worst case
        // Space Complexity: O(log n) for recursion stack
    }
    
    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    // Counting Sort
    public void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        int[] count = new int[max + 1];
        for (int num : arr) count[num]++;
        for (int i = 1; i <= max; i++) count[i] += count[i - 1];
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
        // Time Complexity: O(n + k), where k is the range of input values
        // Space Complexity: O(n + k)
    }
    
    // Heap Sort
    public void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
        // Time Complexity: O(n log n)
        // Space Complexity: O(1)
    }
    
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}
