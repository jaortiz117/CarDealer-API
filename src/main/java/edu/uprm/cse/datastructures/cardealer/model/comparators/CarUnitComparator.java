package edu.uprm.cse.datastructures.cardealer.model.comparators;

import java.util.Comparator;

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;

public class CarUnitComparator implements Comparator<CarUnit>{

	@Override
	public int compare(CarUnit arg0, CarUnit arg1) {
		CarUnit cU1 = arg0;
		CarUnit cU2 = arg1;
		
		Integer cU1VIN = Integer.parseInt(cU1.getVIN());
		Integer cU2VIN = Integer.parseInt(cU2.getVIN());

		//compare each category
		return cU1VIN.compareTo(cU2VIN);//compare VIN numbers
	}

}
