package GUI;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Objects.*;
import proj01.*;

public class MyCanvas extends Canvas{
	private int dim, size;
	private SelectedObjects so;
	
	public DCEL dcel = new DCEL();
	public MouseEvents ms;
	
	public MyCanvas(int size){
//		ms = new MouseEvents(this);		//For testing
//		this.addMouseListener(ms);		//For testing
		
		this.addMouseListener(new MouseEvents(this));
		this.size = size/10;
		dim = 1000;
		setSize(dim, dim);
        so = new SelectedObjects();
	}
	
	public void clear(){
		so.clear();
		dcel.Points = new Point[0];
		dcel.Segments = new Segment[0];
		dcel.Vectors = new ArrayList();
		repaint();
	}
	
	public void randPoints(int n, int m){
		Point[] pts = dcel.randomPts(n, m);
		so.clear();
		dcel.Points = pts;
		dcel.Segments = new Segment[0];
		dcel.Vectors = new ArrayList();
		repaint();
	}
	
	public void addPoint(Point[] p){
		so.clear();
		so.select(p[0]);
		dcel.addPoints(p);
		repaint();
	}
	
	public void Convexhull(){
		if(dcel.Points.length > 2){
			Point[] p = dcel.Points;
			Convex con = new Convex();
			Segment[] s = con.convexHull(p);
			if(s.length < 2){
				System.out.println("::::ERROR::::\rCannot make Convex Hull from points given");
			}else{
				dcel.addSegments(s);
				so.clear();
				repaint();
			}
			
		}else{
			System.out.println("::::ERROR::::\rNot Enough Points on Plane");
		}
		
	}
	
	public void Triangulate(){
		so.clear();
		dcel.triangulate();
		repaint();
	}
	
	public void PointLocation(){
		if(so.point != null && dcel.Segments.length > 0){
			Segment[] s = dcel.Segments;
			PointLocation pl = new PointLocation();
			Face face = pl.locatePoint(so.point, s);
			so.select(face);
			repaint();
		}else{
			System.out.println("Point isn't located in a face");
		}
		
	}
	
	public void pickVector(){
		int n = dcel.Vectors.size();
		if(n > 0){
			Random rand = new Random();
			int index = rand.nextInt(n);
			so.select(dcel.Vectors.get(index));
//			so.select(dcel.Vectors[index]);
			repaint();
		}
	}
	
	public void nextVector(){
		if(so.vector != null){
			so.vector = so.vector.next;
			repaint();	
		}
	}
	
	public void prevVector(){
		if(so.vector != null){
			so.vector = so.vector.prev;
			repaint();
		}
	}
	
	public void twinVector(){
		if(so.vector != null){
			so.vector = so.vector.twin;
			repaint();
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if(so.face == null){
			setBackground(Color.WHITE);
		}else if( so.face.inside){
			setBackground(Color.WHITE);
			g2.setColor(Color.GREEN);
			so.drawFace(g2);
		}else{
			setBackground(Color.GREEN);
			g2.setColor(Color.WHITE);
			so.drawFace(g2);
		}
		//Draws a black grid
		g2.setColor(Color.BLACK);
		for(int i = 1; i < size; i++){
			int increment = dim/size * i;
			g2.drawLine(increment, 0, increment, dim); //Vertical axis
			g2.drawLine(0, increment, dim, increment); //Horizontal axis
		}
		//Draws all objects on plane blue
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(3));
		for(Point p : dcel.Points){
			int x = (p.x*10)- 5;
			int y = (dim - p.y*10) - 5;
			g2.fillOval(x, y, 10, 10);
		}for(Segment s : dcel.Segments){
			int x1 = s.lp.x * 10;
			int y1 = dim - s.lp.y * 10;
			int x2 = s.rp.x * 10;
			int y2 = dim - s.rp.y * 10;
			g2.drawLine(x1, y1, x2, y2);
		}
		//Drawing selected objects red
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(5));
		so.drawSelected(g2, dim);
		
		//Example how to draw a polygon
//		g2.setColor(Color.GREEN);
//		int[] x = {200,150,300,450,400};
//		int[] y = {dim-200,dim-350,dim-450,dim-350,dim-200};
//		g2.fillPolygon(x, y, 5);
	}
	
}
