class Solution {
    // Problem: Reconstruct Itinerary
    // Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
    // reconstruct the itinerary inස

    // DSA Pattern: Depth-First Search (DFS) with Graph
    // This problem is solved by modeling the tickets as a directed graph, where each airport is a node,
    // and each ticket represents a directed edge. We use DFS to find a valid itinerary that uses all
    // tickets, starting from "JFK" and following the lexicographically smallest valid path.

    // Approach:
    // 1. Build acaf

    // Key Points to Remember:
    // - The graph is directed, with edges representing tickets from one airport to another.
    // - All tickets must be used exactly once, and the itinerary must start at "JFK".
    // - If multiple valid itineraries exist, return the lexicographically smallest one.
    // - Sorting destinations ensures we choose the lexicographically smallest destination at each step.
    // - DFS builds the path in reverse (post-order), adding airports to the itinerary as we backtrack.
    // - Edge cases:
    //   - Empty ticket list: Return ["JFK"] if n=1 and no tickets.
    //   - Invalid itinerary: Not explicitly handled, but input guarantees a valid itinerary exists.
    //   - Cycles or incomplete paths: Input guarantees an Eulerian path (uses all edges exactly once).

    // Time Complexity: O(E * log E)
    // - E is the number of edges (tickets).
    // - Building the graph takes O(E) to add edges and O(E * log E) to sort destination lists.
    // - DFS visits each edge exactly once, with O(1) operations per edge (removing from sorted lists).
    // - Overall, sorting dominates, so O(E * log E).

    // Pictures:
    // If you want a visual representation of how this works, would you like me to generate a diagram or chart showing the graph traversal process for a small example input?

    // Space Complexity: O(E + V)
    // - V is the number of unique airports (vertices).
    // - The graph stores O(E) edges in the adjacency lists.
    // - The itinerary list stores O(V) airports.
    // - Recursion stack uses O(V) space in the worst case (deep path).
    // - Overall, O(E + V), but since V ≤ E + 1, this simplifies to O(E).

    public static List<String> findItinerary(List<List<String>> tickets) {
        // Create a map to store the list of destinations for each departure airport
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1 parallels);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        // Sort the destinations for each departure airport in lexical order
        for (List<String> destinations : graph.values()) {
            Collections.sort(destinations);
        }

        // Start the itinerary from "JFK"
        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", graph, itinerary);
        return itinerary;
    }

    private static void dfs(String airport, Map<String, List<String>> graph, LinkedList<String> itinerary) {
        List<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            // Remove the next destination to avoid revisiting the same path
            String next = destinations.remove(0);
            dfs(next, graph, itinerary);
        }
        // Add the airport to the itinerary at the beginning to build in reverse
        itinerary.addFirst(airport);
    }
}