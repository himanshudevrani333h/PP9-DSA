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
        for (int i = n - 1; i >= 0; i--) {
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
        for (int i = n - 1; i >= 0; i--) {
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
        for (int i = 0; i < n * 2; i++) {

            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i % n])
                ans[st.removeFirst()] = arr[i % n];

            if (i < n)
                st.addFirst(i);
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1#
    public static int[] calculateSpan(int[] arr, int n) {
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i])
                st.removeFirst();

            ans[i] = i - st.getFirst();
            st.addFirst(i);
        }

        return ans;
    }

    // leetcode 901

    class StockSpanner {
        int day = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            // {index, value}
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();

            int span = day - st.getFirst()[0];
            st.addFirst(new int[] { day++, price });

            return span;
        }
    }

    // leetcode 20
    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0;
    }

    // leetcode 739. Daily Temperatures
    public int[] dailyTemperatures(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        int ans[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {

            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i]) {
                st.removeFirst();
            }

            if (st.getFirst() != -1)
                ans[i] = st.getFirst() - i;

            st.addFirst(i);
        }

        return ans;
    }

    // Leetcode 735. Asteroid Collision
    public int[] asteroidCollision(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            if (arr[i] > 0) {
                st.addFirst(i);
                continue;
            }

            while (st.size() != 0 && arr[st.getFirst()] > 0 && arr[st.getFirst()] < -arr[i])
                st.removeFirst();

            if (st.size() != 0 && arr[st.getFirst()] == -arr[i])
                st.removeFirst();
            else if (st.size() == 0 || arr[st.getFirst()] < 0)
                st.addFirst(i);
        }

        int ans[] = new int[st.size()];
        int idx = ans.length - 1;

        while (st.size() != 0) {
            ans[idx--] = arr[st.removeFirst()];
        }

        return ans;
    }

    // Leetcode 946. Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> st = new LinkedList<>();
        int idx = 0;
        for (int i = 0; i < pushed.length; i++) {
            st.addFirst(i);

            while (st.size() != 0 && pushed[st.getFirst()] == popped[idx]) {
                idx++;
                st.removeFirst();
            }

        }

        return st.size() == 0;
    }

    public static void main(String[] args) {
        int arr[] = { 2, 1, 3, 1, 2, 4, 5, 9, 6 };
        int ans[] = NSEonRight(arr);
        for (int e : ans)
            System.out.print(e + " ");
    }

}