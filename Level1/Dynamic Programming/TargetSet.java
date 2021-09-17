import java.util.Arrays;

public class TargetSet {
    
    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
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


    public static void fill(int dp[]) {
        Arrays.fill(dp, -1);
    }

    public static void fill2D(int dp[][]) {
        for (int d[] : dp)
            fill(d);
    }

    public static void main(String[] args) {
        int coins[] = { 2, 3, 5, 7 };
        int targ = 10;
        // int dp[] = new int[targ+1];
        // Arrays.fill(dp,-1);
        // System.out.println(permutations_tabu(coins,targ,dp));

        int dp[][] = new int[coins.length+1][targ+1];
        fill2D(dp);
        // System.out.println(combination_tabu(coins, targ, coins.length - 1, dp));
         display2D(dp);
    }
}
