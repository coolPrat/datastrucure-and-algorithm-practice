import java.util.Stack;

/**
 * Created by Pratik on 2/9/2016.
 */
public class LinkedList {

    Node head;

    public void printList() {
        Node pointer = head;
        if (pointer == null) {
            return;
        }
        while(pointer !=null) {
            System.out.println(pointer.data);
            pointer = pointer.next;
        }
    }

    public String getHead() {
        return head != null? head.data: null;
    }

    public void addStart(String obj) {
        Node newNode = new Node(obj);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        /* short version
            if (head != null) {
                newNode.next = head;
            }
            head = newNode;
         */
        /* even short version: No need to check as there is no chance of nullpointerexception
            newNode.next = head;
            head = newNode;
         */
    }


    public void addLast(String obj) {
        Node newNode = new Node(obj);
        if (head == null) {
            head = newNode;
            return;
        }
        Node pointer = head;
        while(pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = newNode;
    }

    public void deleteStart() {
        if (head == null)
            return;
        head = head.next;
    }

    public void deleteLast() {
        if (head == null)
            return;
        Node pointer = head;
        Node prev = head;
        while(pointer.next != null) { // or pointer.next.next if we dont want prev
            prev = pointer;
            pointer = pointer.next;
        }
        if (prev == head) {
            head = null;
        }
        prev = null;
    }

//    public void deleteNode(Object obj) {
//        if (head == null) {
//            return;
//        }
//        Node pointer = head;
//        Node prev = head;
//        while(pointer != null && !pointer.data.equals(obj)) {
//            prev = pointer;
//            pointer = pointer.next;
//        }
//        if (pointer != null) {
//            if (pointer == head) {
//                head = null;
//                return;
//            }
//            prev.next = pointer.next;
//        }
//    }

    public void deleteNode(Object obj) {
        if (head != null) {
            if (head.data.equals(obj)) {
                deleteStart();
            } else {
                Node pointer = head;
                Node prev = head;
                while (pointer != null && !pointer.data.equals(obj)) {
                    prev = pointer;
                    pointer = pointer.next;
                }
                if (pointer != null) {
                    prev.next = pointer.next;
                }
            }
        }
    }

    public void swapTwoNodes(String a, String b) {
        if(head == null)
            return;
        Node myX = null;
        Node myY = null;
        Node myA = head;
        Node myB = head;
        while(myA != null && !myA.data.equals(a) ) {
            myX = myA;
            myA = myA.next;
        }
        if (myA == null) {
            return;
        }
        while(myB != null && !myB.data.equals(b) ) {
            myY = myB;
            myB = myB.next;
        }
        if (myB == null) {
            return;
        }
        if (myX == null) {
            myY.next = myA;
            Node temp = myA.next;
            myA.next = myB.next;
            myB.next = temp;
            head = myB;
        } else {
            myX.next = myB;
            myY.next = myA;
            Node temp = myA.next;
            myA.next = myB.next;
            myB.next = temp;
        }
    }

    public Node reverse() {
        if (head == null)
            return null;
        Node newHead = new Node(head.data);
        head = head.next;
        while (head != null) {
            // create new node from the data
            Node newNode = new Node(head.data);
            // set current head to its next
            newNode.next = newHead;
            // set new node as new head
            newHead = newNode;
            // move actual head ahead
            head = head.next;
        }
        return newHead;
    }

    public void reverse2() {
        if (head == null) {
            return;
        }
        Stack<Node> nodes = new Stack<>();
        Node pointer = head;
        while(pointer != null) {
            nodes.push(pointer);
            pointer = pointer.next;
        }
        head = nodes.pop();
        pointer = head;
        while(!nodes.empty()) {
            pointer.next = nodes.pop();
            pointer = pointer.next;
        }
        // important to remove next link of pointer
        // as now pointer is previous head it will
        // create an infinite loop
        pointer.next = null;
    }

    public void inPlaceReverse() {
        Node current, prev = null, next;
        current = head;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        // as current moves to null,
        // prev holds last element added
        // which is new head
        head = prev;
    }

    /**
     * create a new list which just passes through both the lists
     * @param a
     * @param b
     * @return
     */
    public Node mergeLists(LinkedList a, LinkedList b) {
        Node res = null, myA = a.head, myB = b.head, newHead = null;
        while(myA != null && myB != null) {
            if (myA.data.compareTo(myB.data) > 0) {
                if (res == null) {
                    res = myB;
                    newHead = res;
                } else {
                    res.next = myB;
                    res = res.next;
                }
                myB = myB.next;
            } else {
                if (res == null) {
                    res = myA;
                    newHead = res;
                } else {
                    res.next = myA;
                    res = res.next;
                }
                myA = myA.next;
            }
        }

        if (myA !=null) {
            res.next = myA;
        } else if(myB != null) {
            res.next = myB;
        }
        return newHead;
    }


    public Node merge2ListsRec(Node a, Node b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }

        if (a.data.compareTo(b.data) > 0) {
            b.next = merge2ListsRec(a, b.next);
            return b;
        } else {
            a.next = merge2ListsRec(a.next, b);
            return a;
        }
    }

    public Node mergeSort(Node pointer) {
        if (pointer.next == null)
            return pointer;

        Node nodeOne = pointer;
        Node nodeTwo = pointer.next;

        while(nodeTwo != null) {
            nodeTwo = nodeTwo.next;
            if (nodeTwo != null) {
                nodeTwo = nodeTwo.next;
                pointer = pointer.next;
            }
        }
        nodeTwo = pointer.next;
        pointer.next = null;
        Node head = merge2ListsRec(mergeSort(nodeOne), mergeSort(nodeTwo));
        return head;
    }


    public static NodeI add2Lists(NodeI a, NodeI b) {

        int length1 = length(a);
        int length2 = length(b);

        if (length1 != length2) {
            if (length1 > length2) {
                b = pad(b, length1 - length2);
            } else {
                a = pad(a, length2 - length1);
            }
        }

        NodeI res = addThem(a,b);

        if (res.data >= 10) {
            NodeI n = new NodeI(res.data/10);
            res.data = res.data - 10;
            n.next = res;
            res = n;
        }
        return res;
    }

    public static NodeI pad(NodeI node, int length) {
        NodeI n;
        for (int i = 0; i < length; i++) {
            n = new NodeI(0);
            n.next = node;
            node = n;
        }
        return node;
    }


    public static int length(NodeI node) {
        int counter = 0;

        while(node != null) {
            counter++;
            node = node.next;
        }
        return counter;
    }


    public static NodeI addThem(NodeI a, NodeI b) {
        NodeI n = new NodeI();
        int carry = 0;
        if (a.next == null && b.next == null) {
            n = new NodeI(a.data + b.data);
            return n;
        }
        n.next = addThem(a.next, b.next);
        if (n.next.data >= 10) {
            carry = n.next.data / 10;
            n.next.data = n.next.data - 10;
        }
        n.data = a.data + b.data + carry;

        return n;
    }

// TODO
    public void reverseKnodes(int k) {
        Node current = head, next = null, prev = null;
        int count = 0;

        while( current.next !=null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
            if (count == k) {
                count = 0;
                prev = current;
                current = current.next;
            }
        }

    }



    public static void main(String[] args) {
//        LinkedList ll = new LinkedList();
//        ll.addStart("Pratik");
//        ll.addLast("Shirish");
//        ll.addLast("kulkarni");
//        ll.printList();
//        LinkedList ll2 = new LinkedList();
//        ll2.head = ll.reverse();
//        ll2.printList();
//        ll.reverse2();
//        ll.inPlaceReverse();
//        ll.printList();
//        ll.swapTwoNodes("Pratik", "kulkarni");
//        ll.printList();
//        ll.deleteNode("Shirish");
//        ll.printList();
//        ll.deleteStart();
//        ll.printList();
//        ll.deleteLast();
//        ll.printList();


        LinkedList ll1 = new LinkedList();
        ll1.addLast("a");
        ll1.addLast("c");
        ll1.addLast("f");
        ll1.addLast("h");
        ll1.addLast("j");
        ll1.addLast("e");
        ll1.addLast("g");
        ll1.addLast("i");
        ll1.addLast("b");
        ll1.addLast("d");
        ll1.addLast("k");

//        LinkedList ll2 = new LinkedList();
//        ll2.addLast("b");
//        ll2.addLast("d");
//        ll2.addLast("f");
//        ll2.addLast("h");
//        ll2.addLast("j");
//        ll2.addLast("l");

//        LinkedList list = new LinkedList();
//        list.head = ll1.mergeLists(ll1, ll2);
//        list.head = ll1.merge2ListsRec(ll1.head, ll2.head);
//        list.printList();

//        LinkedList l1 = new LinkedList();
//        l1.head = ll1.mergeSort(ll1.head);
//        l1.printList();

        NodeI a = new NodeI(5, new NodeI(2, new NodeI(6, null)));
        NodeI b = new NodeI(3, new NodeI(7, new NodeI(5, null)));

        NodeI c = add2Lists(a, b);
        System.out.println("1");
    }

}

class Node {
    String data;
    Node next ;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class NodeI {
    int data;
    NodeI next ;
    NodeI() {
        this.data = 0;
        this.next = null;
    }

    NodeI(int data) {
        this.data = data;
        this.next = null;
    }


    NodeI(int data, NodeI n) {
        this.data = data;
        this.next = n;
    }

}
