import java.util.Arrays;
import java.util.*;

class CutSet {

    // Matrix Chain Multiplication <-GFG
    static int matrixMul(int arr[], int si, int ei, int dp[][]) {

        if (ei - si == 1) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int min = (int) 1e9;

        for (int cut = si + 1; cut < ei; cut++) {

            int leftRes = matrixMul(arr, si, cut, dp);
            int RightRes = matrixMul(arr, cut, ei, dp);

            min = Math.min(min, leftRes + RightRes + (arr[cut] * arr[si] * arr[ei]));
        }

        return dp[si][ei] = min;

    }

    static int matrixMul_tabu(int arr[], int SI, int EI, int dp[][]) {
        int n = arr.length;
        for (int gap = 1; gap <= EI; gap++)
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    continue;
                }

                int min = (int) 1e9;

                for (int cut = si + 1; cut < ei; cut++) {

                    int leftRes = dp[si][cut]; // matrixMul(arr, si, cut, dp);
                    int RightRes = dp[cut][ei]; // matrixMul(arr, cut, ei, dp);

                    min = Math.min(min, leftRes + RightRes + (arr[cut] * arr[si] * arr[ei]));
                }

                dp[si][ei] = min;
            }

        return dp[SI][EI];
    }

    public static int matrixMul_tabu_path(int[] arr, int SI, int EI, int[][] dp) {
        int n = arr.length;
        String[][] sdp = new String[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (ei - si == 1) {
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char) (si + 'A') + "";
                    continue;
                }

                int minRes = (int) 1e9;
                String minStr = "";
                for (int cut = si + 1; cut < ei; cut++) {
                    int leftRes = dp[si][cut];
                    int rightRes = dp[cut][ei];

                    if (leftRes + arr[si] * arr[cut] * arr[ei] + rightRes < minRes) {
                        minRes = leftRes + arr[si] * arr[cut] * arr[ei] + rightRes;
                        minStr = "(" + sdp[si][cut] + sdp[cut][ei] + ")";
                    }
                }

                dp[si][ei] = minRes;
                sdp[si][ei] = minStr;
            }

        }
        System.out.println(sdp[SI][EI]);
        return dp[SI][EI];
    }

    static int matrixMultiplication(int N, int arr[]) {
        int dp[][] = new int[N + 1][N + 1];
        // for (int d[] : dp)
        // Arrays.fill(d, -1);

        int res = matrixMul_tabu(arr, 0, N - 1, dp);
        for (int d[] : dp) {
            for (int dr : d)
                System.out.print(dr + " ");

            System.out.println();
        }
        return res;
    }

    // Minimum and Maximum values of an expression with * and + <- GFG

    static class pairMM {
        int min = (int) 1e9;
        int max = 0;

        pairMM() {

        };

        pairMM(int val) {
            this.min = this.max = val;
        }
    }

    public static pairMM evaluate(pairMM leftRes, pairMM rightRes, char operator) {
        pairMM res = new pairMM();
        if (operator == '+') {
            res.min = leftRes.min + rightRes.min;
            res.max = leftRes.max + rightRes.max;
        } else if (operator == '*') {
            res.min = leftRes.min * rightRes.min;
            res.max = leftRes.max * rightRes.max;
        }
        return res;
    }

    public static pairMM mmValueOfExpression(String str, int si, int ei, pairMM dp[][]) {
        if (si == ei) {
            return dp[si][ei] = new pairMM(str.charAt(ei) - '0');
        }

        if (dp[si][ei] != null)
            return dp[si][ei];
        pairMM myRes = new pairMM();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairMM leftRes = mmValueOfExpression(str, si, cut - 1, dp);
            pairMM rightRes = mmValueOfExpression(str, cut + 1, ei, dp);
            pairMM evalRes = evaluate(leftRes, rightRes, str.charAt(cut));

            myRes.min = Math.min(myRes.min, evalRes.min);
            myRes.max = Math.max(myRes.max, evalRes.max);
        }

        return dp[si][ei] = myRes;
    }

    public static void mmValueOfExpression() {
        String str = "1+2*3+4*5";
        int n = str.length();
        pairMM dp[][] = new pairMM[n][n];
        pairMM res = mmValueOfExpression(str, 0, n - 1, dp);
        System.out.println("Min val : " + res.min);
        System.out.println("Max val : " + res.max);
    }

    // leetcode 312

    public int maxCoins(int arr[], int si, int ei, int dp[][]) {
        if (dp[si][ei] != 0)
            return dp[si][ei];
        int lele = si == 0 ? 1 : arr[si - 1];
        int rele = ei == arr.length - 1 ? 1 : arr[ei + 1];

        int max = 0;
        for (int cut = si; cut <= ei; cut++) {
            int leftCost = cut == si ? 0 : maxCoins(arr, si, cut - 1, dp);
            int rightCost = cut == ei ? 0 : maxCoins(arr, cut + 1, ei, dp);

            max = Math.max(max, leftCost + lele * arr[cut] * rele + rightCost);
        }

        return dp[si][ei] = max;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n];
        return maxCoins(nums, 0, n - 1, dp);
    }

    // Boolean Parenthesization <-GFG

    static class pairBoolean {
        long totalTrue = 0;
        long totalFalse = 0;

        pairBoolean() {
        };

        public pairBoolean(long totalTrue, long totalFalse) {
            this.totalTrue = totalTrue;
            this.totalFalse = totalFalse;
        }
    }

    static void evaluateTotalTF(pairBoolean leftWays, pairBoolean rightWays, pairBoolean res, char operator) {
        long mod = 1003;
        long totalTF = ((leftWays.totalTrue + leftWays.totalFalse) * (rightWays.totalTrue + rightWays.totalFalse))
                % mod;
        long totalFalse = 0, totalTrue = 0;
        if (operator == '|') {
            totalFalse = (leftWays.totalFalse * rightWays.totalFalse) % mod;
            totalTrue = (totalTF - totalFalse + mod) % mod;
        } else if (operator == '&') {
            totalTrue = (leftWays.totalTrue * rightWays.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;
        } else if (operator == '^') {
            totalTrue = (leftWays.totalTrue * rightWays.totalFalse) % mod
                    + (leftWays.totalFalse * rightWays.totalTrue) % mod;
            totalFalse = (totalTF - totalTrue + mod) % mod;
        }

        res.totalFalse = (res.totalFalse + totalFalse) % mod;
        res.totalTrue = (res.totalTrue + totalTrue) % mod;

    }

    static pairBoolean countWays(String s, int si, int ei, pairBoolean dp[][]) {

        if (si == ei) {
            char ch = s.charAt(si);
            int t = ch == 'T' ? 1 : 0;
            int f = ch == 'F' ? 1 : 0;

            return dp[si][ei] = new pairBoolean(t, f);
        }

        if (dp[si][ei] != null)
            return dp[si][ei];
        pairBoolean res = new pairBoolean();
        for (int cut = si + 1; cut < ei; cut += 2) {
            pairBoolean leftWays = countWays(s, si, cut - 1, dp);
            pairBoolean rightWays = countWays(s, cut + 1, ei, dp);

            evaluateTotalTF(leftWays, rightWays, res, s.charAt(cut));
        }
        return dp[si][ei] = res;
    }

    static int countWays(int N, String S) {
        pairBoolean dp[][] = new pairBoolean[N][N];
        return (int) countWays(S, 0, N - 1, dp).totalTrue;
    }

    // gfg OPTIMAL BINARY TREE
    public static int obst(int[] val, int[] freq, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];
        int minCost = (int) 1e8;

        int sum = 0;

        for (int i = si; i <= ei; i++)
            sum += freq[i];

        for (int cut = si; cut <= ei; cut++) {
            int lres = cut == si ? 0 : obst(val, freq, si, cut - 1, dp);
            int rres = cut == ei ? 0 : obst(val, freq, cut + 1, ei, dp);
            // sum += freq[cut];
            minCost = Math.min(minCost, lres + sum + rres); // sum : sumOfRange(freq, si,ei);
        }

        return dp[si][ei] = minCost; // minCost + sum
    }

    // Leetcode 1039
    public int minScoreTriangulation(int[] arr, int si, int ei, int dp[][]) {

        if (ei - si < 2) {
            return 0;
        }

        if (dp[si][ei] != 0)
            return dp[si][ei];

        int minimumScore = (int) 1e9;
        for (int cut = si + 1; cut < ei; cut++) {

            int middletriangle = arr[si] * arr[ei] * arr[cut];

            int leftScore = minScoreTriangulation(arr, si, cut, dp);

            int rightScore = minScoreTriangulation(arr, cut, ei, dp);

            minimumScore = Math.min(leftScore + rightScore + middletriangle, minimumScore);
        }

        return dp[si][ei] = minimumScore;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int dp[][] = new int[n][n];
        return minScoreTriangulation(values, 0, n - 1, dp);
    }

    // leetcode 95
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void generateAlllBST(List<TreeNode> leftList, List<TreeNode> rightList, List<TreeNode> ans, int num) {
        for (TreeNode ln : leftList) {
            for (TreeNode rn : rightList) {
                TreeNode root = new TreeNode(num);
                root.left = ln;
                root.right = rn;
                ans.add(root);
            }
        }
    }

    public List<TreeNode> generateTrees(int si, int ei, List<TreeNode>[][] dp) {
        List<TreeNode> myAns = new ArrayList<>();
        if (si >= ei) {
            TreeNode root = (si == ei ? new TreeNode(si) : null);
            myAns.add(root);
            return myAns;
        }

        if (dp[si][ei] != null)
            return dp[si][ei];

        for (int cut = si; cut <= ei; cut++) {
            List<TreeNode> leftList = generateTrees(si, cut - 1, dp);
            List<TreeNode> rightList = generateTrees(cut + 1, ei, dp);

            generateAlllBST(leftList, rightList, myAns, cut);
        }

        return dp[si][ei] = myAns;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] dp = new ArrayList[n][n];
        return generateTrees(1, n, dp);
    }

    // leetcode 576
    int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
    int mod = (int) 1e9 + 7;

    public int findPaths(int n, int m, int K, int sr, int sc, int[][][] dp) {
        if (sr < 0 || sc < 0 || sr == n || sc == m) {
            return 1;
        }

        if (K == 0)
            return 0;

        if (dp[sr][sc][K] != -1)
            return dp[sr][sc][K];
        int count = 0;

        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            count = (count + findPaths(n, m, K - 1, r, c, dp)) % mod;

        }

        return dp[sr][sc][K] = count;
    }

    public int findPaths(int n, int m, int k, int r, int c) {
        int[][][] dpp = new int[n + 1][m + 1][k + 1];
        for (int[][] dp : dpp)
            for (int[] d : dp)
                Arrays.fill(d, -1);

        return findPaths(n, m, k, r, c, dpp);
    }

    public static void main(String[] args) {
        // int arr[] = { 40, 20, 30, 10, 30 };
        // System.out.println(matrixMultiplication(5, arr));

        mmValueOfExpression();
    }
}