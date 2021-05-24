import java.util.*;

public class threepairSumtarget {
    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int seggrigate(int arr[], int pidx, int si, int ei) {
        swap(arr, pidx, ei);

        int itr = si, p = si - 1;

        while (itr <= ei) {
            if (arr[itr] <= arr[ei])
                swap(arr, ++p, itr);
            itr++;
        }

        return p;
    }

    public static void quicksort(int arr[], int si, int ei) {
        if (si > ei)
            return;

        int pidx = ei;
        int p = seggrigate(arr, pidx, si, ei);

        quicksort(arr, si, p - 1);
        quicksort(arr, p + 1, ei);
    }

    public static ArrayList<int[]> targetsum2(int arr[], int si, int ei, int tar) {
        ArrayList<int[]> ans = new ArrayList<>();
        while (si < ei) {
            if (arr[si] + arr[ei] == tar) {
                ans.add(new int[] { arr[si], arr[ei] });
                si++;
                ei--;
            } else if (arr[si] + arr[ei] > tar)
                ei--;
            else
                si++;
        }
        return ans;
    }

    public static void targetthreesum(int arr[], int tar) {
        quicksort(arr, 0, arr.length - 1); // Arrays.sot(arr)
        ArrayList<int[]> ans = new ArrayList<>();
        for( int i =0; i<=arr.length-1; i++){
         ArrayList<int[]> res = targetsum2(arr,i+1,arr.length -1, tar - arr[i]);
         for(int []a:res)
         {
             ans.add(new int[]{arr[i],a[0],a[1]});
         }
        }

        for(int a[]:ans)
        {
            System.out.println(a[0]+","+a[1]+", "+a[2]);
        }
    }

    public static void main(String[] args) {
        int arr[] = { -2, -3, 2, 5, 3, 9, 8, 7, 19, 15, 10 };
        targetthreesum(arr, 25);
    }
}
