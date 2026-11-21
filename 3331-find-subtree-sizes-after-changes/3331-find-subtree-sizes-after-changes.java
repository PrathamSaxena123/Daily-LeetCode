import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private List<Integer>[] tree;
    private int[] lastSeen;
    private int[] newParent;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = Arrays.copyOf(parent, n);
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        findNewParents(0);
        for (int i = 0; i < n; i++) {
            tree[i].clear();
        }
        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }
        calculateSubtreeSizes(0);

        return ans;
    }

    private void findNewParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }
        lastSeen[charIdx] = u;

        for (int v : tree[u]) {
            findNewParents(v);
        }
        lastSeen[charIdx] = prevAncestor;
    }

    private int calculateSubtreeSizes(int u) {
        int size = 1;
        for (int v : tree[u]) {
            size += calculateSubtreeSizes(v);
        }
        ans[u] = size;
        return size;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna