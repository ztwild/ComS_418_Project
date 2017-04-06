package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Objects.Point;
import Objects.Segment;

public class MouseEvents implements MouseListener{
	public MyCanvas canvas;
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
