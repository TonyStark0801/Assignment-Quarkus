package com.myjio.resource;

import com.myjio.model.NewsResponse;
import com.myjio.service.NewsService;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;

@Path("/news")
public class NewsResource {
    @Inject
    NewsService newsService;
    private static final String BASE_HEADLINE_URL = "https://newsapi.org/v2/top-headlines";
    private static final String BASE_URL = "https://newsapi.org/v2/everything";

    @GET
    @Path("/headlines")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsResponse getHeadlines(@QueryParam("country") @DefaultValue("us") String country) {
        return newsService.getHeadlines(country,BASE_HEADLINE_URL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public NewsResponse getNews (@QueryParam("q") @NotNull String query) {
        return newsService.getAllNews(query,BASE_URL);
    }

}
