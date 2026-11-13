import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = !dq.isEmpty() ? dq.peekLast() : j + 1;
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.addLast(i);

            while (cost > k) {
                cost -= (nums[dq.peekFirst()] - nums[j]);
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }
            ans += (j - i + 1);
        }
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna