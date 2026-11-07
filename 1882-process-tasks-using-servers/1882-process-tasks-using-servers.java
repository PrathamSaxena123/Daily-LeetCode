import java.util.*;

class Solution {
    static class Server {
        int weight;
        int index;
        int freeTime;

        Server(int weight, int index, int freeTime) {
            this.weight = weight;
            this.index = index;
            this.freeTime = freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<Server> freeServers = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        PriorityQueue<Server> busyServers = new PriorityQueue<>((a, b) -> {
            if (a.freeTime != b.freeTime) {
                return Integer.compare(a.freeTime, b.freeTime);
            }
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new Server(servers[i], i, 0));
        }

        int currTime = 0;
        for (int i = 0; i < m; i++) {
            currTime = Math.max(currTime, i);

            if (freeServers.isEmpty() && busyServers.peek().freeTime > currTime) {
                currTime = busyServers.peek().freeTime;
            }

            while (!busyServers.isEmpty() && busyServers.peek().freeTime <= currTime) {
                freeServers.offer(busyServers.poll());
            }

            Server server = freeServers.poll();
            ans[i] = server.index;
            server.freeTime = currTime + tasks[i];
            busyServers.offer(server);
        }

        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna