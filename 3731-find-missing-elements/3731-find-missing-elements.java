import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> missing = new ArrayList<>();
        
        // 1. Sort the array to find sequential gaps easily
        Arrays.sort(nums);
        
        // 2. Iterate through adjacent elements and find missing integers
        for (int i = 0; i < nums.length - 1; i++) {
            int current = nums[i];
            int next = nums[i + 1];
            
            // Collect numbers between the current element and the next element
            for (int j = current + 1; j < next; j++) {
                missing.add(j);
            }
        }
        
        return missing;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna