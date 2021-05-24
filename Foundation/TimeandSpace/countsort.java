public class countsort {
    public static void countsrt(int arr[]) {
        int val = -(int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > val)
                val = arr[i];
        }
        int freq[] = new int[val + 1];

        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]] += 1;
        }
        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                arr[idx++] = i;
            }
        }

        for (int e : arr)
            System.err.print(e + " ");
    }

    public static void countsrt2(int arr[]) {
        int minval = (int) 1e9;
        int maxval = -(int) 1e9;
        for (int e : arr) {
            maxval = Math.max(minval, e);
            minval = Math.min(minval, e);
        }

        int freq[] = new int[maxval - minval + 1];
        for (int e : arr) {
            freq[e - minval]++;
        }

        int idx = 0;
        for (int i = minval; i <= maxval; i++) {
            while (freq[i - minval]-- > 0) {
                arr[idx++] = i;
            }
        }

        for (int e : arr)
            System.err.print(e + " ");

    }

    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sortwithrecur(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int num = arr[si];
        sortwithrecur(arr, si + 1, ei);
        while (si < arr.length) {
            if (arr[si + 1] > num) {
                swap(arr, si + 1, si);
            }
        }
    }

    public static void main(String[] args) {
        // int arr[] = { 2, 2, -5, 7, 2, 4, 1, 0, 8, 10 };
        int arr[] = { -2, -3, 8, 1, 5 };
        sortwithrecur(arr, 0, arr.length - 1);
        for (int e : arr)
            System.out.print(e + " ");
    }
}
