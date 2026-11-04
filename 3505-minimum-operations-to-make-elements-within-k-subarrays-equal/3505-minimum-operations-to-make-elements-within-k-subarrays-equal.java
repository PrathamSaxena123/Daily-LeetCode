class Solution {
    private static final long INF = Long.MAX_VALUE / 2;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = getMinOps(nums, x);
        long[][] mem = new long[n + 1][k + 1];
        for (long[] row : mem) {
            Arrays.fill(row, -1);
        }
        long ans = solve(nums, x, 0, k, minOps, mem);
        return ans >= INF ? -1 : ans;
    }

    private long solve(int[] nums, int x, int i, int k, long[] minOps, long[][] mem) {
        if (k == 0) return 0;
        if (i >= nums.length) return INF;
        if (mem[i][k] != -1) return mem[i][k];

        long res = solve(nums, x, i + 1, k, minOps, mem);
        if (i + x <= nums.length) {
            res = Math.min(res, minOps[i] + solve(nums, x, i + x, k - 1, minOps, mem));
        }
        return mem[i][k] = res;
    }

    private long[] getMinOps(int[] nums, int x) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        int lowerSize = 0, upperSize = 0;
        long lowerSum = 0, upperSum = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (lowerSize == 0 || num <= lower.lastKey()) {
                lower.put(num, lower.getOrDefault(num, 0) + 1);
                lowerSize++;
                lowerSum += num;
            } else {
                upper.put(num, upper.getOrDefault(num, 0) + 1);
                upperSize++;
                upperSum += num;
            }

            if (i >= x) {
                int outNum = nums[i - x];
                if (lower.containsKey(outNum)) {
                    int count = lower.get(outNum);
                    if (count == 1) lower.remove(outNum);
                    else lower.put(outNum, count - 1);
                    lowerSize--;
                    lowerSum -= outNum;
                } else {
                    int count = upper.get(outNum);
                    if (count == 1) upper.remove(outNum);
                    else upper.put(outNum, count - 1);
                    upperSize--;
                    upperSum -= outNum;
                }
            }

            while (lowerSize < upperSize) {
                int firstUpper = upper.firstKey();
                int count = upper.get(firstUpper);
                if (count == 1) upper.remove(firstUpper);
                else upper.put(firstUpper, count - 1);
                upperSize--;
                upperSum -= firstUpper;

                lower.put(firstUpper, lower.getOrDefault(firstUpper, 0) + 1);
                lowerSize++;
                lowerSum += firstUpper;
            }

            while (lowerSize - upperSize > 1) {
                int lastLower = lower.lastKey();
                int count = lower.get(lastLower);
                if (count == 1) lower.remove(lastLower);
                else lower.put(lastLower, count - 1);
                lowerSize--;
                lowerSum -= lastLower;

                upper.put(lastLower, upper.getOrDefault(lastLower, 0) + 1);
                upperSize++;
                upperSum += lastLower;
            }

            if (i >= x - 1) {
                long median = lower.lastKey();
                long ops = (median * lowerSize - lowerSum) + (upperSum - median * upperSize);
                minOps[i - x + 1] = ops;
            }
        }
        return minOps;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna