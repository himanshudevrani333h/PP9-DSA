import java.util.PriorityQueue;

public class questions {

    public static void kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k)
                pq.remove();

        }

        while (pq.size() > 0)
            System.out.println(pq.remove());
    }

    // GFG
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k)
                pq.remove();

        }

        return pq.peek();
    }

    public static void SortKsortedArray(int arr[][], int k) {
        int n = arr.length, m = arr[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int r1 = a / m, c1 = a % m, r2 = b / m, c2 = b % m;
            return arr[r1][c1] - arr[r2][c2];
        });

        for (int i = 0; i < n; i++) {
            pq.add(i * m + 0);
        }
        int res[] = new int[n * m];
        int indx = 0;
        while (pq.size() > 0) {
            int pqindex = pq.remove();
            int row = pqindex / m, col = pqindex % m;
            res[indx++] = arr[row][col];

            col++;
            if (col < m)
                pq.add(row * m + col);
        }

        for (int e : res)
            System.out.println(e);
    }
}
