import java.util.Arrays;
import java.util.LinkedList;

public class largerNototheright {
    public static class Stack {
        LinkedList<Integer> st = new LinkedList<>();

        public void push(int data) {
            this.st.addFirst(data);
        }

        public int peek() {
            return this.st.getFirst();
        }

        public int pop() {
            return this.st.removeFirst();
        }

        public int size(){
            return this.st.size();
        }
    }
    public static int[] solve2(int arr[]){
        int res[] = new int[arr.length];
        Stack st = new Stack();
        Arrays.fill(res, -1);
        for( int i =0; i<arr.length; i++){
            while( st.size() != 0 && arr[st.peek()] < arr[i])
             res[st.pop()] = arr[i];
            
             st.push(i);
        }
        return res;
    }
    public static int[] solve(int[] arr) {

        int res[] = new int[arr.length];
        Stack st = new Stack();
        st.push(arr[arr.length - 1]);
        int idx = arr.length - 1;
        res[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() != 0 && arr[i] > st.peek()) {
                st.pop();
            }
            if (st.size() == 0) {
                st.push(arr[i]);
                res[--idx] = -1;
            } else {
                res[--idx] = st.peek();
                st.push(arr[i]);
            }

        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = { 5, 3, 8, -2, 7 };
        int res[] = solve(arr);
        for( int e: res) System.out.println(e);
    }
}
