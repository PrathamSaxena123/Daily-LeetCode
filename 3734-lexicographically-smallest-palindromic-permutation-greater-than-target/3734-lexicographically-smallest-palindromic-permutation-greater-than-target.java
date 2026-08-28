import java.util.Arrays;

public class Solution {
    private String result = "";
    private int n;
    public String lexPalindromicPermutation(String s, String target) {
        this.n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        int oddIndex = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddIndex = i;
            }
        }

        if ((n % 2 == 0 && oddCount != 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        char midChar = oddIndex != -1 ? (char) ('a' + oddIndex) : '#';
        int halfLen = n / 2;
        char[] currentHalf = new char[halfLen];

        if (backtrack(0, currentHalf, halfCount, midChar, target, false)) {
            return result;
        }

        return "";
    }

    private boolean backtrack(int index, char[] currentHalf, int[] halfCount, char midChar, String target, boolean isGreater) {
        if (index == n / 2) {
            String fullPalindrome = buildPalindrome(currentHalf, midChar);
            if (isGreater || fullPalindrome.compareTo(target) > 0) {
                result = fullPalindrome;
                return true; 
            }
            return false;
        }

        for (int i = 0; i < 26; i++) {
            if (halfCount[i] > 0) {
                char ch = (char) ('a' + i);
                
                if (!isGreater && ch < target.charAt(index)) {
                    continue;
                }

                currentHalf[index] = ch;
                halfCount[i]--;

                boolean nextIsGreater = isGreater || (ch > target.charAt(index));

                if (backtrack(index + 1, currentHalf, halfCount, midChar, target, nextIsGreater)) {
                    return true;
                }

                halfCount[i]++;
            }
        }
        return false;
    }

    private String buildPalindrome(char[] firstHalf, char midChar) {
        StringBuilder sb = new StringBuilder(new String(firstHalf));
        String secondHalf = new StringBuilder(sb).reverse().toString();
        if (midChar != '#') {
            sb.append(midChar);
        }
        sb.append(secondHalf);
        return sb.toString();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna