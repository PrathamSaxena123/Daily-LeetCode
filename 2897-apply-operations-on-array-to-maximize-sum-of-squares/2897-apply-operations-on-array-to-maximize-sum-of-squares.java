import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    bits[i]++;
                }
            }
        }
        long sum = 0;
        int mod = 1000000007;
        for (int i = 0; i < k; i++) {
            long num = 0;
            for (int j = 31; j >= 0; j--) {
                if (bits[j] > 0) {
                    num |= (1L << j);
                    bits[j]--;
                }
            }
            sum = (sum + num * num) % mod;
        }
        return (int) sum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna