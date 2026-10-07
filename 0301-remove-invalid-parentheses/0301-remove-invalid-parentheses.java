import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;
        int leftRemove = 0, rightRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--; 
                } else {
                    rightRemove++; 
                }
            }
        }
        Set<String> visited = new HashSet<>();
        dfs(s, 0, leftRemove, rightRemove, visited, result);
        return result;
    }

    private void dfs(String s, int start, int leftRemove, int rightRemove, Set<String> visited, List<String> result) {
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(i) == s.charAt(i - 1)) continue;

            String current = s.substring(0, i) + s.substring(i + 1);
            if (leftRemove > 0 && s.charAt(i) == '(') {
                if (!visited.contains(current)) {
                    visited.add(current);
                    dfs(current, i, leftRemove - 1, rightRemove, visited, result);
                }
            }
            else if (rightRemove > 0 && s.charAt(i) == ')') {
                if (!visited.contains(current)) {
                    visited.add(current);
                    dfs(current, i, leftRemove, rightRemove - 1, visited, result);
                }
            }
        }
    }
    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false; 
        }
        return count == 0;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna