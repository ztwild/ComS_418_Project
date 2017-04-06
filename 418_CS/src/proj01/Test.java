package proj01;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import GUI.GUI;
import Objects.*;
import Sorting.MergeSort;
import Sorting.Treap;

public class Test {
	public static void main(String[] args){
		runTest();
		
		
		
		
	}
	
	public static void runTest(){
		GUI gui = new GUI();
		gui.setVisible(true);
		
		int n = 5;
		int m = 50;
		DCEL dcel = gui.canvas.dcel;
		MergeSort srt = new MergeSort();
		
//		Point[] p = dcel.randomPts(20, 75);
		
		Point[] p = new Point[7];
		p[0] = new Point(25, 35);
		p[1] = new Point(27, 13);
		p[2] = new Point(53, 24);
		p[3] = new Point(48, 45);
		p[4] = new Point(10, 56);
		p[5] = new Point(30, 79);
		p[6] = new Point(62, 83);
		
		dcel.addPoints(p);
		dividingConvexTest(dcel);
		
		
	}
	
	public static void divideNoDcel(){
		Point[] p = new Point[7];
		p[0] = new Point(25, 35);
		p[1] = new Point(27, 13);
		p[2] = new Point(53, 24);
		p[3] = new Point(48, 45);
		p[4] = new Point(10, 56);
		p[5] = new Point(30, 79);
		p[6] = new Point(62, 83);
		
		DCEL d = new DCEL();
		d.addPoints(p);
		dividingConvexTest(d);
	}
	
//	public static Segment[] getDiagonals(Point[] left, Point[] right){
//		
//		Stack stack = new Stack();
//		stack.push(left[0]);	//Initializes the stack with the first point
//		int n = left.length;
//		int l = 1;
//		
//		int m = right.length;
//		int r = 1;
//		
//		boolean isLeft = false;
//		Segment[] s = new Segment[n+m-5];
////		Segment[] s = new Segment[n+m];
//		if(left[l].y > right[r].y){
//			stack.push(left[l++]);
//			isLeft = true;
//		}else{
//			stack.push(right[r++]);
//		}
//		int i = 0;
//		System.out.println(":::NeeD "+ s.length + " DiaGoNaLS:::");
//		while(i < s.length){
//			if(left[l].y > right[r].y && !isLeft){
//				Point p1 = left[l++];
//				Point p2 = (Point) stack.pop();
//				Point p3 = p2;
//				while(!stack.isEmpty() ){// && i < n+m-5){
//					System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
//					s[i++] = new Segment(p1, p3);
//					p3 = (Point) stack.pop();
//				}
//				stack.push(p2);
//				stack.push(p1);
//				isLeft = true;
//			}else if(right[r].y > left[l].y && isLeft){
//				Point p1 = right[r++];
//				Point p2 = (Point) stack.pop();
//				Point p3 = p2;
//				while(!stack.isEmpty() ){// && i < n+m-5){
//					System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
//					s[i++] = new Segment(p1, p3);
//					p3 = (Point) stack.pop();
//				}
//				stack.push(p2);
//				stack.push(p1);
//				isLeft = false;
//			}else if(left[l].y > right[r].y && isLeft){
//				Point p1 = left[l++];
//				Point p2 = (Point) stack.pop();
//				boolean cont = true;
//				while(!stack.isEmpty() && cont){
//					Point p3 = (Point) stack.pop();
//					double ang = angle(p1, p2, p3);
//					if(ang < 0 ){// && i < n+m-5){
//						p2 = p3;
//						System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
//						s[i++] = new Segment(p1, p2); 
//					}else{
//						stack.push(p3);
//						cont = false;
//					}
//				}
//				stack.push(p2);
//				stack.push(p1);
//			}else if(right[r].y > left[l].y && !isLeft){
//				Point p1 = right[r++];
//				Point p2 = (Point) stack.pop();
//				boolean cont = true;
//				while(!stack.isEmpty() && cont){
//					Point p3 = (Point) stack.pop();
//					double ang = angle(p1, p2, p3);
//					if(ang > 0  ){//&& i < n+m-5){
//						p2 = p3;
//						System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
//						s[i++] = new Segment(p1, p2); 
//					}else{
//						stack.push(p3);
//						cont = false;
//					}
//				}
//				stack.push(p2);
//				stack.push(p1);
//			}
//		}
//		
//		return s;
//	}
	
	public static Segment[] getDiagonals(Point[] left, Point[] right){
		
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
		while(i < n){
			boolean leftPt = left[l].y > right[r].y;
			Point p1 = (leftPt ? left[l++] : right[r++]);
			if(leftPt ^ isLeft){
				Point p2 = (Point) stack.pop();
				Point p3 = p2;
				while(!stack.isEmpty() ){// && i < n+m-5){
					System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
					s[i++] = new Segment(p1, p3);
					p3 = (Point) stack.pop();
				}
				stack.push(p2);
				stack.push(p1);
				isLeft = !isLeft;
			}else{
				Point p2 = (Point) stack.pop();
				boolean cont = true;
				while(!stack.isEmpty() && cont){
					Point p3 = (Point) stack.pop();
					double ang = angle(p1, p2, p3);
					if((ang < 0 && isLeft) || (ang > 0 && !isLeft)){// && i < n+m-5){
						p2 = p3;
						System.out.println(":::::aDDiNG DiaGoNaL "+i+":::::");
						s[i++] = new Segment(p1, p2); 
					}else{
						stack.push(p3);
						cont = false;
					}
				}
				stack.push(p2);
				stack.push(p1);
			}
		}
		
		return s;
	}
	
	
	public static void dividingConvexTest(DCEL dcel){
		Point[] p = dcel.Points;
		
		Convex con = new Convex();
		Triangulation tri = new Triangulation();
		MergeSort sort = new MergeSort();
		p = sort.sortY(p);
		
//		print(p, "Total Points");
		Point[] right = con.hull(p);
//		print(right, "Right after Construction");
		right = con.reverse(right);
		p = con.reverse(p);
//		print(p, "Total Reversed(Bottom up)");
		Point[] left = con.hull(p);
//		print(left, "Left after Construction");
		p = con.reverse(p);
		Point[] middle = tri.getMiddle(p, left, right);
		
//		print(left, "Left");
//		print(middle, "Middle");
//		print(right, "Right");
		
		Segment[] s1 = con.getSegments(left);
		Segment[] s2 = con.getSegments(middle);
		Segment[] s3 = con.getSegments(right);
		
		dcel.addSegments(s1);
		dcel.addSegments(s2);
		dcel.addSegments(s3);
		
		System.out.println("::::::TRiaNGLaTiNG::::::");
		Segment[] s4 = getDiagonals(left, middle);
		System.out.println("::::::DiaGoNaLS FoR LeFT::::::");
		Segment[] s5 = getDiagonals(middle, right);
		System.out.println("::::::DiaGoNaLS FoR RiGHT::::::");
		
		dcel.addSegments(s4);
		dcel.addSegments(s5);
		
	}
	
	public static void print(Point[] p, String title){
		System.out.println("----------Printing Points----------");
		for(Point p1 : p){
			System.out.print(p1.toString());
		}System.out.println("\r----------End of " + title + "----------");
	}
	
	public static double angle(Point p1, Point p2, Point p3){
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p1, p3);
		int cross = (s1.a*s2.b) - (s2.a*s1.b);
		double ang = Math.asin(cross/ (s1.length() * s2.length()) );
		return ang;
	}
	
	public static void angleTest(){
		Point[] p = new Point[15];
		p[0] = new Point(0, 0);
		p[1] = new Point(0, 50);

		p[2] = new Point(-1, 100);
		p[3] = new Point(-50, -50);
		p[4] = new Point(-100, 0);
		p[5] = new Point(-50, 50);
		p[6] = new Point(-30, 80);
		p[7] = new Point(-1, 100);
		
		p[8] = new Point(0,110);
		
		p[9] = new Point(1, 100);
		p[10] = new Point(30, 80);
		p[11] = new Point(50, 50);
		p[12] = new Point(100, 0);
		p[13] = new Point(50, -50);
		p[14] = new Point(1, 100);
		
		for(int i = 2; i < 15; i++){
			System.out.println("angle for p["+i+"] = "+angle(p[0], p[1], p[i]));
		}
	}
}

