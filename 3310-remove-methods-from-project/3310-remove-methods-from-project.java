import java.util.*;

public class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the adjacency list representation of the graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] invocation : invocations) {
            int u = invocation[0];
            int v = invocation[1];
            graph[u].add(v); // u invokes v
        }

        // Step 2: Mark all suspicious methods reachable from k using BFS
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(k);
        isSuspicious[k] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph[curr]) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        // Step 3: Check if any non-suspicious method invokes a suspicious method
        for (int[] invocation : invocations) {
            int u = invocation[0];
            int v = invocation[1];
            // If the invoker is safe (not suspicious) but calls a suspicious method
            if (!isSuspicious[u] && isSuspicious[v]) {
                // Cannot remove any method; return all methods from 0 to n-1
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // Step 4: Collect and return only the non-suspicious methods
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna