// Problem: Course Schedule II
// Given an integer numCourses representing the number of courses (labeled from 0 to numCourses-1) and an array of prerequisites
// where prerequisites[i] = [a, b] indicates that course a requires course b as a prerequisite, return the order in which courses
// can be taken to complete all courses. If it is impossible to complete all courses (due to a cycle), return an empty array.

// DSA Pattern: Topological Sort / Breadth-First Search (BFS) / Graph Traversal
// This problem is solved using topological sorting with Kahn's algorithm (BFS-based) to determine a valid order of courses.
// A cycle in the dependency graph indicates that no valid order exists.

// Approach:
// 1. Build an adjacency list representation of the directed graph, where graph[i] contains all courses that depend on course i.
// 2. Create an array to track the in-degree (number of prerequisites) for each course.
// 3. Populate the graph and in-degree array by iterating through the prerequisites:
//    - For each [a, b], add course a to graph[b] and increment inDegree[a].
// 4. Initialize a queue with all courses that have no prerequisites (inDegree = 0).
// 5. Perform BFS (Kahn's algorithm):
//    - Dequeue a course and add it to the result array (order).
//    - For each course that depends on the dequeued course, reduce its in-degree by 1.
//    - If a course's in-degree becomes 0, add it to the queue (all prerequisites are satisfied).
// 6. If the number of courses in the result array equals numCourses, return the order; otherwise, return an empty array (indicating a cycle).

// Key Points to Remember:
// - The problem models course dependencies as a directed graph, where an edge from b to a means course a requires course b.
// - A valid topological order ensures that each course is taken only after all its prerequisites are completed.
// - A cycle in the graph (e.g., course A requires B, B requires C, C requires A) makes it impossible to complete all courses.
// - Edge cases:
//   - No prerequisites: return any order of courses (e.g., [0, 1, ..., numCourses-1]).
//   - Single course with no prerequisites: return [0].
//   - Empty graph (numCourses = 0): return empty array.
// - The solution assumes courses are labeled from 0 to numCourses-1.

// Time Complexity: O(V + E), where V is the number of courses (numCourses) and E is the number of prerequisites
// - Building the graph and in-degree array takes O(E).
// - Initializing the queue with zero in-degree nodes takes O(V).
// - BFS processes each node once (O(V)) and each edge once (O(E)) when updating in-degrees.
// - Total complexity is O(V + E).

// Space Complexity: O(V + E)
// - The adjacency list (graph) requires O(V + E) space to store nodes and their neighbors.
// - The in-degree array requires O(V) space.
// - The queue requires O(V) space in the worst case (e.g., when most nodes have no prerequisites).
// - The output array requires O(V) space.
// - Total space is O(V + E).

// Constraints:
// - 1 <= numCourses <= 2000
// - 0 <= prerequisites.length <= numCourses * (numCourses - 1)
// - prerequisites[i].length == 2
// - 0 <= prerequisites[i][0], prerequisites[i][1] < numCourses
// - prerequisites[i][0] != prerequisites[i][1]
// - The input represents a directed graph, which may contain cycles.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create adjacency list to represent the course dependency graph
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Track the in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];

        // Build the graph and calculate in-degrees
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            graph[prereq].add(course); // Add edge: prereq -> course
            inDegree[course]++;
        }

        // Initialize queue with courses having no prerequisites (inDegree = 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Array to store the topological order of courses
        int[] order = new int[numCourses];
        int index = 0;

        // Process courses using topological sorting (Kahn's Algorithm)
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[index++] = course; // Add course to the result order

            // Update in-degrees for courses that depend on the current course
            for (int nextCourse : graph[course]) {
                inDegree[nextCourse]--;
                // If all prerequisites are satisfied, add course to queue
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // Return the order if all courses can be completed, else empty array
        if (index == numCourses) {
            return order;
        }
        return new int[0]; // Cycle detected, no valid order
    }
}