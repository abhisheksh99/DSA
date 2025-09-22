// Problem: Assign Cookies
// Given two arrays: g (greed factors of children) and s (sizes of cookies), assign cookies to children such that
// each child gets at most one cookie, and the cookie's size is greater than or equal to the child's greed factor.
// Return the maximum number of children that can be satisfied.

// DSA Pattern: Greedy / Two Pointers
// This problem is solved using a greedy approach by sorting both arrays and assigning cookies to children in a
// single pass. We match the smallest possible cookie that satisfies each child's greed factor in ascending order.

// Approach:
// 1. Sort the greed factors array (g) and the cookie sizes array (s) in ascending order.
// 2. Initialize two pointers: childIndex for the greed array and cookieIndex for the cookies array.
// 3. While both pointers are within their respective array bounds:
//    - If the current cookie size (s[cookieIndex]) is greater than or equal to the current child's greed factor (g[childIndex]),
//      increment childIndex (child is satisfied).
//    - Increment cookieIndex to move to the next cookie regardless of assignment.
// 4. Return childIndex, which represents the number of children satisfied.

// Key Points to Remember:
// - Sorting ensures we try to satisfy children with smaller greed factors first and use the smallest possible cookies.
// - A cookie can satisfy a child if its size is at least the child's greed factor.
// - Each child gets at most one cookie, and each cookie is assigned to at most one child.
// - The greedy approach maximizes the number of satisfied children by assigning cookies optimally.
// - Edge cases:
//   - Empty g or s: return 0.
//   - More cookies than children: satisfy as many children as possible.
//   - More children than cookies: satisfy as many children as possible.
//   - No cookies large enough: return 0 if no assignments are made.
// - The solution assumes non-negative integers in both arrays.

// Time Complexity: O(n log n + m log m), where n is the length of g and m is the length of s
// - Sorting g takes O(n log n).
// - Sorting s takes O(m log m).
// - The while loop takes O(min(n, m)) to iterate through the arrays.
// - The dominant factor is the sorting, resulting in O(n log n + m log m).

// Space Complexity: O(1) excluding the input
// - Only a few variables (childIndex, cookieIndex) are used, requiring O(1) extra space.
// - The sorting step may use O(log n) or O(log m) space for the recursion stack in some implementations, but this is typically negligible.
// - The input arrays are modified in-place during sorting.

// Constraints:
// - 1 <= g.length <= 3 * 10^4
// - 0 <= s.length <= 3 * 10^4
// - 1 <= g[i], s[j] <= 2^31 - 1

public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // Sort greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);
        
        // Initialize pointers
        int childIndex = 0;
        int cookieIndex = 0;
        
        // Assign cookies to children
        while (childIndex < g.length && cookieIndex < s.length) {
            // If cookie can satisfy child, move to next child
            if (s[cookieIndex] >= g[childIndex]) {
                childIndex++;
            }
            // Move to next cookie
            cookieIndex++;
        }
        
        // Return number of satisfied children
        return childIndex;
    }
}