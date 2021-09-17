import java.util.Arrays;

public class LIS {
    
// Leetcode 300 Longest Increasing subsequence
    public static int LIS_memo(int arr[], int ei, int dp[]){
        if(dp[ei] != 0) return dp[ei];
       int maxlen = 1;
        for( int i = ei -1; i>=0; i--){
           if(arr[i] < arr[ei])
             maxlen = Math.max(maxlen,LIS_memo(arr, i, dp) + 1);
        }

        return dp[ei] = maxlen;
    }

    // use index pe khatam hone wala longest increasing subseq -> Remeber uspe khatam hone wala
    public static int LIS_LR(int arr[], int dp[]){
       int n = arr.length, maxlen =0;

       for( int i =0; i<n; i++){
           dp[i] = 1;

           for( int j = i -1; j>=0; j--){
               if( arr[i] > arr[j]){
                 dp[i] = Math.max(dp[i],dp[j] +1);
               }
           }

           maxlen = Math.max(maxlen, dp[i]);
       }

       return maxlen;
    }

//   Agar hum LIS ko right to left chala de to to us index se shuru hone  wla LDS mil jaega -> Remeber us index se shuru hone wala
//    /\  <- is point se shuru hoke 
//   /  \
//  /    \  <- yahn khatam hona

    public static int LIS_RL(int arr[], int dp[]){
        int n = arr.length, maxlen =0;
 
        for( int i =n-1; i>=0; i--){
            dp[i] = 1;
 
            for( int j = i +1; j<n; j++){
                if( arr[i] > arr[j]){
                  dp[i] = Math.max(dp[i],dp[j] +1);
                }
            }
 
            maxlen = Math.max(maxlen, dp[i]);
        }
 
        return maxlen;
     }

    //  Sign change karke LIS ko left to right chala dene se LDS ban jaata hai 
    // us Index pe khatam hone wala LDS -Remember uspe khatam
     public static int LDS_LR(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    
  

// Longest Bitonic subsequence  
// https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int bitonicSequence(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] LIS = new int[n];
        int[] LDS = new int[n];

        LIS_LR(arr, LIS);
        LIS_RL(arr, LDS);

        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen, LIS[i] + LDS[i] - 1);
        }

        return maxLen;
    }


//   Maximum sum increasing subsequence  GFG
    public int maxSumIS(int arr[], int dp[])  
	{   int n = arr.length, maxSum =0;
        for( int i =0; i<n; i++){
            dp[i] = arr[i];
            int Tempmax = 0;
            for( int j = i -1; j>=0; j--){
                if( arr[i] > arr[j]){
                    Tempmax = Math.max(dp[j],Tempmax);
                
                }
            }
                dp[i] = dp[i] + Tempmax;
            
            maxSum = Math.max(maxSum, dp[i]);
        }
        
        return maxSum;
	} 
    



    public static int LDS_RL(int[] arr, int[] dp) {
        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    

    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    // Mimimum deletetion required to make array sorted.

    public static int minDeletion(int[] arr) {
        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] >= arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }

        return arr.length - maxLen;
    }

    // 673
    public int findNumberOfLIS(int[] arr) {

        int n = arr.length, maxLen = 0, maxCount = 0;
        int[] dp = new int[n];
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxCount = count[i];
            } else if (dp[i] == maxLen) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

    // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
    // {{sp,ep}...}
    public static int maxmimumBridge(int[][] arr) {
        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        int n = arr.length, maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i][0] > arr[j][0] && arr[i][1] > arr[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

   //354


    public static void main(String[] args) {
        int arr[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };
        int n = arr.length;
        int maxlen = 0;
        int dp[] = new int[n];
        // return LIS_DP_LR(arr, dp);
        for( int i = 0; i<n; i++){
            maxlen = Math.max(maxlen,LIS_memo(arr, i, dp));
        }
        System.out.println(maxlen);
    }


}
