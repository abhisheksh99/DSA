public class QueueUsingArray {
    private int[] arr;
    private int front, rear, size, capacity;

    public QueueUsingArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int x) {
        if (size == capacity) throw new RuntimeException("Queue is full");
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    public int dequeue() {
        if (size == 0) throw new RuntimeException("Queue is empty");
        int value = arr[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public int peek() {
        if (size == 0) throw new RuntimeException("Queue is empty");
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
