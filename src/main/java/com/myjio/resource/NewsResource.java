package com.myjio.resource;

import com.myjio.model.NewsResponse;
import com.myjio.service.NewsService;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@Path("/news")
public class NewsResource {
    @Inject
    NewsService newsService;
    private static final String BASE_HEADLINE_URL = "https://newsapi.org/v2/top-headlines";
    private static final String BASE_URL = "https://newsapi.org/v2/everything";

    @GET
    @Path("/headlines")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeadlines(@QueryParam("country") @DefaultValue("us") String country) {
        NewsResponse newsResponse = newsService.getHeadlines(country,BASE_HEADLINE_URL);
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

        NewsResponse newsResponse = newsService.getAllNews(query, BASE_URL);
        return Response.ok(newsResponse).build();
    }

}
