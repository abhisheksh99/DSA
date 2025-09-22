import java.util.Stack;

public class ImplementQueueUsingStacks {
    private Stack<Integer> inputStack = new Stack<>();
    private Stack<Integer> outputStack = new Stack<>();

    public void push(int x) {
        inputStack.push(x);
    }

    private void transfer() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty())
                outputStack.push(inputStack.pop());
        }
    }

    public int pop() {
        transfer();
        return outputStack.pop();
    }

    public int peek() {
        transfer();
        return outputStack.peek();
    }

    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}
