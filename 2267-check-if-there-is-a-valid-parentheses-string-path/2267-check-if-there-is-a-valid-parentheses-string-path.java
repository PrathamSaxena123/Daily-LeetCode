class Solution {
    private int m, n;
    private char[][] grid;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        this.memo = new Boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int open) {
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }
        if (open < 0 || open > (m + n) / 2) {
            return false;
        }
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }
        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }
        boolean res = false;
        if (r + 1 < m) {
            res = dfs(r + 1, c, open);
        }
        if (!res && c + 1 < n) {
            res = dfs(r, c + 1, open);
        }
        return memo[r][c][open] = res;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna