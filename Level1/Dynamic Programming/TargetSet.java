import java.util.Arrays;

public class TargetSet {

    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void fill(int dp[]) {
        Arrays.fill(dp, -1);
    }

    public static void fill2D(int dp[][]) {
        for (int d[] : dp)
            fill(d);
    }

    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }

    public static int permutations_memo(int[] coins, int targ, int dp[]) {

        if (targ == 0) {
            return dp[targ] = 1;
        }

        if (dp[targ] != -1)
            return dp[targ];
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            if (targ - coins[i] >= 0) {
                count += permutations_memo(coins, targ - coins[i], dp);
            }
        }

        return dp[targ] = count;

    }

    public static int permutations_tabu(int[] coins, int TARG, int dp[]) {

        for (int targ = 0; targ <= TARG; targ++) {
            if (targ == 0) {
                dp[targ] = 1;
                continue;
            }

            int count = 0;
            for (int i = 0; i < coins.length; i++) {
                if (targ - coins[i] >= 0) {
                    count += dp[targ - coins[i]]; // permutations_memo(coins, targ - coins[i],dp );
                }
            }

            dp[targ] = count;
        }

        return dp[TARG];
    }

    public static int combination_memo(int coins[], int targ, int idx, int dp[][]) {
        if (targ == 0) {
            return dp[idx][targ] = 1;
        }
        if (dp[idx][targ] != -1)
            return dp[idx][targ];
        int count = 0;
        for (int i = idx; i >= 0; i--) {
            if (targ - coins[i] >= 0)
                count += combination_memo(coins, targ - coins[i], i, dp);
        }

        return dp[idx][targ] = count;
    }

    public static int combination_tabu(int coins[], int TARG, int dp[]) {
        dp[0] = 1;
        for (int ele : coins) {
            for (int tar = ele; tar <= TARG; tar++) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }
        return dp[TARG];
    }

    // leetcode 322

    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;
        for (int targ = 1; targ <= amount; targ++) {
            for (int el : coins) {
                if (targ - el >= 0) {
                    dp[targ] = Math.min(dp[targ - el] + 1, dp[targ]);
                }
            }
        }

        return dp[amount] != (int) 1e9 ? dp[amount] : -1;
    }

    // Subset Sum Problem <- gfg
    static int subset(int arr[], int idx, int sum, int dp[][]) {
        if (sum == 0 || idx >= arr.length) {
            if (sum == 0) {
                return dp[idx][sum] = 1;
            }
            return dp[idx][sum] = 0;
        }
        if (dp[idx][sum] != -1)
            return dp[idx][sum];
        boolean flag = false;

        if (sum - arr[idx] >= 0)
            flag = flag || subset(arr, idx + 1, sum - arr[idx], dp) == 1;

        flag = flag || subset(arr, idx + 1, sum, dp) == 1;

        return dp[idx][sum] = (flag ? 1 : 0);

    }

    static Boolean isSubsetSum(int N, int arr[], int sum) {
        int dp[][] = new int[N + 1][sum + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);

        return subset(arr, 0, sum, dp) == 1 ? true : false;
    }

    public static boolean targetSum_DP(int[] arr, int N, int Tar, boolean[][] dp) {
        for (int n = 0; n <= N; n++) {
            for (int tar = 0; tar <= Tar; tar++) {
                if (n == 0 || tar == 0) {
                    dp[n][tar] = (tar == 0);
                    continue;
                }

                if (tar - arr[n - 1] >= 0)
                    dp[n][tar] = dp[n][tar] || dp[n - 1][tar - arr[n - 1]];
                dp[n][tar] = dp[n][tar] || dp[n - 1][tar];
            }
        }

        return dp[N][Tar];
    }

    // back Engineering
    public static int targetSum_path(int[] arr, int N, boolean[][] dp, int tar, String psf) {
        if (N == 0 || tar == 0) {
            if (tar == 0) {
                System.out.println(psf);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[N - 1] >= 0 && dp[N - 1][tar - arr[N - 1]])
            count += targetSum_path(arr, N - 1, dp, tar - arr[N - 1], psf + arr[N - 1] + " ");
        if (dp[N - 1][tar])
            count += targetSum_path(arr, N - 1, dp, tar, psf);

        return count;
    }

    public static void targetSum_backEngg() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10, N = 4;
        boolean[][] dp = new boolean[N + 1][tar + 1];
        System.out.println(targetSum_DP(arr, N, tar, dp));
        System.out.println(targetSum_path(arr, N, dp, tar, ""));
    }

    // 0-1 Knapsack <- GFG
    static int knapSack(int W, int wt[], int val[], int n, int[][] dp) {
        if (n == 0 || W == 0) {
            return dp[n][W] = 0;
        }

        if (dp[n][W] != -1)
            return dp[n][W];

        int maxAns = 0;
        if (W - wt[n - 1] >= 0)
            maxAns = Math.max(maxAns, knapSack(W - wt[n - 1], wt, val, n - 1, dp) + val[n - 1]);
        maxAns = Math.max(maxAns, knapSack(W, wt, val, n - 1, dp));

        return dp[n][W] = maxAns;
    }

    static int knapSack(int W, int wt[], int val[], int N) {
        int[][] dp = new int[N + 1][W + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return knapSack(W, wt, val, N, dp);
    }

    public static void main(String[] args) {
        int coins[] = { 2, 3, 5, 7 };
        int targ = 10;
        // int dp[] = new int[targ+1];
        // Arrays.fill(dp,-1);
        // System.out.println(permutations_tabu(coins,targ,dp));

        int dp[][] = new int[coins.length + 1][targ + 1];
        fill2D(dp);
        // System.out.println(combination_tabu(coins, targ, coins.length - 1, dp));
        display2D(dp);
    }
}
