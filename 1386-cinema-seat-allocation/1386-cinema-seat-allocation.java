class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        java.util.Map<Integer, Integer> rowToSeats = new java.util.HashMap<>();
        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        int ans = 0;
        for (int seats : rowToSeats.values()) {
            boolean left = (seats & 0b0111100000) == 0;
            boolean right = (seats & 0b0000011110) == 0;
            boolean middle = (seats & 0b0001111000) == 0;
            if (left && right) {
                ans += 2;
            } else if (left || right || middle) {
                ans += 1;
            }
        }
        return ans + (n - rowToSeats.size()) * 2;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna