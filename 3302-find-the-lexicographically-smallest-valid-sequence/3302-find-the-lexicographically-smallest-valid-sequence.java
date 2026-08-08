import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // last[j] stores the maximum index in word1 that can match word2[j...m-1]
        // using exact matching (without using the single allowed modification).
        int[] last = new int[m];
        Arrays.fill(last, -1);
        
        int j = m - 1;
        // Populate the 'last' array by traversing from right to left
        for (int i = n - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }
        
        int[] ans = new int[m];
        int w2Idx = 0;
        boolean hasSkipped = false;
        
        // Find the lexicographically smallest sequence by traversing from left to right
        for (int i = 0; i < n && w2Idx < m; i++) {
            if (word1.charAt(i) == word2.charAt(w2Idx)) {
                ans[w2Idx] = i;
                w2Idx++;
            } else if (!hasSkipped) {
                // If it doesn't match, check if we can safely use our 1 wildcard modification.
                // We can skip if this is the last character of word2,
                // OR if the remaining suffix word2[w2Idx + 1 ... m - 1] can be matched 
                // entirely after the current index `i`.
                if (w2Idx == m - 1 || i < last[w2Idx + 1]) {
                    hasSkipped = true;
                    ans[w2Idx] = i;
                    w2Idx++;
                }
            }
        }
        
        // If we matched all characters of word2, return the sequence; otherwise return empty array.
        return w2Idx == m ? ans : new int[0];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna