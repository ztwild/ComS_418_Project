package Objects;

import java.util.Random;

public class Node{
	public Point pt;
	public Segment seg;
	public Node right, left, parent;
	public int heap;
	public Random rand = new Random();
	
	public Node(Point p){
		pt = p;
		heap = rand.nextInt(100);
	}
	
	public Node(Segment s){
		seg = s;
		heap = rand.nextInt(100);
	}
	
	public boolean leftOf(Node n){
		if(this.pt != null){
			return this.pt.leftOf(n.pt);
		}else{
			return this.seg.leftOf(n.seg);
		}
	}
	
	public boolean above(Node n){
		if(this.seg != null){
			return this.seg.above(n.seg);
		}else{
			return this.pt.above(n.seg);
		}
	}
	
	public boolean endAbove(Node n){
		if(this.seg != null){
			return this.seg.endAbove(n.seg);
		}else{
			return false;
		}
	}
	
	public String toString(){
		if(pt != null){
			return pt.toString();
		}else if(seg != null){
			return seg.toString();
		}else{
			return "null";
		}
	}
	
	public boolean equal(Node n){
		if(this.pt != null){
			return this.pt.equal(n.pt);
		}else{
			return this.seg.equal(n.seg);
		}
	}
	
	//This can return null
	public Node next(){
		Node temp = this;
		if(temp.right != null){
			temp = temp.right;
			while(temp.left != null){
				temp = temp.left;
			}
		}else if(temp.parent != null){
			while(temp != null && temp.parent != null && temp == temp.parent.right){
				temp = temp.parent;
			}
			temp = temp.parent;
		}else{
			temp = null;
		}
		return temp;
	}
	
	//This can return null
	public Node prev(){
		Node temp = this;
		if(temp.left != null){
			temp = temp.left;
			while(temp.right != null){
				temp = temp.right;
			}
		}else if(temp.parent != null){
			while(temp != null && temp.parent != null && temp == temp.parent.left){
				temp = temp.parent;
			}
			temp = temp.parent;
		}else{
			temp = null;
		}
		return temp;
	}
	
	public Node delete(){
		Node lastParent = null;
		while(left != null || right != null){
			if(left != null){
				left.rotateRight();
			}else{
				right.rotateLeft();
			}
		}
		lastParent = parent;
		if(parent != null && parent.left == this){
			parent.left = null;
			parent = null;
		}else if(parent != null && parent.right == this){
			parent.right = null;
			parent = null;
		}
		return lastParent;
	}
	
	public void rotateLeft(){
		if(this.parent != null){
			Node swap = parent;
			swap.right = left;
			if(left != null){
				left.parent = swap;
			}
			parent = swap.parent;
			if(parent != null){
				if(parent.left == swap)
					parent.left = this;
				else
					parent.right = this;
			}
			swap.parent = this;
			left = swap;
		}
		
	}
	
	public void rotateRight(){
		if(this.parent != null){
			Node swap = parent;
			swap.left = right;
			if(right != null){
				right.parent = swap;
			}
			parent = swap.parent;
			if(parent != null){
				if(parent.left == swap)
					parent.left = this;
				else
					parent.right = this;
			}
			swap.parent = this;
			right = swap;
			
		}
	}
}
