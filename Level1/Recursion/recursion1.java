import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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

        return power(a, b - 1) * a;
    }

    // O(logn)
    public static int powerBtr(int a, int b) {
        if (b == 0)
            return 1;

        int recans = power(a, b / 2);
        recans *= recans;

        return b % 2 == 0 ? recans : recans * a;
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
            return -(int) 1e9;
        }
        int recans = maximum(arr, idx + 1);

        return Math.max(arr[idx], recans);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx >= arr.length) {
            return (int) 1e9;
        }
        int recans = minimum(arr, idx + 1);

        return Math.min(arr[idx], recans);
    }

    public static boolean find(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return false;

        return arr[idx] == data || find(arr, data, idx + 1);
    }

    public static int firstIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length)
            return -1;

        return arr[idx] == data ? idx : firstIndex(arr, data, idx + 1);
    }

    public static int lastIndex(int[] arr, int data, int idx) {
        if (idx >= arr.length - 1)
            return -1;

        int recans = lastIndex(arr, data, idx + 1);
        if (recans != -1)
            return recans;

        return arr[idx] == data ? idx : -1;
    }

    public static int[] allindex(int[] arr, int data, int idxofarr, int idxofres) {
        if (idxofarr >= arr.length) {
            return new int[idxofres];
        }

        if (arr[idxofarr] == data) {
            idxofres++;
        }
        int recAns[] = allindex(arr, data, idxofarr + 1, idxofres);
        if (arr[idxofarr] == data) {
            recAns[idxofres - 1] = idxofarr;
        }

        return recAns;
    }

    public static ArrayList<String> subsequnce(String str, int idx) {

        if (idx == str.length()) {
            ArrayList<String> baseres = new ArrayList<>();
            baseres.add("");
            return baseres;
        }
        char ch = str.charAt(idx);
        ArrayList<String> recRes = subsequnce(str, idx + 1);
        ArrayList<String> myans = new ArrayList<>(recRes);
        for (String a : recRes) {
            myans.add(ch + a);
        }

        return myans;
    }

    public static int subsequnce(String str, int idx, String asf, ArrayList<String> ans) {

        if (idx == str.length()) {
            ans.add(asf);
            return 1;
        }

        int count = 0;
        count += subsequnce(str, idx + 1, asf + str.charAt(idx), ans);
        count += subsequnce(str, idx + 1, asf, ans);

        return count;
    }

    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int nokiaKeyPad(String str, String ans, int idx) {
        if (idx >= str.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        char ch = str.charAt(idx);
        int key = ch - '0';
        String keyvalue = nokiaKeys[key];
        for (int i = 0; i < keyvalue.length(); i++)
            count += nokiaKeyPad(str, ans + keyvalue.charAt(i), idx + 1);
        return count;

    }

    public static ArrayList<String> nokiaKeyPad_(String str, int idx) {
        if (idx >= str.length()) {
            ArrayList<String> baseres = new ArrayList<>();
            baseres.add("");
            return baseres;
        }

        char ch = str.charAt(idx);
        int key = ch - '0';
        String keyvalue = nokiaKeys[key];

        ArrayList<String> recAns = nokiaKeyPad_(str, idx + 1);
        ArrayList<String> myans = new ArrayList<>();
        for (int i = 0; i < keyvalue.length(); i++) {
            for (String s : recAns) {
                myans.add(keyvalue.charAt(i) + s);
            }
        }

        return myans;
    }

    public static int stairPath(int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            System.out.println(psf);
            return 1;
        }
        int count = 0;

        for (int i = 1; i <= 3 && n - i >= 0; i++) {
            count += stairPath(n - i, psf + i, ans);
        }

        return count;
    }

    public static ArrayList<String> stairPath_(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myans = new ArrayList<>();
        for (int i = 1; i <= 3 && n - i >= 0; i++) {
            ArrayList<String> recAns = stairPath_(n - i);
            for (String s : recAns)
                myans.add(s + i);
        }
        // for (String s : res1)
        // myans.add(1 + s);
        // for (String s : res2)
        // myans.add(2 + s);
        // for (String s : res3)
        // myans.add(3 + s);

        return myans;
    }

    public static int boardPath(int n, String psf, ArrayList<String> ans) {
        if (n == 0) {
            ans.add(psf);
            System.out.println(psf);
            return 1;
        }
        int count = 0;

        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath(n - dice, psf + dice, ans);
        }

        return count;
    }

    public static int boardPath(int[] arr, int n, String psf, String ans) {
        if (n == 0) {

            System.out.println(psf);
            return 1;
        }
        int count = 0;

        for (int i = 1; i <= 6 && n - i >= 0; i++) {
            count += boardPath(arr, n - arr[i], psf + arr[i], ans);
        }

        return count;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sc == ec && sr == er) {
            ans.add(psf);
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int i = 1; sc + i <= ec; i++) {
            count += mazePath_HVD_multi(sr, sc + i, er, ec, psf + "h" + i, ans);
        }
        for (int i = 1; sr + i <= er; i++) {
            count += mazePath_HVD_multi(sr + i, sc, er, ec, psf + "v" + i, ans);
        }
        for (int i = 1; sc + i <= ec && sr + i <= er; i++) {
            count += mazePath_HVD_multi(sr + i, sc + i, er, ec, psf + "d" + i, ans);
        }
        return count;

    }

    public static ArrayList<String> mazePath_HVD_multi_(int sr, int sc, int er, int ec) {
        if (sc == ec && sr == er) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;

        }

        ArrayList<String> res = new ArrayList<>();
        for (int i = 1; sc + i <= ec; i++) {
            ArrayList<String> res1 = mazePath_HVD_multi_(sr, sc + i, er, ec);
            for (String s : res1) {
                res.add("h" + i + s);
            }
        }
        for (int i = 1; sr + i <= er; i++) {
            ArrayList<String> res2 = mazePath_HVD_multi_(sr + i, sc, er, ec);
            for (String s : res2) {
                res.add("v" + i + s);
            }
        }
        for (int i = 1; sc + i <= ec && sr + i <= er; i++) {
            ArrayList<String> res3 = mazePath_HVD_multi_(sr + i, sc + i, er, ec);
            for (String s : res3) {
                res.add("d" + i + s);
            }
        }
        return res;

    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec && sr <= er)
            count += mazePath_HVD(sr, sc + 1, er, ec, psf + "h", ans);
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc + 1, er, ec, psf + "d", ans);
        if (sr + 1 <= er)
            count += mazePath_HVD(sr + 1, sc, er, ec, psf + "v", ans);

        return count;
    }

    public static ArrayList<String> mazePath_HVD_(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        ArrayList<String> res = new ArrayList<>();
        if (sc + 1 <= ec && sr <= er) {
            ArrayList<String> res1 = mazePath_HVD_(sr, sc + 1, er, ec);
            for (String s : res1) {
                res.add("h" + s);
            }
        }
        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> res2 = mazePath_HVD_(sr + 1, sc + 1, er, ec);
            for (String s : res2) {
                res.add("d" + s);
            }
        }
        if (sr + 1 <= er) {
            ArrayList<String> res3 = mazePath_HVD_(sr + 1, sc, er, ec);
            for (String s : res3) {
                res.add("v" + s);
            }
        }

        return res;
    }

    public static int mazePath_HVD(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath_HVD(r, c, er, ec, psf + dirS[d], ans, dir, dirS);
            }
        }

        return count;
    }

    public static int mazePath_HVD_multi(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
            String[] dirS) {
        if (sr == er && sc == ec) {
            ans.add(psf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePath_HVD_multi(r, c, er, ec, psf + dirS[d] + rad, ans, dir, dirS);
                } else
                    break;
            }
        }

        return count;
    }

    // public static ArrayList<String> mazePath_HVD_multi_(int sr, int sc, int er, int ec, String psf, ArrayList<String> ans, int[][] dir,
    //         String[] dirS) {
    //     if (sr == er && sc == ec) {
    //         ans.add(psf);
    //         return 1;
    //     }

    //     int count = 0;
    //     for (int d = 0; d < dir.length; d++) {
    //         for (int rad = 1; rad <= Math.max(er, ec); rad++) {
    //             int r = sr + rad * dir[d][0];
    //             int c = sc + rad * dir[d][1];

    //             if (r >= 0 && c >= 0 && r <= er && c <= ec) {
    //                 count += mazePath_HVD_multi(r, c, er, ec, psf + dirS[d] + rad, ans, dir, dirS);
    //             } else
    //                 break;
    //         }
    //     }

    //     return count;
    // }

    public static void mazePath() {
        int[][] dir = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
        String[] dirS = { "H", "V", "D" };

        ArrayList<String> ans = new ArrayList<>();
        System.out.println(mazePath_HVD_multi(0, 0, 2, 2, "", ans, dir, dirS));

        System.out.println(ans);
    }
 
    // Rat in maze path---------https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1#---------------GFG
    public static void findpath_(int arr[][], int sr, int sc,String psf, int dir[][], String[]dirS,ArrayList<String> res){
        if( sr == arr.length -1  && sc == arr[0].length -1){
           res.add(psf);
           return;
        } 
        arr[sr][sc]= 0;
        for( int d =0; d< dir.length; d++){
            int row = sr + dir[d][0];
            int col = sc + dir[d][1];
            
            if(row >=0 && col >=0 && row< arr.length && col< arr[0].length && arr[row][col] == 1){
                findpath_(arr,row,col,psf + dirS[d],dir,dirS, res);
            }
        }
         arr[sr][sc]= 1;
        
        return;
     }
     public static ArrayList<String> findPath(int[][] m, int n) {
      ArrayList<String> res = new ArrayList<>();
      if (n == 0 || m[n - 1][n - 1] == 0 || m[0][0] == 0)
             return res;
       int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
       String[] dirS = { "D", "U", "R", "L" };
       
       findpath_(m,0,0,"",dir,dirS,res);
       Collections.sort(res);
       return res;
     }
 // Rat in maze path------------------------GFG

//  Special Matrix-----------https://practice.geeksforgeeks.org/problems/special-matrix4201/1#------------------------------------
 public int fway(int sr, int sc, int[][] arr, int[][] dir) {

    int n = arr.length, m = arr[0].length;
    if (sr == n - 1 && sc == m - 1) {
        return 1;
    }

    arr[sr][sc] = 1; // block
    int count = 0;
    for (int d = 0; d < dir.length; d++) {
        int r = sr + dir[d][0];
        int c = sc + dir[d][1];

        if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] == 0) {
            count = (count + fway(r, c, arr, dir) ) ;
        }
    }

    arr[sr][sc] = 0; // free
    return count;
}

public int FindWays(int n, int m, int[][] blocked_cells) {
    int[][] arr = new int[n][m];
    for (int[] cell : blocked_cells) {
        int i = cell[0] - 1;
        int j = cell[1] - 1;

        arr[i][j] = 1;
    }

    if (arr[n - 1][m - 1] == 1 || arr[0][0] == 1)
        return 0;

    int[][] dir = { { 0, 1 }, { 1, 0 } };
    int count = fway(0, 0, arr, dir);
    return count;
}

//  Special Matrix-----------------------------------------------

//leetcode max gold problem recursion 1219______________________________________________
public int getMaximumGold(int[][]grid, int sr, int sc, int dir[][], boolean visited[][]){
    visited[sr][sc] = true;
    
    int max =0;
    for(int d=0; d< dir.length; d++){
    
        int row = sr + dir[d][0];
        int col = sc + dir[d][1];
        
        if( row>=0 && col>=0 && row < grid.length && col< grid[0].length && grid[row][col] != 0 && !visited[row][col]){
          int recres =  getMaximumGold(grid,row,col,dir,visited);
            if( recres > max){
                max = recres;
            }
                }
          }
    visited[sr][sc] = false;
    return max + grid[sr][sc];
}

public int getMaximumGold(int[][] grid) {
    boolean visited[][] = new boolean[grid.length][grid[0].length];
    int max = -(int)1e9;
    int dir[][] = {{0,1},{0,-1},{-1,0},{1,0}};
    for( int i =0; i<grid.length; i++){
        for( int j =0; j<grid[0].length; j++){
            
            if(grid[i][j] != 0 && !visited[i][j]){
               max = Math.max(max,getMaximumGold(grid,i,j,dir,visited));
            }
               
        }
    }
    return max;
}
//leetcode max gold problem recursion 1219______________________________________________
   
    public static int c = 1;

    public static void friendsPairing(int count, int n, boolean[] used, String asf) {
        if (count == n) {
            System.out.println(c++ + "." + asf);
            return;
        }

        int fup = 0; // first un used person
        while (fup <= n && used[fup])
            fup++;

        used[fup] = true;
        friendsPairing(count + 1, n, used, asf + "(" + fup + ") ");

        for (int pp = fup + 1; pp <= n; pp++) { // pair person
            if (!used[pp]) {
                used[pp] = true;
                friendsPairing(count + 2, n, used, asf + "(" + fup + "," + pp + ") ");
                used[pp] = false;
            }
        }

        used[fup] = false;
    }

    public static int wordBreak(String str, String ans, HashSet<String> dict) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ei = 0; ei < str.length(); ei++) {
            String pword = str.substring(0, ei + 1);
            if (dict.contains(pword)) {
                count += wordBreak(str.substring(ei + 1), ans + pword + " ", dict);
            }
        }

        return count;
    }

    public static String max = "";

    public static void findMaximum(String str, int k, int ii) {
        if (k == 0)
            return;

        for (int i = ii; i < str.length(); i++) {
            int idx = -1;
            char maxCh = '0';
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) < str.charAt(j) && maxCh < str.charAt(j)) {
                    idx = j;
                    maxCh = str.charAt(j);
                }
            }

            if (idx != -1) {
                for (int j = idx; j < str.length(); j++) {
                    if (str.charAt(j) == maxCh) {
                        String temp = swap(str, i, j);
                        if (isGreater(temp, max))
                            max = temp;
                        findMaximum(temp, k - 1, i + 1);
                    }
                }
            }
        }
    }

    public static boolean isGreater(String temp, String str) {
        if (temp.length() > str.length())
            return true;
        else if (temp.length() < str.length())
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (temp.charAt(i) > str.charAt(i))
                return true;
            else if (temp.charAt(i) < str.charAt(i))
                return false;
        }

        return true;
    }

    public static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        char c1 = str.charAt(i);
        char c2 = str.charAt(j);

        sb.setCharAt(i, c2);
        sb.setCharAt(j, c1);

        return sb.toString();
    }

    public static void main(String[] args) {
        // recursionPattern(1, 6);
        // System.out.println( power(2,3));
        int arr[] = { 1, 2, 3, 2, 8, 4, 10, 8, 9 };
        // printArrayReverse(arr,arr.length-1);
        // System.out.println(maximum(arr,0));
        // System.out.println(lastIndex(arr, 8, 0));

        // int res[] = allindex(arr,8,0,0);
        // for(int a:res)System.out.print(a+" ");

        // ArrayList<String> res =subsequnce("abc",0);
        // System.out.println(res);
        // System.out.println(nokiaKeyPad("78", "",0));
        // ArrayList<String> res = new ArrayList<>();
        // ArrayList<String> res = stairPath_(3);
        // System.out.println(boardPath(3, "", res));
        // System.out.println(res);
        // System.out.println(mazePath_HVD_multi(1,1,2,2,"",res));
        // System.out.println(res);
        // ArrayList<String> res = mazePath_HVD_multi_(1,1,2,2);
        // System.out.println(mazePath_HVD(1,1,2,2,"",res));
        // System.out.println(res);
        ArrayList<String> res = mazePath_HVD_(1, 1, 2, 2);
        System.out.println(res);
    }

}