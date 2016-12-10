import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 * https://www.hackerrank.com/contests/ncr-codesprint/challenges/area-of-triangles
 * 5
17 20 5 23 5 -3
20 18 9 6 18 1
1 -4 9 -2 14 -5
6 16 -3 -3 14 15
16 2 13 10 9 2
333.8752293422984 only one test case not passed
 */
public class Solution8 {

	public static final double fMin = -1000000; 
	public static final double fMax = 1000000;
	public static final double precision = 0.0000001;
	
	static class Point{
		public double x;
		public double y;
		int tri;
		public Point(double x, double y){this.x = x; this.y = y;}
		public String toString(){
			return "(p:"+x+","+y+")";
		}
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
		BigDecimal minX = new BigDecimal(Double.toString(fMax)).setScale(7, RoundingMode.HALF_UP);
		BigDecimal maxX = new BigDecimal(Double.toString(fMin)).setScale(7, RoundingMode.HALF_UP);
		
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
					minX = new BigDecimal(Double.toString(tempX)).setScale(7, RoundingMode.HALF_UP);
				}
				if(maxX.doubleValue() < tempX){
					maxX = new BigDecimal(Double.toString(tempX)).setScale(7, RoundingMode.HALF_UP);
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
	 
	 public static double merge2(List<Interval> list) {
		 	double sum = 0.0;
	        if (list == null || list.size() ==0) {//bug here for sum add
	            return sum;
	        }
	        Collections.sort(list, new IntervalComparator());       
	        Interval last = list.get(0);
	        for (int i = 1; i < list.size(); i++) {
	            Interval curt = list.get(i);
	            if (curt.start <= last.end ){
	                last.end = Math.max(last.end, curt.end);
	            }else{
	                sum+=last.end-last.start;
	                last = curt;
	            }
	        }
	        sum+=last.end-last.start;
	        return sum;
	 }
	 
	public static List<BigDecimal> findXofScanLine(Triangle[] tArr){
    	Set<BigDecimal> xSet = new HashSet<BigDecimal>();
    	for(int i = 0;i<tArr.length;i++){
    		//Add all intersections
    		for(int j=i+1;j<tArr.length;j++){
    			for(int k=0;k<3;k++){
    				for(int l=0;l<3;l++){//fixed bug here, 3x3 = 9 instead of 3x2 =6 for comparing two triangles
	    				Double iX=xIntersect(tArr[i].lines.get(k),tArr[j].lines.get(l));
	    				if(iX!=null){
	    					xSet.add(new BigDecimal(Double.toString(iX.doubleValue())).setScale(7, RoundingMode.HALF_UP));
	    				}
    				}
    			}
    		}
    		//Add all triangle points
			for(int j = 0;j<3;j++){
				xSet.add(new BigDecimal(Double.toString(tArr[i].points.get(j).x)).setScale(7, RoundingMode.HALF_UP));
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
    	int xListS = xList.size();
    	for(int i = 0; i<xList.size();i++){
    		xListIndexMap.put(xList.get(i), i);
    	}
    	
    	/*find active triangles*/
    	List<List<Interval>> activeIntervalsArr = new ArrayList<List<Interval>>();
    	for(int i = 0; i < xListS-1;i++){
    		activeIntervalsArr.add(new ArrayList<Interval>());
    	}
    	//for each triangle, fill scanLine array, for mid position
    	for(int i = 0;i<tArr.length;i++){

    		//for each scanLines mid-line that cut through, append intervals
    		int minJ =xListIndexMap.get(tArr[i].minX);
    		int maxJ =xListIndexMap.get(tArr[i].maxX);
    		Double preX = xList.get(minJ).doubleValue();
    		for(int j=minJ+1;j<=maxJ;j++){
    			Double curX = xList.get(j).doubleValue();
    			Double midX = (preX+curX)/2.0;
    			Interval intersectInterval = findIntersectInterval(midX,tArr[i]);
    			if(intersectInterval!=null){
    				activeIntervalsArr.get(j-1).add(intersectInterval);
    			}
    			preX=curX;
    		}
    	}
    	
    	//calculate area base on mid-line intervals
    	double tArea= 0;
    	for(int i = 0;i<xListS-1;i++){
    		tArea += merge2(activeIntervalsArr.get(i)) * (xList.get(i+1).doubleValue()-xList.get(i).doubleValue());
    	}
    	System.out.println(tArea);
    }
    
    private static Interval findIntersectInterval(Double x, Triangle t) {
    	//A triangle can have 3 lines joined to the scan line, thus use set.
    	Set<BigDecimal> set = new HashSet<BigDecimal>();
    	//System.out.println("curLine:"+x);
    	for(int i = 0;i<3;i++){
    		Double temp = getY(x,t.lines.get(i));
    		
    		if(temp != null){
    			//System.out.print("(x:"+x+":"+t.toString()+"y-intersect"+temp.toString());
    			BigDecimal key = new BigDecimal(temp.toString()).setScale(7,RoundingMode.HALF_UP);
    			set.add(key);
    		}
    	}
    	List<BigDecimal> list = new ArrayList<BigDecimal>(set);
    	if(set.size()==2){
    		//System.out.println("(x:"+x+":"+list.get(0).doubleValue()+" "+ list.get(1).doubleValue()+";)");
    		return new Interval(list.get(0).doubleValue(),list.get(1).doubleValue());
    	}
    	return null;
	}

    
    /*line x=a, and line p1->p2 's intersection y*/
	public static Double getY(double x, Line l)
	{
		//System.out.print("x:"+x+l.toString());
		Point p1 = l.p1;Point p2 = l.p2;
		double left = 0;
		double right = 0;
		if(p1.x<p2.x){
			left = p1.x;
			right = p2.x;
		}else{
			right = p1.x;
			left = p2.x;
		}
		
		//double left > x comparison may lost precision
		if(left-x>precision||x-right>precision || Math.abs(p2.x-p1.x)<precision){
			//System.out.print("left:"+left+"right:"+right+"x:"+x);
			//System.out.println("null");
			return null;
		}
		double y = p1.y + (x-p1.x)*(p2.y-p1.y)/(p2.x-p1.x);
		//System.out.println("y:"+y);
		return y;
	}
	
	/*
	 * http://stackoverflow.com/questions/31506740/java-find-intersection-of-two-lines
	 * http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect  
	 */
    private static Double xIntersect(Line l1, Line l2) {
    	double denominator = (double)(l1.p2.x-l1.p1.x)*(l2.p2.y-l2.p1.y) - (double)(l1.p2.y-l1.p1.y)*(l2.p2.x-l2.p1.x);	
		if(denominator==0.0) return null;
		double numerator1 = (l2.p2.x-l2.p1.x)*(l1.p1.y-l2.p1.y) - (l2.p2.y-l2.p1.y)*(l1.p1.x-l2.p1.x);
		double numerator2 = (l1.p2.x-l1.p1.x)*(l1.p1.y-l2.p1.y) - (l1.p2.y-l1.p1.y)*(l1.p1.x-l2.p1.x);
		double t1 = (double)numerator1/(double)denominator;
		double t2 = (double)numerator2/(double)denominator;
		if (t1<=1.0&&t1>=0.0&&t2<=1.0&&t2>=0.0){
			return l1.p1.x + t1*(l1.p2.x-l1.p1.x);
		}else{
			return null;
		}
    }
}

/*
 * http://ucsmp.uchicago.edu/secondary/curriculum/advanced-algebra/demos/polyplotter-aa/
 * */

/*
3
-1 2 -5 -4 -2 -5
1 -3 -5 4 -1 -3
-1 -5 -5 2 -3 -1

17.019693005407

https://www.hackerrank.com/contests/ncr-codesprint/challenges/area-of-triangles/submissions/code/7734664
https://www.hackerrank.com/contests/ncr-codesprint/challenges/area-of-triangles/
http://ucsmp.uchicago.edu/secondary/curriculum/advanced-algebra/demos/polyplotter-aa/
*/
