
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

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

    // leetcode 583.
    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
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

    // cost : {insert = a, replace = b, delete = c}
    public int minDistance_02(String word1, String word2, int n, int m, int[] cost, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0 ? m * cost[0] : n * cost[2]);
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        int insert = minDistance_02(word1, word2, n, m - 1, cost, dp);
        int delete = minDistance_02(word1, word2, n - 1, m, cost, dp);
        int replace = minDistance_02(word1, word2, n - 1, m - 1, cost, dp);

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = replace;
        else
            return dp[n][m] = Math.min(Math.min(insert + cost[0], delete + cost[2]), replace + cost[1]);
    }

    public int EditDistance(String word1, String word2) {
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

    // leetcode 1035
    public int maxUncrossedLines(int[] num1, int[] num2, int dp[][], int N, int M) {

        for (int n = 0; n <= N; n++) {
            for (int m = 0; m <= M; m++) {

                if (n == 0 || m == 0) {
                    dp[n][m] = 0;
                    continue;
                }

                if (num1[n - 1] == num2[m - 1]) {
                    dp[n][m] = dp[n - 1][m - 1] + 1;
                } else
                    dp[n][m] = Math.max(dp[n - 1][m], dp[n][m - 1]);

            }
        }

        return dp[N][M];
    }

    public int maxUncrossedLines(int[] num1, int[] num2) {
        int n = num1.length, m = num2.length;
        int dp[][] = new int[n + 1][m + 1];
        return maxUncrossedLines(num1, num2, dp, n, m);

    }

    // leetcode 1458
    public int maximum(int... arr) {
        int max = arr[0];
        for (int e : arr)
            max = Math.max(max, e);
        return max;
    }

    public int maxDotProduct(int[] num1, int[] num2, int dp[][], int n, int m) {

        if (n == 0 || m == 0) {
            return dp[n][m] = -(int) 1e9;
        }

        if (dp[n][m] != -(int) 1e9)
            return dp[n][m];

        int val = num1[n - 1] * num2[m - 1];
        int bothnumbers = maxDotProduct(num1, num2, dp, n - 1, m - 1) + val;
        int num1accept = maxDotProduct(num1, num2, dp, n - 1, m);
        int num2accept = maxDotProduct(num1, num2, dp, n, m - 1);

        return dp[n][m] = maximum(val, bothnumbers, num1accept, num2accept);
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int dp[][] = new int[n + 1][m + 1];
        for (int d[] : dp)
            Arrays.fill(d, -(int) 1e9);
        return maxDotProduct(nums1, nums2, dp, n, m);
    }

    // Leetcode 5
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int count = 0, maxlen = 0, si = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];

                if (dp[i][j]) {
                    count++;
                    if (j - i + 1 > maxlen) {
                        maxlen = j - i + 1;
                        si = i;
                    }
                }
            }
        }

        return s.substring(si, si + maxlen);
    }

    // Leetcode 132
    // faafaaaaabaageeg <- test case
    public int mincut(String s, int si, int ei, boolean pdp[][], int dp[]) {

        if (pdp[si][ei])
            return 0;

        if (dp[si] != -1)
            return dp[si];
        int mincut = (int) 1e8;
        for (int cut = si; cut <= ei; cut++) {
            if (pdp[si][cut]) {
                mincut = Math.min(mincut, mincut(s, cut + 1, ei, pdp, dp) + 1);
            }
        }

        return dp[si] = mincut;
    }

    public int minCut(String s) {
        int n = s.length();
        boolean pdp[][] = new boolean[n][n];

        for (int gap = 0; gap < n; gap++)
            for (int i = 0, j = gap; j < n; j++, i++) {
                if (gap == 0)
                    pdp[i][j] = true;
                else if (gap == 1 && s.charAt(i) == s.charAt(j))
                    pdp[i][j] = true;
                else
                    pdp[i][j] = s.charAt(i) == s.charAt(j) && pdp[i + 1][j - 1];
            }

        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);

        int ans = mincut(s, 0, n - 1, pdp, dp);
        for (int a : dp)
            System.out.print(a + " ");
        return ans;
    }

    // Count subsequences of type a^i, b^j, c^k GFG
    // https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1#
    // follow up question ai,bj,ck,dl,em....
    public int CountSubSeq(String s) {
        long emptyCount = 1, aCount = 0, bCount = 0, cCount = 0, mod = (long) 1e9 + 7;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'c') {
                cCount = cCount + (bCount + cCount) % mod;
            } else if (s.charAt(i) == 'b') {
                bCount = bCount + (aCount + bCount) % mod;
            } else if (s.charAt(i) == 'a') {
                aCount = aCount + (emptyCount + aCount) % mod;
            }
        }

        return (int) (cCount % mod);
    }

    // leetcode 139 Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length(), len = 0;
        boolean dp[] = new boolean[n + 1];
        HashSet<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
            len = Math.max(str.length(), len);
        }
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;
            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }

        return dp[n];
    }

    // Longest Palindromic substring print via BACK_ENGINEERING;

    public String lpss_backEng(String str, int si, int ei, int dp[][]) {

        if (si >= ei) {
            return si == ei ? str.charAt(si) + "" : "";
        }

        if (str.charAt(si) == str.charAt(ei)) {
            return str.charAt(si) + lpss_backEng(str, si + 1, ei - 1, dp) + str.charAt(ei);
        } else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return lpss_backEng(str, si + 1, ei, dp);
        } else {
            return lpss_backEng(str, si, ei - 1, dp);
        }
    }

    public void wordBreak_backEngg(String s, int idx, boolean[] dp, int maxLen, List<String> wordDict,
            HashSet<String> set, String ssf, List<String> ans) {
        if (idx >= s.length()) {
            ans.add(ssf.substring(0, ssf.length() - 1));
            return;
        }

        for (int l = 1; l <= maxLen && idx + l <= s.length(); l++) {
            if (dp[idx + l]) {
                String substr = s.substring(idx, idx + l);
                if (set.contains(substr)) {
                    wordBreak_backEngg(s, idx + l, dp, maxLen, wordDict, set, ssf + substr + " ", ans);
                }
            }
        }
    }

    // leetcode 140
    public List<String> Word_Break_II(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        int len = 0, n = s.length();
        for (String ss : wordDict) {
            set.add(ss);
            len = Math.max(ss.length(), len);
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            if (!dp[i])
                continue;

            for (int l = 1; l <= len && i + l <= n; l++) {
                String substr = s.substring(i, i + l);
                if (set.contains(substr)) {
                    dp[i + l] = true;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        if (dp[n])
            wordBreak_backEngg(s, 0, dp, len, wordDict, set, "", ans);

        return ans;
    }

    // Leetcode 10 <- Regular Expression matching
    public String removeStarsRE(String p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        int i = 1;

        while (i < p.length()) {
            while (i < p.length() && sb.charAt(sb.length() - 1) == '*' && p.charAt(i) == '*')
                i++;

            if (i < p.length())
                sb.append(p.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public int isMatchRE(String s, String p, int n, int m, int[][] dp) {

        if (n == 0 && m == 0)
            return dp[n][m] = 1;
        if (m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        char ch = n > 0 ? s.charAt(n - 1) : '$';
        char ch2 = p.charAt(m - 1);

        if (ch != '$' && (ch == ch2 || ch2 == '.')) {
            return dp[n][m] = isMatch(s, p, n - 1, m - 1, dp);
        } else if (ch2 == '*') {
            boolean res = false;
            if (n > 0 && m > 1 && ((p.charAt(m - 2) == ch) || p.charAt(m - 2) == '.'))
                res = res || isMatch(s, p, n - 1, m, dp) == 1;

            res = res || isMatch(s, p, n, m - 2, dp) == 1;

            return dp[n][m] = res ? 1 : 0;
        } else {

            return dp[n][m] = 0;
        }
    }

    public boolean isMatchRE(String s, String p) {
        int n = s.length(), m = removeStars(p).length();
        int dp[][] = new int[n + 1][m + 1];
        for (int d[] : dp)
            Arrays.fill(d, -1);
        return isMatch(s, p, n, m, dp) == 1;
    }

    // 213. House Robber II
    public int rob(int arr[], int si, int ei, int[] dp) {
        if (si > ei)
            return 0;
        if (dp[si] != -1)
            return dp[si];
        int ifRob = arr[si] + rob(arr, si + 2, ei, dp);
        int ifNotRob = rob(arr, si + 1, ei, dp);

        return dp[si] = Math.max(ifRob, ifNotRob);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1)
            return n == 1 ? nums[0] : 0;

        int dp1[] = new int[n];
        Arrays.fill(dp1, -1);

        int dp2[] = new int[n];
        Arrays.fill(dp2, -1);

        return Math.max(rob(nums, 0, n - 2, dp1), rob(nums, 1, n - 1, dp2));
    }


    
    // // Raaju baandar question
    // public static void pattern(int n){
    // int sNum = 1;
    // int eNum = n;
    // boolean even = n %2 == 0;
    // int mid = (n +1) /2;

    // for( int i = 1; i<=n; i++){

    // for( int j = sNum; j<=eNum; j++){
    // if( i == 1 || i == n)
    // System.out.print(j+" ");
    // else if( i > 1 && i< n && j > sNum && j<eNum){
    // System.out.print("* ");
    // }else if(j==sNum || j == eNum){
    // System.out.print(j+" ");
    // }
    // sNum = j;
    // }
    // System.out.println();
    // if(even){
    // if( i < mid){
    // sNum = sNum + (n +1);
    // eNum = sNum + (n-1);
    // }
    // else if( i == mid) {
    // eNum = sNum + n;
    // sNum = eNum - (n-1);
    // }else{
    // eNum = sNum - 2*n;
    // sNum = eNum - (n-1);
    // }

    // }else{
    // if( i < mid){
    // sNum = sNum + (n +1);
    // eNum = sNum + (n-1);
    // }
    // else if( i == mid) {
    // eNum = sNum - n;
    // sNum = eNum - (n-1);
    // }else{
    // eNum = sNum - 2*n;
    // sNum = eNum - (n-1);
    // }

    // }
    // }
    // }

    public static void main(String[] args) {
        // System.out.println(removeStars("****a*b**"));
        // pattern(8);
    }

}