class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    // Heapify subtree rooted at index i
    private void maxHeapify(int i) {
        int largest = i;
        int left = left(i);
        int right = right(i);

        if (left < size && heap[left] > heap[largest]) largest = left;
        if (right < size && heap[right] > heap[largest]) largest = right;

        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest);
        }
    }

    // Insert key into max heap
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap overflow");
            return;
        }
        size++;
        int i = size - 1;
        heap[i] = key;

        // Fix violated max heap property upwards
        while (i != 0 && heap[parent(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    // Extract max element (root)
    public int extractMax() {
        if (size <= 0) return Integer.MIN_VALUE;
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);
        return root;
    }

    // Get max element without removing
    public int getMax() {
        if (size <= 0) return Integer.MIN_VALUE;
        return heap[0];
    }
}
