i436. Find Right Interval My SubmissionsBack To Contest
User Accepted: 211
User Tried: 281
Total Accepted: 215
Total Submissions: 514
Difficulty: Medium
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

public class Solution {
    class IMap{
        public int value;
        public int index;
        public IMap(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
    public int[] findRightInterval(Interval[] intervals) {
        int len = intervals.length;
        ArrayList<IMap> list = new ArrayList<IMap>();
        for(int i = 0; i < len; i++){
            IMap iMap= new IMap(intervals[i].start,i);
            list.add(iMap);
        }
        Collections.sort(list, new Comparator<IMap>(){
            @Override
            public int compare(IMap i1, IMap i2) {
                return i1.value - i2.value;
            }
        });
        int[] ret = new int[len];
        for(int i = 0; i < len; i++){
            ret[i] = binarySearch(list, intervals[i].end);
        }
        return ret;
    }
    public int binarySearch(ArrayList<IMap> list, int target) {
    if (list == null || list.size() == 0) {
        return -1;
    }

    int start = 0;
    int end = list.size() - 1;
    int mid;
    while (start + 1 < end) {
        mid = start + (end - start) / 2; // avoid overflow when (end + start)
        if (target < list.get(mid).value) {
            end = mid;
        } else if (target > list.get(mid).value) {
            start = mid;
        } else {
            end = mid;
        }
    }

    if (list.get(start).value == target) {
        return list.get(start).index;
    }
    if (list.get(end).value == target) {
        return list.get(end).index;
    }

    return -1;
    }
}
