package com.myjio.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class Greeting {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHelloByName(@QueryParam("name") @DefaultValue("World") String name) {
        return String.format("Hello %s!",name);
    }
}
