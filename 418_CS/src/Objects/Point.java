package Objects;

import java.util.ArrayList;

public class Point{
	public int x, y;
	public ArrayList<Vector> vectors;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		vectors = new ArrayList<>();
	}
	
	public String toString(){
		return "("+ x +", "+ y + ")";
	}
	
	public boolean leftOf(Point p){
		return this.x < p.x || (this.x == p.x && this.y > p.y);
	}
	
	public boolean above(Point p){
		return this.y > p.y || (this.y == p.y && this.x < p.x);
	}
	
	public boolean above(Segment s){
//		Segment s1 =  new Segment(s.lp, s.rp);
		double c = -(s.b * y) - (s.a * x);
//		System.out.println("Inserting segment c: "+c);
//		System.out.println("new c == old c: " + (s1.c == s.c));
//		System.out.println(c < s1.c);
		return c < s.c;
	}
	
	public boolean equal(Point p){
		return this.x == p.x && this.y == p.y;
	}
}