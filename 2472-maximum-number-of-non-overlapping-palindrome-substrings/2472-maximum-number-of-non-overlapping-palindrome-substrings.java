import java.util.Arrays;

class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (boolean[] row : isPalindrome) {
            Arrays.fill(row, true);
        }
        
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }
        
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(0, s, k, isPalindrome, memo);
    }
    
    private int dfs(int i, String s, int k, boolean[][] isPalindrome, int[] memo) {
        if (i >= s.length()) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        
        int res = dfs(i + 1, s, k, isPalindrome, memo);
        
        for (int j = i + k - 1; j < s.length(); ++j) {
            if (isPalindrome[i][j]) {
                res = Math.max(res, 1 + dfs(j + 1, s, k, isPalindrome, memo));
                break; 
            }
        }
        
        return memo[i] = res;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna