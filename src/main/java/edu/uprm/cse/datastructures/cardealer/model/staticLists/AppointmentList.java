package edu.uprm.cse.datastructures.cardealer.model.staticLists;


import java.lang.reflect.Array;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.PositionalList;

public class AppointmentList {
	public static final int MONDAY = 0; 
	public static final int TUESDAY = 1; 
	public static final int WEDNESDAY = 2;
	public static final int THURSDAY = 3; 
	public static final int FRIDAY = 4;
	
	private static final int WEEK = 5;
	
	private final static PositionalList<Appointment>[] appointmentsList = generateList(WEEK, new LinkedPositionalList<Appointment>());

	private AppointmentList(){}

	@SuppressWarnings("unchecked")
	private static LinkedPositionalList<Appointment>[] generateList(int size, LinkedPositionalList<Appointment> list) {
		LinkedPositionalList<Appointment>[] arr = (LinkedPositionalList<Appointment>[]) Array.newInstance(list.getClass(), size);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new LinkedPositionalList<Appointment>();
		}
		return  arr;
	}

	public static PositionalList<Appointment>[] getInstance(){
		return appointmentsList;
	}
}
