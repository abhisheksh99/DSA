public class QueueUsingLinkedList {
    private static class Node {
        int value;
        Node next;
        Node(int val) {
            value = val;
            next = null;
        }
    }

    private Node front, rear;

    public QueueUsingLinkedList() {
        front = rear = null;
    }

    public void enqueue(int x) {
        Node newNode = new Node(x);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public int dequeue() {
        if (front == null) throw new RuntimeException("Queue is empty");
        int val = front.value;
        front = front.next;
        if (front == null) rear = null;
        return val;
    }

    public int peek() {
        if (front == null) throw new RuntimeException("Queue is empty");
        return front.value;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
