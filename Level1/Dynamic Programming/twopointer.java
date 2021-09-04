import java.util.Arrays;

class twopointer {
    // 1. Faith
    // 2. Recursive tree
    // 3. Recursive code -> memoization
    // 4. Observation
    // 5. Tabulation
    // 6. Optimization

    public static void display(int arr[]) {
        for (int a : arr)
            System.out.print(a + " ");
        System.out.println();
    }

    public static void display2D(int arr[][]) {
        for (int a[] : arr)
            display(a);
        System.out.println();
    }

    public static int fabo_(int n) {
        if (n <= 1)
            return n;
        int ans = fabo_(n - 1) + fabo_(n - 2);
        return ans;
    }

    public static int fabo_memo(int n, int dp[]) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int ans = fabo_(n - 1) + fabo_(n - 2);
        return dp[n] = ans;
    }

    public static int fabo_tabu(int N, int dp[]) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2]; // fabo_(n - 1) + fabo_(n - 2);
            dp[n] = ans;
        }

        return dp[N];
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int[][] dp, int[][] dir) {
        if (er == sr && ec == sc) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int[] d : dir) {
            int r = sr + d[0], c = sc + d[1];
            if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                count += mazePath_memo(r, c, er, ec, dp, dir);
            }
        }

        return dp[sr][sc] = count;
    }

    public static int mazePath_tabu(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];// mazePath_memo(r, c, er, ec, dp, dir);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int mazePathJump_tabu(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    while (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length) {
                        count += dp[r][c];// mazePath_memo(r, c, er, ec, dp, dir);
                        r += d[0];
                        c += d[1];
                    }
                }
                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static void mazePath() {
        int sr = 0, sc = 0, er = 3, ec = 3;
        int[][] dp = new int[er + 1][ec + 1];

        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        System.out.println(mazePathJump_tabu(sr, sc, er, ec, dp, dir));

        display2D(dp);
    }

    // Leetcode 62, 63
    public int mazePath_tabuObstacle(int SR, int SC, int ER, int EC, int[][] dp, int[][] dir, int[][] obstacleGrid) {
        for (int sr = ER; sr >= SR; sr--) {
            for (int sc = EC; sc >= SC; sc--) {
                if (ER == sr && EC == sc) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0], c = sc + d[1];
                    if (r >= 0 && c >= 0 && r < dp.length && c < dp[0].length && obstacleGrid[r][c] == 0) {
                        count += dp[r][c];// mazePath_memo(r, c, er, ec, dp, dir);
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[n - 1][m - 1] == 1)
            return 0;
        int[][] dp = new int[n][m];
        int[][] dir = { { 1, 0 }, { 0, 1 } };
        return mazePath_tabuObstacle(0, 0, n - 1, m - 1, dp, dir, obstacleGrid);
    }

    // Leetcode 70
    // 746
    public int minCostClimbingStairs(int[] cost) {
        int N = cost.length;
        int[] dp = new int[N];
        for (int n = 0; n < N; n++) {
            if (n <= 1) {
                dp[n] = cost[n];
                continue;
            }

            int ans = Math.min(dp[n - 1], dp[n - 2]) + cost[n];
            dp[n] = ans;
        }

        return Math.min(dp[N - 2], dp[N - 1]);
    }

    // Board Path.========================================================

    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }

        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }

    public static int boardPath_tabu(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];// boardPath_memo(sp + dice, ep, dp);
            }

            dp[sp] = count;
        }

        return dp[SP];
    }

    public static int num(String s, int idx, int dp[]) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }
        if (dp[idx] != -1)
            return dp[idx];
        int count = 0;
        char ch = s.charAt(idx);
        if (ch - '0' == 0) {
            return dp[idx] = 0;
        }
        if (ch - '0' > 0 && ch - '0' <= 26) {

            count += num(s, idx + 1, dp);
        }
        if (idx < s.length() - 1) {
            int num = (s.charAt(idx) - '0') * 10 + s.charAt(idx + 1) - '0';
            if (num <= 26) {
                count += num(s, idx + 2, dp);
            }
        }

        return dp[idx] = count;
    }

    public static int num_tabu(String s, int IDX, int dp[]) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            int count = 0;
            char ch = s.charAt(idx);
            if (ch - '0' == 0) {
                dp[idx] = 0;
                continue;
            }
            if (ch - '0' > 0 && ch - '0' <= 26) {
                count += dp[idx + 1]; // num(s, idx + 1, dp);
            }
            if (idx < s.length() - 1) {
                int num = (s.charAt(idx) - '0') * 10 + s.charAt(idx + 1) - '0';
                if (num <= 26) {
                    count += dp[idx + 2]; // num(s, idx + 2, dp);
                }
            }

            dp[idx] = count;
        }

        return dp[IDX];
    }

    public static int num_opti(String s) {
        int a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            char ch = s.charAt(idx);
            int sum = 0;
            if (ch - '0' != 0) {
                sum += a;

                if (idx < s.length() - 1) {
                    int num = (s.charAt(idx) - '0') * 10 + s.charAt(idx + 1) - '0';
                    if (num <= 26) {
                        sum += b; // num(s, idx + 2, dp);
                    }
                }
            }
            b = a;
            a = sum;

        }

        return a;
    }

    public static int numDecodings(String s) {
        if (s.length() == 1) {
            if (s.charAt(0) - '0' == 0)
                return 0;
        }
        int dp[] = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        return num(s, 0, dp);
    }

    static int mod = (int) 1e9 + 7;

    public static long numDecodingII(String str, int idx, long dp[]) {
        int n = str.length();
        if (idx == n) {
            return dp[idx] = 1;
        }
        if (dp[idx] != -1)
            return dp[idx];
        long count = 0;
        char ch = str.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;
        if (ch == '*') {
            count = (count + 9 * numDecodingII(str, idx + 1, dp)) % mod;
            if (idx < n - 1) {
                char ch1 = str.charAt(idx + 1);
                if (ch1 >= '0' && ch1 <= '6') {
                    count = (count + 2 * numDecodingII(str, idx + 2, dp)) % mod;
                } else if (ch1 >= '7' && ch1 <= '9') {
                    count = (count + 1 * numDecodingII(str, idx + 2, dp)) % mod;
                } else {
                    count = (count + 15 * numDecodingII(str, idx + 2, dp)) % mod;
                }
            }
        } else {
            count = (count + 1 * numDecodingII(str, idx + 1, dp)) % mod;
            if (idx < n - 1) {
                char ch2 = str.charAt(idx + 1);
                if (ch2 == '*' && ch == '1') {
                    count = (count + 9 * numDecodingII(str, idx + 2, dp)) % mod;
                } else if (ch2 == '*' && ch == '2') {
                    count = (count + 6 * numDecodingII(str, idx + 2, dp)) % mod;
                } else if (ch2 != '*') {
                    int num = ch - '0' * 10 + ch2 - '0';
                    if (num <= 26) {
                        count = (count + 1 * numDecodingII(str, idx + 2, dp)) % mod;
                    }
                }
            }
        }

        return dp[idx] = count;
    }

    public static int numDecodingII(String s) {

        long dp[] = new long[s.length() + 1];
        Arrays.fill(dp, -1);
        long ans = numDecodingII(s, 0, dp);
        for (long e : dp)
            System.out.print(e + " ");
        return (int) ans;
    }

    public static long numDecodingII_tabu(String str, int IDX, long dp[]) {
        int n = str.length();

        for (int idx = str.length(); idx >= 0; idx--) {
            if (idx == n) {
                dp[idx] = 1;
                continue;
            }
            long count = 0;
            char ch = str.charAt(idx);
            if (ch == '0') {
                dp[idx] = 0;
                continue;
            }
            if (ch == '*') {
                count = (count + 9 * dp[idx + 1]) % mod;
                if (idx < n - 1) {
                    char ch1 = str.charAt(idx + 1);
                    if (ch1 >= '0' && ch1 <= '6') {
                        count = (count + 2 * dp[idx + 2]) % mod;
                    } else if (ch1 >= '7' && ch1 <= '9') {
                        count = (count + 1 * dp[idx + 2]) % mod;
                    } else {
                        count = (count + 15 * dp[idx + 2]) % mod;
                    }
                }
            } else {
                count = (count + 1 * dp[idx + 1]) % mod;
                if (idx < n - 1) {
                    char ch2 = str.charAt(idx + 1);
                    if (ch2 == '*' && ch == '1') {
                        count = (count + 9 * dp[idx + 2]) % mod;
                    } else if (ch2 == '*' && ch == '2') {
                        count = (count + 6 * dp[idx + 2]) % mod;
                    } else if (ch2 != '*') {
                        int num = (ch - '0') * 10 + (ch2 - '0');
                        if (num <= 26) {
                            count = (count + 1 * dp[idx + 2]) % mod;
                        }
                    }
                }
            }

            dp[idx] = count;
        }

        return dp[IDX];
    }

    public static long numDecodingOptiTwoPointer(String str) {
        int n = str.length();
        long a = 1, b = 0;
        for (int idx = str.length() - 1; idx >= 0; idx--) {

            long sum = 0;
            char ch = str.charAt(idx);
            if (ch != '0') {
                if (ch == '*') {
                    sum = (sum + 9 * a) % mod;
                    if (idx < n - 1) {
                        char ch1 = str.charAt(idx + 1);
                        if (ch1 >= '0' && ch1 <= '6') {
                            sum = (sum + 2 * b) % mod;
                        } else if (ch1 >= '7' && ch1 <= '9') {
                            sum = (sum + 1 * b) % mod;
                        } else {
                            sum = (sum + 15 * b) % mod;
                        }
                    }
                } else {
                    sum = (sum + 1 * a) % mod;
                    if (idx < n - 1) {
                        char ch2 = str.charAt(idx + 1);
                        if (ch2 == '*' && ch == '1') {
                            sum = (sum + 9 * b) % mod;
                        } else if (ch2 == '*' && ch == '2') {
                            sum = (sum + 6 * b) % mod;
                        } else if (ch2 != '*') {
                            int num = (ch - '0') * 10 + (ch2 - '0');
                            if (num <= 26) {
                                sum = (sum + 1 * b) % mod;
                            }
                        }
                    }
                }
            }
            b = a;
            a = sum;

        }
        return a;
    }

    public static int goldMine(int[][] arr, int sr, int sc, int[][] dir, int[][] dp) {
        int n = arr.length, m = arr[0].length;
        if (sc == m - 1)
            return dp[sr][sc] = arr[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxGold = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                maxGold = Math.max(maxGold, goldMine(arr, r, c, dir, dp) + arr[sr][sc]);
            }
        }

        return dp[sr][sc] = maxGold;
    }

    public static void goldMine() {
        int[][] arr = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
        int[][] dir = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
        int n = arr.length, m = arr[0].length;
        int[][] dp = new int[n][m];

        for (int[] d : dp)
            Arrays.fill(d, -1);

        int maxGold = 0;
        for (int r = 0; r < n; r++) {
            maxGold = Math.max(maxGold, goldMine(arr, r, 0, dir, dp));
        }

        System.out.println(maxGold);
    }

    // Min Cost Path | DP-6
    public static int MinCostPath(int[][] arr, int sr, int sc, int[][] dir, int[][] dp) {
        int n = arr.length, m = arr[0].length;
        if (sc == m - 1 && sr == n - 1)
            return dp[sr][sc] = arr[sr][sc];

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int minPath = (int) 1e9;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m) {
                minPath = Math.min(minPath, MinCostPath(arr, r, c, dir, dp) + arr[sr][sc]);
            }
        }

        return dp[sr][sc] = minPath;
    }

    public static void MinCostPath() {
        int cost[][] = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
        int[][] dir = { { 1, 0 }, { 0, 1 }, { 1, 1 } };
        int n = cost.length, m = cost[0].length;
        int[][] dp = new int[n][m];

        for (int[] d : dp)
            Arrays.fill(d, -1);

        int minpath = (int) 1e9;
        for (int r = 0; r < n; r++) {
            minpath = Math.min(minpath, MinCostPath(cost, r, 0, dir, dp));
        }

        System.out.println(minpath);
    }

    public long countfrnds(int n, long dp[]) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != -1)
            return dp[n];
        long s = countfrnds(n - 1, dp);

        long p = n - 2 >= 0 ? countfrnds(n - 2, dp) * (n - 1) : 0;

        return dp[n] = (s + p % mod) % mod;
    }

    public long countfrndsptr(int n) {
        long a = 1, b = 1;
        for (int i = 2; i <= n; i++) {

            long sum = (b + a * (i - 1) % mod) % mod;

            a = b;
            b = sum % mod;

        }

        return b;
    }

    public long countFriendsPairings(int n) {
        long dp[] = new long[n + 1];
        Arrays.fill(dp, -1);
        long res = countfrnds(n, dp);
        return countfrndsptr(n);

    }

    public static int divideinKgroups(int n, int k, int dp[][]){
        if( n== k || k == 1){
            return dp[n][k] = 1;
        }

        if( dp[n][k] != 0) return dp[n][k];
        int sgroup = divideinKgroups(n-1, k-1, dp);
        int pgroup = divideinKgroups(n-1, k, dp) * k;

        return dp[n][k] = sgroup + pgroup;
    }
    
    public static int divideinKgroupstabu(int N, int K, int dp[][]){
        for(int n =1; n<=N; n++){
            for( int k =1; k<=K; k++){
                if( n== k || k == 1){
                    dp[n][k] = 1;
                    continue;
                }
        
                
                int sgroup = dp[n-1][k-1]; //divideinKgroups(n-1, k-1, dp);
                int pgroup = dp[n-1][k]; //divideinKgroups(n-1, k, dp) * k;
        
               dp[n][k] = sgroup + pgroup;
            }
        }

        return dp[N][K];
       
    }
    
    public static int divideinKgroups(){
        int n = 10;
        int k = 5;
        int dp[][] = new int[n+1][k+1];
        return divideinKgroups(n,k,dp);
    }
    public static void main(String[] args) {
        int n = 7;
        // int[] dp = new int[n + 1];
        // String str = "**";
        // System.out.println( (int)numDecodingOptiTwoPointer(str));
        MinCostPath();
        // long dp[] = new long[str.length()+1];
        // Arrays.fill(dp, -1);
        // System.out.println( (int)numDecodingII_tabu("904",0,dp));
        // for(long e: dp) System.out.print(e+" ");
        // System.out.println(fabo_(n));
        // System.out.println(fabo_memo(n, dp));
        // System.out.println(fabo_tabu(n, dp));

    }

}