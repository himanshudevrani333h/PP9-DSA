public class quickselect {

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionarr(int arr[], int pidx,int si, int ei) {
        swap(arr,pidx,ei);
        int p = si - 1, itr = si;
        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, itr, ++p);
            itr++;
        }

        return p;
    }

    public static void qukslect(int arr[], int k, int si, int ei) {
        if (si >= ei)
            return;
        int pidx = ei;
        int p = partitionarr(arr, pidx,si, ei);
        
        if( p == k) return;
        if( p > k)
        {
            qukslect(arr, k, p+1, ei);
        }else{
            qukslect(arr, k, si, p-1);
        }

    }

    public static int qukslect( int arr[], int k)
    {
           int indx = arr.length - k;
           qukslect(arr, indx, 0, arr.length -1 );

           return arr[indx];
    }

    public static void main(String[] args) {
        int arr[] = { 7, -2, 4, 1, 3 };
        System.out.println(qukslect(arr, 3));
    }
}
