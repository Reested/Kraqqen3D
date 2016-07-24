package com.kraqqen.util.storage;

import java.util.AbstractCollection;
import java.util.Queue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<AnyType> extends AbstractCollection<AnyType> implements Queue<AnyType> {

	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[DEFAULT_CAPACITY + 1];
	}

	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[DEFAULT_CAPACITY + 1];
	}

	public PriorityQueue(Collection<? extends AnyType> coll) {
		cmp = null;
		currentSize = coll.size();
		array = (AnyType[]) new Object[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (AnyType item : coll)
			array[i++] = item;
		buildHeap();
	}

	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null)
			return ((Comparable) lhs).compareTo(rhs);
		else
			return cmp.compare(lhs, rhs);
	}

	public boolean add(AnyType x) {
		if (currentSize + 1 == array.length)
			doubleArray();

		// Percolate up
		int hole = ++currentSize;
		array[0] = x;

		for (; compare(x, array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;

		return true;
	}

	public int size() {
		return currentSize;
	}

	public void clear() {
		currentSize = 0;
	}

	public Iterator<AnyType> iterator() {
		return new Iterator<AnyType>() {
			int current = 0;

			public boolean hasNext() {
				return current != size();
			}

			public AnyType next() {
				if (hasNext())
					return array[++current];
				else
					throw new NoSuchElementException();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public AnyType element() {
		if (isEmpty())
			throw new NoSuchElementException();
		return array[1];
	}

	public AnyType remove() {
		AnyType minItem = element();
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	private static final int DEFAULT_CAPACITY = 100;

	private int currentSize; 
	private AnyType[] array; 
	private Comparator<? super AnyType> cmp;

	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && compare(array[child + 1], array[child]) < 0)
				child++;
			if (compare(array[child], tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	private void doubleArray() {
		AnyType[] newArray;

		newArray = (AnyType[]) new Object[array.length * 2];
		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];
		array = newArray;
	}

	@Override
	public boolean offer(AnyType arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AnyType peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnyType poll() {
		// TODO Auto-generated method stub
		return null;
	}
}
