import java.util.Arrays;

class stringset {

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

    // 516. Longest Palindromic Subsequence
    public int lpss(String s, int i, int j) {
        if (i >= j) {
            return i == j ? 1 : 0;
        }

        int maxlen = 0;
        if (s.charAt(i) != s.charAt(j)) {
            maxlen = Math.max(Math.max(lpss(s, i + 1, j), lpss(s, i, j - 1)), maxlen);
        } else {
            maxlen = Math.max(maxlen, lpss(s, i + 1, j - 1) + 2);
        }
        return maxlen;
    }

    public int lpss_memo(String s, int i, int j, int dp[][]) {
        if (i >= j) {
            if (i == j)
                return dp[i][j] = 1;
            return dp[i][j];
        }

        if (dp[i][j] != 0)
            return dp[i][j];
        int maxlen = 0;
        if (s.charAt(i) != s.charAt(j)) {
            maxlen = Math.max(Math.max(lpss_memo(s, i + 1, j, dp), lpss_memo(s, i, j - 1, dp)), maxlen);

        } else
            maxlen = Math.max(maxlen, lpss_memo(s, i + 1, j - 1, dp) + 2);

        return dp[i][j] = maxlen;
    }

    public int lpss_tabu(String s, int I, int J, int dp[][]) {
        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < s.length(); j++, i++) {
                if (i >= j) {
                    if (i == j) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = 0;
                    continue;
                }

                int maxlen = 0;
                if (s.charAt(i) != s.charAt(j)) {
                    maxlen = Math.max(Math.max(dp[i + 1][j], dp[i][j - 1]), maxlen);

                } else
                    maxlen = Math.max(maxlen, dp[i + 1][j - 1] + 2);

                dp[i][j] = maxlen;
            }
        }
        return dp[I][J];

    }

    // 1143. Longest Common Subsequence
    public int lcss(String str1, String str2, int n, int m, int dp[][]) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if (dp[n][m] != -1)
            return dp[n][m];

        if (str1.charAt(n - 1) == str2.charAt(m - 1))
            dp[n][m] = lcss(str1, str2, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(lcss(str1, str2, n - 1, m, dp), lcss(str1, str2, n, m - 1, dp));

        return dp[n][m];
    }

    public int lcss_tabu(String str1, String str2, int N, int M, int dp[][]) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (str1.charAt(n - 1) == str2.charAt(m - 1))
                    dp[n][m] = dp[n - 1][m - 1] + 1; // lcss(str1,str2,n-1,m-1,dp) +1;
                else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);

            }
        }
        return dp[N][M];

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text1.length() + 1][text2.length() + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return lcss(text1, text2, text1.length(), text2.length(), dp);
    }

    public static void main(String[] args) {

    }

}