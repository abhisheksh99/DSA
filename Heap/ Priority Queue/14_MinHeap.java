import java.util.PriorityQueue;

class MinHeapUsingPQ {
    private PriorityQueue<Integer> pq;

    public MinHeapUsingPQ() {
        pq = new PriorityQueue<>();  // Min heap by default
    }

    // Insert element into heap
    public void insert(int val) {
        pq.add(val);
    }

    // Extract min element
    public int extractMin() {
        return pq.poll();
    }

    // Get min element without removing
    public int getMin() {
        return pq.peek();
    }

    // Check if heap is empty
    public boolean isEmpty() {
        return pq.isEmpty();
    }
}
