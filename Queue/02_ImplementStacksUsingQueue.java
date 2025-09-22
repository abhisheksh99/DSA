import java.util.LinkedList;
import java.util.Queue;

public class ImplementStacksUsingQueue {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

// Alternative implementation using a single queue
public class MyStack {
    private Queue<Integer> queue = new LinkedList<>();

    // Push element on top
    public void push(int x) {
        queue.add(x);
        int sz = queue.size();
        for (int i = 0; i < sz - 1; i++) {
            queue.add(queue.remove());  // Rotate to put new element at front
        }
    }

    // Removes the element on top and returns it
    public int pop() {
        if (queue.isEmpty()) throw new RuntimeException("Stack is empty");
        return queue.remove();
    }

    // Get the top element
    public int top() {
        if (queue.isEmpty()) throw new RuntimeException("Stack is empty");
        return queue.peek();
    }

    // Returns whether the stack is empty
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */