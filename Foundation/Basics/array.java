import java.util.*;

public class array {
    static Scanner sc = new Scanner(System.in);

    public static void display(int res[]) {
        // for( int ele:res)
        // {
        // System.out.print(ele+"\t");
        // }
        int tpt = 0;
        while (res[tpt] == 0) {
            tpt += 1;
        }
        for (int i = tpt; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static int max(int[] arr) {
        int max = -(int) 1e9; // Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int min(int[] arr) {
        int min = (int) 1e9; // Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int find(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == n) {
                return i;
            }
        }
        return -1;
    }

    public static int span(int[] n) {
        int max = max(n);
        int min = min(n);
        int span = max - min;
        return span;
    }

    public static int[] reversearray(int[] arr) {
        int res[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i];
            res[idx] = i;

        }
        return res;
    }

    public static int[] inversearray(int[] arr) {
        int res[] = new int[arr.length];
        int idx = 0;
        for (int i = arr.length - 1; i >= 0; i--) {

            res[idx] = arr[i];
            idx++;

        }
        return res;
    }

    public static int[] rotatearray(int arr[], int r) {
        int res[] = new int[arr.length];
        r = r % arr.length;
        if (r < 0)
            r += arr.length;
        int idx = 0;
        for (int i = arr.length - r; i < arr.length; i++) {
            res[idx] = arr[i];
            idx++;
        }
        for (int i = 0; i <= arr.length - 1 - r; i++) {
            res[idx] = arr[i];
            idx++;
        }
        return res;
    }

    public static int[] rotatearray2(int arr[], int r) {
        int res[] = new int[arr.length];
        r = r % arr.length;
        if (r < 0)
            r += arr.length;

        for (int i = 0; i < arr.length; i++) {
            int idx = (i + r) % r;
            res[idx] = arr[i];
        }

        return res;
    }

    public static int[] sumofarray(int a[], int b[]) {
        int alength = a.length;
        int blength = b.length;
        int k = Math.max(alength, blength) + 1;
        int res[] = new int[k];
        int i = alength - 1, j = blength - 1, p = k - 1, carry = 0;
        while (p >= 0) {
            int sum = carry + (i >= 0 ? a[i] : 0) + (j >= 0 ? b[j] : 0);
            int val = sum % 10;
            res[p] = val;
            carry = sum / 10;
            i--;
            j--;
            p--;
        }
        return res;
    }

    public static int[] Diffofarray(int a[], int b[]) {
        int alength = a.length;
        int blength = b.length;
        int k = Math.max(alength, blength) + 1;
        int res[] = new int[k];
        int i = alength - 1, j = blength - 1, p = k - 1, borrow = 0;
        while (p >= 0) {

            int diff = (borrow + b[j]) - (i >= 0 ? a[i] : 0);
            if (diff < 0) {
                diff += 10;
                borrow = -1;
            } else {
                borrow = 0;
            }
            res[k] = diff;

            i--;
            j--;
            p--;
        }
        return res;
    }

    public static void subarray(int a[]) {
        int j = 0, len = a.length;
        while (j < len) {
            for (int i = 0; i < len; i++) {
                for (int k = j; k <= i; k++) {
                    System.out.print(a[k] + "\t");
                }
                if (i >= j) {
                    System.out.println();
                }
            }
            j++;
        }

    }

    // bahut ghatiya working code
    public static void substring1(int a[]) {
        int tcombination = (int) Math.pow(2, a.length);
        int temparrrayint[] = new int[a.length];
        String temparray[] = new String[a.length];
        int oidx = temparrrayint.length - 1;
        for (int i = 0; i < tcombination; i++) {
            int tempidx = oidx;
            int tempstep = i;
            for (int j = 0; j < temparrrayint.length; j++) {
                int val = tempstep % 2;
                temparrrayint[tempidx] = val;
                tempidx--;
                tempstep /= 2;
            }

            for (int k = 0; k < temparrrayint.length; k++) {
                if (temparrrayint[k] == 0) {
                    temparray[k] = "-";
                } else {
                    temparray[k] = Integer.toString(a[k]);
                }
            }
            for (String str : temparray) {
                System.out.print(str + " ");
            }
            for (int m = 0; m < temparray.length; m++) {
                temparray[m] = "";
            }
            for (int l = 0; l < temparrrayint.length; l++) {
                temparrrayint[l] = 0;
            }
            System.out.println();
        }

    }

    public static void substring(int a[]) {
        String str = "";
        for (int i = 0; i < (int) Math.pow(2, a.length); i++) {
            int Forbinary = i;
            for (int j = a.length - 1; j >= 0; j--) {
                int r = Forbinary % 2;
                Forbinary /= 2;
                if (r == 0) {
                    str = "-" + "\t" + str;
                } else {
                    str = a[j] + "\t" + str;
                }
            }
            System.out.println(str);
            str = "";
        }
    }

    // first index and last index
    public static int fidx(int a[], int tar) {
        int si = 0;
        int li = a.length - 1;
        while (si <= li) {
            int mid = (si + li) / 2;
            if (a[mid] == tar) {
                if (mid - 1 >= 0 && a[mid - 1] == tar) {
                    li = mid - 1;
                } else
                    return mid;
            } else if (a[mid] > tar) {
                li = mid - 1;
            } else
                si = mid + 1;
        }
        return -1;
    }

    public static int Lidx(int a[], int tar) {
        int si = 0;
        int li = a.length - 1;
        while (si <= li) {
            int mid = (si + li) / 2;
            if (a[mid] == tar) {
                if (mid + 1 < a.length && a[mid + 1] == tar) {
                    si = mid + 1;
                } else
                    return mid;
            } else if (a[mid] > tar) {
                li = mid - 1;
            } else
                si = mid + 1;
        }
        return -1;
    }

    public static void FindexLindex(int a[], int target) {
        int fi = fidx(a, target);
        if (fi == -1) {
            System.out.println("data not found!");
            return;
        }
        int li = Lidx(a, target);

        System.out.println(fi);
        System.out.println(li);
    }

    public static void brokenEco(int a[], int data) {
        int si = 0, li = a.length - 1;
        int floor = 0, ceiling = 0;
        while (si <= li) {
            int mid = (si + li) / 2;
            if (a[mid] > data) {
                floor = mid;
                li = mid - 1;
            } else if (a[mid] < data) {
                ceiling = mid;
                si = mid + 1;
            } else {
                floor = mid;
                ceiling = mid;
                break;
            }
        }
        System.out.println(floor);
        System.out.println(ceiling);
    }

    public static void main(String[] args) {
        int n = sc.nextInt();

        // int rot = sc.nextInt();
        // int f = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int tar = sc.nextInt();
        FindexLindex(arr, tar);

        // int m = sc.nextInt();
        // int arr2[] = new int[m];
        // for( int i =0; i<m; i++)
        // {
        // arr2[i] = sc.nextInt();
        // }
        // int res[] = reversearray(arr);
        // int res[] = rotatearray2(arr, rot);
        // int res[]= sumofarray(arr, arr2);
        // int res[] = Diffofarray(arr, arr2);
        // display(res);

        // int span = span(arr);
        // System.out.println(span);
        // int max = max( arr);
        // int min = min(arr);
        // int found= find(arr, f);
        // System.out.println(max);
        // System.out.println(min);
        // System.out.println(found);

    }
}
