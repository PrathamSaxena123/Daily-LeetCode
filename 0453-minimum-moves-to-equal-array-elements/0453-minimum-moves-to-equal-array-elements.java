class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;
        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }
        return (int) (sum - (long) min * nums.length);
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna