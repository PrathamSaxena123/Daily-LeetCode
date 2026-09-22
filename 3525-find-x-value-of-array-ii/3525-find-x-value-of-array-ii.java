class Solution {
    static class Node {
        int prod;
        int[] remain;
        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    private Node[] tree;
    private int n;
    private int kMod;

    private Node merge(Node left, Node right) {
        Node parent = new Node(kMod);
        parent.prod = (left.prod * right.prod) % kMod;
        System.arraycopy(left.remain, 0, parent.remain, 0, kMod);
        for (int r = 0; r < kMod; r++) {
            if (right.remain[r] > 0) {
                int nextRem = (left.prod * r) % kMod;
                parent.remain[nextRem] += right.remain[r];
            }
        }
        return parent;
    }

    private void build(int[] nums, int cur, int left, int right) {
        if (left == right) {
            tree[cur] = new Node(kMod);
            int val = nums[left] % kMod;
            tree[cur].remain[val] = 1;
            tree[cur].prod = val;
            return;
        }
        int mid = (left + right) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            java.util.Arrays.fill(tree[cur].remain, 0);
            int valMod = val % kMod;
            tree[cur].remain[valMod] = 1;
            tree[cur].prod = valMod;
            return;
        }
        int mid = (lo + hi) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, lo, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, hi, idx, val);
        }
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int lo, int hi, int ql, int qr) {
        if (ql <= lo && hi <= qr) {
            return tree[cur];
        }
        int mid = (lo + hi) / 2;
        if (qr <= mid) {
            return query(2 * cur + 1, lo, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * cur + 2, mid + 1, hi, ql, qr);
        }
        Node leftNode = query(2 * cur + 1, lo, mid, ql, mid);
        Node rightNode = query(2 * cur + 2, mid + 1, hi, mid + 1, qr);
        return merge(leftNode, rightNode);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.kMod = k;
        this.tree = new Node[4 * n];
        
        build(nums, 0, 0, n - 1);
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            update(0, 0, n - 1, idx, val);
            
            if (start >= n) {
                result[i] = (x == 1) ? 1 : 0;
                continue;
            }
            
            Node queryRes = query(0, 0, n - 1, start, n - 1);
            result[i] = queryRes.remain[x];
        }
        return result;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna