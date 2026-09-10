class Solution {
    private int result = 0;

    public int averageOfSubtree(TreeNode root) {
        calculateSubtree(root);
        return result;
    }

    private int[] calculateSubtree(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0}; 
        }

        int[] left = calculateSubtree(root.left);
        int[] right = calculateSubtree(root.right);

        int currentSum = left[0] + right[0] + root.val;
        int currentCount = left[1] + right[1] + 1;

        if (currentSum / currentCount == root.val) {
            result++;
        }

        return new int[]{currentSum, currentCount};
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna