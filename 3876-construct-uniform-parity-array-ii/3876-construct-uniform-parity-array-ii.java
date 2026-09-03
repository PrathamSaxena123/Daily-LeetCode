class Solution {
    public boolean uniformArray(int[] nums1) {
        final int inf = Integer.MAX_VALUE;
        int mn = inf;
        for (int x : nums1) {
            if (x % 2 == 1) {
                mn = Math.min(mn, x);
            }
        }
        for (int x : nums1) {
            if (x % 2 == 0 && mn != inf && x < mn) {
                return false;
            }
        }
        return true;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna