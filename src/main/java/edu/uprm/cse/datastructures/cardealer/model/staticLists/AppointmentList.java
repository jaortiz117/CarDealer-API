package edu.uprm.cse.datastructures.cardealer.model.staticLists;


import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.PositionalList;

public class AppointmentList {
	private static final int WEEK = 5;
	private final static PositionalList<Appointment>[] appointmentsList = generateList(WEEK);

	private AppointmentList(){}

	@SuppressWarnings("unchecked")
	private static LinkedPositionalList<Appointment>[] generateList(int size) {
		Object[] arr = new Object[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new LinkedPositionalList<Appointment>();
		}
		return  (LinkedPositionalList<Appointment>[]) arr;
	}

	public static PositionalList<Appointment>[] getInstance(){
		return appointmentsList;
	}
}
