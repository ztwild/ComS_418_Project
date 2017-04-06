package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Face {
	public Vector[] vectors;
	public int[] x, y;
	public int n;
	
	public Face(Vector[] vs){
		vectors = vs;
		n = vs.length;
		fillArrays();
	}
	
	public Face(ArrayList<Vector> vs){
		vectors = new Vector[vs.size()];
		n = vs.size();
		for(int i = 0; i < n; i++){
			vectors[i] = vs.get(i);
		}
		fillArrays();
	}
	
	public void fillArrays(){
		x = new int[n];
		y = new int[n];
		
		for(int i = 0; i < n; i++){
			x[i] = vectors[i].pt.x * 10;
			y[i] = 1000 - vectors[i].pt.y * 10;
		}
	}
	
	
	public void drawFace(Graphics2D g2, int dim){
		
		g2.fillPolygon(x, y, n);
		
	}
}
