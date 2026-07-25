class Solution {
    public int maxProduct(int n) {
        int largest = 0;
        int secondLargest = 0;
        while (n > 0) {
            int currentDigit = n % 10;
            n /= 10;
            if (currentDigit > largest) {
                secondLargest = largest;
                largest = currentDigit;
            } else if (currentDigit > secondLargest) {
                secondLargest = currentDigit;
            }
        }
        return largest * secondLargest;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna