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

    public static int[] sortwithrecur(int arr[], int si, int ei, int res[],int idx) {
        if (si == ei){
            res[idx++] = arr[si];
            return res;
        }
        int num = arr[si];
        int rec[] = sortwithrecur(arr, si + 1, ei, res,idx);
        int itr =0;
        while(itr < rec.length)
        {
             if( rec[itr] > num)
             {
                 
             }
        }

        return res;

    }

    public static void main(String[] args) {
        // int arr[] = { 2, 2, -5, 7, 2, 4, 1, 0, 8, 10 };
        int arr[] = { -2, -3, 8, 1, 5 };
        int res[] = sortwithrecur(arr, 0, arr.length - 1, new int[arr.length],0);
        for (int e : res)
            System.out.print(e + " ");
    }
}
