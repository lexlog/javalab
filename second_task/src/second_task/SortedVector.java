package second_task;

import java.util.Iterator;
import java.util.Vector;


public class SortedVector<T> implements ISortedVector, Iterable<T> {

	public T[] arr; // array of elements
	private int size; // size of array
	private int maxSize; // max size of array

	public SortedVector(int maxSize) {
		super();
		this.size = 0;
		this.maxSize = maxSize;
		this.arr = (T[]) new Object[maxSize];
	}
//ДОБАВИТЬ GENERICI
	@Override
	public void add(Comparable o) {
		if (size == maxSize) { //если вектор заполнен, выделяем в 2 раза больше памяти
			resizeArray();
		}		
		int temp=0;
		boolean flag=false;
		for(int i=0;i<size;i++) {
			if (o.compareTo(arr[i]) == -1) { //функция сравнения - ищем, куда вставить
				temp=i;
				flag=true;
				break;
			}	
		}
		
		if ( temp == 0 && flag == false) { // если добавляемый эл-т больше всех, то в конец
			arr[size] =(T) o;
		}
		else {
			for(int i=size;i>temp;i--) { //сдвигаем и вставляем 
				arr[i]=arr[i-1];	
			}
			arr[temp]=(T)o;
		}
		size++;
	}

	private void resizeArray() { //выделение памяти при заполнении вектора (динамичный)
		int temp = maxSize * 2;
		Object[] tmp = new Object[maxSize];
		for (int i = 0; i < maxSize; i++) {
			tmp[i] = arr[i];
		}
		arr = (T[]) new Object[temp];

		for (int i = 0; i < maxSize; i++) {
			arr[i] = (T) tmp[i];
		}
		maxSize = temp;
	}

	@Override
	public void remove(int index) { //удал. со сдвигом
		if (index <= size && index >= 0) {
			for (int i=index;i<size;i++)
			{
				arr[i]=arr[i+1];
			}
			size--;
		}
	}

	@Override
	public Comparable get(int index) { //возврат значения по индексу
		if (index <= size && index >= 0) {
			return (Comparable)arr[index];
		}
		return -1;//нет такого индекса
	}

	@Override
	public int indexOf(Comparable o) { // возврат индекса по значению
		for (int i=0;i<size;i++) {
			if (arr[i]==o)
				return i;
		}
		return -1;//нет такого элемента
	}

	@Override
	public Iterator iterator() { //итератор
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{

		T currentEl;
		int number = -1;
		
		
		
		@Override
		public boolean hasNext() {
			if (number == size)
				return false;
			return true;
		}

		@Override
		public T next() {
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
		A.remove(0);
		for (Object a : A) {
			System.out.print(a);
		}
		System.out.println();
		System.out.println(A.indexOf(55));
		System.out.println(A.indexOf(3));
		
	}

}
