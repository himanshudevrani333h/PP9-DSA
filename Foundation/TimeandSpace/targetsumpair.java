
public class targetsumpair {

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionidx(int arr[], int pidx, int si, int ei) {
        swap(arr, pidx, ei);
        int itr = si, p = si - 1;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }

        return p;
    }

    public static void quicksort(int arr[], int si, int ei) {
        if (si > ei)
            return;

        int pidx = ei;
        int p = partitionidx(arr, pidx, si, ei);

        quicksort(arr, si, p - 1);
        quicksort(arr, p + 1, ei);
    }

    public static void targetsum(int arr[], int tar) {
        quicksort(arr, 0, arr.length - 1);

        int si = 0, ei = arr.length - 1;

        while (si < ei) {
            if (arr[si] + arr[ei] < tar)
                si++;
            else if (arr[si] + arr[ei] > tar)
                ei--;
            else {
                System.err.print(arr[si++] + ", " + arr[ei--]);
                System.err.println();
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = { 9, -48, 100, 43, 84, 74, 86, 34, -37, 60, -29, 44 };
        targetsum(arr, 160);
    }
}
