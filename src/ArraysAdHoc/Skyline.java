package ArraysAdHoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java
 *
 * Time complexity is O(nlogn)
 * Space complexity is O(n)
 *
 * References
 * https://leetcode.com/problems/the-skyline-problem/
 * https://leetcode.com/discuss/67091/once-for-all-explanation-with-clean-java-code-nlog-time-space
 */
public class Skyline {

    static class EdgePoint implements Comparable<EdgePoint> {

        private int x;
        private int ht;
        private boolean isStart;

        @Override
        /**
         * first compare by x
         * if they are equal, then do the following comparison
         * if 2 starts are being compared, then height of taller building to be picked
         * if 2 ends are being compared, then height of shorter building to be picked
         * if one is start and other is end, then start should appear first
         */
        public int compareTo(EdgePoint o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.ht : this.ht) - (o.isStart ? -o.ht : o.ht);
            }
        }
    }

    public static List<int[]> getSkyline(int buildingEdges[][]) {

        //for all start and end of building put them into List of BuildingPoint
        EdgePoint[] edges = new EdgePoint[buildingEdges.length * 2];
        int index = 0;
        for (int building[] : buildingEdges) {
            edges[index] = new EdgePoint();
            edges[index].x = building[0]; // index 1 = left edge x value
            edges[index].isStart = true;
            edges[index].ht = building[1];

            edges[index + 1] = new EdgePoint();
            edges[index + 1].x = building[2]; // index 2 = right edge x value
            edges[index + 1].isStart = false;
            edges[index + 1].ht = building[1];
            index += 2;
        }

      /*  System.out.println("*** BEFORE ***");
        for(EdgePoint e : edges){
            System.out.println(e.x + "," + e.ht + ", " + e.isStart);
        }
*/
        Arrays.sort(edges);

        /*System.out.println("*** AFTER ***");
        for(EdgePoint e : edges){
            System.out.println(e.x + "," + e.ht + ", " + e.isStart);
        }*/

        //using TreeMap because it gives log time performance.
        //PriorityQueue in java does not support remove(object) operation in log time.
        TreeMap<Integer, Integer> queue = new TreeMap<>(); // map of edge and its height

        queue.put(0, 1);

        int prevMaxHeight = 0;

        List<int[]> skylineResult = new ArrayList<>();

        for (EdgePoint edge : edges) {
            //if start of an edge, then add height to the queue.
            // If height already exists, increment
            if (edge.isStart) {
                /*queue.compute(edge.ht, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });*/

                Integer val = queue.get(edge.ht);
                if (val != null) {
                    queue.put(edge.ht, val++);
                } else {
                    queue.put(edge.ht, 1);
                }
            } else {
                //edge is end of building then decrement or remove the height from map.
                Integer val = queue.get(edge.ht);
                if (val != null) {

                    if (val == 1) {
                        queue.remove(edge.ht);

                    } else {
                        queue.put(edge.ht, val--);
                    }
                }

//              if(queue.containsKey(edge.ht)) {
//                  queue.compute(edge.ht, (key, value) -> {
//                      if (value == 1) {
//                          return null;
//                      }
//                      return value - 1;
//                  });
//              }
            }

            //peek the current height (MAX in the queue) after addition or removal of building x.
            int currentMaxHeight = queue.lastKey();
            System.out.println(edge.x + "," + edge.isStart + ", " + edge.ht);
            System.out.println(currentMaxHeight);
            System.out.println(prevMaxHeight);

            if (currentMaxHeight != prevMaxHeight) {
                skylineResult.add(new int[]{edge.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }

            System.out.println("After prevMaxHeight " + prevMaxHeight);

        }


        return skylineResult;
    }

    public static void main(String args[]) {
//        int[][] buildings = {{1, 3, 4}, {3, 4, 4}, {2, 6, 2} ,{8,11,4}, {7,9,3},{10,11,2}};
        int [][] buildings = {{1, 11, 5}, {2, 6, 7}, {3, 13, 9},
                {12, 7, 16}, {14, 3, 25}, {19, 18, 22},
                {23, 13, 29}, {24, 4, 28}};
        List<int[]> criticalPoints = getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));

    }
}
