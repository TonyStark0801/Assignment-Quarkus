package com.myjio.service;


import com.myjio.model.NewsResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "https://newsapi.org")
public  interface NewsApiClient  {

    @GET
    @Path("v2/top-headlines")
    @Produces(MediaType.APPLICATION_JSON)
    NewsResponse getTopHeadlines(
            @QueryParam("country") String country,
            @QueryParam("apiKey") String apiKey
    );

    @GET
    @Path("v2/everything")
    @Produces(MediaType.APPLICATION_JSON)
    NewsResponse getEverything(
            @QueryParam("q") String query,
            @QueryParam("apikey") String apikey
    );

}
