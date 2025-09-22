class Solution {
    // Problem: High Five
    // Given a list of scores for students, where each score is represented as an array [id, score],
    // return an array of average scores for each student, where the average is computed from their
    // top 5 scores. Each student is guaranteed to have at least 5 scores. The result should be
    // sorted by student ID in ascending order, with each entry as [id, average_score].

    // DSA Pattern: HashMap with Max-Heap
    // This problem is solved using a TreeMap to store student scores, where each key is a student ID
    // and the value is a max-heap (PriorityQueue) of their scores. The TreeMap ensures that student IDs
    // are processed in ascending order, and the max-heap allows efficient retrieval of the top 5 scores
    // for each student. The average of these scores is computed and returned in the required format.

    // Approach:
    // 1. Initialize a TreeMap to map student IDs to max-heaps of their scores.
    // 2. Iterate through the input array of [id, score] pairs:
    //    - For each student ID, create a max-heap if it doesn't exist.
    //    - Add the score to the student's max-heap.
    // 3. For each student ID in the TreeMap (in ascending order):
    //    - Extract the top 5 scores from the max-heap and compute their sum.
    //    - Calculate the average by dividing the sum by 5.
    //    - Store the result as [id, average] in a list.
    // 4. Convert the list of results to a 2D array and return it.

    // Key Points to Remember:
    // - The TreeMap ensures that student IDs are processed in ascending order, satisfying the requirement
    //   for sorted output.
    // - The max-heap (PriorityQueue with reversed comparator) ensures efficient access to the top 5 scores
    //   for each student.
    // - Each student is guaranteed to have at least 5 scores, so no edge case handling is needed for
    //   insufficient scores.
    // - The average is computed as an integer (sum / 5), which truncates any decimal part as required.
    // - Edge cases are minimal due to the problem's constraints (e.g., at least 5 scores per student).
    // - The implementation is efficient, using a max-heap for score selection and a TreeMap for sorted output.

    // Time Complexity: O(n * log m + m * log k)
    // - n: Total number of scores (length of items array).
    // - m: Number of unique students.
    // - k: Number of scores per student (k >= 5).
    // - Building the TreeMap with max-heaps:
    //   - Iterating through n scores and adding to max-heaps: O(n * log k), as each insertion to a
    //     PriorityQueue is O(log k), where k is the number of scores for that student.
    // - Processing each student:
    //   - Extracting top 5 scores for each of m students: O(m * 5 * log k) = O(m * log k).
    //   - Adding [id, average] to the result list: O(m).
    // - Converting the list to an array: O(m).
    // - Overall complexity: O(n * log k + m * log k), simplified to O(n * log m) since k <= n/m and
    //   m <= n.
    // - In practice, k is often small, making the heap operations efficient.

    // Space Complexity: O(m + k)
    // - TreeMap stores m entries (one per student): O(m).
    // - Each max-heap stores up to k scores for a student: O(k) per student, but typically O(n) total
    //   across all students since n = m * k (approximately).
    // - Result list and output array store m entries: O(m).
    // - Overall space: O(n) for all scores in the heaps plus O(m) for the TreeMap and output, simplified
    //   to O(n) since m <= n.

    public int[][] highFive(int[][] items) {
        // Step 1: Initialize a TreeMap to store student IDs mapped to max-heaps of scores
        Map<Integer, Queue<Integer>> scores = new TreeMap<>(); // TreeMap for sorted IDs

        // Step 2: Process each score and add to the corresponding student's max-heap
        for (int[] item : items) {
            int id = item[0];    // Student ID
            int score = item[1]; // Score

            // Create a max-heap for the student if it doesn't exist
            if (!scores.containsKey(id)) {
                scores.put(id, new PriorityQueue<>((a, b) -> b - a)); // Max-heap (O(1))
            }
            // Add the score to the student's max-heap
            scores.get(id).add(score); // O(log k), where k is number of scores for the student
        }

        // Step 3: Compute the average of top 5 scores for each student
        List<int[]> ans = new ArrayList<>(); // List to store [id, average] pairs
        for (int id : scores.keySet()) {
            int sum = 0;
            // Extract top 5 scores from the max-heap
            for (int i = 0; i < 5; i++) {
                sum += scores.get(id).poll(); // O(log k) per poll
            }
            // Add [id, average] to the result list
            ans.add(new int[]{id, sum / 5}); // Integer division for average
        }

        // Step 4: Convert the result list to a 2D array
        int[][] ansArray = new int[ans.size()][]; // Initialize output array
        return ans.toArray(ansArray); // O(m) to copy list to array
    }
}