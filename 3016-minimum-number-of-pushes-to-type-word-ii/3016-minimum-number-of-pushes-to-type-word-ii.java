import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count the frequency of each character
        int[] frequency = new int[26];
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(frequency);
        
        int totalPushes = 0;
        int distinctLettersProcessed = 0;
        
        // Step 3: Iterate backwards from the most frequent character
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) {
                break; // No more characters to process
            }
            
            // Calculate how many pushes are needed for this tier
            // First 8 distinct letters need 1 push, next 8 need 2 pushes, etc.
            int pushesPerPress = (distinctLettersProcessed / 8) + 1;
            
            totalPushes += frequency[i] * pushesPerPress;
            distinctLettersProcessed++;
        }
        
        return totalPushes;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna