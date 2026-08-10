class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] represents if the current player can win with i stones remaining
        boolean[] dp = new boolean[n + 1];
        
        // Iterate through all stone states from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try removing every possible non-zero square number of stones
            for (int k = 1; k * k <= i; k++) {
                // If the next state leads to a loss for the opponent, current player wins
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // No need to check further moves for state i
                }
            }
        }
        
        return dp[n];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna