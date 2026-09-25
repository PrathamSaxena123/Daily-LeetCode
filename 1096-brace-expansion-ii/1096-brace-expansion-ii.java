class Solution {
    private Set<String> resultSet = new HashSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private void dfs(String exp) {
        int j = exp.indexOf('}');
        if (j == -1) {
            resultSet.add(exp);
            return;
        }
        
        int i = exp.lastIndexOf('{', j);
        
        String prefix = exp.substring(0, i);
        String suffix = exp.substring(j + 1);
        
        String[] options = exp.substring(i + 1, j).split(",");
        
        for (String option : options) {
            dfs(prefix + option + suffix);
        }
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna