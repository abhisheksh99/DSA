// Problem: Course Schedule
// Given an integer numCourses representing the number of courses (labeled from 0 to numCourses-1) and an array of prerequisites
// where prerequisites[i] = [a, b] indicates that course a requires course b as a prerequisite, determine if it is possible to
// finish all courses. Return true if possible, false if there is a cycle in the dependency graph.

// DSA Pattern: Topological Sort / Breadth-First Search (BFS) / Graph Traversal
// This problem is solved using topological sorting with Kahn's algorithm (BFS-based) to detect whether a directed acyclic graph (DAG)
// can be formed from the course dependencies. A cycle in the graph indicates that it is impossible to complete all courses.

// Approach:
// 1. Build an adjacency list representation of the directed graph, where graph[i] contains all courses that depend on course i.
// 2. Create an array to track the in-degree (number of prerequisites) for each course.
// 3. Populate the graph and in-degree array by iterating through the prerequisites:
//    - For each [a, b], add course a to graph[b] and increment inDegree[a].
// 4. Initialize a queue with all courses that have no prerequisites (inDegree = 0).
// 5. Perform BFS (Kahn's algorithm):
//    - Dequeue a course and increment a counter of completed courses.
//    - For each course that depends on the dequeued course, reduce its in-degree by 1.
//    - If a course's in-degree becomes 0, add it to the queue (all prerequisites are satisfied).
// 6. Return true if the number of completed courses equals numCourses; otherwise, return false (indicating a cycle).

// Key Points to Remember:
// - The problem models course dependencies as a directed graph, where an edge from b to a means course a requires course b.
// - A cycle in the graph (e.g., course A requires B, B requires C, C requires A) makes it impossible to complete all courses.
// - Kahn's algorithm ensures that courses are processed only when all their prerequisites are satisfied.
// - Edge cases:
//   - No prerequisites: return true (all courses can be taken immediately).
//   - Single course with no prerequisites: return true.
//   - Empty graph (numCourses = 0): return true (no courses to complete).
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
// - Total space is O(V + E).

// Constraints:
// - 1 <= numCourses <= 2000
// - 0 <= prerequisites.length <= 5000
// - prerequisites[i].length == 2
// - 0 <= prerequisites[i][0], prerequisites[i][1] < numCourses
// - All prerequisites pairs are unique.
// - The input represents a directed graph, which may contain cycles.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create adjacency list to represent the course dependency graph
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // Track the in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];

        // Build the graph and calculate in-degrees
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph[prereq].add(course); // Add edge: prereq -> course
            inDegree[course]++;
        }

        // Initialize queue with courses having no prerequisites (inDegree = 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                queue.offer(course);
            }
        }

        // Process courses using topological sorting (Kahn's Algorithm)
        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            completedCourses++;

            // Update in-degrees for courses that depend on the current course
            for (int nextCourse : graph[currentCourse]) {
                inDegree[nextCourse]--;
                // If all prerequisites are satisfied, add course to queue
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // Return true if all courses can be completed, false if a cycle exists
        return completedCourses == numCourses;
    }
}