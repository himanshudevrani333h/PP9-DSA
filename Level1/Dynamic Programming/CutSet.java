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

                    int leftRes = dp[si][cut]; //matrixMul(arr, si, cut, dp);
                    int RightRes = dp[cut][ei]; //matrixMul(arr, cut, ei, dp);

                    min = Math.min(min, leftRes + RightRes + (arr[cut] * arr[si] * arr[ei]));
                }

                 dp[si][ei] = min;
            }

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

    public static void main(String[] args) {
        int arr[] = { 40, 20, 30, 10, 30 };
        System.out.println(matrixMultiplication(5, arr));
    }
}