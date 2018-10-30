package edu.uprm.cse.datastructures.cardealer.util;
/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class NotFoundException extends WebApplicationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1380010666344534202L;

	/**
     * Create a HTTP 404 (Not Found) exception.
     */
    public NotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Create a HTTP 404 (Not Found) exception.
     * @param message the String that is the entity of the 404 response.
     */
    public NotFoundException(String message) {
      super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
    }

    public NotFoundException(JsonError jse) {
      super(Response.status(Response.Status.NOT_FOUND).entity(jse).type(MediaType.APPLICATION_JSON).build());
    }

}