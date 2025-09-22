import java.util.*;

public class RecursionTemplate {

    /** ------------------------------------------------------------------
     * 1. BASIC RECURSION TEMPLATE
     * Time: Depends on the number of calls; often exponential if no memo
     * ------------------------------------------------------------------ */
    public void basicRecursion(int n) {
        if (n == 0) return;  // Base case

        // Recursive call
        basicRecursion(n - 1);

        // After call
        System.out.println(n);
    }

    /** ------------------------------------------------------------------
     * 2. TAIL RECURSION TEMPLATE (rarely optimized in Java)
     * Time: O(n), Space: O(n) call stack
     * ------------------------------------------------------------------ */
    public void tailRecursion(int n) {
        if (n == 0) return;
        System.out.println(n); // before recursion
        tailRecursion(n - 1);  // recursion at end
    }

    /** ------------------------------------------------------------------
     * 3. MULTIPLE RECURSIVE CALLS (e.g., Fibonacci)
     * Time: O(2^n), Space: O(n)
     * ------------------------------------------------------------------ */
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    /** ------------------------------------------------------------------
     * 4. BACKTRACKING RECURSION (e.g., subsets)
     * Time: O(2^n), Space: O(n)
     * ------------------------------------------------------------------ */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrackSubsets(int index, int[] nums, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path)); // include current path

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]); // choose
            backtrackSubsets(i + 1, nums, path, result); // explore
            path.remove(path.size() - 1); // un-choose (backtrack)
        }
    }

    /** ------------------------------------------------------------------
     * 5. DIVIDE & CONQUER RECURSION (e.g., merge sort)
     * Time: O(n log n), Space: O(n)
     * ------------------------------------------------------------------ */
    public int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            res[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        while (i < left.length) res[k++] = left[i++];
        while (j < right.length) res[k++] = right[j++];
        return res;
    }

    /** ------------------------------------------------------------------
     * 6. PERMUTATIONS USING BACKTRACKING
     * Time: O(n * n!), Space: O(n)
     * ------------------------------------------------------------------ */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrackPermute(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);

            backtrackPermute(nums, used, path, result);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /** ------------------------------------------------------------------
     * 7. MEMOIZED RECURSION TEMPLATE
     * Time: O(N), Space: O(N)
     * ------------------------------------------------------------------ */
    Map<Integer, Integer> memo = new HashMap<>();

    public int fibMemo(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);

        int result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    /** ------------------------------------------------------------------
     * 8. RECURSIVE BINARY SEARCH (classic D&C)
     * Time: O(log n), Space: O(log n) stack
     * ------------------------------------------------------------------ */
    public int binarySearch(int[] arr, int target) {
        return binarySearchHelper(arr, target, 0, arr.length - 1);
    }

    private int binarySearchHelper(int[] arr, int target, int left, int right) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] > target)
            return binarySearchHelper(arr, target, left, mid - 1);
        else
            return binarySearchHelper(arr, target, mid + 1, right);
    }

    /** ------------------------------------------------------------------
     * 9. DRIVER METHOD TO TEST
     * ------------------------------------------------------------------ */
    public static void main(String[] args) {
        RecursionTemplate r = new RecursionTemplate();

        System.out.println("Basic Recursion:");
        r.basicRecursion(5);

        System.out.println("\nTail Recursion:");
        r.tailRecursion(5);

        System.out.println("\nFibonacci (Recursive): " + r.fib(5));
        System.out.println("Fibonacci (Memoized): " + r.fibMemo(10));

        System.out.println("\nSubsets:");
        System.out.println(r.subsets(new int[]{1, 2, 3}));

        System.out.println("\nPermutations:");
        System.out.println(r.permute(new int[]{1, 2, 3}));

        System.out.println("\nMerge Sort:");
        System.out.println(Arrays.toString(r.mergeSort(new int[]{5, 2, 3, 1})));

        System.out.println("\nBinary Search (Recursive):");
        System.out.println(r.binarySearch(new int[]{1, 2, 3, 4, 5, 6}, 4)); // Output: 3
    }
}
