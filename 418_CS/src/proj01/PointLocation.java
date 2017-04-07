package proj01;

import java.util.ArrayList;
import java.util.Arrays;

import Objects.Face;
import Objects.Node;
import Objects.Point;
import Objects.Segment;
import Objects.Vector;
import Sorting.MergeSort;
import Sorting.Treap;

public class PointLocation {

	public void PointLocation(Point p, Segment[] seg){
		Face face = locatePoint(p, seg);
		
	}
	
	public Face locatePoint(Point p, Segment[] seg){
		MergeSort ms = new MergeSort();
		int n = seg.length;
		
		Segment[] ends = new Segment[n];
		Segment[] starts = new Segment[n];
		for(int i = 0; i < n; i++){
			Segment dumb = new Segment(seg[i].lp, seg[i].rp );
			ends[i] = dumb;
			starts[i] = dumb;
		}
		
		ends = ms.sortEnd(ends);
		starts = ms.sortSeg(starts);
		
		for(int i = 0; i < n; i++){
			System.out.print(starts[i].toString()+", ");
		}System.out.println("\r----------------STaRTS----------------");
		for(int i = 0; i < n; i++){
			System.out.print(ends[i].toString()+", ");
		}System.out.println("\r----------------EnDS----------------");
		
		Treap treeSeg = new Treap(starts[0]);
		int s = 1;
		int e = 0;
		printTree(treeSeg);	//Prints the initial tree
		Point start = null;
		Point end = null;
		while(e < n){
			if(s < n){
				start = starts[s].lp;
			}end = ends[e].rp;
			
			if(p.x < end.x && (s >= n || p.x < start.x)){
				System.out.println("::::::::LoCaTiNG "+p.toString()+"::::::::");
				Node node = treeSeg.findSegment(p);	//returns the segment below point
				boolean inside = (node.prev() != null && node.next() != null);
				System.out.println("Point is inside the polygon: "+inside);
				Segment segment = (inside ? node.prev().seg : starts[0]);
				return getFace(segment, inside);
			}else if((s >= n && end.x <= p.x) || (s < n && end.x <= start.x)){
				System.out.println("::::::::DeLeTiNG "+ends[e].toString()+"::::::::");
				treeSeg.deleteSegment(ends[e++]);
			}else{
				System.out.println("::::::::aDDiNG "+starts[s].toString()+"::::::::");
				treeSeg.insert(starts[s++]);
			}
			printTree(treeSeg);		//Prints tree after insertion/deletion
		}
		Segment segment = starts[0];	//This is the highest, leftmost segment.
		return getFace(segment, false) ;  	//Outside the polygon
	}
	
	public Face getFace(Segment s, boolean inside){
		ArrayList<Vector> vctrs = new ArrayList();
		Point pt = s.lp;
		ArrayList<Vector> ptVctrs = pt.vectors;	//Copy vectors from the point
		Vector v = null;
		for(int i = 0; i < ptVctrs.size(); i++){	//find the vector with same segment
			Vector v1 = ptVctrs.get(i);
			if(v1.seg.equal(s)){
				v = v1;
			}
		}
		Vector temp = v;
		vctrs = new ArrayList(Arrays.asList(temp)); //All Vectors of the face
		while(temp.next != v){
			temp = temp.next;
			vctrs.add(temp);			
		}
		return new Face(vctrs, inside);
	}
	
	public static void printTree(Treap t){
		Node r = t.root;
		if(r != null){
			while(r.left != null){
				r = r.left;
			}
			System.out.println("--------TReaP--------");
			System.out.print(r.toString()+ ", ");
			while(r.next() != null){
				r = r.next();
				System.out.print(r.toString()+ ", ");
			}
			System.out.println("\r--------eND oF TReaP--------");
			
		}else{
			System.out.println("!!!!!!!!!! TReaP DoNe !!!!!!!!!!");
		}
		
	}
	
}
