package edu.uprm.cse.datastructures.cardealer.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class CircularSortedDoublyLinkedList<E> implements SortedList<E>{
	// valid position in the list
	// [0, size() - 1]	
	private int size;
	private DNode<E> header;
	private Comparator<E> comp;
	
	public CircularSortedDoublyLinkedList() {
		this.size = 0;
		this.header = new DNode<E>();
		this.header.setNext(this.header);
		this.header.setPrev(this.header);
		this.comp = new DefaultComparator();
	}
	
	public CircularSortedDoublyLinkedList(Comparator<E> comp) {
		this();
		this.comp = comp;
	}
	
	@Override
	public Iterator<E> iterator() {
		List<E> list = new ArrayList<E>();
		DNode<E> cursor = this.header.getNext();
		
		while(cursor != header) {
			list.add(cursor.getElement());
			cursor = cursor.getNext();
		}
		return list.iterator();
	}

	@Override
	public boolean add(E obj) {
		if(obj == null) {
			return false;
		}
		if(this.contains(obj)){
			return false;
		}
		
		DNode<E> temp = new DNode<E>(obj);
		
		//add to front when empty
		if(this.isEmpty()) {
			this.header.setNext(temp);
			this.header.setPrev(temp);
			temp.setNext(this.header);
			temp.setPrev(this.header);
			this.size++;
			return true;
		}
		
		//we go through the list finding the place to place the object
		DNode<E> cursor = header.getNext();
		while(cursor != header) {
			//obj is smaller or equal to the cursor
			if(comp.compare(obj,  cursor.getElement()) <= 0) {
				temp.setNext(cursor);
				temp.setPrev(cursor.getPrev());
				cursor.setPrev(temp);
				temp.getPrev().setNext(temp);
				this.size++;
				return true;
			}
			
			cursor = cursor.getNext();
		}
		
		//if it reaches the end
		temp.setNext(this.header);
		temp.setPrev(this.header.getPrev());
		this.header.setPrev(temp);
		temp.getPrev().setNext(temp);
		size++;
		return true;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean remove(E obj) {
		int index = this.firstIndex(obj);
		return this.remove(index);
	}

	@Override
	public boolean remove(int index) {
		if(index > size-1 || index<0) return false;
		
		DNode<E> ntr = this.header.getNext();
		for (int i = 0; i < index; i++) {
			ntr = ntr.getNext();
		}
		
		DNode<E> prev = ntr.getPrev();
		DNode<E> next = ntr.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		ntr.clear();
		
		size--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int firstIdx = this.firstIndex(obj);
		int lastIdx = this.lastIndex(obj);
		int currSize = this.size();
		
		for(int i = firstIdx; i<=lastIdx; i++) {
			this.remove(firstIdx);
		}
		return currSize - this.size();
	}

	@Override
	public E first() {
		return header.getNext().getElement();
	}

	@Override
	public E last() {
		return header.getPrev().getElement();
	}

	@Override
	public E get(int index) {
		DNode<E> ntr = this.header.getNext();
		for (int i = 0; i < index; i++) {
			ntr = ntr.getNext();
		}
		
		return ntr.getElement();
	}

	@Override
	public void clear() {
		//first we clear the nodes to help the GC
		DNode<E> cursor = header.getNext();
		while(cursor != header) {
			DNode<E> ntc = cursor;
			cursor = cursor.getNext();
			ntc.clear();
		}
		
		this.header.setNext(this.header);
		this.header.setPrev(this.header);
		this.size = 0;
	}

	@Override
	public boolean contains(E e) {
		if(this.firstIndex(e)>=0)return true;
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0 && header.getNext() == header && header.getPrev() == header;
	}

	@Override
	public int firstIndex(E e) {
		int index = 0;
		
		DNode<E> temp = this.header.getNext();
		while(temp != this.header) {
			if(temp.getElement().equals(e)) return index;
			temp = temp.getNext();
			index++;
		}
		
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		int index = this.size-1;

		DNode<E> temp = this.header.getPrev();
		while(temp != this.header) {
			if(temp.getElement() == e) return index;
			temp = temp.getPrev();
			index--;
		}

		return -1;
	}
	
	public void setComparator(Comparator<E> comp) {
		this.comp = comp;
	}
	
	private class DNode<T>{
		private DNode<T> prev;
		private DNode<T> next;
		private T e;
		
		public DNode(T e, DNode<T> prev, DNode<T> next){
			this.e = e;
			this.prev = prev;
			this.next = next;
		}
		public DNode(T e) {
			this(e, null, null);
		}
		
		public DNode() {
			this(null, null, null);
		}
		
		public void clear() {
			this.setElement(null);
			this.setNext(null);
			this.setPrev(null);
		}
		
		public DNode<T> getPrev(){
			return prev;
		}
		
		public DNode<T> getNext(){
			return next;
		}
		
		public T getElement() {
			return e;
		}
		
		public void setPrev(DNode<T> node){
			this.prev = node;
		}
		
		public void setNext(DNode<T> node){
			this.next = node;
		}
		
		public void setElement(T e) {
			this.e = e;
		}
	}
	
	private class DefaultComparator implements Comparator<E>{

		@Override
		public int compare(E o1, E o2) {
			String obj1 = o1.toString();
			String obj2 = o2.toString();
			return obj1.compareTo(obj2);
		}
		
	}
}
