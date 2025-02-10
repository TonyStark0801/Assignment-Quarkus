package com.myjio.resource;

import com.myjio.model.Users;
import com.myjio.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<Users> fetchUsers(){
        return userService.getAllUsers();
    }

    @POST
    public Response addUser(@Valid Users user){
        try {
            Users createdUser = userService.addUser(user);
            return Response.status(Response.Status.CREATED).entity(createdUser).build();
        } catch (WebApplicationException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("status", e.getResponse().getStatus());
            return Response.status(e.getResponse().getStatus()).entity(errorResponse).build();
        }
    }

    @GET
    @Path("/{id}")
    public Users fetchUserById(@PathParam("id") Long id){
        return  userService.getById(id);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUserByID(@PathParam("id") Long id){
        userService.deleteById(id);
    }


}
