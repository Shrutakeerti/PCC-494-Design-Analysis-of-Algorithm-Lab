// MaxNonOverlappingIntervals.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MaxNonOverlappingIntervals {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of intervals
        System.out.print("Enter the number of intervals: ");
        int n = scanner.nextInt();

        // Input the intervals
        List<Interval> intervals = new ArrayList<>();
        System.out.println("Enter the intervals:");
        for (int i = 0; i < n; i++) {
            System.out.print("Interval " + (i + 1) + " start: ");
            int start = scanner.nextInt();
            System.out.print("Interval " + (i + 1) + " end: ");
            int end = scanner.nextInt();
            intervals.add(new Interval(start, end));
        }

        // Find the maximum number of non-overlapping intervals
        int maxNonOverlappingIntervals = findMaxNonOverlappingIntervals(intervals);

        // Output the result
        System.out.println("Maximum number of non-overlapping intervals: " + maxNonOverlappingIntervals);
    }

    public static int findMaxNonOverlappingIntervals(List<Interval> intervals) {
        // Sort intervals based on their end times
        Collections.sort(intervals, Comparator.comparingInt(interval -> interval.end));

        int count = 0;
        int lastEnd = Integer.MIN_VALUE;

        // Iterate through each interval
        for (Interval interval : intervals) {
            // If the current interval's start time is greater than or equal to the last selected interval's end time,
            // then select the current interval
            if (interval.start >= lastEnd) {
                count++;
                lastEnd = interval.end;
            }
        }

        return count;
    }
}
