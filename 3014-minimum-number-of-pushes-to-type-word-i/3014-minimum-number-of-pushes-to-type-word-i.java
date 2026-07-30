class Solution {
    public int minimumPushes(String word) {
        int totalPushes = 0;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            // The first 8 letters take 1 push, next 8 take 2 pushes, etc.
            int multiplier = (i / 8) + 1;
            totalPushes += multiplier;
        }
        
        return totalPushes;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna