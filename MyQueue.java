/**
 * Created by Pratik on 2/15/2016.
 */
public class MyQueue {
    MyStack stack1 = new MyStack();
    MyStack stack2 = new MyStack();

    public void queue(String str) {
        if (stack2.isEmpty())
            stack1.push(str);
        else {
            while(!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    public String dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty())
            return null;
        else if (stack1.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    public boolean isEmpty() {
        return (stack1.isEmpty() && stack2.isEmpty());
    }


    public String stringYak(String str) {
        int end = str.length();
        int start = 0;
        String str2 = "";
        while(str.indexOf("yak", start) >=0) {

            if (str.indexOf("yak", start) == 0)
                end = 0;
            else
                end = str.indexOf("yak", start) - 1;
            str = str.substring(start, end) + str.substring(end + 3, str.length());
            start = end + 3;
        }
        return str;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.queue("Pratik");
        queue.queue("shirish");
        queue.queue("kulkarni");

        while(!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }


        System.out.println(queue.stringYak("yakpakyak"));

    }


}
