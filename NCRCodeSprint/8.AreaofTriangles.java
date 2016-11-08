/*
Area of Triangles locked
by erdemkiraz
Problem
Submissions
Leaderboard
Discussions
Editorial 
Given  triangles, find and print the total area covered by the triangles. Note that some of the triangles may overlap, and the area covered by intersecting triangles must only be counted once.

Input Format

The first line contains an integer, , denoting the number of triangles. 
Each of the  subsequent lines contains six space-separated integers denoting the respective values of , , , , , and . These six values define a single triangle with the vertices , , and .

Constraints

For  of the total score, .
Output Format

Print a single real number denoting the total area. The answer is considered to be correct if it has a relative error of at most .

Sample Input

3
1 1 5 1 3 3
1 2 5 2 5 6
1 6 5 2 1 2
Sample Output

15.000000
Explanation

The diagram below depicts the three triangles given as input:

image

 
 
 
 
 
 

Thus, we print the real number  as our answer.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 * https://www.hackerrank.com/contests/ncr-codesprint/challenges/area-of-triangles
 */
public class Solution8 {

	public static final double fMin = -1000000; //can't be 6 0s, will lose precision in intersect function
	public static final double fMax = 1000000;
	
	static class Point{
		public double x;
		public double y;
		int tri;
		public Point(double x, double y){this.x = x; this.y = y;}
		public String toString(){
			return "(p:"+x+","+y+")";
		}
	}
	
	long doubleToLong(double input){
		return (long) (input * 10000000);//70
	}
	
	double longToDouble(long input){
		return (double)input/10000000.0;
	}
	static class Line
	{
		public Point p1;
		public Point p2;
		public Line(Point x, Point y) { this.p1 = x; this.p2 = y; }
		public String toString(){
			return "(l:"+p1.toString()+","+p2.toString()+")";
		}
	}
	
	static class Triangle{
		List<Point> points;
		List<Line> lines;
		BigDecimal minX = new BigDecimal(Double.toString(fMax)).setScale(10, RoundingMode.HALF_UP);
		BigDecimal maxX = new BigDecimal(Double.toString(fMin)).setScale(10, RoundingMode.HALF_UP);
		Triangle(Point p1, Point p2, Point p3) { 
			points = new ArrayList<Point>();
			points.add(p1);
			points.add(p2);
			points.add(p3);
			
			lines = new ArrayList<Line>();
			lines.add(new Line(p1, p2));
			lines.add(new Line(p1, p3));
			lines.add(new Line(p2, p3));
			for(int i = 0;i<3;i++){
				double tempX = points.get(i).x;
				if(minX.doubleValue() > tempX){
					minX = new BigDecimal(Double.toString(tempX)).setScale(10, RoundingMode.HALF_UP);
				}
				if(maxX.doubleValue() < tempX){
					maxX = new BigDecimal(Double.toString(tempX)).setScale(10, RoundingMode.HALF_UP);
				}
			}
		}
		public String toString(){
			return "(t:"+lines.get(0).toString()+","+lines.get(1).toString()+","+lines.get(2).toString()+")";
		}
	}
	
	
	/* Merge Intervals Reference jiuzhang*/
	
	 public static class Interval{
		      double start;
		      double end;
		      Interval(double s, double e) { 
		    	  if(s<e){
		    		  start = s; end = e; 
		    	  }else{
		    		  start = e; end = s; 
		    	  }
		    	  
		      }
		      public String toString(){
		    	  return ("(iv:"+start+","+end+")");
		      }
	 }
	 
    private static class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            double diff = a.start - b.start;
            if(diff<0) return -1;
            if(diff>0) return 1;
            return 0;
        }
    }

	 public static List<Interval> merge(List<Interval> list) {
	        if (list == null || list.size() <= 1) {
	            return list;
	        }
	        Collections.sort(list, new IntervalComparator());       
	        ArrayList<Interval> result = new ArrayList<Interval>();
	        Interval last = list.get(0);
	        for (int i = 1; i < list.size(); i++) {
	            Interval curt = list.get(i);
	            if (curt.start <= last.end ){
	                last.end = Math.max(last.end, curt.end);
	            }else{
	                result.add(last);
	                last = curt;
	            }
	        }
	        result.add(last);
	        return result;
	 }
	 
	public static List<BigDecimal> findXofScanLine(Triangle[] tArr){
    	Set<BigDecimal> xSet = new HashSet<BigDecimal>();
    	for(int i = 0;i<tArr.length;i++){
    		//Add all intersections
    		for(int j=i+1;j<tArr.length;j++){
    			for(int k=0;k<3;k++){
    				for(int l=0;l<3;l++){//fixed bug here, 3x3 = 9 instead of 3x2 =6 for comparing two triangles
	    				Point iP=lineIntersect(tArr[i].lines.get(k),tArr[j].lines.get(l));
	    				if(iP!=null){
	    					xSet.add(new BigDecimal(Double.toString(iP.x)).setScale(10, RoundingMode.HALF_UP));
	    				}
    				}
    			}
    		}
    		//Add all triangle points
			for(int j = 0;j<3;j++){
				xSet.add(new BigDecimal(Double.toString(tArr[i].points.get(j).x)).setScale(10, RoundingMode.HALF_UP));
			}
    	}
    	List<BigDecimal> result = new ArrayList<BigDecimal>(xSet);
		Collections.sort(result);
		return result;
    }
	 
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	Triangle[] tArr = new Triangle[n];
    	for(int i = 0;i<n;i++){
    		tArr[i] = new Triangle(new Point(scan.nextInt(),scan.nextInt()),
    							   new Point(scan.nextInt(),scan.nextInt()),
    							   new Point(scan.nextInt(),scan.nextInt()));
    	}
    	List<BigDecimal> xList = findXofScanLine(tArr);
    	Map<BigDecimal, Integer> xListIndexMap = new HashMap<BigDecimal, Integer>();
    	for(int i = 0; i<xList.size();i++){
    		xListIndexMap.put(xList.get(i), i);
    		//System.out.println(xList.get(i));
    	}
    	/*find active triangles*/
    	Map<BigDecimal, List<Interval>> activeIntervalsMap = new HashMap<BigDecimal, List<Interval>>();
    	//for each triangle
    	for(int i = 0;i<tArr.length;i++){
//    		if(i==1){
//    			System.out.print("");
//    		}
    		//for each scanLines that cut through, append intervals
//    		System.out.println("minX"+tArr[i].minX);System.out.println("maxX"+tArr[i].maxX);
    		for(int j=xListIndexMap.get(tArr[i].minX);j<=xListIndexMap.get(tArr[i].maxX);j++){
    			Double curX = xList.get(j).doubleValue();
    			BigDecimal key = new BigDecimal(Double.toString(curX)).setScale(10,RoundingMode.HALF_UP);
    			Interval intersectInterval = findIntersectInterval(curX,tArr[i]);
    			
    			if(intersectInterval!=null){
		    		if(activeIntervalsMap.containsKey(key)){
		    			activeIntervalsMap.get(key).add(intersectInterval);
		    		}else{
		    			List<Interval> intervals = new ArrayList<Interval>();
		    			intervals.add(intersectInterval);
		    			activeIntervalsMap.put(key, intervals);
		    		}
    			}
			}
    		//System.out.println();
    		
    	}
    	
    	BigDecimal preX = xList.get(0);
    	double preSum = intervalSum(preX,activeIntervalsMap);
    	double tArea= 0;
    	for(int i = 1;i<xList.size();i++){
    		BigDecimal curX = xList.get(i);
    		double curSum = intervalSum(curX,activeIntervalsMap);
    		tArea += (preSum+curSum)*(curX.doubleValue()-preX.doubleValue())/2.0;
    		preSum = curSum;
    		preX = curX;
    	}
    	System.out.println(tArea);
    }
    
    private static Interval findIntersectInterval(Double x, Triangle t) {
    	//A triangle can have 3 lines joined to the scan line, thus use set.
    	Set<BigDecimal> set = new HashSet<BigDecimal>();
    	//System.out.println("curLine:"+x);
    	for(int i = 0;i<3;i++){
    		Point temp = lineIntersect(new Line(new Point(x,fMin), new Point(x,fMax)), t.lines.get(i));
    		if(temp != null){
    			BigDecimal key = new BigDecimal(Double.toString(temp.y)).setScale(10,RoundingMode.HALF_UP);
    			set.add(key);
    		}
    	}
    	
    	List<BigDecimal> list = new ArrayList<BigDecimal>(set);
    	if(set.size()==2){
    		//System.out.print("(x:"+x+":"+points.get(0).y+" "+ points.get(1).y+";)");
    		return new Interval(list.get(0).doubleValue(),list.get(1).doubleValue());
    		
    	}else if (set.size()==1){
    		return new Interval(list.get(0).doubleValue(),list.get(0).doubleValue());
    	}
    	return null;
	}
    
    private static double intervalSum(BigDecimal cur, Map<BigDecimal, List<Interval>> activeIntervalsMap){
    	List<Interval> feed =activeIntervalsMap.get(cur);
    	//System.out.println(feed.toString());
		List<Interval> ivl = merge(feed);
		double sum = 0;
		if(ivl!=null){
			for(Interval iv:ivl){
				sum += iv.end-iv.start;
				//System.out.println(iv.toString());
			}
		}
    	return sum;
    }

 
    
    /*
     * http://stackoverflow.com/questions/31506740/java-find-intersection-of-two-lines
     * 
6
down vote
accepted
Lets assume you have these 2 functions:

y = m1*x + b1    
y = m2*x + b2
To find the intersection point of the x-axis we do:

m1*x+b1 = m2*x+b2    
m1*x-m2*x = b2 - b2    
x(m1-m2) = (b2-b1)    
x = (b2-b1) / (m1-m2)
To find y, you use of the function expressions and replace x for its value (b2-b1) / (m1-m2).

So:

y = m1 * [(b2-b1) / (m1-m2)] + b1
You have (this.b - line.b), change to (line.b - this.b).
    
    http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
  

*/  
    
    private static Point lineIntersect(Line l1, Line l2) {
    	Point a =l1.p1;
        Point b= l1.p2;
        Point c= l2.p1;
        Point d= l2.p2;
    	
        Point intersection = new Point(0, 0);

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) + Math.abs(d.y - c.y)
                + Math.abs(d.x - c.x) == 0) {
            if ((c.x - a.x) + (c.y - a.y) == 0) {
                //System.out.println("ABCD Same Point");
                return l1.p1;
            } else {
                //System.out.println("AB is same point，CD is same point，AC differs！");
                return null;
            }
        }

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) == 0) {
            if ((a.x - d.x) * (c.y - d.y) - (a.y - d.y) * (c.x - d.x) == 0) {
                //System.out.println("A B is same point，AB on CD line segment！");
            	return l1.p1;
            } else {
//                System.out.println("A B is same point，AB not on CD line segment！");
                return null;
            }
        }
        if (Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
            if ((d.x - b.x) * (a.y - b.y) - (d.y - b.y) * (a.x - b.x) == 0) {
                //System.out.println("C D is same point，CD on AB line segment！");
            	 return l2.p1;
            } else {
            	 return null;
                //System.out.println("C D is same point，CD no on AB line segment！");
            }
        }

        if ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y) == 0) {
            //System.out.println("two line segments parallel, no intersection！");
            return null;
        }

        intersection.x = ((b.x - a.x) * (c.x - d.x) * (c.y - a.y) - 
                c.x * (b.x - a.x) * (c.y - d.y) + a.x * (b.y - a.y) * (c.x - d.x)) / 
                ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y));
        intersection.y = ((b.y - a.y) * (c.y - d.y) * (c.x - a.x) - c.y
                * (b.y - a.y) * (c.x - d.x) + a.y * (b.x - a.x) * (c.y - d.y))
                / ((b.x - a.x) * (c.y - d.y) - (b.y - a.y) * (c.x - d.x));

        
    	double denominator = (l1.p2.x-l1.p1.x)*(l2.p2.y-l2.p1.y) - (l1.p2.y-l1.p1.y)*(l2.p2.x-l2.p1.x);	
		if(denominator==0.0) return null;
		double numerator1 = (l2.p2.x-l2.p1.x)*(l1.p1.y-l2.p1.y) - (l2.p2.y-l2.p1.y)*(l1.p1.x-l2.p1.x);
		double numerator2 = (l1.p2.x-l1.p1.x)*(l1.p1.y-l2.p1.y) - (l1.p2.y-l1.p1.y)*(l1.p1.x-l2.p1.x);
		double t1 = (double)numerator1/(double)denominator;
		double t2 = (double)numerator2/(double)denominator;
		if(t1<=1.0&&t1>=0.0&&t2<=1.0&&t2>=0.0){
  	    	
  	    
//        if ((intersection.x - a.x) * (intersection.x - b.x) <= 0
//                && (intersection.x - c.x) * (intersection.x - d.x) <= 0
//                && (intersection.y - a.y) * (intersection.y - b.y) <= 0
//                && (intersection.y - c.y) * (intersection.y - d.y) <= 0) {
            
            //System.out.println("line segments intersect at (" + intersection.x + "," + intersection.y + ")！");
            return new Point(intersection.x,intersection.y); // '相交
        } else {
            //System.out.println("line segments intersect at extended lines(" + intersection.x + "," + intersection.y + ")！");
            return null; // intersect but not on two segments
        }
    }
    

}

/*
 * http://ucsmp.uchicago.edu/secondary/curriculum/advanced-algebra/demos/polyplotter-aa/
 * */

/*
 * 3
-1 2 -5 -4 -2 -5
1 -3 -5 4 -1 -3
-1 -5 -5 2 -3 -1
[(iv:-4.0,-4.0), (iv:4.0,4.0), (iv:2.0,2.0)]
[(iv:-4.615384615384615,-1.2307692307692306), (iv:0.769230769230769,1.8461538461538454), (iv:-1.230769230769231,-0.7692307692307695)]
[(iv:-4.666666666666667,-1.0), (iv:0.5,1.6666666666666667), (iv:-1.5,-1.0)]
[(iv:-4.82051282051282,-0.3076923076923075), (iv:-0.307692307692308,1.128205128205128), (iv:-2.307692307692308,-1.9230769230769236)]
[(iv:-5.0,0.5), (iv:-1.25,0.5), (iv:-3.25,-3.0)]
[(iv:-3.444444444444444,0.8333333333333335), (iv:-1.638888888888889,0.24074074074074067), (iv:-3.638888888888889,-3.444444444444445)]
[(iv:-2.0,1.142857142857143), (iv:-2.0,-0.0), (iv:-4.0,-3.857142857142857)]
[(iv:2.0,2.0), (iv:-3.0,-0.6666666666666666), (iv:-5.0,-5.0)]
[(iv:-3.0,-3.0)]
16.712606837606835


3
-1 2 -5 -4 -2 -5
1 -3 -5 4 -1 -3
-1 -5 -5 2 -3 -1

17.019693005407

https://www.hackerrank.com/contests/ncr-codesprint/challenges/area-of-triangles/submissions/code/7734664

http://ucsmp.uchicago.edu/secondary/curriculum/advanced-algebra/demos/polyplotter-aa/
*/

