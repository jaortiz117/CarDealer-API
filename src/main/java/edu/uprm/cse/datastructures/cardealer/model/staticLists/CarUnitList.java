package edu.uprm.cse.datastructures.cardealer.model.staticLists;

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;
import edu.uprm.cse.datastructures.cardealer.model.comparators.CarUnitComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.SortedList;

public class CarUnitList {
	private final static SortedList<CarUnit> unitList = new CircularSortedDoublyLinkedList<>(new CarUnitComparator());

	private CarUnitList(){}

	public static SortedList<CarUnit> getInstance(){
		return unitList;
	}
}
