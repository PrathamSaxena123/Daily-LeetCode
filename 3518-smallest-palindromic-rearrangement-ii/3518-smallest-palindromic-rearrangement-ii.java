import java.util.*;

public class Solution {
    public String smallestPalindrome(String s, long k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Deduce the left half frequency and middle character (if any)
        int[] halfFreq = new int[26];
        char midChar = '#';
        int halfLength = 0;

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
            halfLength += halfFreq[i];
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
        }

        // Greedily build the left half character by character
        StringBuilder leftHalf = new StringBuilder();
        
        for (int pos = 0; pos < halfLength; pos++) {
            boolean found = false;
            for (int i = 0; i < 26; i++) {
                if (halfFreq[i] > 0) {
                    // Try putting character i at the current position
                    halfFreq[i]--;
                    
                    // Count how many unique permutations can be formed with the remaining characters
                    long ways = countPermutations(halfFreq, halfLength - 1 - pos, k);
                    
                    if (k <= ways) {
                        leftHalf.append((char) ('a' + i));
                        found = true;
                        break; // Keep this character and move to the next position
                    } else {
                        k -= ways; // Skip all permutations starting with this character
                        halfFreq[i]++; // Backtrack and try the next larger character
                    }
                }
            }
            // If we couldn't place any character, k is out of bounds
            if (!found) return "";
        }

        // If k wasn't reduced down to 1 by the end, k is out of bounds
        if (k != 1) return "";

        // Construct the full palindrome using the left half
        String leftStr = leftHalf.toString();
        String rightStr = new StringBuilder(leftStr).reverse().toString();
        
        if (midChar != '#') {
            return leftStr + midChar + rightStr;
        }
        return leftStr + rightStr;
    }

    // Safely calculates unique multiset permutations using combination counts (C(n, r))
    // It caps values to k to avoid long overflow errors.
    private long countPermutations(int[] halfFreq, int remainingLength, long limit) {
        long totalWays = 1;
        int slotsLeft = remainingLength;

        for (int count : halfFreq) {
            if (count > 0) {
                long combinations = nCr(slotsLeft, count, limit);
                // Safe multiplication to prevent overflow
                if (totalWays > limit / combinations) {
                    return limit + 1; 
                }
                totalWays *= combinations;
                slotsLeft -= count;
            }
        }
        return totalWays;
    }

    // Computes nCr safely while capping the value at the limit
    private long nCr(int n, int r, long limit) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;

        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
            if (res > limit) return limit + 1; // Cap early to avoid overflow
        }
        return res;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna