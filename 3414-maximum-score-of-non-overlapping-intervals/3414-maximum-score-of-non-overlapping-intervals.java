import java.util.*;

class Solution {
    private static class Element {
        long weight;
        List<Integer> ids;

        Element() {
            this.weight = 0;
            this.ids = new ArrayList<>();
        }

        Element(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = new ArrayList<>(ids);
        }
    }

    private static class Interval implements Comparable<Interval> {
        int start, end, weight, id;

        Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(Interval other) {
            if (this.start != other.start) return Integer.compare(this.start, other.start);
            if (this.end != other.end) return Integer.compare(this.end, other.end);
            return Integer.compare(this.id, other.id);
        }
    }

    private Element[][] memo;
    private int[] nextValidIndex;

    public int[] maximumWeight(List<List<Integer>> intervalsInput) {
        int n = intervalsInput.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> curr = intervalsInput.get(i);
            intervals[i] = new Interval(curr.get(0), curr.get(1), curr.get(2), i);
        }

        Arrays.sort(intervals);

        nextValidIndex = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1, ans = n;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (intervals[mid].start > intervals[i].end) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            nextValidIndex[i] = ans;
        }

        memo = new Element[n][5];

        Element resultElement = solve(intervals, 0, 4);

        List<Integer> resultList = resultElement.ids;
        Collections.sort(resultList);
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private Element solve(Interval[] intervals, int index, int quota) {
        if (index == intervals.length || quota == 0) {
            return new Element();
        }

        if (memo[index][quota] != null) {
            return memo[index][quota];
        }

        Element skip = solve(intervals, index + 1, quota);

        Element nextState = solve(intervals, nextValidIndex[index], quota - 1);
        long takeWeight = intervals[index].weight + nextState.weight;
        List<Integer> takeIds = new ArrayList<>(nextState.ids);
        takeIds.add(intervals[index].id);

        Element best;
        if (takeWeight > skip.weight) {
            best = new Element(takeWeight, takeIds);
        } else if (skip.weight > takeWeight) {
            best = skip;
        } else {
            List<Integer> sortedTake = new ArrayList<>(takeIds);
            List<Integer> sortedSkip = new ArrayList<>(skip.ids);
            Collections.sort(sortedTake);
            Collections.sort(sortedSkip);

            if (isLexicographicallySmaller(sortedTake, sortedSkip)) {
                best = new Element(takeWeight, takeIds);
            } else {
                best = skip;
            }
        }

        memo[index][quota] = best;
        return best;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna