package com.kraqqen.util.storage;

public class HeapSorter {
	
	public static void sort(int[] a){
		int n = a.length - 1;
		for(int i=(n-1)/2;i>=0;i--){
			fixheap(a,i,n);
		}
		while(n>0){
			ArrayMethods.swap(a,0,n);
			n--;
			fixheap(a,0,n);
		}
	}
	
	private static void fixheap(int[] a, int rootIndex, int lastIndex){
		
		int rootValue = a[rootIndex];
		int index = rootIndex;
		boolean more = true;
		while(more){
			int childIndex = getLeftChildIndex(index);
			if(childIndex >= lastIndex){
				int rightChildIndex = getRightChildIndex(index);
				if(rightChildIndex <= lastIndex && a[rightChildIndex] > a[childIndex]){
					childIndex = rightChildIndex;
				}
				
				if(a[childIndex] > rootValue){
					a[index] = a[childIndex];
					index = childIndex;
				}
				else{
					more = false;
				}
			}
			else{
				more = false;
			}
		}
		a[index] = rootValue;
	}
	
	private static int getLeftChildIndex(int index){
		return 2*index+1;
	}
	
	private static int getRightChildIndex(int index){
		return 2*index+2;
	}

}
