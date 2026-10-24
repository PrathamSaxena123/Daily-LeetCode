class Solution {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = convertToDayOfYear(arriveAlice);
        int leaveA = convertToDayOfYear(leaveAlice);
        int arriveB = convertToDayOfYear(arriveBob);
        int leaveB = convertToDayOfYear(leaveBob);

        int startOverlap = Math.max(arriveA, arriveB);
        int endOverlap = Math.min(leaveA, leaveB);

        if (startOverlap <= endOverlap) {
            return endOverlap - startOverlap + 1;
        }
        
        return 0;
    }

    private int convertToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int dayOfYear = 0;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonth[i];
        }
        
        return dayOfYear + day;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna