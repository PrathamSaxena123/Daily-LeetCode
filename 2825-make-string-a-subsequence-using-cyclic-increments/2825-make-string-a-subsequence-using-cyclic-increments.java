class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int i = 0;
        int j = 0;
        
        while (i < n1 && j < n2) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(j);
            
            if (c1 == c2 || (c1 + 1 == c2) || (c1 == 'z' && c2 == 'a')) {
                j++;
            }
            i++; 
        }
        
        return j == n2;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna