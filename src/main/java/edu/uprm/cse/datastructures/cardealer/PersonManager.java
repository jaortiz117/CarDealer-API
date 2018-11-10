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
import edu.uprm.cse.datastructures.cardealer.model.Person;
import edu.uprm.cse.datastructures.cardealer.model.staticLists.PersonList;
import edu.uprm.cse.datastructures.cardealer.util.errors.NotFoundException;
import edu.uprm.cse.datastructures.cardealer.util.interfaces.SortedList;


@Path("person")
public class PersonManager {
	private final SortedList<Person> personList = PersonList.getInstance();


	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Person[] getAllPersons() {
		Person[] arr = new Person[personList.size()];

		int i = 0;
		for (Person person : personList) {
			arr[i] = person;
			i++;
		}

		return arr;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") long id){
		for(Person person : personList) {
			if(person.getPersonId() == id)
				return person;
		}

		throw new NotFoundException();
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){

		//checks that the id is not already used
		for(Person p : personList) {
			if(p.getPersonId() == person.getPersonId())
				return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		if(personList.add(person)) {
			return Response.status(Response.Status.CREATED).build();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") long id, Person person){
		if(person.getPersonId() != id)
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		for(Person c: personList) {
			if(c.getPersonId() == person.getPersonId()) {
				if(personList.remove(c))
					if(personList.add(person))
						return Response.status(Response.Status.OK).build();
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deletePerson(@PathParam("id") long id){
		for(Person Person: personList) {
			if(Person.getPersonId() == id) {
				if(personList.remove(Person))
					return Response.status(Response.Status.OK).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/lastname/{lastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person[] brandSearch(@PathParam("lastName")String lastName) {
		ArrayList<Person> result = new ArrayList<>();

		//since it is sorted by last names first, when we reach another last name
		//we can just return the final list
		//If we reach end of list and result length is 0 throw 404
		for(Person p : personList) {
			if(p.getLastName().equals(lastName))
				result.add(p);
			else if(!result.isEmpty())
				return result.toArray(new Person[result.size()]);
		}

		//if list is empty we throw 404, else we return list
		if(!result.isEmpty()) return result.toArray(new Person[result.size()]);

		throw new NotFoundException();
	}
}
