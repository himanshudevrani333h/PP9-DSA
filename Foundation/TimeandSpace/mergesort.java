public class mergesort {

    public static int[] mergeSort(int arr[], int si, int ei) {
        if (si == ei) {
            int bres[] = new int[1];
            bres[0] = arr[si];
            return bres;
        }

        int mid = (si + ei) / 2;
        int res1[] = mergeSort(arr, si, mid);
        int res2[] = mergeSort(arr, mid + 1, ei);
        int res[] = mergetwosortedarray(res1, res2);
        return res;
    }

    public static int[] mergetwosortedarray(int a1[], int a2[]) {
        int res[] = new int[a1.length + a2.length];
        int k = 0, i = 0, j = 0;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                res[k] = a1[i];
                k++;
                i++;
            } else {
                res[k] = a2[j];
                k++;
                j++;
            }
        }

        while (i < a1.length) {
            res[k] = a1[i];
            k++;
            i++;
        }

        while (j < a2.length) {
            res[k] = a2[j];
            k++;
            j++;
        }

        return res;
    }

    public static void main(String[] args) {
        int arr[] = { 7, -2, 4, 1, 3 };
        int res[] =mergeSort(arr,0,arr.length -1);
        for( int e: res) System.err.print(e+" ");
    }

}
