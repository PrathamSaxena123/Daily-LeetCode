import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Normalize k to avoid redundant full rotations
        k = k % totalElements;
        
        // Initialize the result structure with placeholder elements
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            result.add(row);
        }
        
        // Map old 2D positions to their new shifted 2D positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Convert 2D coordinates (i, j) to 1D index
                int old1DIndex = i * n + j;
                
                // Calculate the new 1D index after shifting k times
                int new1DIndex = (old1DIndex + k) % totalElements;
                
                // Convert the new 1D index back to 2D coordinates
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                
                // Assign the value to the result list
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        
        return result;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna