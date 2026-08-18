class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) maxVal = Math.max(maxVal, num);
            return maxVal;
        }
        
        int[] count = new int[51];
        for (int num : nums) {
            count[num]++;
        }
        
        if (k == 1) {
            int maxUnique = -1;
            for (int num : nums) {
                if (count[num] == 1) {
                    maxUnique = Math.max(maxUnique, num);
                }
            }
            return maxUnique;
        }
        
        int ans = -1;
        if (count[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna