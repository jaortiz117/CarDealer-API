package edu.uprm.cse.datastructures.cardealer.model.staticLists;

import edu.uprm.cse.datastructures.cardealer.model.Person;
import edu.uprm.cse.datastructures.cardealer.model.comparators.PersonComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.SortedList;

public class PersonList {
	private final static SortedList<Person> personList = new CircularSortedDoublyLinkedList<>(new PersonComparator());
	
	private PersonList(){}
	  
	public static SortedList<Person> getInstance(){
		return personList;
	}
}
