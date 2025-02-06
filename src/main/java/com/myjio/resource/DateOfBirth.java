package com.myjio.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.Calendar;
import java.util.HashMap;

@Path("/")
public class DateOfBirth {
    @GET
    @Path("/dob")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Integer> dateOfBirth(@QueryParam("age") Integer age) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("age", age);
        map.put("year", year-age);
        return map;
    }
}
