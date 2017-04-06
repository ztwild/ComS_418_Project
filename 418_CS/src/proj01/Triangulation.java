package proj01;

import java.util.Stack;

import Objects.*;
import Sorting.*;

public class Triangulation {
	
	public Segment[] triangulate(Point[] p){
		Convex con = new Convex();
		Segment[] s = new Segment[0];
		
		if(p.length > 2){
			
			MergeSort sort = new MergeSort();
			p = sort.sortY(p);
			
			Point[] right = con.hull(p);	//Constructs the right hull;
			right = con.reverse(right);		//Reverses the order of right hull (top down)
			p = con.reverse(p);				//Reverses the order of Points(for left hull)
			Point[] left = con.hull(p);		//Constructs the left hull
			p = con.reverse(p);				//Reverses the order of Points hull (top down)
			Point[] middle = getMiddle(p, left, right);	//Gets the points between hulls
			
			Segment[] s1 = con.getSegments(left);		//Gets segments form left
			Segment[] s2 = con.getSegments(middle);		//Gets segments form middle
			Segment[] s3 = con.getSegments(right);		//Gets segments form right
			
			Segment[] s4 = getDiagonals(left, middle);
			Segment[] s5 = getDiagonals(middle, right);
			
		}
		
		return s;
	}
	
	public Segment[] getDiagonals(Point[] left, Point[] right){
		
		Stack stack = new Stack();
		stack.push(left[0]);	//Initializes the stack with the first point
		int n = left.length + right.length - 5;
		Segment[] s = new Segment[n];
		
		int l = 1;
		int r = 1;
		boolean isLeft = left[l].y > right[r].y;
		Point p = (isLeft ? left[l++] : right[r++]);
		stack.push(p);
		
		int i = 0;
		System.out.println(":::NeeD "+ s.length + " DiaGoNaLS:::");
		while(i < n && l < left.length && r < right.length){
			boolean leftPt = left[l].y > right[r].y;
			Point p1 = (leftPt ? left[l++] : right[r++]);
			Point p2 = (Point) stack.pop();
			if(leftPt ^ isLeft){
//				Point p2 = (Point) stack.pop();
				Point p3 = p2;
				while(!stack.isEmpty() && i < n){
					System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
					s[i++] = new Segment(p1, p3);
					p3 = (Point) stack.pop();
				}
//				stack.push(p2);
//				stack.push(p1);
				isLeft = !isLeft;
			}else{
//				Point p2 = (Point) stack.pop();
				boolean cont = true;
				while(!stack.isEmpty() && cont){
					Point p3 = (Point) stack.pop();
					double ang = angle(p1, p2, p3);
					if(((ang < 0 && isLeft) || (ang > 0 && !isLeft)) && i < n){
						p2 = p3;
						System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
						s[i++] = new Segment(p1, p2); 
					}else{
						stack.push(p3);
						cont = false;
					}
				}
//				stack.push(p2);
//				stack.push(p1);
			}
			stack.push(p2);
			stack.push(p1);
		}
		
		return s;
	}
	
	public static double angle(Point p1, Point p2, Point p3){
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p1, p3);
		int cross = (s1.a*s2.b) - (s2.a*s1.b);
		double ang = Math.asin(cross/ (s1.length() * s2.length()) );
		return ang;
	}
	
	
	public Point[] getMiddle(Point[] total, Point[] left, Point[] right){
		int l = left.length;
		int r = right.length;
		int n = total.length + 4-l-r;
		Point[] middle = new Point[n];
		middle[0] = left[0];
		middle[n-1] = left[l-1];
		
		l = 1;
		r = 1;
		n = 1;
		for(int i = 1; i < total.length - 1; i++){
//			System.out.print("Left: "+left[l].toString());
//			System.out.print(", Total: "+total[i].toString());
//			System.out.println("Right: "+right[r].toString());
			
			if(!total[i].equal(left[l]) && !total[i].equal(right[r])){
				middle[n] = total[i];
//				System.out.println("Adding "+total[i].toString()+" to middle");
				n++;
			}else if(total[i].equal(left[l])){
//				System.out.println("Skipping left: "+left[l].toString());
				l++;
			}else{
//				System.out.println("Skipping right: "+right[r].toString());
				r++;
			}
			if(total.length == n - 4 + l + r){
				System.out.println(":::::::::SoMeTHiNG BRoKe iN GeTTiNG THe MiDDLe:::::::::");
			}
		}
		return middle;
	}
}
