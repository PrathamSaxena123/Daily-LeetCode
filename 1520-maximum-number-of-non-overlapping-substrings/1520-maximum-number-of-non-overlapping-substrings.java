class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = Math.max(right[idx], i);
        }

        List<String> res = new ArrayList<>();
        int lastRight = -1;

        for (int i = 0; i < n; i++) {
            if (i != left[s.charAt(i) - 'a']) continue;
            
            int newRight = right[s.charAt(i) - 'a'];
            boolean valid = true;
            for (int j = i; j <= newRight; j++) {
                int charIdx = s.charAt(j) - 'a';
                if (left[charIdx] < i) {
                    valid = false;
                    break;
                }
                newRight = Math.max(newRight, right[charIdx]);
            }

            if (valid) {
                if (i > lastRight) {
                    res.add(s.substring(i, newRight + 1));
                } else {
                    res.set(res.size() - 1, s.substring(i, newRight + 1));
                }
                lastRight = newRight;
            }
        }
        return res;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna