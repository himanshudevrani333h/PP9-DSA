import java.util.*;

public class sudoku {
    public class pair {
        int r = 0;
        int c = 0;

        pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public boolean isValidToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++)
            if (board[r][i] - '0' == num)
                return false;

        // col
        for (int i = 0; i < 9; i++)
            if (board[i][c] - '0' == num)
                return false;

        // mat
        r = (r / 3) * 3;
        c = (c / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }

        return true;
    }

    public boolean solveSudoku(char[][] board, int idx, ArrayList<pair> arr) {
        if (idx == arr.size()) {
            return true;
        }

        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;

        for (int num = 1; num <= 9; num++) {
            if (isValidToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, idx + 1, arr))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<pair> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    arr.add(new pair(i, j)); // i * 9 + j
                }
            }
        }

        solveSudoku(board, 0, arr);
    }

    // Optimized sudoku using bits

    public static int[] row, col;
    public static int[][] mat;

    public boolean solveSudokuBits(char[][] board, int idx, ArrayList<pair> arr) {
        if (idx == arr.size()) {
            return true;
        }

        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;

        for (int num = 1; num <= 9; num++) {
            int mask = (1 << num);
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
                board[r][c] = (char) (num + '0');
                if (solveSudokuBits(board, idx + 1, arr))
                    return true;
                board[r][c] = '.';
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
            }
        }

        return false;
    }

    public void solveSudokuBits(char[][] board) {
        row = new int[9];
        col = new int[9];
        mat = new int[9][9];
        ArrayList<pair> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    arr.add(new pair(i, j)); // i * 9 + j
                } else {
                    int mask = (1 << (board[i][j] - '0'));
                    row[i] ^= mask;
                    col[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;
                }
            }
        }

        solveSudokuBits(board, 0, arr);
    }

    // 36
    public boolean isValidSudoku(char[][] board) {
        row = new int[9];
        col = new int[9];
        mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int mask = 1 << (board[i][j] - '0');
                    if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                        row[i] ^= mask;
                        col[j] ^= mask;
                        mat[i / 3][j / 3] ^= mask;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Crypto
    public static int stringToInt(String str, HashMap<Character, Integer> charIntMap) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + charIntMap.get(str.charAt(i));
        }
        return res;
    }

    public static void crypto(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int x = stringToInt(s1, charIntMap);
            int y = stringToInt(s2, charIntMap);
            int z = stringToInt(s3, charIntMap);

            if (x + y == z) {
                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch))
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                }
                System.out.println();
            }
            return;
        }

        char ch = unique.charAt(idx);
        for (int num = 0; num < 10; num++) {
            if (!usedNumbers[num]) {
                usedNumbers[num] = true;
                charIntMap.put(ch, num);

                crypto(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);

                charIntMap.remove(ch);
                usedNumbers[num] = false;
            }

        }
    }

    // 2 set of equal sum
    public static int equalSet(int[] arr, int idx, int sum1, String set1, int sum2, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], set1 + arr[idx] + " ", sum2, set2);
        count += equalSet(arr, idx + 1, sum1, set1, sum2 + arr[idx], set2 + arr[idx] + " ");

        return count;
    }

    public static void equalSet(int[] arr, int idx, int sum, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> smallAns) {
        if (idx >= arr.length)
            return;

        int tempsum = 0;
        for (int e : smallAns)
            tempsum += e;

        if (tempsum > sum)
            return;

        if (tempsum == sum) {
            ArrayList<Integer> set1 = new ArrayList<>(smallAns);
            ArrayList<Integer> set2 = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                boolean flag = false;
                for (int e : set1) {
                    if (arr[i] == e) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    set2.add(arr[i]);
            }
            ans.set(0, set1);
            ans.set(1, set2);

            return;
        }
        // int count = 0;
        int leader = 0;
        if (idx == 0)
            leader = arr[idx];
        smallAns.add(arr[idx]);
        equalSet(arr, idx + 1, sum, ans, smallAns);
        smallAns.remove(smallAns.size() - 1);

        if (arr[idx] != leader)
            equalSet(arr, idx + 1, sum, ans, smallAns);

    }

    public static void equalSet(int[] arr, int idx) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if ((sum & 1) != 0)
            return;

        ArrayList<Integer> smallAns = new ArrayList<>();
        equalSet(arr, 0, sum / 2, ans, smallAns);
        System.out.println(ans);
    }

    public static void ksubset(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int comp = subsetSum[0];

            for (int i = 1; i < subsetSum.length; i++)
                if (comp != subsetSum[i])
                    return;

            for (ArrayList<Integer> res : ans) {
                System.out.print(res + " ");
            }
            System.out.println();

            return;
        }

        for (int k = 0; k < subsetSum.length; k++) {
            ArrayList<Integer> set = ans.get(k);
            set.add(arr[idx]);
            subsetSum[k] += arr[idx];

            ksubset(arr, idx + 1, subsetSum, ans);

            subsetSum[k] -= arr[idx];
            set.remove(set.size() - 1);
            if (set.size() == 0)
                break;
        }

    }

    static int count = 1;

    public static void k_partitions(int i, int n, int k, ArrayList<ArrayList<Integer>> ans) {
        if (i > n) {

            if (ans.get(ans.size() - 1).size() == 0)
                return;

            System.out.print(count + ". ");
            count++;
            for (ArrayList<Integer> res : ans) {
                System.out.print(res + " ");
            }
            System.out.println();
            return;
        }

        for (int j = 0; j < k; j++) {
            ArrayList<Integer> temp = ans.get(j);
            temp.add(i);
            k_partitions(i + 1, n, k, ans);
            temp.remove(temp.size() - 1);
            if (temp.size() == 0)
                break;
        }

    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        // equalSet(arr, 0, 0, " ", 0, "");
        equalSet(arr, 0);
        // equalSet(arr, 1 , 10, "10 ", 0, "");
    }
}
