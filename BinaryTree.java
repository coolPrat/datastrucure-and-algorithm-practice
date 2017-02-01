import java.util.*;

public class BinaryTree<E> {

    Node<E> head;
    static int preStart = 0;

    BinaryTree(E e) {
        head = new Node<>(e);
    }

// ****************** traversals ******************************************


    public void inOrder(Node head) {
        if(head != null) {
            inOrder(head.left);
            System.out.print(head.data + " ");
            inOrder(head.right);
        }
    }

    public void inOrderIt(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (head != null) {
                stack.push(head);
                head = head.left;
            }

            while(!stack.isEmpty()) {
                Node node = stack.pop();
                System.out.println(node.data);
                if (node.right != null) {
                    stack.push(node.right);
                    node = node.right;
                    while (node.left != null) {
                        stack.push(node.left);
                        node = node.left;
                    }
                }
            }
        }
    }

    public void preorder(Node head) {
        if (head != null) {
            System.out.print(head.data + " ");
            preorder(head.left);
            preorder(head.right);
        }
    }

    public void postOrder(Node head) {
        if (head != null) {
            postOrder(head.left);
            postOrder(head.right);
            System.out.println(head.data);
        }
    }



// ****************** Level order traversal (BFS) ******************************************

    public void levelOrder(Node head) {
        if (head != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);

            while(!queue.isEmpty()) {
                Node node = queue.remove();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                System.out.println(node.data);
            }
        }
    }



// ****************** reverse level order traversals ******************************************

    public void reverseLevelOrder(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                if (node.right != null)
                    queue.add(node.right);
                if (node.left != null)
                    queue.add(node.left);
                stack.push(node);
            }

            while(!stack.isEmpty()) {
                System.out.println(stack.pop().data);
            }
        }
    }


// ****************** ZigZag ******************************************

    public void printZigZag(Node head) {
        if (head != null) {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            boolean leftToRight = true;
            stack1.push(head);
            while(!stack1.isEmpty()) {
                Node node = stack1.pop();
                if (leftToRight) {
                    if (node.left != null)
                        stack2.push(node.left);
                    if (node.right != null)
                        stack2.push(node.right);
                } else {
                    if (node.right != null)
                        stack2.push(node.right);
                    if (node.left != null)
                        stack2.push(node.left);
                }
                System.out.println(node.data);
                if (stack1.isEmpty()) {
                    stack1 = stack2;
                    stack2 = new Stack<>();
                    leftToRight = !leftToRight;
                }
            }
        }
    }


// ****************** is balanced  ******************************************
// Method 1

/*
    public boolean isBalanced(Node head) {
        boolean isBalaced = true;
        if (head != null) {
            isBalaced = (Math.abs(height(head.left) - height(head.right)) <= 1);
        }
        return isBalaced;
    }
*/


    public int height(Node head) {
        if (head == null)
            return 0;
        else
            return 1 + Math.max(height(head.left) , height(head.right));
    }


// Method 2

    public int isBalanced(Node head) {
        int left = 0, right = 0;
        if(head != null) {
            left = isBalanced(head.left);
            right = isBalanced(head.right);
            if(left >= 0 && right >= 0) {
                if (Math.abs(left - right) <= 1)
                    return 1 + Math.max(left, right);
                else
                    return -1;
            } else
                return -1;
        }
        return 0;
    }


// ****************** verticle traversals ******************************************

    public void verticleTraversal(Node head) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        travers(head, hashMap, 0);
        Set<Map.Entry<Integer, String>> set = hashMap.entrySet();
        for(Map.Entry<Integer, String> e: set) {
            System.out.println("Level "+ e.getKey() + ": " + e.getValue());
        }
    }

    private void travers(Node head, HashMap<Integer, String> hashMap,int level) {
        if (head != null) {
            if (hashMap.containsKey(level)) {
                String aString = hashMap.get(level);
                aString += head.data;
                hashMap.put(level, aString);
            } else {
                String aString = (String) head.data;
                hashMap.put(level, aString);
            }
            travers(head.left, hashMap, level+1);
            travers(head.right, hashMap, level-1);
        }
    }


// ****************** Mirror ******************************************

    public void mirror(Node<String> root) {
        if (root != null) {
            Node<String> temp = root.left;
            root.left = root.right;
            root.right = temp;

            mirror(root.left);
            mirror(root.right);

        }
    }


// ****************** Build tree from in and pre ******************************************

    public Node<String> getTree(String[] inorder, String[] preorder) {

        int inStart = 0, inEnd = inorder.length - 1, preEnd = preorder.length - 1;

        return buildTree(inorder, inStart, inEnd, preorder, preEnd);
    }


    public Node<String> buildTree(String[] inorder, int inStart, int inEnd,
                                  String[] preorder, int preEnd)
    {
        if ((preStart <= preEnd) && (inStart <= inEnd)) {
            Node<String> node = new Node<>(preorder[preStart]);
            preStart++;

            if (inStart == inEnd)
                return node;

            int index = search(node.data, inorder, inStart, inEnd);

            node.left = buildTree(inorder, inStart, index - 1, preorder, preEnd);
            node.right = buildTree(inorder, index + 1, inEnd, preorder, preEnd);

            return node;
        }
        return null;
    }



    public int search(String data, String[] inorder, int inStart, int inEnd) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i].equals(data))
                return i;
        }
        return -1;
    }


// ****************** find longest level ******************************************

    public int findLogestLevel(Node<String> root) {
        if (root != null) {
            int maxLevel = 0, maxSize = 0, level = 0;
            Stack<Node<String>> current = new Stack<>();
            Stack<Node<String>> next = new Stack<>();
            current.push(root);

            while(!current.isEmpty()) {

                Node<String> node = current.pop();
                if (node.left != null)
                    next.push(node.left);
                if (node.right != null)
                    next.push(node.right);

                if (current.isEmpty()) {
                    if (maxSize < next.size()){
                        maxLevel = level + 1;
                        maxSize = next.size();
                    }
                    level++;
                    current = next;
                    next = new Stack<>();
                }
            }
            if (maxLevel >= 0) {
                return maxLevel;
            }

        }
        return -1;
    }


    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree("a");
        tree.head.left = new Node<String>("b");
        tree.head.right = new Node<String>("c");
        tree.head.left.left = new Node<String>("d");
        tree.head.left.right = new Node<String>("e");
        tree.head.right.left = new Node<String>("f");
        tree.head.right.right = new Node<String>("g");
        tree.head.left.left.left = new Node<String>("h");
        tree.head.left.left.right = new Node<String>("i");
        tree.head.left.right.left = new Node<String>("j");
        tree.head.left.right.right = new Node<String>("k");

        tree.head.right.left.left = new Node<String>("l");
        tree.head.right.left.right = new Node<String>("m");
 //-----------------------------------------------------------------
        tree.inOrder(tree.head);
        tree.preorder(tree.head);
        System.out.println("");
        tree.postOrder(tree.head);
         tree.levelOrder(tree.head);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        tree.inOrderIt(tree.head);
        tree.reverseLevelOrder(tree.head);
        tree.printZigZag(tree.head);

        System.out.println(tree.isBalanced(tree.head));
        tree.verticleTraversal(tree.head);
        tree.mirror(tree.head);
        System.out.println("");
        tree.inOrder(tree.head);



        String[] inorder = {"d", "b", "e", "a", "c"};
        String[] preorder = {"a", "b", "d", "e", "c"};

        Node e = tree.getTree(inorder, preorder);

        tree.inOrder(e);


        System.out.println(tree.findLogestLevel(tree.head));

    }
}


class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;

    Node() {

    }


    Node(E e) {
        data = e;
        left = null;
        right = null;
    }
}
