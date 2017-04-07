package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Objects.*;

public class SelectedObjects {
	
	public Point point;
	public Segment segment;
	public Vector vector;
	public Face face;
	
	public void select(Point p){
		clear();
		point = p;
	}
	
	public void select(Segment s){
		clear();
		segment = s;
	}
	
	public void select(Vector v){
		clear();
		vector = v;
	}
	
	public void select(Face f){
		clear();
		face = f;
	}
	
	public void clear(){
		point = null;
		segment = null;
		vector = null;
		face = null;
	}
	
	
	
	public void drawSelected(Graphics2D g2, int dim){
		if(vector != null){
			drawVector(g2, dim, vector);
		}if(segment != null){
			drawSegment(g2, dim, segment);
			drawPoint(g2, dim, segment.lp);
			drawPoint(g2, dim, segment.rp);
		}if(point != null){
			drawPoint(g2, dim, point);
		}
	}
	
	public void drawFace(Graphics2D g2){
//		System.out.println("Points for face");
//		for(int i = 0; i < face.x.length; i++){
//			System.out.println("x: "+ face.x[i] + " y: " + face.y[i]);
//		}
		if(face != null && face.n > 2){
			g2.fillPolygon(face.x, face.y, face.n);
		}
	}
	
	public void drawVector(Graphics2D g2, int dim, Vector v){
		Segment s = v.seg;
		drawSegment(g2, dim, s);
		Point p = v.pt;
		drawPoint(g2, dim, p);
	}
	
	public void drawSegment(Graphics2D g2, int dim, Segment s){
		int x1 = s.lp.x * 10;
		int y1 = dim - s.lp.y * 10;
		int x2 = s.rp.x * 10;
		int y2 = dim - s.rp.y * 10;
		g2.drawLine(x1, y1, x2, y2);
	}
	
	public void drawPoint(Graphics2D g2, int dim, Point p){
		int x = (p.x*10)- 8;
		int y = (dim - p.y*10) - 8;
		g2.fillOval(x, y, 16, 16);
	}
	
}
