public class StackUsingArray {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public StackUsingArray(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
        } else {
            throw new RuntimeException("Stack overflow");
        }
    }

    public int pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            throw new RuntimeException("Stack underflow");
        }
    }

    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            throw new RuntimeException("Stack is empty");
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
