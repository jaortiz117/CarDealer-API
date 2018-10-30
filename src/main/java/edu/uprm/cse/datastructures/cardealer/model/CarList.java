package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.SortedList;

public class CarList {
	private final static SortedList<Car> carList = new CircularSortedDoublyLinkedList<>(new CarComparator()); 
	
	private CarList(){}
	  
	public static SortedList<Car> getInstance(){
		return carList;
	}
}
