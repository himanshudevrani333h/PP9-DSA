import java.util.Arrays;
import java.util.LinkedList;

public class NGE {

    public static int[] NGEonRight(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n - 1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);    
        }

        return ans;
    }


    public static int[] NSEonRight(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n - 1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);    
        }

        return ans;
    }

    public static int[] NGEonLeft(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n-1; i >=0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);    
        }

        return ans;
    }


    public static int[] NSEonLeft(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n-1; i >=0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);    
        }

        return ans;
    }

    // 503. Next Greater Element II
    public static int[] nextGreaterElements(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i <n * 2; i++) {
         
         
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i%n])
                ans[st.removeFirst()] = arr[i%n];
            
            
           if(i < n)
            st.addFirst(i);    
        }

        return ans;
    }

    
    public static void main(String[] args) {
        int arr[] ={2,1,3,1,2,4,5,9,6};
        int ans [] = NSEonRight(arr);
        for(int e : ans) System.out.print(e+" ");
    }

}