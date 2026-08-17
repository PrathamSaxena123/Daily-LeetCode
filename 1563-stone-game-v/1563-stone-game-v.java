import java.util.Arrays;

class Solution {
    private int[][] memo;
    private int[] prefixSums;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Precompute prefix sums for O(1) range sum queries
        prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + stoneValue[i];
        }

        return getMaxScore(0, n - 1);
    }

    private int getMaxScore(int left, int right) {
        // Base case: only one stone left, no split possible
        if (left == right) {
            return 0;
        }

        // Return cached result if already calculated
        if (memo[left][right] != -1) {
            return memo[left][right];
        }

        int maxScore = 0;

        // Try splitting the array at every possible index 'i'
        for (int i = left; i < right; i++) {
            int leftSum = getRangeSum(left, i);
            int rightSum = getRangeSum(i + 1, right);

            if (leftSum < rightSum) {
                // Bob throws away the right row, Alice gets leftSum
                maxScore = Math.max(maxScore, leftSum + getMaxScore(left, i));
            } else if (leftSum > rightSum) {
                // Bob throws away the left row, Alice gets rightSum
                maxScore = Math.max(maxScore, rightSum + getMaxScore(i + 1, right));
            } else {
                // If sums are equal, Alice chooses which part Bob throws away
                int chooseLeft = leftSum + getMaxScore(left, i);
                int chooseRight = rightSum + getMaxScore(i + 1, right);
                maxScore = Math.max(maxScore, Math.max(chooseLeft, chooseRight));
            }
        }

        return memo[left][right] = maxScore;
    }

    // Helper method to compute sum of elements from index 'l' to 'r' inclusive
    private int getRangeSum(int l, int r) {
        return prefixSums[r + 1] - prefixSums[l];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna