package com.aa.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rt.aa.Customer;

@Path("/json/customer")
public class CustomerService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getTrackInJSON() {

		Customer customer = new Customer();
		customer.setId(101);
		customer.setName("Enter Sandman");
		customer.setInfo("Metallica");

		return customer;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Customer customer) {

		String result = "Cusotomer saved : " + customer;
		return Response.status(201).entity(result).build();

	}

}
