package com.myjio.resource;

import com.myjio.model.NewsResponse;
import com.myjio.service.NewsService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/news")
public class NewsResource {
    @Inject
    NewsService newsService;

    @GET
    @Path("/headlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeadlines(@QueryParam("country") @DefaultValue("us") String country) {
        NewsResponse newsResponse = newsService.getHeadlines(country);
        return Response.ok(newsResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@QueryParam("q") String query) {
        // Check if 'q' parameter is provided
        if (query == null || query.trim().isEmpty()) {

            // Throw a custom exception with HTTP 400 Bad Request status
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Query parameter 'q' is required.")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        NewsResponse newsResponse = newsService.getAllNews(query);
        return Response.ok(newsResponse).build();
    }

}
