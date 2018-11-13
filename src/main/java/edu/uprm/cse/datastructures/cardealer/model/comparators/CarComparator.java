package edu.uprm.cse.datastructures.cardealer.model.comparators;

import java.util.Comparator;

import edu.uprm.cse.datastructures.cardealer.model.Car;

/*
 * Car comparator sorts compraes by brand, then by model, and then by option
 */
public class CarComparator implements Comparator<Car>{
	@Override
	public int compare(Car arg0, Car arg1) {
		Car car1 = arg0;
		Car car2 = arg1;
		
		//compare each category
		int brand = car1.getCarBrand().compareTo(car2.getCarBrand());//different brands
		if(brand != 0) {
			return brand;
		}
		
		int model = car1.getCarModel().compareTo(car2.getCarModel());//same brand different model
		if(model != 0) {
			return model;
		}
		int option = car1.getCarModelOption().compareTo(car2.getCarModelOption());//same brand same model different options
		if(option != 0) {
			return option;
		}
		
		return Long.compare(car1.getCarYear(), car2.getCarYear());
	}
}
