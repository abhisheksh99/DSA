import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}

public class TreeTemplate {

    /** -----------------------
     * 1. RECURSIVE TRAVERSALS
     * -----------------------
     */

    // Preorder: Root -> Left -> Right
    // Time: O(N), Space: O(H)
    public void preorderRecursive(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorderRecursive(root.left);
        preorderRecursive(root.right);
    }

    // Inorder: Left -> Root -> Right
    // Time: O(N), Space: O(H)
    public void inorderRecursive(TreeNode root) {
        if (root == null) return;
        inorderRecursive(root.left);
        System.out.print(root.val + " ");
        inorderRecursive(root.right);
    }

    // Postorder: Left -> Right -> Root
    // Time: O(N), Space: O(H)
    public void postorderRecursive(TreeNode root) {
        if (root == null) return;
        postorderRecursive(root.left);
        postorderRecursive(root.right);
        System.out.print(root.val + " ");
    }

    /** -----------------------
     * 2. ITERATIVE TRAVERSALS
     * -----------------------
     */

    // Preorder Iterative
    // Time: O(N), Space: O(N)
    public void preorderIterative(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            System.out.print(curr.val + " ");
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
    }

    // Inorder Iterative
    // Time: O(N), Space: O(N)
    public void inorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    // Postorder Iterative (Using two stacks)
    // Time: O(N), Space: O(N)
    public void postorderIterative(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
    }

    /** -----------------------
     * 3. LEVEL ORDER (BFS)
     * -----------------------
     */

    // Iterative Level Order Traversal
    // Time: O(N), Space: O(N)
    public void levelOrderIterative(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.val + " ");
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
    }

    // Recursive Level Order (Helper for BFS by level)
    // Time: O(N), Space: O(H) for recursion + O(N) for result list
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderHelper(root, 0, result);
        return result;
    }

    private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;
        if (result.size() == level)
            result.add(new ArrayList<>());
        result.get(level).add(node.val);
        levelOrderHelper(node.left, level + 1, result);
        levelOrderHelper(node.right, level + 1, result);
    }

    /** -----------------------
     * 4. DEPTH-FIRST SEARCH (DFS)
     * -----------------------
     */

    // DFS Recursive (identical to preorderRecursive)
    // Time: O(N), Space: O(H)
    public void dfsRecursive(TreeNode root) {
        preorderRecursive(root); // alias
    }

    // DFS Iterative (Same as Preorder Iterative)
    // Time: O(N), Space: O(N)
    public void dfsIterative(TreeNode root) {
        preorderIterative(root); // alias
    }

    /** -----------------------
     * 5. BACKTRACKING TEMPLATE
     * -----------------------
     */

    // Find all root-to-leaf paths that sum to target
    // Time: O(N), Space: O(H) + paths
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(root, target, path, result);
        return result;
    }

    private void backtrack(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);
        target -= node.val;

        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(path));
        } else {
            backtrack(node.left, target, path, result);
            backtrack(node.right, target, path, result);
        }

        path.remove(path.size() - 1); // backtrack
    }

    /** -----------------------
     * 6. GENERAL RECURSION TEMPLATE
     * -----------------------
     */

    // Example: Sum of all nodes
    // Time: O(N), Space: O(H)
    public int sum(TreeNode root) {
        if (root == null) return 0;
        int left = sum(root.left);
        int right = sum(root.right);
        return root.val + left + right;
    }

    /** -----------------------
     * 7. TEST DRIVER
     * -----------------------
     */

    public static void main(String[] args) {
        TreeTemplate tree = new TreeTemplate();

        // Build sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Preorder Recursive:");
        tree.preorderRecursive(root);

        System.out.println("\nInorder Recursive:");
        tree.inorderRecursive(root);

        System.out.println("\nPostorder Recursive:");
        tree.postorderRecursive(root);

        System.out.println("\nPreorder Iterative:");
        tree.preorderIterative(root);

        System.out.println("\nInorder Iterative:");
        tree.inorderIterative(root);

        System.out.println("\nPostorder Iterative:");
        tree.postorderIterative(root);

        System.out.println("\nLevel Order Iterative:");
        tree.levelOrderIterative(root);

        System.out.println("\nLevel Order Recursive:");
        List<List<Integer>> levels = tree.levelOrderRecursive(root);
        for (List<Integer> level : levels)
            System.out.println(level);

        System.out.println("\nDFS Recursive:");
        tree.dfsRecursive(root);

        System.out.println("\nDFS Iterative:");
        tree.dfsIterative(root);

        System.out.println("\nBacktracking (Path Sum = 7):");
        List<List<Integer>> paths = tree.pathSum(root, 7);
        for (List<Integer> path : paths)
            System.out.println(path);

        System.out.println("\nRecursive Sum of Nodes:");
        System.out.println(tree.sum(root));
    }
}
