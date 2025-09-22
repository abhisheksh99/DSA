// Problem: Detect Squares
// Given a stream of points on a 2D plane, implement a data structure to support two operations:
// 1. add(point): Add a point to the plane.
// 2. count(point): Count the number of axis-aligned squares that can be formed with the given point
// as one corner and previously added points as the other three corners.

// DSA Pattern: Hash Map / Geometry
// This problem is solved using a hash map to store the frequency of points and a list to store all points.
// The count operation checks for points that can form an axis-aligned square with the input point by
// leveraging the property that opposite corners of a square have equal side lengths.

// Approach:
// 1. Initialize a list to store all points and a hash map to store point frequencies (x@y as key).
// 2. add(point):
//    - Append the point to the list of coordinates.
//    - Create a string key from the point's coordinates (x@y) and increment its frequency in the hash map.
// 3. count(point):
//    - For each previously added point (x, y) in the coordinates list:
//      - Skip if it shares the same x or y coordinate as the input point (px, py), or if the differences
//        |px - x| and |py - y| are not equal (not a square).
//      - For valid points, check for the other two corners of the square (x, py) and (px, y).
//      - Multiply the frequencies of these two corners (from the hash map) to get the number of squares
//        formed with the current point (x, y) and add to the total sum.
// 4. Return the total number of valid squares.

// Key Points to Remember:
// - A square is axis-aligned, so sides are parallel to x and y axes, and all sides are equal.
// - For a point (px, py) and another point (x, y), they can form opposite corners of a square if
//   |px - x| = |py - y| and they are not on the same x or y axis (px != x and py != y).
// - The other two corners of the square are (x, py) and (px, y).
// - The hash map stores point frequencies to efficiently count occurrences of these corners.
// - Edge cases: No squares if no points, duplicate points increase square count, or points on same axis (no squares).
// - The string key x@y uniquely identifies a point in the hash map.

// Time Complexity:
// - add(point): O(1)
//   - Adding to the list and updating the hash map are constant-time operations.
// - count(point): O(n), where n is the number of points added
//   - Iterates through all points in the coordinates list.
//   - For each point, performs O(1) operations (hash map lookups and arithmetic).
// - Initialization: O(1)
//   - Creating the list and hash map is constant time.

// Space Complexity: O(n), where n is the number of points added
// - The coordinates list stores all points: O(n).
// - The hash map stores unique points with their frequencies: O(n) in the worst case.
// - No additional data structures are used beyond these.

public class DetectSquares {
    // List to store all points
    List<int[]> coordinates;
    // Hash map to store point frequencies
    Map<String, Integer> counts;
    
    public DetectSquares() {
        // Initialize empty list and hash map
        coordinates = new ArrayList<>();
        counts = new HashMap<>();
    }
    
    public void add(int[] point) {
        // Add point to coordinates list
        coordinates.add(point);
        // Create string key from coordinates (x@y)
        String key = point[0] + "@" + point[1];
        // Increment frequency in hash map
        counts.put(key, counts.getOrDefault(key, 0) + 1);
    }
    
    public int count(int[] point) {
        // Initialize sum of valid squares
        int sum = 0;
        // Extract x and y of input point
        int px = point[0], py = point[1];
        // Iterate through all stored points
        for (int[] coordinate : coordinates) {
            int x = coordinate[0], y = coordinate[1];
            // Skip if same x or y, or not a square (|px - x| != |py - y|)
            if (px == x || py == y || Math.abs(px - x) != Math.abs(py - y))
                continue;
            // Multiply frequencies of other two corners (x, py) and (px, y)
            sum += counts.getOrDefault(x + "@" + py, 0) * counts.getOrDefault(px + "@" + y, 0);
        }
        // Return total number of squares
        return sum;
    }
}