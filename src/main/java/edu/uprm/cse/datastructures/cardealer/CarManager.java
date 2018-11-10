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

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.staticLists.CarList;
import edu.uprm.cse.datastructures.cardealer.util.errors.NotFoundException;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.SortedList;

@Path("cars")
public class CarManager {
	private final SortedList<Car> carList = CarList.getInstance();


	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		Car[] arr = new Car[carList.size()];

		int i = 0;
		for (Car car : carList) {
			arr[i] = car;
			i++;
		}

		return arr;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("id") long id){
		for(Car car : carList) {
			if(car.getCarId() == id)
				return car;
		}

		//		throw new NotFoundException(new JsonError("Error", "Car " + id + " not found"));
		throw new NotFoundException();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(Car car){
		//checks that the id is not already used
		for(Car c : carList) {
			if(c.getCarId() == car.getCarId())
				return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if(carList.add(car)) {
			return Response.status(Response.Status.CREATED).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(@PathParam("id") long id, Car car){
		if(car.getCarId() != id)
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		for(Car c: carList) {
			if(c.getCarId() == car.getCarId()) {
				if(carList.remove(c))
					if(carList.add(car))
						return Response.status(Response.Status.OK).build();
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCar(@PathParam("id") long id){
		for(Car car: carList) {
			if(car.getCarId() == id) {
				if(carList.remove(car))
					return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/brand/{brandName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] brandSearch(@PathParam("brandName")String brand) {
		ArrayList<Car> result = new ArrayList<>();

		//since it is sorted by brand names first, when we reach another brand 
		//we can just return the final list
		//If we reach end of list and result length is 0 throw 404
		for(Car car : carList) {
			if(car.getCarBrand().equals(brand))
				result.add(car);
			else if(!result.isEmpty())
				return result.toArray(new Car[result.size()]);
		}

		//if list is empty we throw 404, else we return list
		if(!result.isEmpty()) return result.toArray(new Car[result.size()]);

		throw new NotFoundException();
	}

	@GET
	@Path("/year/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] yearSearch(@PathParam("year") long year) {
		ArrayList<Car> result = new ArrayList<>();

		for(Car car : carList) {
			if(car.getCarYear() == year)
				result.add(car);
		}

		if(!result.isEmpty()) return result.toArray(new Car[result.size()]);

		throw new NotFoundException();
	}
}
