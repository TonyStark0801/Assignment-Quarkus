package com.myjio.resource;

import com.myjio.model.Avenger;
import com.myjio.service.AvengerService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/avengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvengerResource {

    @Inject
    AvengerService avengerService;

    @GET
    public List<Avenger> fetchUsers(){
        return avengerService.getAllUsers();
    }

    @POST
    public Response addUser(@Valid Avenger avenger){
        try {
            Avenger createdAvenger = avengerService.addUser(avenger);
            return Response.status(Response.Status.CREATED).entity(createdAvenger).build();
        }
        catch (WebApplicationException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("status", e.getResponse().getStatus());
            return Response.status(e.getResponse().getStatus()).entity(errorResponse).build();
        }
    }

    @GET
    @Path("/{id}")
    public Avenger fetchUserById(@PathParam("id") ObjectId userId){
        return  avengerService.getByUserId(userId);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUserByID(@PathParam("id") ObjectId userId){
        avengerService.deleteByUserId(userId);
    }


}
