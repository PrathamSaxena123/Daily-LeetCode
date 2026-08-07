class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    public int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
}

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(2 * n + 1);
        int s = n + 1;
        tree.update(s, 1);
        long ans = 0;
        for (int x : nums) {
            s += x == target ? 1 : -1;
            ans += tree.query(s - 1);
            tree.update(s, 1);
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna