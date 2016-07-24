package com.kraqqen.util.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSet {
	
	List<Float> numbers = new ArrayList<Float>();
	
	public DataSet(){}
	
	public void add(double value){
		numbers.add((float) value);
	}
	
	public float getAverage(){
		float sum = 0;
		for(int i = 0; i<numbers.size(); i++){
			sum = sum + numbers.get(i);
		}
		return sum/numbers.size();
	}
	
	public void getSmallest(){
		System.out.println("Smallest= " + Collections.min(numbers));
	}
	
	public void getLargest(){
		System.out.println("Largest= " + Collections.max(numbers));
	}

	public void getRange(){
		System.out.println("The range is between " + Collections.min(numbers) + " and " + Collections.max(numbers));
	}
	
	public void getStandardDeviation(){
		double mean = getAverage();
		double squareSum = 0;
		
		for (int i = 0; i < numbers.size(); i++) {
				squareSum += Math.pow(numbers.get(i) - mean, 2);
			}
		
		System.out.println(Math.sqrt((squareSum) / (numbers.size() - 1)));
	}
}
