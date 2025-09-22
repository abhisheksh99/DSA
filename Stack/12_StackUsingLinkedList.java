public class StackUsingLinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int val) {
            value = val;
            next = null;
        }
    }

    private Node top;

    public StackUsingLinkedList() {
        this.top = null;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) throw new RuntimeException("Stack underflow");
        int val = top.value;
        top = top.next;
        return val;
    }

    public int peek() {
        if (top == null) throw new RuntimeException("Stack is empty");
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
