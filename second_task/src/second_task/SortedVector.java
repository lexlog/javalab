package second_task;

import java.util.Iterator;
import java.util.Vector;


public class SortedVector implements ISortedVector, Iterable {

	public Object[] arr; // array of elements
	private int size; // size of array
	private int maxSize; // max size of array

	public SortedVector(int maxSize) {
		super();
		this.size = 0;
		this.maxSize = maxSize;
		this.arr = new Object[maxSize];
	}

	@Override
	public void add(Comparable o) {
		if (size == maxSize) {
			resizeArray();
		}		
		int temp=0;
		boolean flag=false;
		for(int i=0;i<size;i++) {
			if (o.compareTo(arr[i]) == -1) {
				temp=i;
				flag=true;
				break;
			}	
		}
		
		if ( temp == 0 && flag == false) {
			arr[size] = o;
		}
		else {
			for(int i=size;i>temp;i--) {
				arr[i]=arr[i-1];	
			}
			arr[temp]=o;
		}
		size++;
	}

	private void resizeArray() {
		// TODO Auto-generated method stub
		int temp = maxSize * 2;
		Object[] tmp = new Object[maxSize];
		for (int i = 0; i < maxSize; i++) {
			tmp[i] = arr[i];
		}
		arr = new Object[temp];

		for (int i = 0; i < maxSize; i++) {
			arr[i] = tmp[i];
		}
		maxSize = temp;
	}

	@Override
	public void remove(int index) {
		if (index <= size && index >= 0) {
			for (int i=index;i<size;i++)
			{
				arr[i]=arr[i+1];
			}
			size--;
		}
	}

	@Override
	public Comparable get(int index) {
		if (index <= size && index >= 0) {
			return (Comparable)arr[index];
		}
		return -1;
	}

	@Override
	public int indexOf(Comparable o) {
		for (int i=0;i<size;i++) {
			if (arr[i]==o)
				return i;
		}
		return -1;
	}

	@Override
	public Iterator iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Object>{

		Object currentEl;
		int number = -1;
		
		public MyIterator() 
		{
			currentEl=arr[0]; 
		}
		
		@Override
		public boolean hasNext() {
			if (number == size)
				return false;
			return true;
		}

		@Override
		public Object next() {
			currentEl = arr[++number];
			return currentEl;
		}
		
	}

	public static void main(String[] args) {
		SortedVector A = new SortedVector(2);
		A.add(3);
		A.add(4);
		A.add(2);
		A.add(2);
		A.add(6);
		A.add(5);
	
		for (Object a : A) {
			System.out.print(a);
		}
		
		System.out.println();
		
		System.out.println(A.get(3));
		System.out.println(A.get(8));
		System.out.println(A.get(-1));
		System.out.println(A.get(2));
	}

}
