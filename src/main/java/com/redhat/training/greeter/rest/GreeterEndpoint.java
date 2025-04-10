package com.redhat.training.greeter.rest;

import com.redhat.training.greeter.service.EnglishGreeter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/")
@Produces("text/plain")
public class GreeterEndpoint {
    @GET
    public String greet() {
        return new EnglishGreeter().greet("stranger");
    }
    @GET
    @Path("/{name}")
    public String greet(@PathParam("name") String name) {
        return new EnglishGreeter().greet(name);
    }
}
