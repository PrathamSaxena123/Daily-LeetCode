import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; 
            arr[i][1] = i;      
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        List<List<int[]>> groups = new ArrayList<>();
        List<int[]> currentGroup = new ArrayList<>();
        currentGroup.add(arr[0]);

        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i - 1][0] <= limit) {
                currentGroup.add(arr[i]);
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(arr[i]);
            }
        }
        groups.add(currentGroup);

        int[] result = new int[n];
        for (List<int[]> group : groups) {
            List<Integer> indices = new ArrayList<>();
            for (int[] pair : group) {
                indices.add(pair[1]);
            }
            Collections.sort(indices);

            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = group.get(i)[0];
            }
        }

        return result;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna