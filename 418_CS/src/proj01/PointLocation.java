package proj01;

import java.util.ArrayList;
import java.util.Arrays;

import Objects.Node;
import Objects.Point;
import Objects.Segment;
import Objects.Vector;
import Sorting.MergeSort;
import Sorting.Treap;

public class PointLocation {

	public ArrayList<Vector> PointLocation(Point p, Segment[] seg){
		MergeSort ms = new MergeSort();
		boolean done = false;
		int n = seg.length;
		
		Segment[] ends = new Segment[n];
		Segment[] starts = new Segment[n];
		for(int i = 0; i < n; i++){
			Segment dumb = new Segment(seg[i].lp, seg[i].rp );
			ends[i] = dumb;
			starts[i] = dumb;
		}
		
		for(int i = 0; i < n; i++){
			System.out.print(starts[i].toString());
		}System.out.println("\r----------------STaRTS----------------");
		for(int i = 0; i < n; i++){
			System.out.print(ends[i].toString());
		}System.out.println("\r----------------EnDS----------------");
		
		ends = ms.sortEnd(ends);
		starts = ms.sortSeg(starts);
		
		Treap treeSeg = new Treap(starts[0]);
		int s = 1;
		int e = 0;
		Node node = null;
		while(s < n && e < n && !done){
			Point start = starts[s].lp;
			Point end = ends[e].rp;
			Segment watch;
			if(end.x <= start.x){
				watch = ends[e];
				treeSeg.deleteSegment(ends[e++]);
			}else if(start.x <= p.x){
				watch = starts[s];
				treeSeg.insert(starts[s++]);
			}else{
				node = treeSeg.findSegment(p);	//returns the segment below point
				done = true;
			}
			printTree(treeSeg.root);
		}
		
		Point pt = null;
		ArrayList<Vector> vctrs = new ArrayList();
		if(node != null && 
				node.prev() != null && 
				node.next() != null){
			node = node.prev();
			pt = node.seg.lp;
			ArrayList<Vector> ptVctrs = pt.vectors;	//Copy vectors from the point
			Vector v = null;
			for(int i = 0; i < ptVctrs.size(); i++){	//find the vector with same segment
				if(ptVctrs.get(i).seg == node.seg){
					v = ptVctrs.get(i);
					break;
				}
			}
			Vector temp = v;
			vctrs = new ArrayList(Arrays.asList(temp)); //All Vectors of the face
			while(temp.next != v){
				temp = temp.next;
				vctrs.add(temp);
				
			}
//			System.out.println("------End of Point Location------");
		}
		return vctrs;
	}
	
	public void printTree(Node root){
		Node temp = root;
		while(temp.left != null){
			temp = temp.left;
		}
		System.out.print(temp.toString());
		while(temp.next() != null){
			temp = temp.next();
			System.out.print(temp.toString());
		}System.out.println("\r-----------------TReE-----------------");
	}
	
}
