package NCRCodeprint;

import java.util.*;

public class AreaOfTriangles {
	
	class Point
	{
		int x;
		int y;
		Point() { x = 0; y = 0; }
		Point(int x, int y) { this.x = x; this.y = y; }
		Point add(Point p)
		{
			return new Point(x+p.x, y+p.y);
		}
		Point subtract(Point p)
		{
			return new Point(x-p.x, y-p.y);
		}
	}
	
	class Line
	{
		Point end1;
		Point end2;
		Line(Point x, Point y) { this.end1 = x; this.end2 = y; }
	}
	
	class Triangle
	{
		List<Point> points;
		List<Line> lines;
		Triangle(Point x1, Point x2, Point x3) { 
			points = new ArrayList<Point>();
			points.add(x1);
			points.add(x2);
			points.add(x3);
			lines = new ArrayList<Line>();
			lines.add(new Line(x1, x2));
			lines.add(new Line(x1, x3));
			lines.add(new Line(x2, x3));
		}
	}
	
	class Interval
	{
		double start;
		double end;
		Interval() { start = 0; end = 0; }
		Interval(double s, double e) { start = s; end = e; }
	}
		
	public double intervalUnion(List<Interval> intervals)
	{
		if(intervals.size()==0) return 0.0;
		
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(final Interval entry1, final Interval entry2)
			{
				final double start1 = entry1.start;
				final double start2 = entry2.start;
				return start1<start2?-1:1;
			}
		});
		
		double result = 0;
		int len = intervals.size();
		Interval prev = intervals.get(0);
		for(int i=1;i<len;i++)
		{
			Interval curr = intervals.get(i);
			if(curr.start>prev.end)
			{
				result += (prev.end-prev.start);
				prev = curr;
			}
			else
			{
				prev = new Interval(prev.start, prev.end>curr.end?prev.end:curr.end);
			}
		}
		result += (prev.end-prev.start);
		
		return result;
	}

	public List<Double> xArray(Triangle[] triangles)
	{
		Set<Double> intersectsX = new HashSet<Double>();
		for(Triangle triangle:triangles)
		{
			for(Point point:triangle.points)
			{
				intersectsX.add((double)point.x);
			}
			for(Triangle triangle2:triangles)
			{
				if(triangle!=triangle2)
				{
					for(Line l1:triangle.lines)
					{
						for(Line l2:triangle2.lines)
						{
							double[] temp = intersectX(l1,l2);
							if(temp[0]>0.5 && !intersectsX.contains(temp[1])) 
								intersectsX.add(temp[1]);
						}
					}
				}
			}
		}
		List<Double> result = new ArrayList<Double>(intersectsX);
		Collections.sort(result);
		return result;
	}
	
	public double[] intersectX(Line l1, Line l2)
	{
		long denominator = (long)(l1.end2.x-l1.end1.x)*(l2.end2.y-l2.end1.y) - (long)(l1.end2.y-l1.end1.y)*(l2.end2.x-l2.end1.x);	
		if(denominator==0.0) return new double[]{0.0, 0.0};		
		long numerator1 = (long)(l2.end2.x-l2.end1.x)*(l1.end1.y-l2.end1.y) - (long)(l2.end2.y-l2.end1.y)*(l1.end1.x-l2.end1.x);
		long numerator2 = (long)(l1.end2.x-l1.end1.x)*(l1.end1.y-l2.end1.y) - (long)(l1.end2.y-l1.end1.y)*(l1.end1.x-l2.end1.x);
		double t1 = (double)numerator1/(double)denominator;
		double t2 = (double)numerator2/(double)denominator;
		return new double[]{(t1<=1.0&&t1>=0.0&&t2<=1.0&&t2>=0.0)?1.0:0.0, l1.end1.x+t1*(l1.end2.x-l1.end1.x)}; 
				// (int)l1.end1.y+t*(l1.end2.y-l1.end1.y));
	}
	
	public double areaCalc(double left, double right, Triangle[] triangles)
	{
		double area = 0.0;
		double mid = (left+right)/2.0;
		double width = right-left;
		
		List<Interval> intervals = new ArrayList<Interval>();
		for(Triangle triangle:triangles)
		{
			List<Double> tempRes = new ArrayList<Double>();
			for(Line line:triangle.lines)
			{
				double[] temp = withinRegion(mid,line.end1,line.end2);
				if(temp[0]>0.5)
				{
					tempRes.add(temp[1]);
				}
			}
			if(tempRes.size()>0)
			{
				double y1 = tempRes.get(0);
				double y2 = tempRes.get(1);
				intervals.add(new Interval(y1<y2?y1:y2, y1<y2?y2:y1));
			}
		}
		area = width * intervalUnion(intervals);
		return area;
	}
	
	public double[] withinRegion(double x, Point p1, Point p2)
	{
		double left = p1.x<p2.x?p1.x:p2.x;
		double right = p1.x<p2.x?p2.x:p1.x;
		if(left>x||right<x) return new double[]{0.0, 0.0};
		else
		{
			double y = p1.y + (x-p1.x)*(double)(p2.y-p1.y)/(double)(p2.x-p1.x);
			return new double[]{1.0, y};
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AreaOfTriangles instance = new AreaOfTriangles();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int triangleNum = in.nextInt();
		in.nextLine();
		Triangle[] triangles = new Triangle[triangleNum];
		for(int i=0;i<triangleNum;i++)
		{
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			int x3 = in.nextInt();
			int y3 = in.nextInt();
			in.nextLine();
			Point p1 = instance.new Point(x1, y1);
			Point p2 = instance.new Point(x2, y2);
			Point p3 = instance.new Point(x3, y3);
			triangles[i] = instance.new Triangle(p1, p2, p3);
		}
		
		List<Double> xArray = instance.xArray(triangles);
		double totalArea = 0.0;
		int len = xArray.size();
		double prev = xArray.get(0);
		for(int i=1;i<len;i++)
		{
			double curr = xArray.get(i);
			totalArea += instance.areaCalc(prev, curr, triangles);
			prev = curr;
		}
		
		System.out.println(totalArea);
		
//		List<Interval> intervals = new ArrayList<Interval>();
//		intervals.add(instance.new Interval(3,7));
//		intervals.add(instance.new Interval(8,10));
//		intervals.add(instance.new Interval(1,4));
//		List<Interval> result1 = instance.intervalUnion(intervals);
//		for(Interval interval:result1)
//		{
//			System.out.println("("+ interval.start + "," + interval.end + ")");
//		}
//		int totalLen = instance.intervalUnion(intervals);
//		System.out.println(totalLen);
	}
}


/*

3
1 1 5 1 3 3
1 2 5 2 5 6
1 6 5 2 1 2 

3
0 1 5 4 2 4
3 1 3 5 6 2
2 2 4 5 7 3

4
3 2 3 6 7 6
1 2 4 3 1 6
2 3 6 3 5 6
6 3 8 1 8 5

4
300000 200000 300000 600000 700000 600000
100000 200000 400000 300000 100000 600000
200000 300000 600000 300000 500000 600000
600000 300000 800000 100000 800000 500000

4
0 200000 0 600000 400000 600000
-200000 200000 100000 300000 -200000 600000
-100000 300000 300000 300000 200000 600000
300000 300000 500000 100000 500000 500000

1
1 1 5 1 4 3

*/
