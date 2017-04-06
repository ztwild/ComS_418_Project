package Objects;

public class Vector{
	public Vector next, prev, twin;
	public Segment seg;
	public Point pt, ptDest;
	public Vector(Point pt, Segment seg){
		this.pt = pt;
		this.seg = seg;
		if(seg.rp == pt){
			ptDest = seg.lp;
		}else{
			ptDest = seg.rp;
		}
	}
	
	public void assignNext(Point newPt){
		int n = newPt.vectors.size();
		if(n == 0){
			next = twin;
			twin.prev = this;
		}else{
			Vector v1 = newPt.vectors.get(0);
			Vector v2 = v1;
			for(int i = 1; i < n; i++){
				Vector v3 = newPt.vectors.get(i);
//				double ang1 = angle(pt, newPt, v1.next.pt);
//				double ang2 = angle(pt, newPt, v2.next.pt);
//				double ang3 = angle(pt, newPt, v3.next.pt);
				double ang1 = angle(pt, newPt, v1.ptDest);
				double ang2 = angle(pt, newPt, v2.ptDest);
				double ang3 = angle(pt, newPt, v3.ptDest);
//				System.out.println(v1.toString()+": "+ang1);
//				System.out.println(v2.toString()+": "+ang2);
//				System.out.println(v3.toString()+": "+ang3);
//				System.out.println("---------------------");
				if(ang3 > ang1 || Double.isNaN(ang1)){
					v1 = v3;
				}
				if((ang3 < ang2) || Double.isNaN(ang2)){
					v2 = v3;
				}
			}
//			System.out.println("v1: "+ v1.toString());
//			System.out.println("v2: "+ v2.toString());
//			System.out.println("-------------------");
			next = v1;
			v1.prev = this;
			
			v2 = v2.twin;
			v2.next = twin;
			twin.prev = v2;
		}
		pt.vectors.add(this);
	}
	
	public double cross(Point p1, Point p2, Point p3){
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p1, p3);
		int cross = (s1.a*s2.b) - (s2.a*s1.b);
		double ang = Math.asin(cross/ (s1.length() * s2.length()) );
		return ang;
	}
	
	public static double dot(Point p1, Point p2, Point p3){
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p2, p3);
		int dot = (p1.x-p2.x)*(p3.x-p2.x) + (p1.y-p2.y)*(p3.y-p2.y);
		double ang = Math.acos(dot/ (s1.length() * s2.length()));
		return ang;
	}
	
	public double angle(Point p1, Point p2, Point p3){
		double c = Math.signum(cross(p1,p2,p3));
		double d = Math.PI - dot(p1,p2,p3);
		return c*d;
	}
	
	public String toString(){
		return pt.toString() + "-->" + next.pt.toString();
	}
}