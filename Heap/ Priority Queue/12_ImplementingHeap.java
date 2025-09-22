class MinHeap {
    private int[] heap;  // array representation of heap
    private int size;    // current number of elements
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Returns index of parent
    private int parent(int i) { return (i - 1) / 2; }

    // Returns index of left child
    private int left(int i) { return 2 * i + 1; }

    // Returns index of right child
    private int right(int i) { return 2 * i + 2; }

    // To heapify subtree rooted at index i
    private void heapify(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);

        // Find smallest among root, left, and right child
        if (left < size && heap[left] < heap[smallest]) smallest = left;
        if (right < size && heap[right] < heap[smallest]) smallest = right;

        // If root is not smallest, swap and continue heapify
        if (smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapify(smallest);
        }
    }

    // Insert a new key
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap is full");
            return;
        }
        size++;
        int i = size - 1;
        heap[i] = key;

        // Fix heap property if violated (bubble up)
        while (i != 0 && heap[parent(i)] > heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    // Extract minimum element (root)
    public int extractMin() {
        if (size <= 0) return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return heap[0];
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return root;
    }

    // Get minimum element without removing
    public int getMin() {
        if (size <= 0) return Integer.MAX_VALUE;
        return heap[0];
    }
}
