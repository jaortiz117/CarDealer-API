package edu.uprm.cse.datastructures.cardealer.model.staticLists;


import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.PositionalList;

public class AppointmentList {
	private static final int WEEK = 5;
	private final static PositionalList<Appointment>[] appointmentsList = generateList(WEEK, 
			new LinkedPositionalList<Appointment>());

	private AppointmentList(){}

	private static <E> E[] generateList(int size, E list) {
		E[] arr = (E[])new Object[size];
		return arr;
	}

	public static PositionalList<Appointment>[] getInstance(){
		return appointmentsList;
	}
}
