import java.util.*;

class Solution {

    static class Group {
        int start;
        int length;

        Group(int s, int l) {
            start = s;
            length = l;
        }
    }

    static class Pair<K, V> {
        K first;
        V second;

        Pair(K f, V s) {
            first = f;
            second = s;
        }
    }

    static class SparseTable {
        int[][] st;

        SparseTable(int[] nums) {
            int n = nums.length;
            if (n == 0) {
                st = new int[1][1];
                return;
            }

            int log = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[log][n];

            System.arraycopy(nums, 0, st[0], 0, n);

            for (int k = 1; k < log; k++) {
                for (int i = 0; i + (1 << k) <= n; i++) {
                    st[k][i] = Math.max(
                            st[k - 1][i],
                            st[k - 1][i + (1 << (k - 1))]
                    );
                }
            }
        }

        int query(int l, int r) {
            if (l > r) return 0;
            int len = r - l + 1;
            int k = 31 - Integer.numberOfLeadingZeros(len);
            return Math.max(st[k][l], st[k][r - (1 << k) + 1]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        int ones = 0;
        for (char c : s.toCharArray())
            if (c == '1') ones++;

        Pair<List<Group>, int[]> info = getZeroGroups(s);
        List<Group> groups = info.first;
        int[] groupIndex = info.second;

        List<Integer> ans = new ArrayList<>();

        if (groups.isEmpty()) {
            for (int i = 0; i < queries.length; i++)
                ans.add(ones);
            return ans;
        }

        SparseTable st = new SparseTable(getMergeLengths(groups));

        for (int[] q : queries) {

            int l = q[0];
            int r = q[1];

            int left =
                    groupIndex[l] == -1 ? -1 :
                            groups.get(groupIndex[l]).length -
                                    (l - groups.get(groupIndex[l]).start);

            int right =
                    groupIndex[r] == -1 ? -1 :
                            r - groups.get(groupIndex[r]).start + 1;

            int startAdj = groupIndex[l] + 1;
            int endAdj =
                    (s.charAt(r) == '1' ? groupIndex[r] : groupIndex[r] - 1) - 1;

            int best = ones;

            if (s.charAt(l) == '0' &&
                    s.charAt(r) == '0' &&
                    groupIndex[l] + 1 == groupIndex[r]) {

                best = Math.max(best, ones + left + right);

            } else if (startAdj <= endAdj) {

                best = Math.max(best,
                        ones + st.query(startAdj, endAdj));
            }

            if (s.charAt(l) == '0' &&
                    groupIndex[l] + 1 <=
                            (s.charAt(r) == '1'
                                    ? groupIndex[r]
                                    : groupIndex[r] - 1)) {

                best = Math.max(best,
                        ones + left +
                                groups.get(groupIndex[l] + 1).length);
            }

            if (s.charAt(r) == '0' &&
                    groupIndex[l] < groupIndex[r] - 1) {

                best = Math.max(best,
                        ones + right +
                                groups.get(groupIndex[r] - 1).length);
            }

            ans.add(best);
        }

        return ans;
    }

    private Pair<List<Group>, int[]> getZeroGroups(String s) {

        List<Group> groups = new ArrayList<>();
        int[] idx = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '0') {

                if (i > 0 && s.charAt(i - 1) == '0')
                    groups.get(groups.size() - 1).length++;
                else
                    groups.add(new Group(i, 1));
            }

            idx[i] = groups.size() - 1;
        }

        return new Pair<>(groups, idx);
    }

    private int[] getMergeLengths(List<Group> groups) {

        int[] res = new int[Math.max(0, groups.size() - 1)];

        for (int i = 0; i + 1 < groups.size(); i++)
            res[i] = groups.get(i).length + groups.get(i + 1).length;

        return res;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna