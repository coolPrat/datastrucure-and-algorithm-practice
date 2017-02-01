/**
 * Created by Pratik on 2/21/2016.
 */
public class Heap {

    int[] heap = new int[16];
    int size = 0;


    public void add(int input) {
        heap[size] = input;
        bubbleUp(size);
        size++;
    }

    public int getNext() {
        int res = heap[0];
        heap[0] = heap[size - 1];
        size--;
        pushDown(0);
        return res;
    }

    private void bubbleUp(int position) {
        int parent = getParent(position);

        while ((parent >= 0) && (heap[parent] > heap[position])) {
            int temp = heap[parent];
            heap[parent] = heap[position];
            heap[position] = temp;
            position = parent;
            parent = getParent(parent);
        }
    }

    private int getParent(int position) {
        return (position-1)/2;
    }

    private void pushDown(int position) {
        int child = getMinChild(position);
        while (child != position) {
            int temp = heap[child];
            heap[child] = heap[position];
            heap[position] = temp;
            position = child;
            child = getMinChild(position);
        }
    }

    private int getMinChild(int position) {
        int child1 = (position*2) + 1;
        int child2 = (position*2) + 2;

        if (child1 <= size && child2 <= size) {

            if (heap[child1] <= heap[child2]) {
                if (heap[child1] < heap[position]) {
                    return child1;
                }
            } else {
                if (heap[child2] < heap[position]) {
                    return child2;
                }
            }
        }
        return position;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        Heap myHeap = new Heap();
        myHeap.add(5);
        myHeap.add(2);
        myHeap.add(3);
        myHeap.add(4);
        myHeap.add(9);
        myHeap.add(8);
        myHeap.add(7);
        myHeap.add(6);
        myHeap.add(1);
        myHeap.add(10);
        myHeap.printHeap();
        for (int i = 0; i< 5; i++)
            System.out.println(myHeap.getNext());
        myHeap.printHeap();
    }

}
