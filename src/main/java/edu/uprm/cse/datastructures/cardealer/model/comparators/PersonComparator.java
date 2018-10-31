package edu.uprm.cse.datastructures.cardealer.model.comparators;

import java.util.Comparator;

import edu.uprm.cse.datastructures.cardealer.model.Person;

public class PersonComparator implements Comparator<Person>{

	@Override
	public int compare(Person arg0, Person arg1) {
		Person p1 = arg0;
		Person p2 = arg1;
		
		//compare each category
		int lastName = p1.getLastName().compareTo(p2.getLastName());//different last names
		if(lastName != 0) {
			return lastName;
		}
		
		return p1.getFirstName().compareTo(p2.getFirstName());//same last name different name
	}

}
