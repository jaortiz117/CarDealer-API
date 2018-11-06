package edu.uprm.cse.datastructures.cardealer;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.model.staticLists.AppointmentList;
import edu.uprm.cse.datastructures.cardealer.util.errors.NotFoundException;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.Position;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.PositionalList;


@Path("Appointment")
public class AppointmentManager {
	private final PositionalList<Appointment>[] appointmentList = AppointmentList.getInstance();
	private final int DAYS = appointmentList.length;


	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointments() {
		ArrayList<Appointment> arrList = new ArrayList<>();

		for(int i=0; i<DAYS; i++) {
			for(Appointment appointment : appointmentList[i]) {
				arrList.add(appointment);
			}
		}

		return arrList.toArray(new Appointment[arrList.size()]);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id){
		for(int i=0; i<DAYS; i++) {
			for(Appointment appointment : appointmentList[i])
				if(appointment.getAppointmentId() == id)
					return appointment;
		}

		throw new NotFoundException();
	}

	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment appointment, @PathParam("day") String day){
		int validDay = validateDay(day);

		try {
			appointmentList[validDay].addLast(appointment);
			//returns exception when invalid day or appointment already exists
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		return Response.status(Response.Status.CREATED).build();

	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment(@PathParam("id") long id, Appointment appointment){
		if(appointment.getAppointmentId() != id)
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//loops thrugh entire appointments and replaces the id'd one with the new one
		for(int i=0; i<DAYS; i++) {
			for(Position<Appointment> pos : appointmentList[i].positions()) {
				if(pos.getElement().getAppointmentId() == appointment.getAppointmentId()) {

					try {
						//adds new position after previous one
						Position<Appointment> newPos = appointmentList[i].addAfter(pos, appointment);
						//removes previous position
						appointmentList[i].remove(appointmentList[i].before(newPos));
					} catch (Exception e) {
						return Response.status(Response.Status.NOT_FOUND).build();
					}

					return Response.status(Response.Status.OK).build();
				}	
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteAppointment(@PathParam("id") long id){
		for(int i=0; i<DAYS; i++) {
			for(Position<Appointment> pos : appointmentList[i].positions()) {
				if(pos.getElement().getAppointmentId() == id) {
					try {
						appointmentList[i].remove(pos);
					} catch (Exception e) {
						return Response.status(Response.Status.NOT_FOUND).build();
					}
					return Response.status(Response.Status.OK).build();
				}
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}


	private int validateDay(String day) {
		switch(day) {
		case "Monday":
			return AppointmentList.MONDAY;
		case "Tuesday":
			return AppointmentList.TUESDAY;
		case "Wednesday":
			return AppointmentList.WEDNESDAY;
		case "Thursday":
			return AppointmentList.THURSDAY;
		case "Friday":
			return AppointmentList.FRIDAY;
		}

		return -1;
	}
}
