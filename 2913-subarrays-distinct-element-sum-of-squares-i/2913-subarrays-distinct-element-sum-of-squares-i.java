class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            Set<Integer> distinctSet = new HashSet<>();
            for (int j = i; j < n; j++) {
                distinctSet.add(nums.get(j)); 
                int distinctCount = distinctSet.size(); 
                totalSum += distinctCount * distinctCount; 
            }
        }
        return totalSum;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna