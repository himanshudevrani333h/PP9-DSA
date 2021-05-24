
public class RecursionWithArray {
    public static void printarray(int a[], int i) {
        if (i >= a.length) {
            return;
        }

        System.out.println(a[i]);
        printarray(a, i + 1);

    }

    public static void printreverse(int a[], int i) {
        if (i < 0) {
            return;
        }

        System.out.println(a[i]);
        printreverse(a, i - 1);

    }

    public static int printmax(int a[], int i) {
        if (i >= a.length) {
            return -1;
        }
        int maxval = -(int) 1e9;
        int max = printmax(a, i + 1);
        if (max > maxval)
            maxval = max;
        return a[i] > maxval ? a[i] : maxval;

    }

    public static int maximum(int[] a, int i) {
        if (i >= a.length) {
            return -1;
        }
        int maxval = -(int) 1e9;
        int max = printmax(a, i + 1);
        if (max > maxval)
            maxval = max;
        return a[i] > maxval ? a[i] : maxval;
    }

    public static int minimum(int[] a, int i) {
        if (i >= a.length) {
            return -1;
        }
        int minval = (int) 1e9;
        int min = printmax(a, i + 1);
        if (min < minval)
            minval = min;
        return a[i] < minval ? a[i] : minval;
    }

    public static boolean findData(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return false;
        if (arr[idx] != data) {
            findData(arr, idx + 1, data);
        } else
            return true;

        return false;
    }

    public static int firstIdx(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        if (arr[idx] == data)
            return idx;
        return firstIdx(arr, idx + 1, data);
    }

    public static int lastIdx(int[] arr, int idx, int data) {
        if (idx == arr.length)
            return -1;
        int recAns = lastIdx(arr, idx + 1, data);
        if (recAns != -1)
            return recAns;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allIndex(int[] arr, int idx, int data, int count) { 
        if (idx == arr.length) {
            return new int[count];
        }

        if (arr[idx] == data)
            count++;
        int[] recAns = allIndex(arr, idx + 1, data, count);
        if (arr[idx] == data)
            recAns[count - 1] = idx;

        return recAns;
    }

    public static boolean firstAndLastIdx(int[] arr, int idx, int data, int[] ans) {
        if (idx == arr.length)
            return false;
        if (arr[idx] == data)
            ans[0] = idx;

        boolean res = firstAndLastIdx(arr, idx + 1, data, ans);
        if (res)
            return true;

        if (arr[idx] == data) {
            ans[1] = idx;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 12, 3, 2, 4, 34, 3, 4, 2, 2, 76 };
        // printarray(arr, 0);
        // printreverse(arr, arr.length -1);
        // int max= printmax(arr,0);
        // System.out.println(max);
        // System.out.println(maximum(arr, 0));
        // System.out.println(minimum(arr, 0));
        System.out.println(findData(arr, 0, 2));
        // System.out.println(firstIdx(arr, 0, 2));
        // System.out.println(lastIdx(arr, 0, 2));
    }
}
