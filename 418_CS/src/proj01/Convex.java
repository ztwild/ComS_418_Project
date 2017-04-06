package proj01;

import java.util.Stack;

import Objects.Point;
import Objects.Segment;
import Sorting.MergeSort;

public class Convex {
	
	
	public Segment[] convexHull(Point[] p){
		int n = p.length;
		Stack stack = new Stack();
		MergeSort merge = new MergeSort();
		Segment[] segs = new Segment[0];
		if(p.length > 2){
			p = merge.sortX(p);
			///////Upper Hull
			Point[] upper = hull(p);
			Segment[] s1 = getSegments(upper);
			/////Lower Hull
			p = reverse(p);
			Point[] lower = hull(p);
			Segment[] s2 = getSegments(lower);
			
			if(s1.length + s2.length > 2){
				segs = combine(s1, s2);
			}else{
				System.out.println("Convex Hull Cannot be Constructed");
			}
		}
		return segs;
		
	}
	
	public Segment[] getSegments(Point[] p){
		int n = p.length;
		Segment[] s = new Segment[n - 1];
		Point p1 = p[0];
		for(int i = 1; i < n; i++){
			Point p2 = p[i];
			s[i-1] = new Segment(p1, p2);
			p1 = p2;
		}
		return s;
	}
	
	public Segment[] combine(Segment[] s1, Segment[] s2){
		int n = s1.length;
		int m = s2.length;
		Segment[] s3 = new Segment[n + m];
		for(int i = 0; i < n; i++){
			s3[i] = s1[i];
		}for(int i = 0; i < m; i++){
			s3[i+n] = s2[i];
		}
		return s3;
	}
	
	public Point[] hull(Point[] p){
		Point[] arr = new Point[0];
		Stack stack = new Stack();
		stack.push(p[0]);
		stack.push(p[1]);
		for(int i = 2; i < p.length; i++){
			stack.push(p[i]);
			double ang = 0;
			while(stack.size() > 2 && ang >= 0){
				Point p3 = (Point) stack.pop();
				Point p2 = (Point) stack.pop();
				Point p1 = (Point) stack.pop();
				ang = angle(p1, p2, p3);
				stack.push(p1);
				if(ang < 0){	
					stack.push(p2);
				}
				stack.push(p3);
			}
		}
		int n = stack.size();
		arr = new Point[n];
		for(int i = 0; i < n; i++){
			arr[i] = (Point) stack.pop();
		}
		return arr;
	}
	
	/**
	 * returns the angle between vectors, right turn < 0, left turn > 0, straight == 0
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return angle between vectors
	 */
	public double angle(Point p1, Point p2, Point p3){
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p1, p3);
		int cross = (s1.a*s2.b) - (s2.a*s1.b);
		double ang = Math.asin(cross/ (s1.length() * s2.length()) );
		return ang;
	}
	
	
	public Point[] copy(Point[] p, int start, int end){
		int n = end - start;
		Point[] p2 = new Point[n];
		for(int i = 0; i < n; i++){
			p2[i] = p[start+i];
		}
		return p2;
	}
	
	public Point[] reverse(Point[] p){
		int n = p.length;
		Point[] p2 = new Point[n];
		for(int i = 0; i < n; i++){
			p2[i] = p[n-1-i];
		}
		return p2;
	}
	
	
	
}
