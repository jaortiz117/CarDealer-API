package edu.uprm.cse.datastructures.cardealer.model.comparators;

import java.util.Comparator;

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;

public class CarUnitComparator implements Comparator<CarUnit>{

	@Override
	public int compare(CarUnit arg0, CarUnit arg1) {
		CarUnit cU1 = arg0;
		CarUnit cU2 = arg1;
		
		//compare each category
		return cU1.getVIN().compareTo(cU2.getVIN());//compare VIN numbers
	}

}
