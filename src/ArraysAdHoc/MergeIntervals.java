package ArraysAdHoc;

import java.util.*;

import ArraysAdHoc.MergeIntervals.Interval;

/***
 * Given a set of time intervals in any order, merge all overlapping intervals
 * into one and output
 * 
 * the result which should have only mutually exclusive intervals.
 * 
 * e.g. for this input: {{1,3}, {2,4}, {5,7}, {6,8} }. The intervals {1,3} and
 * {2,4} overlap with
 * 
 * each other, so they should be merged and become {1, 4}. Similarly {5, 7} and
 * {6, 8} should
 * 
 * be merged and become {5, 8}.
 * 
 * Write a function which produces the set of merged intervals for the given set
 * of intervals.
 * 
 * Solution: http://www.geeksforgeeks.org/merging-intervals/
 * 
 * @author shalinishah
 *
 **/
public class MergeIntervals {

	/**
	 * Definition for an interval.
	 */
	public class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (intervals.size() == 0)
			return intervals;
		if (intervals.size() == 1)
			return intervals;

		Collections.sort(intervals, new IntervalComparator());

		for (Interval iv : intervals) {
			System.out.println(iv.start + ", " + iv.end);
		}

		Interval first = intervals.get(0);
		int start = first.start;
		int end = first.end;

		ArrayList<Interval> result = new ArrayList<Interval>();

		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.start <= end) {
				end = Math.max(current.end, end);
			} else {
				result.add(new Interval(start, end));
				start = current.start;
				end = current.end;
			}

		}

		result.add(new Interval(start, end));

		return result;

	}

	public static void main(String[] args) {

		ArrayList<Interval> list = new ArrayList<>();
		MergeIntervals mInt = new MergeIntervals();
		list.add(mInt.new Interval(1, 3));
		list.add(mInt.new Interval(8, 10));
		list.add(mInt.new Interval(2, 6));
		list.add(mInt.new Interval(15, 20));
		list.add(mInt.new Interval(11, 13));

		ArrayList<Interval> result = mInt.merge(list);

		for (Interval iv : result) {
			System.out.println(iv.start + ", " + iv.end);
		}

	}
}

class IntervalComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		Interval i1 = (Interval) o1;
		Interval i2 = (Interval) o2;
		return i1.start - i2.start;
	}
}
