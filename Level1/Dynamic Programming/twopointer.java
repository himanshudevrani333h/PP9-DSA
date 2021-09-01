
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

    public static void main(String[] args) {
        int n = 7;
        int[] dp = new int[n + 1];
        // System.out.println(fabo_(n));
        // System.out.println(fabo_memo(n, dp));
        // System.out.println(fabo_tabu(n, dp));

    }

}