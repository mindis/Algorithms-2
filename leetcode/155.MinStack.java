/*
155. Min Stack   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 104450
Total Submissions: 401268
Difficulty: Easy
Contributors: Admin
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

/*
solution with ONLY ONE stack

The question is ask to construct One stack. So I am using one stack.

The idea is to store the gap between the min value and the current value;

The problem for my solution is the cast. I have no idea to avoid the cast. Since the possible gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE;
*/

public class MinStack {
    long min;
    Stack<Long> stack;

    public MinStack(){
        stack=new Stack<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        
        long pop=stack.pop();
        
        if (pop<0)  min=min-pop;//If negative, increase the min value
        
    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }
public int getMin() {
        return (int)min;
    }
}


/*more succinct*/
public class MinStack {
    long min = Integer.MAX_VALUE;
    Stack<Long> stack = new Stack<>();
    public MinStack() {
        
    }
    
    public void push(int x) {
        stack.push((long)x-min);
        min = Math.min(x, min);
    }
    
    public void pop() {
        min = Math.max(min - stack.pop(), min);
    }
    
    public int top() {
        return (int)Math.max(stack.peek() + min, min);
    }
    
    public int getMin() {
        return (int)min;
    }
}

