public class quicksort {

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionindex(int arr[], int si, int ei, int pividx) {
        swap(arr, si, pividx);
        int p = si - 1, itr = si;

        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }
        return p;
    }

    public static void quicksrt(int arr[], int si, int ei) {
        if (si >= ei)
            return;
        int pidx = ei;
        int p = partitionindex(arr, si, ei, pidx);
        quicksrt(arr, si, p - 1);
        quicksrt(arr, p + 1, ei);
    }

    public static void main(String[] args) {
        int arr[] = { 1, 8, 2, 5, 6, 4, -8, 4, 8, 55, 9, 6 };
        quicksrt(arr, 0, arr.length - 1);
        for (int e : arr)
            System.out.print(e + " ");
    }
}
