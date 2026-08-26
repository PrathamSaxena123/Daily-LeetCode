class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + k; j <= n; j++) {
                String t = s.substring(i, j);
                int countOnes = 0;
                for (int idx = 0; idx < t.length(); idx++) {
                    if (t.charAt(idx) == '1') {
                        countOnes++;
                    }
                }
                
                if (countOnes == k) {
                    if (ans.equals("") || j - i < ans.length() || (j - i == ans.length() && t.compareTo(ans) < 0)) {
                        ans = t;
                    }
                }
            }
        }
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna