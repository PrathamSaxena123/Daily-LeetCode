class Solution {
    private static final int MOD = 1_000_000_007;

    public int distinctSubseqII(String s) {
        int[] endsIn = new int[26];
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            int total = 0;
            for (int val : endsIn) {
                total = (total + val) % MOD;
            }
            endsIn[index] = (total + 1) % MOD;
        }
        
        int ans = 0;
        for (int val : endsIn) {
            ans = (ans + val) % MOD;
        }
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna