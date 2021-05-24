public class Sorting {

    public static void sort01(int arr[]) {

        int p = -1, itr = 0;
        while (itr < arr.length) {
            if (arr[itr] == 0) {
                swap(++p, itr, arr);
            }
            itr++;
        }

        for (int a : arr) {
            System.out.println(a);
        }
    }

    public static void sort012(int arr[]) {
        int p = -1, itr = 0, k = arr.length - 1;

        while (itr < k) {
            if (arr[itr] == 0) {
                swap(++p, itr, arr);
                itr++;
            } else if (arr[itr] == 2) {
                swap(itr, k, arr);
                k--;

            } else {
                itr++;
            }
        }

        for (int a : arr) {
            System.out.println(a);
        }
    }

    public static void sortArray(int a[]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    swap(i, j, a);
                }
            }
        }
    }

    public static int[] mergeTwoSortedArray(int arr1[], int arr2[]) {
        int res[] = new int[arr1.length + arr2.length];
        // sortArray(arr1);
        // sortArray(arr2);
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                res[k] = arr1[i];
                i++;
            } else {
                res[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1.length) {
            res[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length) {
            res[k] = arr2[j];
            j++;
            k++;
        }

        return res;
    }

    public static void swap(int i, int j, int arr[]) {
        // System.out.println("Swapping index " + i + " and index " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubblesort_opt(int arr[]) {
        boolean flag = false;
        for (int li = arr.length - 1; li > 0; li--) {
            for (int i = 1; i <= li; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(i, i - 1, arr);
                    flag = true;
                }
            }

            if (!flag)
                break;

        }

        for (int a : arr)
            System.out.println(a);
    }

    public static void selection_sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i])
                    swap(i, j, arr);
            }
        }

        for (int a : arr)
            System.out.println(a);
    }

    public static void insertion_sort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    swap(j + 1, j, arr);
                } else
                    break;
            }
        }

        for (int a : arr)
            System.out.println(a);
    }

    public static void partition_Array(int arr[], int pivot) {
        // int fi = 0, li = arr.length - 1;

        // while (fi < li) {
        //     if (arr[fi] == pivot) {
        //         swap(fi, li, arr);
        //     } else if (arr[fi] > pivot) {

        //         swap(fi, li, arr);
        //         li--;

        //     } else {
        //         fi++;
        //     }
        // }

        int i = 0;
        int j =0;
        while(i<arr.length)
        {
            if(arr[i]>pivot)
            {
                i++;
            }else
            {
                swap(i,j,arr);
                i++;
                j++;
            }
        }

        for (int a : arr)
            System.out.println(a);
    }

    

    public static int PartitionAnArray2(int[] arr, int data) {
        int n = arr.length, p = n - 1, itr = 0;
        while (itr <= p) {
            if (arr[itr] <= data)
                itr++;
            else
                swap( p--, itr,arr);
        }

        return p;
    }

    public static void PartitionOverPivot(int[] arr, int pivotIdx) {
        int n = arr.length;
        swap( pivotIdx, n - 1,arr);

        int itr = 0, p = -1, li = n - 1;
        while (itr < li) {
            if (arr[itr] <= arr[li])
                swap( itr, ++p,arr);

            itr++;
        }

        swap( ++p, li,arr);
    }

    public static void main(String[] args) {
        // int arr1[] = { -2, 5, 9, 11 };
        // int arr2[] = { 4, 5, 6, 8 };
        int arr3[] = { 7,-2 , 4 ,1 ,3};
        // sort012(arr);
        // int res[]=mergeTwoSortedArray(arr1,arr2);

        // for( int a: res)
        // {
        // System.out.println(a);
        // }

        partition_Array(arr3, 2);

    }
}