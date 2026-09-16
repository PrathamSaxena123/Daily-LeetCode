class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalPool = n + k - 1;
        int chooseNum = 2 * k;
        if (totalPool < chooseNum) {
            return 0;
        }
        
        long num = 1;
        long den = 1;
        for (int i = 1; i <= chooseNum; i++) {
            num = (num * (totalPool - i + 1)) % MOD;
            den = (den * i) % MOD;
        }
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }
    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }
    
    private long power(long base, int exp, int mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna