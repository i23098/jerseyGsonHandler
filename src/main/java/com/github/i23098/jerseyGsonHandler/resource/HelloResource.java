package com.github.i23098.jerseyGsonHandler.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    @Path("/str")
    @POST
    public Response helloStr(String name) {
        System.out.println("Got hello string request [" + name + "]");

        String msg = "Hello " + name + "!";
        return Response.ok().entity(msg).build();
    }

    @Path("/obj")
    @POST
    public Response helloStr(User user) {
        System.out.println("Got hello object request [" + user + "]");

        Message msg = new Message("Hello " + user.name() + "!");
        return Response.ok().entity(msg).build();
    }
}
