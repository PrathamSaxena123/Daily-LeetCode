class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // DP array to store maximum relative score difference for subarray [i, j]
        int[][] dp = new int[n][n];
        
        // Base case: when there's only one element, the player must pick it
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // Build the table for subarrays of increasing lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Maximum score difference if player picks from left (i) vs right (j)
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        
        // Player 1 wins if the net score difference for the entire array is >= 0
        return dp[0][n - 1] >= 0;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna