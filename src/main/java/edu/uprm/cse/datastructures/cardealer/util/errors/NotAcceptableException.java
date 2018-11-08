package edu.uprm.cse.datastructures.cardealer.util.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotAcceptableException extends WebApplicationException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2791408246621670405L;

	/**
     * Create a HTTP 406 (Not acceptable) exception.
     */
    public NotAcceptableException() {
        super(Response.status(Response.Status.NOT_ACCEPTABLE).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Create a HTTP 406 (Not acceptable) exception.
     * @param message the String that is the entity of the 406 response.
     */
    public NotAcceptableException(String message) {
      super(Response.status(Response.Status.NOT_ACCEPTABLE).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

    public NotAcceptableException(JsonError jse) {
      super(Response.status(Response.Status.NOT_ACCEPTABLE).entity(jse).type(MediaType.APPLICATION_JSON).build());
    }
}
