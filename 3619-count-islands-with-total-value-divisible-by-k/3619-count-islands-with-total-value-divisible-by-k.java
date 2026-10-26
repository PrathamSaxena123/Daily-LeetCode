class Solution {
    private int m;
    private int n;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long totalValue = dfs(grid, i, j);
                    if (totalValue % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] <= 0) {
            return 0;
        }

        long val = grid[r][c];
        grid[r][c] = 0;

        val += dfs(grid, r + 1, c);
        val += dfs(grid, r - 1, c);
        val += dfs(grid, r, c + 1);
        val += dfs(grid, r, c - 1);

        return val;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna