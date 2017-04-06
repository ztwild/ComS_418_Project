package Sorting;

import java.util.Random;

import Objects.*;

public class Treap {
	public Node root;
	
	public Treap(Point[] p){
		root = new Node(p[0]);
		for(int i = 1; i < p.length; i++){
			Node temp = insert(p[i]);
		}
	}
	
	public Treap(Segment s){
		root = new Node(s);
	}
	
	public Node insert(Point p){
		Node insert = null;
		if(root.pt != null){
			return insert(new Node(p));
		}
		return null;
	}
	
	public Node insert(Segment s){
		Node insert = null;
		if(root.seg != null){
			return insert(new Node(s));
		}
		return null;
	}
	
	public Node insert(Node insert){
		Node temp = root;
		while(temp != null){
			insert.parent = temp;
			if(insert.above(temp)){
				temp = temp.right;
			}else{
				temp = temp.left;
			}
		}
		//assigns the insert to the left/right of its new parent
//		Point parentPt = insert.parent.pt;
		if(insert.above(insert.parent)){
			insert.parent.right = insert;
		}else{
			insert.parent.left = insert;
		}
		//shifts up the tree if it has a bigger heap
		while(insert.parent != null && insert.heap > insert.parent.heap){
			if(insert.parent.left == insert){
				insert.rotateRight();
			}else{
				insert.rotateLeft();
			}
		}
		if(insert.parent == null){
			root = insert;
		}
	
		return insert;
	}
	
	public void deleteSegment(Segment s){
		Node insert = null;
		if(root != null){
			insert = new Node(s);
			Node temp = root;
			while(temp != null){
				if(insert.equal(temp)){
					temp.delete();
					temp = null;
				}else if(insert.above(temp)){
					temp = temp.right;
				}else{
					temp = temp.left;
				}
			}
		}
	}
	
	public Node findSegment(Point p){
		Node insert = new Node(p);
		Node temp = root;
		while(temp != null){
			insert.parent = temp;
			if(insert.above(temp)){
				temp = temp.right;
			}else{
				temp = temp.left;
			}
		}
		if(insert.above(insert.parent)){
			insert.parent.left = insert;
		}else{
			insert.parent.right = insert;
		}
		return insert;
	}
	
}
