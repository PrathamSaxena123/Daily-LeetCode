import java.util.ArrayList;
import java.util.List;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(i * 100 + j);
                }
                if (img2[i][j] == 1) {
                    list2.add(i * 100 + j);
                }
            }
        }
        int[] count = new int[3600];
        int maxOverlap = 0;
        
        for (int p1 : list1) {
            for (int p2 : list2) {
                int rowDiff = (p2 / 100) - (p1 / 100) + 30;
                int colDiff = (p2 % 100) - (p1 % 100) + 30;
                
                int hash = rowDiff * 60 + colDiff;
                count[hash]++;
                maxOverlap = Math.max(maxOverlap, count[hash]);
            }
        }
        
        return maxOverlap;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna