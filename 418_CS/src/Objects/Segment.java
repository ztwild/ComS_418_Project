package Objects;


public class Segment{
	public Point tp, bp;
	public Point rp, lp;
	public int a, b, c;
	
	public Segment(Point u, Point v){
		boolean highest = u.y > v.y || (u.y == v.y && u.x < v.x);
		boolean leftmost = u.x < v.x || (u.x == v.x && u.y < v.y);
		tp = highest ? u : v;
		bp = !highest ? u : v;
		lp = leftmost ? u : v;
		rp = !leftmost ? u : v;
		
//		this.a = this.rp.y - this.lp.y;
//		this.b = this.lp.x - this.rp.x;
//		this.c = -(this.b * this.rp.y) - (this.a * this.rp.x); 
		
		this.a = u.y - v.y;
		this.b = v.x - u.x;
		this.c = -(this.b * u.y) - (this.a * u.x); 
	}
	
	public String toString(){
		return lp.toString()+"--"+rp.toString();
	}
	
	public boolean contains(double x, double y){
		boolean u = lp.x <= x && x <= rp.x;
		boolean v = bp.y <= y && y <= tp.y;
		return u && v;
	}
	
	public double length(){
		double x = (rp.x - lp.x) * (rp.x - lp.x);
		double y = (rp.y - lp.y) * (rp.y - lp.y);
		return Math.sqrt(x + y);
	}
	
	public boolean equal(Segment s){
		boolean b = this.lp.equal(s.lp) && this.rp.equal(s.rp);
		return b;
	}
	
	public boolean leftOf(Segment s){
		boolean b1 = this.lp.leftOf(s.lp);
		boolean b2 = this.lp.equal(s.lp) && this.rp.above(s.rp);
		return b1 || b2;
	}
	
	public boolean above(Segment s){
		boolean b1 = this.lp.above(s);
		boolean b2 = this.lp.equal(s.lp) && this.rp.above(s);
//		System.out.print("Left point above line: "+ b1);
//		System.out.print(" \t Left points equal and right point above line: "+b2);
//		System.out.println("/t Verdict: "+(b1 || b2));
		return b1 || b2;
	}
	
	public boolean endAbove(Segment s){
		boolean b1 = this.rp.above(s);
		boolean b2 = this.rp.equal(s.rp) && this.lp.above(s);
		return b1 || b2;
	}
	
	public boolean endBefore(Segment s){
		boolean b1 = this.rp.leftOf(s.rp);
//		boolean b2 = this.rp.equal(s.rp) && this.lp.above(s.lp);
		boolean b2 = this.rp.equal(s.rp) && this.lp.leftOf(s.lp);
		return b1 || b2;
	}
	
}