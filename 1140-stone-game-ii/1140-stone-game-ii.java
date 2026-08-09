class Solution {
    private int[] suffixSum;
    private int[][] memo;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        memo = new int[n][n + 1];
        return dp(0, 1);
    }

    private int dp(int idx, int m) {
        if (idx + 2 * m >= n) {
            return suffixSum[idx];
        }
        if (memo[idx][m] != 0) {
            return memo[idx][m];
        }
        
        int maxStones = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int opponentStones = dp(idx + x, Math.max(m, x));
            maxStones = Math.max(maxStones, suffixSum[idx] - opponentStones);
        }
        
        return memo[idx][m] = maxStones;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna