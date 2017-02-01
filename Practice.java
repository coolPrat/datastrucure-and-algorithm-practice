import java.util.*;

public class Practice {

// ********************** Find numbers comming odd times ******************

    public static ArrayList<Integer> findOdds(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        HashMap<Integer, Integer> myMap = new HashMap<>();
        int count = 0;
        for (int i: array) {
            count = 0;
            if (myMap.containsKey(i)) {
                count = myMap.get(i);
                myMap.put(i, count+1);
            }
            myMap.put(i, count+1);
        }

        Set<Map.Entry<Integer, Integer>> mySet = myMap.entrySet();
        for (Map.Entry<Integer, Integer> entry: mySet) {
            if (entry.getValue() % 2 == 1) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

// ********************** Balanced parenthesis ******************

    public static boolean isBalanced(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!compare(c, stack.pop())) {
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean compare(char a, char b) {
        if (a == ')' && b == '(')
            return true;
        if (a == ']' && b == '[')
            return true;
        if (a == '}' && b == '{')
            return true;
        return false;
    }


// ********************** 2 sum  ******************

// Method 1
/*
    public static void twosum(int[] array, int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i: array) {
            if(hashMap.containsKey(i)) {
                hashMap.put(i, n - i);
            } else {
                hashMap.put(n - i, -1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = hashMap.entrySet();
        for(Map.Entry<Integer, Integer> entry: entries) {
            if (entry.getValue() != -1) {
                System.out.println(entry.getValue() + " " + entry.getKey());
            }
        }
    }
*/

// Method 2

    public static void twosum(int[] array, int n) {
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i: array) {
            if (hashSet.contains(i)) {
                System.out.println((n - i) + " " + (i));
            } else {
                hashSet.add(n - i);
            }
        }
    }


// ********************** Whats max lenth of 1   ******************

    // given array of 0's and 1's flip one 0 to 1 and generate largest 1's string


    public static int flipZero(int[] array) {
        int current = 0, prev = -1, prev_prev = -1, max_dist = 0, max = -1;

        for (; current < array.length; current++) {
            if (array[current] == 0) {
                if (current - prev_prev > max_dist) {
                    max_dist = current - prev_prev;
                    max = prev;
                }
                prev_prev = prev;
                prev = current;
            }
        }

        if (array.length - prev_prev > max_dist) {
            max = prev;
        }
        return max;
    }


// ********************** merge 2 sorted arrays ******************


    public static int[] merge2arrays(int[] array1, int[] array2) {

        int length = array1.length > array2.length ? array2.length : array1.length;
        int[] res = new int[array1.length + array2.length];

        int count1 = 0, count2 = 0, resCounter = 0;

        while(count1 < array1.length && count2 < array2.length) {
            if (array1[count1] > array2[count2]) {
                res[resCounter] = array2[count2];
                count2++;
            } else {
                res[resCounter] = array1[count1];
                count1++;
            }
            resCounter++;
        }

        if (count1 == array1.length) {
            while(count2 < array2.length) {
                res[resCounter] = array2[count2];
                resCounter++;
                count2++;
            }
        } else {
            while(count1 < array1.length) {
                res[resCounter] = array1[count1];
                resCounter++;
                count1++;
            }
        }

        return res;
    }


// ********************** ASC DEC array return pivot element ******************


    public static int pivotIndex(int[] array) {
        if (array.length > 1) {
            return getPivot(array, 0, array.length - 1);
        }
        return 0;
    }


    public static int getPivot(int[] array, int start, int end) {

        if (end == start + 1) {
            if (array[start] > array[end])
                return start;
            else
                return end;
        }

        int mid = (start + end) / 2;
        if ((array[mid] > array[mid - 1])) {
            if ((array[mid] > array[mid + 1]))
                return mid;
            else
                return getPivot(array, mid + 1, end);
        } else {
            return getPivot(array, start, mid - 1);
        }
    }


// ********************** overlapping rectangles ******************


    public static boolean doOverlap(coor rect1l, coor rect1r, coor rect2l, coor rect2r) {

        if ((rect1l.x > rect2r.x) || (rect1r.x < rect2l.x))
            return false;
        if ((rect1l.y < rect2r.y) || (rect1r.y > rect2l.y))
            return false;
        return true;
    }

    public static boolean allUnique(String str) {
        int check = 0;
        int value = 0;

        for (int i = 0; i < str.length(); i++) {
            value = str.charAt(i) - 'a';
            if ((check & (1 << value)) != 0) {
                return false;
            } else {
                check = check | (1 << value);
            }

        }
        return true;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,5,1,3,1,5,8,2,1,4,9,4,1,6,9,3};
        ArrayList<Integer> arrayList = findOdds(arr);

        ListIterator<Integer> iterator = arrayList.listIterator();

        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println(isBalanced("{{{}}"));

        int[] arr1 = {2,4,6,1,4,8,3,5};

        twosum(arr1, 8);

        int[] arr2 = {1,1,1,0,1,1,1};

        System.out.println(flipZero(arr2));

        int[] arr3 = {1,2,7,8,9};
        int[] arr4 = {3,4,5,6};

        int[] arr5 = merge2arrays(arr3,arr4);

        for (int i: arr5) {
            System.out.print(i + " ");
        }

        int[] array = {1,2,3,5};
        System.out.println(pivotIndex(array));

        System.out.println(doOverlap(new coor(1,5), new coor(3,3), new coor(2,4), new coor(4,3)));
        System.out.println(allUnique("abcda"));
    }


}

class coor {
    int x;
    int y;

    public coor(int x, int y) {
        this.x = x;
        this.y = y;
    }
}