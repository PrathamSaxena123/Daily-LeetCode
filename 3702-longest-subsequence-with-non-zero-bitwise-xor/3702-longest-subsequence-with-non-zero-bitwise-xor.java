class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // Case 1: Total XOR is non-zero
        if (totalXor != 0) {
            return nums.length;
        }
        
        // Case 2: All elements are zero
        if (!hasNonZero) {
            return 0;
        }
        
        // Case 3: Total XOR is zero, but non-zero elements exist
        return nums.length - 1;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna