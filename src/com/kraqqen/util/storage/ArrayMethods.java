package com.kraqqen.util.storage;

import java.util.Random;

public class ArrayMethods {
	
	private int[] values;
	private static Random generator = new Random();
	
	public ArrayMethods(int[] initialValues){
		this.values = initialValues;
	}
	
	public static int[] randomIntArray(int length, int n){
		int[] a = new int[length];
		for(int i=0; i< a.length; i++){
			a[i] = generator.nextInt(n);
		}
		
		return a;
	}
	
	// part a
	public void swapFirstAndLast(){
	    int lastValue = values.length - 1;
		int x = values[lastValue];
		int y = values[0];
		values[0] = x;
		values[lastValue] = y; 
		System.out.println(values[0] +" "+ values[lastValue]);
	}
	
	// part b
	public void shiftRight(){
		int lastValue = values.length - 1;
		int x = values[lastValue];
		for (int i = (values.length - 1); i > 0 ; i--) {    
			values[i] = values[i-1];
		}
		values[0] = x;
		for(int i=0;i<values.length; i++){
			System.out.println(values[i]);
		}
	}
	
	// part c
	public void replaceEvenWith(int x){
		for(int i=0; i<values.length - 1; i++){
			if((values[i]%2)==0){
				values[i] = x;
			}
			System.out.println(values[i]);
		}
	}
	
	// part d
	public void setNumberToGreater(){
		int lastValue = values.length - 1;
		int[] temp = new int[lastValue + 1];
		temp[0] = values[0];
		temp[lastValue] = values[lastValue];
		for(int i=1;i<values.length-1;i++){
			if(values[i-1]>values[i+1]){
				temp[i] = values[i-1];
			}else{
				temp[i] = values[i+1];
			}
		}
		for(int i=0;i<values.length; i++){
			values[i] = temp[i];
			System.out.println(values[i]);
		}
	}
	
	// part e
	public void removeMiddle(){
		int pos = values.length/2;
		int currentSize = values.length;
		if((values.length%2)==0){
			//remove pos and pos-1
			for(int i=pos+1; i<currentSize; i++){
				values[i-1] = values[i];
			}
			for(int i=pos; i<currentSize; i++){
				values[i-1] = values[i];
			}
			currentSize--;
			values[currentSize] = 0;      //cannot take out unless I use an ArrayList.
			values[currentSize - 1] = 0;  //cannot take out unless I use an ArrayList.
		}else{
			//remove pos
			for(int i=pos+1; i<currentSize; i++){
				values[i-1] = values[i];
			}
			currentSize--;
			values[currentSize] = 0;      //cannot take out unless I use an ArrayList.
		}
		for(int i=0;i<values.length; i++){
			System.out.println(values[i]);
		}
	}
	
	// part f
	public void moveEvenToFront(){
		int lastValue = values.length - 1;
		int numberOfEvens = 0;
		int numberOfOdds = 0;
		for(int i=0; i< lastValue + 1; i++){
			if((values[i]%2)==0){
				numberOfEvens++;
			}else{
				numberOfOdds++;
			}
		}
		int[] temp1 = new int[numberOfEvens];
		int[] temp2 = new int[numberOfOdds];
		int currentEvenValue = 0;
		int currentOddValue = 0;
		for(int i=0; i <values.length; i++){
			if((values[i]%2)==0){
				temp1[currentEvenValue] = values[i];
				currentEvenValue++;
			}
		}
		for(int i=0; i <values.length; i++){
			if((values[i]%2)!=0){
				temp2[currentOddValue] = values[i];
				currentOddValue++;
			}
		}
		for(int i=0; i<numberOfEvens;i++){
			values[i] = temp1[i];
		}
		for(int i=numberOfEvens ; i<numberOfEvens + numberOfOdds;i++){
			values[i] = temp2[i-5];
		}
		for(int i=0; i<lastValue+1;i++){
			System.out.println(values[i]);
		}
	}
	
	// part g
	public void returnSecondLargest(){
		int highest = Integer.MIN_VALUE;
		int secondHighest = Integer.MIN_VALUE;
		for(int i = 0; i < values.length; i++){
			if(values[i]>highest){
				secondHighest = highest;
				highest = values[i];
			}else if(values[i]>secondHighest){
				secondHighest = values[i];
			}
		}
		System.out.println(secondHighest);
	}
	
	// part h
	public void isSorted(){
		int j = 0;
		for(int i = 0; i < values.length - 1; i++){
			if(values[i] < values[i+1]){
				
			}else{
				j=j+1;
			}
		}
		if(j==0){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
	
	// part i
	public void containsTwoAdjDuplicates(){
		int j = 0;
		for(int i = 0; i < values.length - 1; i++){
			if(values[i] == values[i+1]){
				j=j+1;
				break;
			}
		}
		if(j==0){
			System.out.println("false");
		}else{
			System.out.println("true");
		}
	}
	
	// part j
	public void containsTwoDuplicates(){
		int h = 0;
		 for (int i = 0; i < values.length; i++) {
	            for (int j = 0; j < values.length; j++) {
	                if (values[i]==values[j] && i != j) {
	                	h=h+1;
	                }
	            }
	        }
		 if(h==0){
				System.out.println("false");
			}else{
				System.out.println("true");
			}
	}
	
	public void removeSmallest(int[] values){
		double smallest = values[0];
		int smallestIndex = 0;
		for(int i=1; i<values.length;i++)
		{
			if(values[i] < smallest)
			{
				smallest = values[i];
				smallestIndex = i;
			}
		}
		values[smallestIndex] = 0; // I could shuffle all values up but that would still leave and int set to 0 unless I use an array list.
		for(int i=0; i<values.length - 1;i++){
		System.out.println(values[i]);
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
