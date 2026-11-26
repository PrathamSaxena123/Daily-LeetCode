import java.util.*;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 133_333_331L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n, ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUnique(nums, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUnique(int[] nums, int len) {
        int n = nums.length;
        long h1 = 0, h2 = 0;
        long p1 = 1, p2 = 1;

        for (int i = 0; i < len; i++) {
            long val = nums[i] + 1000000007L;
            h1 = (h1 * BASE1 + val) % MOD1;
            h2 = (h2 * BASE2 + val) % MOD2;
            if (i < len - 1) {
                p1 = (p1 * BASE1) % MOD1;
                p2 = (p2 * BASE2) % MOD2;
            }
        }

        Map<Long, Integer> freq = new HashMap<>();
        long key = (h1 << 32) | (h2 & 0xFFFFFFFFL);
        freq.put(key, 1);

        for (int i = len; i < n; i++) {
            long outVal = nums[i - len] + 1000000007L;
            long inVal = nums[i] + 1000000007L;

            h1 = (h1 - (outVal % MOD1) * p1 % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + inVal) % MOD1;

            h2 = (h2 - (outVal % MOD2) * p2 % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + inVal) % MOD2;

            key = (h1 << 32) | (h2 & 0xFFFFFFFFL);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna