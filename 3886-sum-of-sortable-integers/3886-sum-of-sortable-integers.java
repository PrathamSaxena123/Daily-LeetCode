import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                boolean ok = true;
                int preMax = -1;
                
                for (int j = 0; j < n; j += i) {
                    int cnt = 1;
                    int mx = -1;
                    int mn = Integer.MAX_VALUE;
                    
                    for (int k = j; k < j + i; k++) {
                        mx = Math.max(mx, nums[k]);
                        mn = Math.min(mn, nums[k]);
                        if (k > j && nums[k] < nums[k - 1]) {
                            cnt++;
                        }
                    }
                    
                    if (cnt > 2 || mn < preMax) {
                        ok = false;
                        break;
                    }
                    if (cnt == 2 && nums[j + i - 1] > nums[j]) {
                        ok = false;
                        break;
                    }
                    
                    preMax = mx;
                }
                
                if (ok) {
                    ans += i;
                }
            }
        }
        
        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna