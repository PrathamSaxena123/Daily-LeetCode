class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] stores the max relative score margin from index i to n-1
        int[] dp = new int[n + 1];
        
        // Base case: No stones left to pick
        dp[n] = 0;
        
        // Build DP table from right to left
        for (int i = n - 1; i >= 0; i--) {
            int takeSum = 0;
            int maxMargin = Integer.MIN_VALUE;
            
            // Current player can pick up to 3 piles, if they exist
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                takeSum += stoneValue[i + k - 1];
                int currentMargin = takeSum - dp[i + k];
                maxMargin = Math.max(maxMargin, currentMargin);
            }
            
            dp[i] = maxMargin;
        }
        
        // Determine the game outcome based on Alice's final margin from index 0
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna