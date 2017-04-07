package proj01;

import java.util.ArrayList;
import java.util.Arrays;
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
		//gui.setVisible(true);
		
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
		
		Segment[] s = new Segment[9];
		s[0] = new Segment(p[1], p[4]);
		s[1] = new Segment(p[1], p[2]);
		s[2] = new Segment(p[2], p[0]);
		s[3] = new Segment(p[2], p[3]);
		s[4] = new Segment(p[4], p[0]);
		s[5] = new Segment(p[4], p[5]);
		s[6] = new Segment(p[5], p[6]);
		s[7] = new Segment(p[6], p[0]);
		s[8] = new Segment(p[6], p[3]);
//		s[9] = new Segment(p[6], p[2]);  //Added for testing
		
		dcel.addPoints(p);
		dcel.addSegments(s);
		
		PointLocation(new Point(70,42), s);
//		dividingConvexTest(dcel);
		
		
		
		
	}
	
	
	
	public static void PointLocation(Point p, Segment[] seg){
		Face face = locatePoint(p, seg);
		
	}
	
	
	
	public static Face locatePoint(Point p, Segment[] seg){
		MergeSort ms = new MergeSort();
		int n = seg.length;
		
		Segment[] ends = new Segment[n];
		Segment[] starts = new Segment[n];
		for(int i = 0; i < n; i++){
			Segment dumb = new Segment(seg[i].lp, seg[i].rp );
			ends[i] = dumb;
			starts[i] = dumb;
		}
		
		ends = ms.sortEnd(ends);
		starts = ms.sortSeg(starts);
		
//		for(int i = 0; i < n; i++){
//			System.out.print(starts[i].toString()+", ");
//		}System.out.println("\r----------------STaRTS----------------");
//		for(int i = 0; i < n; i++){
//			System.out.print(ends[i].toString()+", ");
//		}System.out.println("\r----------------EnDS----------------");
		
		Treap treeSeg = new Treap(starts[0]);
		int s = 1;
		int e = 0;
		printTree(treeSeg);	//Prints the initial tree
		Point start = null;
		Point end = null;
		while(e < n){
			if(s < n){
				start = starts[s].lp;
			}end = ends[e].rp;
			
			if(p.x < end.x && (s >= n || p.x < start.x)){
				System.out.println("::::::::LoCaTiNG "+p.toString()+"::::::::");
				Node node = treeSeg.findSegment(p);	//returns the segment below point
				boolean inside = (node.prev() != null && node.next() != null);
				System.out.println("Found segment: "+node.prev().toString());
				return getFace(node, inside);
			}else if((s >= n && end.x <= p.x) || (s < n && end.x <= start.x)){
				System.out.println("::::::::DeLeTiNG "+ends[e].toString()+"::::::::");
				treeSeg.deleteSegment(ends[e++]);
			}else{
				System.out.println("::::::::aDDiNG "+starts[s].toString()+"::::::::");
				treeSeg.insert(starts[s++]);
			}
			printTree(treeSeg);		//Prints tree after insertion/deletion
		}
		Node node = new Node(starts[0]);	//This is the highest, leftmost segment.
		return getFace(node, false) ;  	//Outside the polygon
	}
	
	public static void printTree(Treap t){
		Node r = t.root;
		if(r != null){
			while(r.left != null){
				r = r.left;
			}
			System.out.println("--------TReaP--------");
			System.out.print(r.toString()+ ", ");
			while(r.next() != null){
				r = r.next();
				System.out.print(r.toString()+ ", ");
			}
			System.out.println("\r--------eND oF TReaP--------");
			
		}else{
			System.out.println("!!!!!!!!!! TReaP DoNe !!!!!!!!!!");
		}
		
	}
	
	public static Face getFace(Node node, boolean inside){
		Point pt = null;
		ArrayList<Vector> vctrs = new ArrayList();
		
		pt = node.seg.lp;
		ArrayList<Vector> ptVctrs = pt.vectors;	//Copy vectors from the point
		Vector v = null;
		for(int i = 0; i < ptVctrs.size(); i++){	//find the vector with same segment
			if(ptVctrs.get(i).seg == node.seg){
				v = ptVctrs.get(i);
			}
		}
		Vector temp = v;
		vctrs = new ArrayList(Arrays.asList(temp)); //All Vectors of the face
		while(temp.next != v){
			temp = temp.next;
			vctrs.add(temp);			
		}
//		System.out.println("------End of Point Location------");
		return new Face(vctrs, inside);
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
	
	
	/**
	 * Test to see that points are left/right of segment p[0]--p[1]
	 */
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
	
	/**
	 * Test to see if new point calculates that its above the line or not,
	 * Comment out the MouseEvents to have ms initialized,
	 * and in MouseEvents comment out the test
	 */
	public static void pointAboveSegmentTest(){
		GUI gui = new GUI();
		gui.setVisible(true);
		
		int n = 5;
		int m = 50;
		DCEL dcel = gui.canvas.dcel;
		MergeSort srt = new MergeSort();
		Segment s1 = new Segment( new Point(68, 47), new Point(95, 22));
		Segment[] sArr = {s1};
		
		dcel.addSegments(sArr);
		gui.canvas.ms.setTest(s1);
	}
}

