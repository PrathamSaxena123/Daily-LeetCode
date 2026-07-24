import java.util.Arrays;

public class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Find the maximum value in the array to determine the upper bound
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // The maximum possible XOR of two numbers below maxVal is less than the next power of 2
        // A safe upper bound for checking unique values is (maxVal * 2)
        int bound = maxVal == 0 ? 1 : Integer.highestOneBit(maxVal) << 2;
        
        // Step 1: Find all unique XOR values achievable by combining pairs (any two elements)
        boolean[] achievedPairs = new boolean[bound];
        for (int a : nums) {
            for (int b : nums) {
                achievedPairs[a ^ b] = true;
            }
        }
        
        // Step 2: Combine the achieved pair results with a third element
        boolean[] achievedTriplets = new boolean[bound];
        for (int pairXor = 0; pairXor < bound; pairXor++) {
            if (achievedPairs[pairXor]) {
                for (int c : nums) {
                    achievedTriplets[pairXor ^ c] = true;
                }
            }
        }
        
        // Step 3: Count the total number of unique XOR values
        int uniqueCount = 0;
        for (boolean achieved : achievedTriplets) {
            if (achieved) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna