import java.rmi.Remote;
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

    // 115 Distinct Subsequence

    public int numDistinct(String s, String t, int i, int j, int dp[][]) {
        if (i < j) {
            return dp[i][j] = 0;
        }
        if (j == 0) {

            return dp[i][j] = 1;
        }
        if (dp[i][j] != -1)
            return dp[i][j];
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            dp[i][j] = numDistinct(s, t, i - 1, j - 1, dp) + numDistinct(s, t, i - 1, j, dp);
        } else {
            dp[i][j] = numDistinct(s, t, i - 1, j, dp);
        }

        return dp[i][j];
    }

    public int numDistinct_tabu(String s, String t, int I, int J, int dp[][]) {
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (i < j) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == 0) {

                    dp[i][j] = 1;
                    continue;

                }

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // numDistinct(s,t,i-1,j-1,dp) +
                                                                // numDistinct(s,t,i-1,j,dp);
                } else {
                    dp[i][j] = dp[i - 1][j]; // numDistinct(s,t,i-1,j,dp);
                }

            }
        }
        return dp[I][J];
    }

    public int numDistinct(String s, String t) {
        int dp[][] = new int[s.length() + 1][t.length() + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct(s, t, s.length(), t.length(), dp);
    }

    // 72 Edit Distance
    public int minDistance(String str1, String str2, int n, int m, int dp[][]) {

        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m : n;
        }

        if (dp[n][m] != -1)
            return dp[n][m];
        int insert = minDistance(str1, str2, n, m - 1, dp);
        int delete = minDistance(str1, str2, n - 1, m, dp);
        int replace = minDistance(str1, str2, n - 1, m - 1, dp);

        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            dp[n][m] = replace;
        } else {
            dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
        }

        return dp[n][m];
    }

    public int minDistance_Tabu(String str1, String str2, int N, int M, int dp[][]) {
        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (n == 0) ? m : n;
                    continue;
                }

                int insert = dp[n][m - 1]; // minDistance(str1,str2,n,m-1,dp);
                int delete = dp[n - 1][m]; // minDistance(str1,str2,n-1,m,dp);
                int replace = dp[n - 1][m - 1]; // minDistance(str1,str2,n-1,m-1,dp);

                if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
                    dp[n][m] = replace;
                } else {
                    dp[n][m] = Math.min(Math.min(insert, delete), replace) + 1;
                }

            }
        }
        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return minDistance(word1, word2, word1.length(), word2.length(), dp);
    }

    // 44. Wildcard Matching

    public String removeStars(String str) {
        if (str.length() == 0)
            return str;

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        int i = 1;
        while (i < str.length()) {
            while (i < str.length() && sb.charAt(sb.length() - 1) == '*' && str.charAt(i) == '*')
                i++;

            if (i < str.length())
                sb.append(str.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public int isMatch(String s, String p, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            if (n == 0 && m == 0)
                return dp[n][m] = 1; // true
            else if (m == 1 && p.charAt(m - 1) == '*')
                return dp[n][m] = 1;
            else
                return dp[n][m] = 0;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch1 = s.charAt(n - 1);
        char ch2 = p.charAt(m - 1);

        if (ch1 == ch2 || ch2 == '?') {
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            res = res || isMatch(s, p, n - 1, m, dp) == 1; // sequnence of character
            res = res || isMatch(s, p, n, m - 1, dp) == 1; // empty string

            return dp[n][m] = res ? 1 : 0;

        } else
            return dp[n][m] = 0;
    }

    public boolean isMatch(String s, String p) {
        p = removeStars(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        int ans = isMatch(s, p, n, m, dp);

        return ans == 1;
    }
    public static void main(String[] args) {
        // System.out.println(removeStars("****a*b**"));
    }

}