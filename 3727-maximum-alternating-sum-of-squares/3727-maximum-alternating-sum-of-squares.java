import java.util.Arrays;
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squares);
        long totalScore = 0;
        int k = n / 2;
        for (int i = 0; i < k; i++) {
            totalScore -= squares[i];
        }
        for (int i = k; i < n; i++) {
            totalScore += squares[i];
        }
        return totalScore;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna