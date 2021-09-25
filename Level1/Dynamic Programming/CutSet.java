import java.util.Arrays;

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





    // Leetcode  1039
    public int minScoreTriangulation(int [] arr, int si, int ei, int dp[][]){
       
        if( ei - si < 2 ){
            return 0;
        }
        
        if( dp[si][ei] != 0) return dp[si][ei];
        
       int minimumScore = (int)1e9;
        for(int cut = si +1; cut<ei; cut++){
            
            int middletriangle = arr[si] * arr[ei] * arr[cut];
            
            int leftScore =minScoreTriangulation(arr,si,cut,dp);
            
            int rightScore =  minScoreTriangulation(arr,cut,ei,dp);
            
            minimumScore = Math.min(leftScore + rightScore + middletriangle, minimumScore);
        }
        
        return dp[si][ei] = minimumScore;
    }
    
    
    public int minScoreTriangulation(int[] values) {
       int n = values.length;
       int dp[][]= new int[n][n];
       return minScoreTriangulation(values, 0, n-1, dp); 
    }


    public static void main(String[] args) {
        // int arr[] = { 40, 20, 30, 10, 30 };
        // System.out.println(matrixMultiplication(5, arr));

        mmValueOfExpression();
    }
}