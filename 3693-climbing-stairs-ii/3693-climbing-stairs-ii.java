class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] f = new int[n + 1];
        final int inf = Integer.MAX_VALUE / 2;
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = costs[i - 1];
            for (int j = Math.max(0, i - 3); j < i; ++j) {
                f[i] = Math.min(f[i], f[j] + x + (i - j) * (i - j));
            }
        }
        return f[n];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna