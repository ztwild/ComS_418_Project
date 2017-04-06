package GUI;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import Objects.*;
import proj01.DCEL;

public class GUI extends JFrame{
	public int x = 1650;
	public int y = 1200;
	public MyCanvas canvas;
	public JTextField numText, maxText;
	public Font font = new Font("Arial", Font.PLAIN, 30);
	public Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	
	public GUI(){
		this.setSize(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel pane = new JPanel();
		this.add(pane, BorderLayout.WEST);

		canvas = new MyCanvas(100);
        pane.add(canvas);
        
        JPanel paneCtrl = new JPanel();
        paneCtrl.setLayout(new BoxLayout(paneCtrl, BoxLayout.Y_AXIS));
        this.add(paneCtrl, BorderLayout.EAST);
        
        //Random Panel
        JPanel paneRand = new JPanel();
        paneRand.setLayout(new FlowLayout());
        paneRand.setBorder(border);
        paneCtrl.add(paneRand);
        
        JButton randBtn = new JButton("Random Points");
        randBtn.setFont(font);
        paneRand.add(randBtn);
        randBtn.addActionListener(new RandListen());
        
        numText = new JTextField(3);
        numText.setFont(font);
        numText.setText("10");
        paneRand.add(numText);
        
        maxText = new JTextField(3);
        maxText.setFont(font);
        maxText.setText("75");
        paneRand.add(maxText);
        
        //Convex hull Panel
        JPanel paneConvex = new JPanel();
        paneConvex.setLayout(new FlowLayout());
        paneConvex.setBorder(border);
        paneCtrl.add(paneConvex);
        
        JButton convexBtn = new JButton("Convexhull");
        convexBtn.setFont(font);
        paneConvex.add(convexBtn);
        convexBtn.addActionListener(new ConvexListen());
        
        //Single Point and Segment Panel
        JPanel paneSingle = new JPanel();
        paneSingle.setLayout(new FlowLayout());
        paneSingle.setBorder(border);
        paneCtrl.add(paneSingle);
        
        JButton ptBtn = new JButton("Add Point");
        ptBtn.setFont(font);
        paneSingle.add(ptBtn);
        ptBtn.addActionListener(new PointListen());
        
        JButton clear = new JButton("Clear");
        clear.setFont(font);
        paneSingle.add(clear);
        clear.addActionListener(new ClearListen());
        
        //Vector Control Panel
        JPanel paneVect = new JPanel();
        paneVect.setLayout(new BoxLayout(paneVect, BoxLayout.Y_AXIS));
        paneVect.setBorder(border);
        paneCtrl.add(paneVect);
        
        JPanel topVect = new JPanel();
        paneVect.add(topVect);
        
        JButton vecBtn = new JButton("Pick Vector");
        vecBtn.setFont(font);
        topVect.add(vecBtn);
        vecBtn.addActionListener(new VectorListen());
        
        JButton twinBtn = new JButton("Twin Vector");
        twinBtn.setFont(font);
        topVect.add(twinBtn);
        twinBtn.addActionListener(new TwinListen());
        
        JPanel btmVect = new JPanel();
        paneVect.add(btmVect);
        
        JButton nextBtn = new JButton("Next Vector");
        nextBtn.setFont(font);
        btmVect.add(nextBtn);
        nextBtn.addActionListener(new NextListen());
        
        JButton prevBtn = new JButton("Prev Vector");
        prevBtn.setFont(font);
        btmVect.add(prevBtn);
        prevBtn.addActionListener(new PrevListen());
        
        //Triangulation Panel
        JPanel paneTri = new JPanel();
        paneTri.setLayout(new FlowLayout());
        paneTri.setBorder(border);
        paneCtrl.add(paneTri);
        
        JButton triBtn = new JButton("Triangulate");
        triBtn.setFont(font);
        paneTri.add(triBtn);
        triBtn.addActionListener(new TriangulateListen());
        
        //Point Location (Get Face) Panel
        JPanel paneFace = new JPanel();
        paneFace.setLayout(new FlowLayout());
        paneFace.setBorder(border);
        paneCtrl.add(paneFace);
        
        JButton faceBtn = new JButton("Point Location");
        faceBtn.setFont(font);
        paneFace.add(faceBtn);
        faceBtn.addActionListener(new PointLocationListen());
		
	}
	
	
	
	class RandListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int n = Integer.parseInt(numText.getText());
			int m = Integer.parseInt(maxText.getText());
			canvas.randPoints(n, m);
		}
	}
	
	class PointListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int m = Integer.parseInt(maxText.getText());
			Point[] p = DCEL.randomPts(1, m);
			canvas.addPoint(p);
			
//			canvas.dcel.randomPts(1, m);
//			canvas.dcel.addPoints(p);
//			canvas.repaint();
		}
	}
	
	class ConvexListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.Convexhull();
		}
	}
	
	class TriangulateListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.Triangulate();
		}
	}
	
	class PointLocationListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.PointLocation();
		}
	}
	
	
	
	class ClearListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.clear();
		}
	}
	
	class VectorListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.pickVector();
		}
	}
	
	class NextListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.nextVector();
		}
	}
	
	class PrevListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.prevVector();
		}
	}
	
	class TwinListen implements ActionListener{
		public void actionPerformed(ActionEvent e){
			canvas.twinVector();
		}
	}
	
	
}


