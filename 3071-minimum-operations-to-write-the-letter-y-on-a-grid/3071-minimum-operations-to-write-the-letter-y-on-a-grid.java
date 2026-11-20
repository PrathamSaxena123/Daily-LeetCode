class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int mid = n / 2;
        int[] yCount = new int[3];
        int[] nonYCount = new int[3];        
        int totalY = 0;
        int totalNonY = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                boolean isTopLeftDiag = (r < mid && r == c);
                boolean isTopRightDiag = (r < mid && r + c == n - 1);
                boolean isBottomStem = (r >= mid && c == mid);
                if (isTopLeftDiag || isTopRightDiag || isBottomStem) {
                    yCount[grid[r][c]]++;
                    totalY++;
                } else {
                    nonYCount[grid[r][c]]++;
                    totalNonY++;
                }
            }
        }
        int minOperations = Integer.MAX_VALUE;
        for (int yVal = 0; yVal < 3; yVal++) {
            for (int nonYVal = 0; nonYVal < 3; nonYVal++) {
                if (yVal == nonYVal) continue;
                int ops = (totalY - yCount[yVal]) + (totalNonY - nonYCount[nonYVal]);
                minOperations = Math.min(minOperations, ops);
            }
        }
        return minOperations;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna