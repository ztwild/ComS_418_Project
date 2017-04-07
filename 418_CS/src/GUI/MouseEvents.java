package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Objects.Point;
import Objects.Segment;

public class MouseEvents implements MouseListener{
	public MyCanvas canvas;
	public Segment s1, s2;		//For testing
	public MouseEvents(MyCanvas c){
		canvas = c;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int a = arg0.getX();
		int b = arg0.getY();
		int x = a/10;
		int y = 100 - b/10;
		
//		int x = (100 * a)/1020;
//		int y = 100 - (100 * b)/1080;
		
		Point[] p = {new Point(x, y)};
		canvas.addPoint(p);
//		System.out.println("(" + x + "," + y + ")");
		
//		pointTest(p[0]);
	}
	
	public void setTest(Segment s){
		s1 = new Segment(s.lp, s.rp);
		s2 = new Segment(s.rp, s.lp);
	}
	
	public void pointTest(Point p){
		System.out.println(p.above(s1)+" and "+p.above(s2));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
