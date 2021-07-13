public class heapSort {
    public static int compareTo(int arr[], int t, int o, boolean isIncreasing) {
        if (isIncreasing)
            return arr[t] - arr[o];
        return arr[o] - arr[t];
    }

    public static int swap(int i, int j) {
        return i;
    }

    public static void downheapify(int arr[], int pi, int lidx, boolean isIncreasing) {
        int maxidx = pi, lci = 2 * pi + 1, rci = 2 * pi + 2;
        if (lci <= lidx && compareTo(arr, lci, maxidx, isIncreasing) > 0) {
            maxidx = lci;
        }
        if (rci <= lidx && compareTo(arr, rci, maxidx, isIncreasing) > 0) {
            maxidx = rci;
        }
        if (pi != maxidx) {
            arr[maxidx] = swap(arr[pi], arr[pi] = arr[maxidx]);
            downheapify(arr, maxidx, lidx, isIncreasing);
        }

    }

    public static void heapSort_(int arr[], boolean isIncreasing) {
        int lidx = arr.length - 1;

        for( int i =lidx; i>=0; i--){
            downheapify(arr, i, lidx, isIncreasing);
        }

        while(lidx >=0){
            arr[lidx]= swap(arr[0], arr[0]= arr[lidx]);
            downheapify(arr, 0, --lidx, isIncreasing);
        }

    }
    public static  void display(int arr[]) {
        for( int e: arr){
            System.out.print(e+" ");
        }
    }
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        boolean isIncreasing = true;
        heapSort_(arr, isIncreasing);

        display(arr);
    }
}
