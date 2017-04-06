package Sorting;

import Objects.Point;
import Objects.Segment;

public class MergeSort {
	/**
	 * Sorts array of Points by leftmost x coordinate to right
	 * (in event of same x coordinate, sort by lowest y coordinate)
	 * @param p
	 * @return sorted array of Points (X coordinates)
	 */
	public Point[] sortX(Point[] p){
		int n = p.length;
		Point[] newS = p;
//		Point[] newS = new Point[n];
//		newS[0] = p[0];
		if(n > 1){
			Point[] arr1 = new Point[n/2];
			Point[] arr2 = new Point[n - n/2];
			System.arraycopy(p, 0, arr1, 0, n/2);
			System.arraycopy(p, n/2, arr2, 0, n-n/2);
			sortX(arr1);
			sortX(arr2);
						
			int a = 0; 
			int b = 0;
			while(a < n/2 && b < n-n/2){
				if(arr1[a].leftOf(arr2[b])){
					newS[a+b] = arr1[a++];
				}else{
					newS[a+b] = arr2[b++];
				}
			}while(a < n/2){
				newS[a+b] = arr1[a++];
			}while(b < n-n/2){
				newS[a+b] = arr2[b++];
			}
		}
		return newS;
	}
	
	/**
	 * Sorts array of Points by highest y coordinate to lowest
	 * (in event of same y coordinate, sort by leftmost x coordinate)
	 * @param p
	 * @return sorted array of Points (Y coordinates)
	 */
	public Point[] sortY(Point[] p){
		int n = p.length;
		Point[] newS = p;
//		Point[] newS = new Point[n];
//		newS[0] = p[0];
		if(n > 1){
			Point[] arr1 = new Point[n/2];
			Point[] arr2 = new Point[n - n/2];
			System.arraycopy(p, 0, arr1, 0, n/2);
			System.arraycopy(p, n/2, arr2, 0, n-n/2);
			arr1 = sortY(arr1);
			arr2 = sortY(arr2);
							
			int a = 0; 
			int b = 0;
			while(a < n/2 && b < n-n/2){
				if(arr1[a].above(arr2[b])){
					newS[a+b] = arr1[a++];
				}else{
					newS[a+b] = arr2[b++];
				}
			}while(a < n/2){
				newS[a+b] = arr1[a++];
			}while(b < n-n/2){
				newS[a+b] = arr2[b++];
			}
		}
		return newS;
	}
	
	/**
	 * Returns an array of Segments from the index start to end
	 * @param s
	 * @param start
	 * @param end
	 * @return Array of Segments from index 'start' to 'end'
	 */
	public Segment[] sortSeg(Segment[] s){
		int n = s.length;
		Segment[] newS = s;
//		Segment[] newS = new Segment[n];
//		newS[0] = s[0];
		if(n > 1){
			Segment[] arr1 = new Segment[n/2];
			Segment[] arr2 = new Segment[n - n/2];
			System.arraycopy(s, 0, arr1, 0, n/2);
			System.arraycopy(s, n/2, arr2, 0, n-n/2);
			arr1 = sortSeg(arr1);
			arr2 = sortSeg(arr2);
						
			int a = 0; 
			int b = 0;
			while(a < n/2 && b < n-n/2){
				if(arr1[a].leftOf(arr2[b])){
					newS[a+b] = arr1[a++];
				}else{
					newS[a+b] = arr2[b++];
				}
			}while(a < n/2){
				newS[a+b] = arr1[a++];
			}while(b < n-n/2){
				newS[a+b] = arr2[b++];
			}
		}
		return newS;
		
	}
	
	public Segment[] sortEnd(Segment[] s){
		int n = s.length;
		Segment[] newS = s;
//		Segment[] newS = new Segment[n];
//		newS[0] = s[0];
		if(n > 1){
			Segment[] arr1 = new Segment[n/2];
			Segment[] arr2 = new Segment[n - n/2];
			System.arraycopy(s, 0, arr1, 0, n/2);
			System.arraycopy(s, n/2, arr2, 0, n-n/2);
			arr1 = sortEnd(arr1);
			arr2 = sortEnd(arr2);
						
			int a = 0; 
			int b = 0;
			while(a < n/2 && b < n-n/2){
				Segment s1 = arr1[a];
				Segment s2 = arr2[b];
				if(arr1[a].endBefore(arr2[b])){
					newS[a+b] = arr1[a++];
				}else{
					newS[a+b] = arr2[b++];
				}
			}while(a < n/2){
				newS[a+b] = arr1[a++];
			}while(b < n-n/2){
				newS[a+b] = arr2[b++];
			}
		}
		return newS;
		
	}
}
