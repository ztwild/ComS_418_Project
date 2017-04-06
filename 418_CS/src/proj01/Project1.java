package proj01;

import java.util.ArrayList;
import java.util.Random;

import GUI.GUI;
import Objects.*;
import Sorting.*;
import Sorting.Treap.*;

public class Project1 {
	public static void main(String[] args){
		GUI gui = new GUI();
		gui.setVisible(true);
		
//		testRun(gui);
	}
	
	public static void testRun(GUI gui){
		DCEL dcel = gui.canvas.dcel;
		MergeSort srt = new MergeSort();
		
		Point[] p = new Point[9];
		p[0] = new Point(25, 35);
		p[1] = new Point(27, 13);
		p[2] = new Point(53, 24);
		p[3] = new Point(48, 45);
		p[4] = new Point(10, 56);
		p[5] = new Point(30, 79);
		p[6] = new Point(62, 83);
		
		Segment[] s = new Segment[9];
		s[0] = new Segment(p[1], p[2]);
		s[1] = new Segment(p[1], p[4]);
		s[2] = new Segment(p[2], p[0]);
		s[3] = new Segment(p[6], p[0]);
		s[4] = new Segment(p[4], p[0]);
		s[5] = new Segment(p[2], p[3]);
		s[6] = new Segment(p[6], p[3]);
		s[7] = new Segment(p[6], p[5]);
		s[8] = new Segment(p[5], p[4]);
		
		
		
		dcel.addPoints(p);
		dcel.addSegments(s);
	}
	
	
}