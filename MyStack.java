import LinkedList.LinkedList;

import java.util.TreeSet;

/**
 * Created by Pratik on 2/12/2016.
 */
public class MyStack {

    LinkedList stack = new LinkedList();


    public void push(String str) {
        stack.addStart(str);
    }

    public String pop() {
        String str = stack.getHead();
        stack.deleteStart();
        return str;
    }

    public String peek() {
        return stack.getHead();
    }

    public boolean isEmpty() {
        return this.stack.getHead() == null;
    }


    private boolean isOp(char op) {
        String ops = "+-/*^";
        return ops.contains(""+op);
    }

    private int precedence(char op) {
        String ops = "-+/*^";
        return ops.indexOf(op);
    }


    public String inToPost(String infix) {
        MyStack stack = new MyStack();
        String postfix = "";

        for (char c : infix.toCharArray()) {
            if (isOp(c)) {
                if (stack.isEmpty())
                    stack.push(""+c);
                else if (precedence(stack.peek().charAt(0)) > precedence(c)) {
                    // (stack.peek() != null) is important
                    while ((stack.peek() != null) && (precedence(stack.peek().charAt(0)) > precedence(c)))
                        postfix += stack.pop();
                    stack.push(""+c);
                }
                else
                    stack.push(""+c);
            } else if(c == '(')
                stack.push(c+"");
            else if (c == ')') {
                while (!stack.peek().equals("(")) {
                    postfix += stack.pop();
                }
                stack.pop();
            } else {
                postfix += c;
            }
        }

        // Important step dont forget oe else
        // operators on stack will not be printed
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }


    public boolean isBalanced(String input) {
        MyStack stack = new MyStack();
        for (char c: input.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(""+c);
                    break;
                case ')':
                    if (!stack.pop().equals("("))
                        return false;
                    break;
                case '}':
                    if (!stack.pop().equals("{"))
                        return false;
                    break;
                case ']':
                    if (!stack.pop().equals("["))
                        return false;
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();

    }


    public String evalPost(String exp) {
        MyStack stack = new MyStack();
        int op1 = 0, op2 = 0;

        for (char c: exp.toCharArray()) {
            if (isOp(c)) {
                op2 = Integer.parseInt(stack.pop());
                op1 = Integer.parseInt(stack.pop());
                switch(c) {
                    case '+':
                        stack.push("" + (op1 + op2));
                        break;
                    case '-':
                        stack.push("" +(op1 - op2));
                        break;
                    case '*':
                        stack.push("" +(op1 * op2));
                        break;
                    case '/':
                        stack.push("" +(op1 / op2));
                        break;
                    case '^':
                        stack.push("" +Math.pow(op1, op2));
                        break;
                }
            } else {
                stack.push(""+c);
            }
        }
        return stack.pop();
    }

    public void reverse(MyStack myStack) {
        if (!myStack.isEmpty()) {
            String temp = myStack.pop();
            reverse(myStack);
            pushAtBottom(temp, myStack);
        }
    }

    private void pushAtBottom(String str, MyStack myStack) {
        if (myStack.isEmpty())
            myStack.push(str);
        else {
            String temp = myStack.pop();
            pushAtBottom(str, myStack);
            myStack.push(temp);
        }
    }


    public MyStack sortStack(MyStack stack) {
        MyStack stack2 = new MyStack();
        if (!stack.isEmpty()) {

            while(!stack.isEmpty()) {
                String str = stack.pop();
                String str2 = stack2.peek();
                if (str2 != null) {
                    if (str.compareTo(str2) > 0) {
                        while(str.compareTo(stack2.peek()) > 0) {
                            stack.push(stack2.pop());
                        }
                    }
                }
                stack2.push(str);
            }
        }

        return stack2;
    }

    public int hashCode() {
        return -1;
    }




    public static void main(String... args) {
        MyStack myStack = new MyStack();
//        myStack.push("Pratik");
//        myStack.push("shirish");
//        myStack.push("kulkarni");

//        while (myStack.peek() != null) {
//            System.out.println(myStack.pop());
//        }
//        System.out.println(myStack.inToPost("a+b*(c^d-e)^(f+g*h)-i"));
//        System.out.println(myStack.inToPost("4^3"));
//        System.out.println("Ans: " + myStack.evalPost(myStack.inToPost("4-3")));
//        System.out.println(myStack.isBalanced("(((({}{}{}[((()))]))))"));

//        myStack.reverse(myStack);
//        while (myStack.peek() != null) {
//            System.out.println(myStack.pop());
//        }



        myStack.push("a");
        myStack.push("c");
        myStack.push("e");
        myStack.push("b");
        myStack.push("f");
        myStack.push("g");
        myStack.push("d");
        myStack.push("h");
        myStack.push("i");

        myStack = myStack.sortStack(myStack);

        while (myStack.peek() != null) {
            System.out.println(myStack.pop());
        }
    }



}
