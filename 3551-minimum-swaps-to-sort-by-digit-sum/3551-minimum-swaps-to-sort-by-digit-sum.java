import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] digitSumPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            digitSumPairs[i][0] = getDigitSum(nums[i]);
            digitSumPairs[i][1] = nums[i];
        }
        Arrays.sort(digitSumPairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        Map<Integer, Integer> targetIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            targetIndexMap.put(digitSumPairs[i][1], i);
        }
        boolean[] visited = new boolean[n];
        int totalSwaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || targetIndexMap.get(nums[i]) == i) {
                continue;
            }
            int cycleSize = 0;
            int currentIdx = i;
            
            while (!visited[currentIdx]) {
                visited[currentIdx] = true;
                currentIdx = targetIndexMap.get(nums[currentIdx]);
                cycleSize++;
            }
            if (cycleSize > 0) {
                totalSwaps += (cycleSize - 1);
            }
        }
        
        return totalSwaps;
    }
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna