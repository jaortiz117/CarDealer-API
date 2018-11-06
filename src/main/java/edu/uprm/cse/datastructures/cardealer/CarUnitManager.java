package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;
import edu.uprm.cse.datastructures.cardealer.model.staticLists.CarUnitList;
import edu.uprm.cse.datastructures.cardealer.util.errors.NotFoundException;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.SortedList;

@Path("carunit")
public class CarUnitManager {
	private final SortedList<CarUnit> carUnitList = CarUnitList.getInstance();


	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public CarUnit[] getAllCarUnits() {
		CarUnit[] arr = new CarUnit[carUnitList.size()];

		int i = 0;
		for (CarUnit carUnit : carUnitList) {
			arr[i] = carUnit;
			i++;
		}

		return arr;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CarUnit getCarUnit(@PathParam("id") long id){
		for(CarUnit carUnit : carUnitList) {
			if(carUnit.getCarUnitId() == id)
				return carUnit;
		}

		//		throw new NotFoundException(new JsonError("Error", "carUnit " + id + " not found"));
		throw new NotFoundException();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCarUnit(CarUnit carUnit){
		if(carUnitList.add(carUnit)) {
			return Response.status(Response.Status.CREATED).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCarUnit(@PathParam("id") long id, CarUnit carUnit){
		if(carUnit.getCarUnitId() != id)
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		for(CarUnit c: carUnitList) {
			if(c.getCarUnitId() == carUnit.getCarUnitId()) {
				if(carUnitList.remove(c))
					if(carUnitList.add(carUnit))
						return Response.status(Response.Status.OK).build();
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCarUnit(@PathParam("id") long id){
		for(CarUnit carUnit: carUnitList) {
			if(carUnit.getCarUnitId() == id) {
				if(carUnitList.remove(carUnit))
					return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
