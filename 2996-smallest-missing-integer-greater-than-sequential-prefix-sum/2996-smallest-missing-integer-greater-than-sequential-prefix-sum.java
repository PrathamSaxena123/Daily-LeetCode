import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Find the longest sequential prefix sum starting from index 0
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Store all elements of the array in a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Step 3: Increment the sum until we find a value not present in the set
        while (numSet.contains(sum)) {
            sum++;
        }

        return sum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1: Output should be 6
        int[] nums1 = {1, 2, 3, 2, 5};
        System.out.println("Result 1: " + sol.missingInteger(nums1)); 

        // Example 2: Output should be 15
        int[] nums2 = {3, 4, 5, 1, 12, 14, 13};
        System.out.println("Result 2: " + sol.missingInteger(nums2)); 
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna