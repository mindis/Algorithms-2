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

import java.awt.geom.Line2D;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution8 {

	public static final double fMin = -1000000; //can't be 6 0s, will lose precision in intersect function
	public static final double fMax = 1000000;
	static class Point{
		public double x;
		public double y;
		int tri;
		Point(double x, double y){this.x = x; this.y = y;}
		public String toString(){
			return "(p:"+x+","+y+")";
		}
	}
	
	static class Line
	{
		Point p1;
		Point p2;
		Line(Point x, Point y) { this.p1 = x; this.p2 = y; }
		public String toString(){
			return "(l:"+p1.toString()+","+p2.toString()+")";
		}
	}
	
	static class Triangle{
		List<Point> points;
		List<Line> lines;
		double minX = fMax;
		double maxX = fMin;
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
				if(minX > tempX){
					minX =tempX;
				}
				if(maxX < tempX){
					maxX = tempX;
				}
			}
		}
		public String toString(){
			return "(t:"+lines.get(0).toString()+","+lines.get(1).toString()+","+lines.get(2).toString()+")";
		}
		
	}
	
	
	/* Merge Intervals Reference jiuzhang*/
	
	 public static class Interval {
		      double start;
		      double end;
		      Interval() { start = 0; end = 0; }
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
	    
	    

	 
	 
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	int n = scan.nextInt();
    	Triangle[] tArr = new Triangle[n];
    	for(int i = 0;i<n;i++){
    		tArr[i] = new Triangle(new Point(scan.nextInt(),scan.nextInt()),
    							   new Point(scan.nextInt(),scan.nextInt()),
    							   new Point(scan.nextInt(),scan.nextInt()));
    	}
    	List<Double> xList = findXofScanLine(tArr);
    	Map<Double, Integer> xListIndexMap = new HashMap<Double, Integer>();
    	for(int i = 0; i<xList.size();i++){
    		xListIndexMap.put(xList.get(i), i);
    	}
    	/*find active triangles*/
    	Map<Double, List<Interval>> activeIntervalsMap = new HashMap<Double, List<Interval>>();
    	//for each triangle
    	for(int i = 0;i<tArr.length;i++){
    		if(i==1){
    			System.out.print("");
    		}
    		//for each scanLines that cut through, append intervals
    		for(int j=xListIndexMap.get(tArr[i].minX);j<=xListIndexMap.get(tArr[i].maxX);j++){
    			Double curX = xList.get(j);
    	
    			Interval intersectInterval = findIntersectInterval(curX,tArr[i]);
    			
    			if(intersectInterval!=null){
		    		if(activeIntervalsMap.containsKey(curX)){
		    			activeIntervalsMap.get(curX).add(intersectInterval);
		    		}else{
		    			List<Interval> intervals = new ArrayList<Interval>();
		    			intervals.add(intersectInterval);
		    			activeIntervalsMap.put(curX, intervals);
		    		}
    			}
			}
    		//System.out.println();
    		
    	}
    	
    	double preX = xList.get(0);
    	double preSum = intervalSum(preX,activeIntervalsMap);
    	double tArea= 0;
    	for(int i = 1;i<xList.size();i++){
    		double curX = xList.get(i);
    		double curSum = intervalSum(curX,activeIntervalsMap);
    		tArea += (preSum+curSum)*(curX-preX)/2.0;
    		preSum = curSum;
    		preX = curX;
    	}
    	System.out.println(tArea);
    }
    
    private static Interval findIntersectInterval(Double x, Triangle t) {
    	//A triangle can have 3 lines joined to the scan line, thus use set.
    	Set<Double> set = new HashSet<Double>();
    	//System.out.println("curLine:"+x);
    	for(int i = 0;i<3;i++){
    		Point temp = lineIntersect(new Line(new Point(x,fMin), new Point(x,fMax)), t.lines.get(i));
    		if(temp != null){
    			set.add(temp.y);
    		}
    	}
    	List<Double> list = new ArrayList<Double>(set);
    	if(set.size()==2){
    		//System.out.print("(x:"+x+":"+points.get(0).y+" "+ points.get(1).y+";)");
    		return new Interval(list.get(0),list.get(1));
    	}else if (set.size()==1){
    		return new Interval(list.get(0),list.get(0));
    	}
    	return null;
	}
    
    private static double intervalSum(double cur, Map<Double,List<Interval>> activeIntervalsMap){
    	List<Interval> feed =activeIntervalsMap.get(cur);
    	System.out.println(feed.toString());
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

	public static List<Double> findXofScanLine(Triangle[] tArr){
    	Set<Double> xSet = new HashSet<Double>();
    	for(int i = 0;i<tArr.length;i++){
    		//Add all intersections
    		for(int j=i+1;j<tArr.length;j++){
    			for(int k=0;k<3;k++){
    				for(int l=k;l<3;l++){
	    				Point iP=lineIntersect(tArr[i].lines.get(k),tArr[j].lines.get(l));
	    				if(iP!=null){
	    					xSet.add(iP.x);
	    				}
    				}
    			}
    		}
    		//Add all triangle points
			for(int j = 0;j<3;j++){
				xSet.add(tArr[i].points.get(j).x);
			}
    	}
    	List<Double> result = new ArrayList<Double>(xSet);
		Collections.sort(result);
		return result;
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

public Point intersect(Line line) {
    double x = (line.b - this.b) / (this.m - line.m);
    double y = this.m * x + this.b;

    return new Point((int) x, (int) y);
    double m1=(l1.p1.y-l1.p1.y)/(l1.p1.x-l1.p2.x)
    double m2=(l2.p1.y-l2.p1.y)/(l2.p1.x-l2.p2.x)
    double denom = m1-m2;
    
    
    http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect
    
    up vote
472
down vote
There’s a nice approach to this problem that uses vector cross products. Define the 2-dimensional vector cross product v × w to be vx wy − vy wx (this is the magnitude of the 3-dimensional cross product).

Suppose the two line segments run from p to p + r and from q to q + s. Then any point on the first line is representable as p + t r (for a scalar parameter t) and any point on the second line as q + u s (for a scalar parameter u).

Two line segments intersecting

The two lines intersect if we can find t and u such that:

p + t r = q + u s
Formulae for the point of intersection

Cross both sides with s, getting

(p + t r) × s = (q + u s) × s
And since s × s = 0, this means

t (r × s) = (q − p) × s
And therefore, solving for t:

t = (q − p) × s / (r × s)
In the same way, we can solve for u:

(p + t r) × r = (q + u s) × r

u (s × r) = (p − q) × r

u = (p − q) × r / (s × r)
To reduce the number of computation steps, it's convenient to rewrite this as follows (remembering that s × r = − r × s):

u = (q − p) × r / (r × s)
Now there are four cases:

If r × s = 0 and (q − p) × r = 0, then the two lines are collinear.

In this case, express the endpoints of the second segment (q and q + s) in terms of the equation of the first line segment (p + t r):

t0 = (q − p) · r / (r · r)

t1 = (q + s − p) · r / (r · r) = t0 + s · r / (r · r)
If the interval between t0 and t1 intersects the interval [0, 1] then the line segments are collinear and overlapping; otherwise they are collinear and disjoint.

Note that if s and r point in opposite directions, then s · r < 0 and so the interval to be checked is [t1, t0] rather than [t0, t1].
If r × s = 0 and (q − p) × r ≠ 0, then the two lines are parallel and non-intersecting.
If r × s ≠ 0 and 0 ≤ t ≤ 1 and 0 ≤ u ≤ 1, the two line segments meet at the point p + t r = q + u s.
Otherwise, the two line segments are not parallel but do not intersect.
Credit: this method is the 2-dimensional specialization of the 3D line intersection algorithm from the article "Intersection of two lines in three-space" by Ronald Goldman, published in Graphics Gems, page 304. In three dimensions, the usual case is that the lines are skew (neither parallel nor intersecting) in which case the method gives the points of closest approach of the two lines.
    
}
     */
    
//    public static Point lineIntersect(Line l1, Line l2){
//   
//    	double denominator = (l1.p2.x-l1.p1.x)*(l2.p2.y-l2.p1.y) - (l1.p2.y-l1.p1.y)*(l2.p2.x-l2.p1.x);	
//		if(denominator==0.0) return null;
//		double numerator1 = (l2.p2.x-l2.p1.x)*(l1.p1.y-l2.p1.y) - (l2.p2.y-l2.p1.y)*(l1.p1.x-l2.p1.x);
//		double numerator2 = (l1.p2.x-l1.p1.x)*(l1.p1.y-l2.p1.y) - (l1.p2.y-l1.p1.y)*(l1.p1.x-l2.p1.x);
//		double t1 = (double)numerator1/(double)denominator;
//		double t2 = (double)numerator2/(double)denominator;
//		if(t1<=1.0&&t1>=0.0&&t2<=1.0&&t2>=0.0){
//			return new Point(1,l1.p1.x+t1*(l1.p2.x-l1.p1.x)); 
//    	}else{
//    		return null;
//    	}
//    }
//    
    
    private static Point lineIntersect(Line l1, Line l2) {
    	Point a =l1.p1;
        Point b= l1.p2;
        Point c= l2.p1;
        Point d= l2.p2;
    	
        Point intersection = new Point(0, 0);

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) + Math.abs(d.y - c.y)
                + Math.abs(d.x - c.x) == 0) {
            if ((c.x - a.x) + (c.y - a.y) == 0) {
                //System.out.println("ABCD是同一个点！");
                return l1.p1;
            } else {
                //System.out.println("AB是一个点，CD是一个点，且AC不同！");
                return null;
            }
        }

        if (Math.abs(b.y - a.y) + Math.abs(b.x - a.x) == 0) {
            if ((a.x - d.x) * (c.y - d.y) - (a.y - d.y) * (c.x - d.x) == 0) {
                //System.out.println("A、B是一个点，且在CD线段上！");
            	return l1.p1;
            } else {
//                System.out.println("A、B是一个点，且不在CD线段上！");
                return null;
            }
        }
        if (Math.abs(d.y - c.y) + Math.abs(d.x - c.x) == 0) {
            if ((d.x - b.x) * (a.y - b.y) - (d.y - b.y) * (a.x - b.x) == 0) {
                //System.out.println("C、D是一个点，且在AB线段上！");
            	 return l2.p1;
            } else {
            	 return null;
                //System.out.println("C、D是一个点，且不在AB线段上！");
            }
        }

        if ((b.y - a.y) * (c.x - d.x) - (b.x - a.x) * (c.y - d.y) == 0) {
            //System.out.println("线段平行，无交点！");
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
            
            //System.out.println("线段相交于点(" + intersection.x + "," + intersection.y + ")！");
            return new Point(intersection.x,intersection.y); // '相交
        } else {
            //System.out.println("线段相交于虚交点(" + intersection.x + "," + intersection.y + ")！");
            return null; // '相交但不在线段上
        }
    }
    
    
    /*
     *     public static Point lineIntersect(Line l1, Line l2) {
   
    	double x1=l1.p1.x, y1=l1.p1.y, x2=l1.p2.x, y2=l1.p2.y, 
    		   x3=l2.p1.x, y3=l2.p1.y, x4=l2.p2.x, y4=l2.p2.y;
    	double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
    	  if (denom == 0.0) { // Lines are parallel.
    	     return null;
    	  }
    	  double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/denom;
    	  double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/denom;
    	    if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
    	        // Get the intersection point.
    	    	Point retP =new Point((int) (x1 + ua*(x2 - x1)), (int) (y1 + ua*(y2 - y1)));
    	    	//System.out.println("(lineIntersect:"+l1.toString()+","+l2.toString()+","+retP);
    	        return retP;
    	    }

    	  return null;
    }*/
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

