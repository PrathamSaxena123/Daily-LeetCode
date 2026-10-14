class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int numNines = sum / 9;
        for (int i = 0; i < numNines; i++) {
            sb.append('9');
        }
        int remainder = sum % 9;
        if (numNines < num) {
            if (remainder > 0) {
                sb.append(remainder);
            }
            while (sb.length() < num) {
                sb.append('0');
            }
        }
        return sb.toString();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna