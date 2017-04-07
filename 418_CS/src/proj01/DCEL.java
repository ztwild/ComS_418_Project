package proj01;
import Objects.*;
import Sorting.MergeSort;
import Sorting.Treap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class DCEL {
	public static Point[] Points = new Point[0];
	public static Segment[] Segments = new Segment[0];
	public static ArrayList<Vector> Vectors = new ArrayList();
	
	
	public void addPoints(Point[] p){
		MergeSort ms = new MergeSort();
		Points = ms.sortX(Points);
		p = ms.sortX(p);
		
		int a = 0;
		int b = 0;
		int count = 0;
		Point temp = p[0];
		Point[] arr = new Point[Points.length + p.length];
		while(a < Points.length && b < p.length){
			if(Points[a].leftOf(temp)){
				arr[count++] = Points[a++];
			}else if(!Points[a].leftOf(temp) && (Points[a].equal(temp) 
					|| (b+1 < p.length && temp.equal(p[b+1])))){
				if(++b < p.length)
					temp = p[b];
			}else{
				arr[count++] = temp;
				if(b+1 < p.length)
					temp = p[b+1];
				b++;
			}
		}while(a < Points.length){
			arr[count++] = Points[a++];
		}while(b < p.length){
			if(b+1 == p.length){
				arr[count++] = temp;
			}else if(!temp.equal(p[b+1])){
				arr[count++] = temp;
				temp = p[b+1];
			}
			b++;
		}
		
		Points = new Point[count];
		System.arraycopy(arr, 0, Points, 0, count);
	}
	
	public void addSegments(Segment[] s){
		if(s.length > 0){
			MergeSort ms = new MergeSort();
			Segments = ms.sortSeg(Segments);
			s = ms.sortSeg(s);
			
			int a = 0;
			int b = 0;
			int count = 0;
			Segment temp = s[0];
			Segment[] arr = new Segment[Segments.length + s.length];
			while(a < Segments.length && b < s.length){
				if(Segments[a].leftOf(temp)){
					arr[count++] = Segments[a++];
				}else if(!Segments[a].leftOf(temp) && (Segments[a].equal(temp) 
						|| (b+1 < s.length && temp.equal(s[b+1])))){
					if(++b < s.length)
						temp = s[b];
				}else{
					arr[count++] = temp;
					addVectors(temp);
					if(b+1 < s.length)
						temp = s[b+1];
					b++;
				}
			}while(a < Segments.length){
				arr[count++] = Segments[a++];
			}while(b < s.length){
				if(b+1 == s.length){
					arr[count++] = temp;
					addVectors(temp);
				}else if(!temp.equal(s[b+1])){
					arr[count++] = temp;
					addVectors(temp);
					temp = s[b+1];
				}
				b++;
			}
			
			Segments = new Segment[count];
			System.arraycopy(arr, 0, Segments, 0, count);
		}
	}

	
	public void addVectors(Segment s){
		Vector v1 = new Vector(s.rp, s);
		Vector v2 = new Vector(s.lp, s);
		
		v1.twin = v2;
		v2.twin = v1;
		
		v1.assignNext(s.lp);
		v2.assignNext(s.rp);
		
		Vectors.add(v1);
		Vectors.add(v2);
	}
	
	public void triangulate(){
		Segments = new Segment[0];
		Triangulation tri = new Triangulation();
		Convex con = new Convex();
		Segment[] s = new Segment[0];
		
		if(Points.length > 3){
			for(Point point : Points){
				point.vectors.clear();
			}Vectors.clear();
			
			MergeSort sort = new MergeSort();
			Points = sort.sortY(Points);
			
			Point[] right = con.hull(Points);	//Constructs the right hull;
			right = con.reverse(right);		//Reverses the order of right hull (top down)
			Points = con.reverse(Points);				//Reverses the order of Points(for left hull)
			Point[] left = con.hull(Points);		//Constructs the left hull
			Points = con.reverse(Points);				//Reverses the order of Points hull (top down)
			Point[] middle = tri.getMiddle(Points, left, right);	//Gets the points between hulls
			System.out.println(middle.length + " Points in middle");
			
			Segment[] s1 = con.getSegments(left);		//Gets segments form left
			Segment[] s2 = con.getSegments(right);		//Gets segments form right
			
			addSegments(s1);
			addSegments(s2);
			
			if(middle.length > 2){
				Segment[] s3 = con.getSegments(middle);		//Gets segments form middle
				Segment[] s4 = tri.getDiagonals(left, middle);
				Segment[] s5 = tri.getDiagonals(middle, right);
				
				addSegments(s3);
				addSegments(s4);
				addSegments(s5);
			}else{
				Segment[] s6 = tri.getDiagonals(left, right);
				addSegments(s6);
			}
			
		}else if(Points.length == 3){
			Segment[] s7 = con.convexHull(Points);
			addSegments(s7);
		}
	}
	
	/**
	 * Returns a random assortment of n points, coordinates ranging from 0 to max
	 * @param n
	 * @param max
	 * @return random array of points
	 */
	public static Point[] randomPts(int n, int max){
		Point[] arr = new Point[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++){
			arr[i] = new Point(rand.nextInt(max), rand.nextInt(max));
		}
		return arr;
	}
		
	public static void print(Point[] p){
		for(int i = 0; i < p.length; i++){
			System.out.print("("+ p[i].x +", "+p[i].y +")");
		}System.out.println();
	}
	
	public static void printVectors(){
		for(Vector v : Vectors){
			System.out.println(v.toString());
		}
	}
	
	
}





