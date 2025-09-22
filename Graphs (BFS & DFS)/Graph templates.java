import java.util.*;

// Adjacency List & Matrix Representation
// Adjacency List: O(V + E) space, O(1) for edge addition, O(degree) for neighbor lookup
// Adjacency Matrix: O(V^2) space, O(1) for edge lookup and addition
class Graph {
    int V;
    boolean directed;
    // Adjacency List: Map of node to list of [neighbor, weight]
    Map<Integer, List<int[]>> adjList;
    // Adjacency Matrix: V x V matrix
    int[][] adjMatrix;

    public Graph(int vertices, boolean directed) {
        this.V = vertices;
        this.directed = directed;
        adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
        adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int u, int v, int weight) {
        // Adjacency List
        adjList.get(u).add(new int[]{v, weight});
        if (!directed) {
            adjList.get(v).add(new int[]{u, weight});
        }
        // Adjacency Matrix
        adjMatrix[u][v] = weight;
        if (!directed) {
            adjMatrix[v][u] = weight;
        }
    }
}

// DFS (Recursive)
// Time: O(V + E), Space: O(V) for recursion stack and visited array
class DFSRecursive {
    public void dfs(Graph g, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int[] neighbor : g.adjList.get(start)) {
            int next = neighbor[0];
            if (!visited[next]) {
                dfs(g, next, visited);
            }
        }
    }

    public void run(Graph g) {
        boolean[] visited = new boolean[g.V];
        dfs(g, 0, visited);
    }
}

// DFS (Iterative)
// Time: O(V + E), Space: O(V) for stack and visited array
class DFSIterative {
    public void dfs(Graph g, int start) {
        boolean[] visited = new boolean[g.V];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                System.out.print(node + " ");
                for (int[] neighbor : g.adjList.get(node)) {
                    int next = neighbor[0];
                    if (!visited[next]) {
                        stack.push(next);
                    }
                }
            }
        }
    }
}

// BFS (Iterative)
// Time: O(V + E), Space: O(V) for queue and visited array
class BFSIterative {
    public void bfs(Graph g, int start) {
        boolean[] visited = new boolean[g.V];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int[] neighbor : g.adjList.get(node)) {
                int next = neighbor[0];
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}

// BFS (Recursive) - Not common, but implemented using a queue
// Time: O(V + E), Space: O(V) for queue and visited array
class BFSRecursive {
    public void bfs(Graph g, Queue<Integer> queue, boolean[] visited) {
        if (queue.isEmpty()) return;
        int node = queue.poll();
        System.out.print(node + " ");
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next]) {
                visited[next] = true;
                queue.offer(next);
            }
        }
        bfs(g, queue, visited);
    }

    public void run(Graph g, int start) {
        boolean[] visited = new boolean[g.V];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        bfs(g, queue, visited);
    }
}

// Topological Sort (DFS-based)
// Time: O(V + E), Space: O(V) for stack and visited array
class TopologicalSortDFS {
    public void dfs(Graph g, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next]) {
                dfs(g, next, visited, stack);
            }
        }
        stack.push(node);
    }

    public List<Integer> topologicalSort(Graph g) {
        boolean[] visited = new boolean[g.V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < g.V; i++) {
            if (!visited[i]) {
                dfs(g, i, visited, stack);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

// Topological Sort (Kahn’s Algorithm - BFS)
// Time: O(V + E), Space: O(V) for queue and in-degree array
class TopologicalSortKahn {
    public List<Integer> topologicalSort(Graph g) {
        int[] inDegree = new int[g.V];
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                inDegree[neighbor[0]]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < g.V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int[] neighbor : g.adjList.get(node)) {
                int next = neighbor[0];
                if (--inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return result.size() == g.V ? result : new ArrayList<>(); // Empty if cycle exists
    }
}

// Cycle Detection (Directed Graph - DFS)
// Time: O(V + E), Space: O(V) for visited and recursion stack
class CycleDetectionDirected {
    public boolean hasCycle(Graph g, int node, boolean[] visited, boolean[] recStack) {
        visited[node] = true;
        recStack[node] = true;
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next] && hasCycle(g, next, visited, recStack)) {
                return true;
            } else if (recStack[next]) {
                return true;
            }
        }
        recStack[node] = false;
        return false;
    }

    public boolean hasCycle(Graph g) {
        boolean[] visited = new boolean[g.V];
        boolean[] recStack = new boolean[g.V];
        for (int i = 0; i < g.V; i++) {
            if (!visited[i] && hasCycle(g, i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }
}

// Cycle Detection (Undirected Graph - DFS)
// Time: O(V + E), Space: O(V) for visited array
class CycleDetectionUndirectedDFS {
    public boolean hasCycle(Graph g, int node, int parent, boolean[] visited) {
        visited[node] = true;
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next]) {
                if (hasCycle(g, next, node, visited)) {
                    return true;
                }
            } else if (next != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(Graph g) {
        boolean[] visited = new boolean[g.V];
        for (int i = 0; i < g.V; i++) {
            if (!visited[i] && hasCycle(g, i, -1, visited)) {
                return true;
            }
        }
        return false;
    }
}

// Cycle Detection (Undirected Graph - DSU)
// Time: O(E * α(V)) amortized (α is inverse Ackermann), Space: O(V)
class UnionFind {
    int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false; // Cycle detected
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
        return true;
    }
}

class CycleDetectionUndirectedDSU {
    public boolean hasCycle(Graph g) {
        UnionFind uf = new UnionFind(g.V);
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0];
                if (u < v && !uf.union(u, v)) { // Avoid duplicate edges
                    return true;
                }
            }
        }
        return false;
    }
}

// Dijkstra's Algorithm (Priority Queue)
// Time: O((V + E) log V), Space: O(V) for dist and pq
class Dijkstra {
    public int[] dijkstra(Graph g, int src) {
        int[] dist = new int[g.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0});
        boolean[] visited = new boolean[g.V];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            if (visited[u]) continue;
            visited[u] = true;
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
}

// Bellman-Ford Algorithm
// Time: O(V * E), Space: O(V) for dist array
class BellmanFord {
    public int[] bellmanFord(Graph g, int src) {
        int[] dist = new int[g.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        // Relax all edges V-1 times
        for (int i = 0; i < g.V - 1; i++) {
            for (int u = 0; u < g.V; u++) {
                for (int[] neighbor : g.adjList.get(u)) {
                    int v = neighbor[0], weight = neighbor[1];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }
        // Check for negative cycles
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    throw new RuntimeException("Graph contains negative cycle");
                }
            }
        }
        return dist;
    }
}

// Kruskal’s Algorithm (MST)
// Time: O(E log E) for sorting + O(E * α(V)) for DSU, Space: O(V + E)
class Kruskal {
    class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    public List<Edge> kruskalMST(Graph g) {
        List<Edge> edges = new ArrayList<>();
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                if (u < v) { // Avoid duplicate edges
                    edges.add(new Edge(u, v, weight));
                }
            }
        }
        Collections.sort(edges);
        UnionFind uf = new UnionFind(g.V);
        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            if (uf.union(e.src, e.dest)) {
                mst.add(e);
            }
        }
        return mst;
    }
}

// Prim’s Algorithm (MST)
// Time: O((V + E) log V) with priority queue, Space: O(V)
class Prim {
    public int primMST(Graph g) {
        int[] key = new int[g.V];
        Arrays.fill(key, Integer.MAX_VALUE);
        boolean[] inMST = new boolean[g.V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        key[0] = 0;
        pq.offer(new int[]{0, 0});
        int totalWeight = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            if (inMST[u]) continue;
            inMST[u] = true;
            totalWeight += curr[1];
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.offer(new int[]{v, key[v]});
                }
            }
        }
        return totalWeight;
    }
}

// Floyd-Warshall Algorithm (All-Pairs Shortest Path)
// Time: O(V^3), Space: O(V^2) for dist matrix
class FloydWarshall {
    public int[][] floydWarshall(Graph g) {
        int[][] dist = new int[g.V][g.V];
        for (int i = 0; i < g.V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                dist[u][v] = weight;
            }
        }
        for (int k = 0; k < g.V; k++) {
            for (int i = 0; i < g.V; i++) {
                for (int j = 0; j < g.V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }
}

// Strongly Connected Components (Kosaraju’s Algorithm)
// Time: O(V + E), Space: O(V) for visited array and stack
class Kosaraju {
    public void dfsFill(Graph g, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next]) {
                dfsFill(g, next, visited, stack);
            }
        }
        stack.push(node);
    }

    public void dfsComponent(Graph g, int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int[] neighbor : g.adjList.get(node)) {
            int next = neighbor[0];
            if (!visited[next]) {
                dfsComponent(g, next, visited, component);
            }
        }
    }

    public List<List<Integer>> findSCCs(Graph g) {
        // Step 1: DFS to fill stack
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[g.V];
        for (int i = 0; i < g.V; i++) {
            if (!visited[i]) {
                dfsFill(g, i, visited, stack);
            }
        }
        // Step 2: Transpose graph
        Graph transposed = new Graph(g.V, true);
        for (int u = 0; u < g.V; u++) {
            for (int[] neighbor : g.adjList.get(u)) {
                int v = neighbor[0], weight = neighbor[1];
                transposed.addEdge(v, u, weight);
            }
        }
        // Step 3: DFS on transposed graph
        List<List<Integer>> sccs = new ArrayList<>();
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfsComponent(transposed, node, visited, component);
                sccs.add(component);
            }
        }
        return sccs;
    }
}