class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;
            newDp[numMod] = 1;
            for (int i = 0; i < k; i++) {
                int newMod = (int) (((long) i * numMod) % k);
                newDp[newMod] += dp[i];
            }
            for (int i = 0; i < k; i++) {
                ans[i] += newDp[i];
            }
            
            dp = newDp;
        }
        
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna