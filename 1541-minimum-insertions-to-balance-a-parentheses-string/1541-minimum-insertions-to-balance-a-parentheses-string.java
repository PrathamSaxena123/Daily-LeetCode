public class Solution {
    public int minInsertions(String s) {
        int insertions = 0; 
        int needed = 0;     
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                needed += 2;
                if (needed % 2 != 0) {
                    insertions++;
                    needed--;
                }
            } else { 
                needed--;
                if (needed < 0) {
                    insertions++; 
                    needed += 2;  
                }
            }
        }
        return insertions + needed;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna