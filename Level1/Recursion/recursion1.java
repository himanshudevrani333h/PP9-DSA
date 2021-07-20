import java.util.ArrayList;

public class recursion1 {
    public static void pppppp(int a) {
        System.out.println("I am Base case: " + a);
        return;
    }

    public static void ppppp(int a) {
        System.out.println("hi: " + a);
        pppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pppp(int a) {
        System.out.println("hi: " + a);
        ppppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void ppp(int a) {
        System.out.println("hi: " + a);
        pppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void pp(int a) {
        System.out.println("hi: " + a);
        ppp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void p(int a) {
        System.out.println("hi: " + a);
        pp(a + 1);
        System.out.println("Bye: " + a);
    }

    public static void recursionPattern(int a, int b) {
        if (a == b) {
            System.out.println("I am Base case: " + a);
            return;
        }

        System.out.println("Hi" + a);
        recursionPattern(a + 1, b);
        System.out.println("Bye" + a);
    }

    public static void printIncreasing(int a, int b) {
        if (a == b) {
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasing(a + 1, b);

    }

    public static void printDecreasing(int a, int b) {

        if (a == b) {
            System.out.println(a);
            return;
        }
        printDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void printIncreasingDecreasing(int a, int b) {

        if (a == b) {
            System.out.println(a);
            return;
        }
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);

    }

    public static void oddEven(int a, int b) {

        if (a == b) {
            if (a % 2 != 0)
                System.out.println(a);
            return;
        }
        if (a % 2 != 0)
            System.out.println(a);
        oddEven(a + 1, b);
        if (a % 2 == 0)
            System.out.println(a);

    }

    public static int factorial(int n) {

        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static int power(int a, int b) {
        if (b == 0) {
            return 1;
        }
       
        return power(a, b - 1) *a;
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
      if( b ==0) return 1;

      int recans = power(a, b/2);
      recans *= recans;

      return b%2 == 0? recans: recans * a;
    }

    public static void printArray(int[] arr, int idx) {
        if (idx == arr.length - 1) {
            System.out.println(arr[idx]);
            return;
        }

        System.out.println(arr[idx]);
        printArray(arr, ++idx);

    }

    public static void printArrayReverse(int[] arr, int idx) {
        if (idx >= 0) {
            return;
        }

        printArrayReverse(arr, ++idx);
        System.out.println(arr[idx]);

    }

    public static int maximum(int[] arr, int idx) {
        if (idx >= arr.length) {
            return -(int)1e9;
        }
        int recans = maximum(arr, idx+1);

        return Math.max(arr[idx],recans);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx >= arr.length) {
            return (int)1e9;
        }
        int recans = minimum(arr, idx+1);

        return Math.min(arr[idx],recans);
    }

    public static boolean find(int[] arr, int data, int idx) {
        if (idx>=arr.length)
            return false;

        return arr[idx] == data || find(arr, data, idx+1);
    }

    public static int firstIndex(int[] arr, int data, int idx) {
        if (idx>=arr.length)
            return -1;

        return arr[idx] == data ? idx : firstIndex(arr, data, idx+1);
    }

    public static int lastIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length-1)
            return -1;

        int recans= lastIndex(arr, data, idx+1);
        if (recans != -1)
            return recans;
        
        return arr[idx] == data ? idx: -1;
    }
    

    public static int[] allindex(int[]arr, int data, int idxofarr, int idxofres){
      if( idxofarr >=arr.length){
          return new int[idxofres];
      } 
    
      if( arr[idxofarr] == data){
          idxofres++;
      }
      int recAns[] = allindex(arr, data, idxofarr+1, idxofres);
      if(arr[idxofarr] == data){
          recAns[idxofres -1] = idxofarr;
      }

      return recAns;
    }
    
    public static ArrayList<String> subsequnce(String str,int idx) {
        
        if(idx == str.length()){
            ArrayList<String> baseres = new ArrayList<>();
            baseres.add("");
            return baseres;
        }
        char ch = str.charAt(idx);
        ArrayList<String> recRes = subsequnce(str, idx+1);
        ArrayList<String> myans = new ArrayList<>(recRes);
        for(String a: recRes){
            myans.add(ch+a);
        }
       
        return myans;
    }

    public static int subsequnce(String str,int idx,String asf,ArrayList<String> ans) {
    
      if(idx == str.length()){
          ans.add(asf);
          return 1;
      }

      int count = 0;
      count += subsequnce(str, idx+1, asf+ str.charAt(idx), ans); 
      count += subsequnce(str, idx+1, asf, ans); 
      
      return count;
    }

    
    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

    }

    public static int stairPath(int n, String psf,ArrayList<String> ans) {

    }

    public static int boardPath(int n, String psf,ArrayList<String> ans) {

    }

    public static int boardPath(int[] arr, int n, String ans) {

    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec,  String psf,ArrayList<String> ans) {
     

    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec,  String psf,ArrayList<String> ans) {
     

    }


    public static void main(String[] args) {
        // recursionPattern(1, 6);
        // System.out.println( power(2,3));
        int arr[] = { 1, 2, 3, 2, 8, 4,10,8, 9 };
        // printArrayReverse(arr,arr.length-1);
        // System.out.println(maximum(arr,0));
        // System.out.println(lastIndex(arr, 8, 0));

        // int res[] = allindex(arr,8,0,0);
        // for(int a:res)System.out.print(a+" ");
         
        ArrayList<String> res =subsequnce("abc",0);
        System.out.println(res);
    }

}