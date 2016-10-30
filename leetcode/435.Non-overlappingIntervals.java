435. Non-overlapping Intervals My SubmissionsBack To Contest
User Accepted: 186
User Tried: 283
Total Accepted: 188
Total Submissions: 656
Difficulty: Medium
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

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
    public int eraseOverlapIntervals(Interval[] intervals) {
        
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }
        
        List<Interval> intervalsL = Arrays.asList(intervals);
        int count = 0;
        Collections.sort(intervalsL, new IntervalComparator());       
  
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval last = intervalsL.get(0);
        for (int i = 1; i < intervalsL.size(); i++) {
            Interval curt = intervalsL.get(i);
            if (curt.start < last.end ){
                //System.out.println(curt.start+" "+last.end);
                
                /*
                
1,100
1,12
2,9
11,22


1,100
1,11
2,12
11,22
remove end that is bigger if two lapse
*/
              if(curt.end >last.end){
                    last = last;
               }else{
                    last = curt;
                }
                count ++;
            }else{
                last = curt;
            }
            
        }
        
        return count;
    }
    
    
    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
           int diff = a.start - b.start;
           if(diff == 0){
               return b.end - a.end;
           }else{
               return diff;
           }
        }
    }
}
