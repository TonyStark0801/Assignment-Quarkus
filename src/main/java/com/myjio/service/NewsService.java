package com.myjio.service;

import com.myjio.model.NewsResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class NewsService {

    @ConfigProperty(name = "news.api.key")
    protected String apiKey;


    @RestClient
    private  NewsApiClient newsApiClient;

    public NewsResponse getHeadlines(String country) {
        return newsApiClient.getTopHeadlines(country,apiKey);
    }

    public NewsResponse getAllNews(String query) {
        return newsApiClient.getEverything(query,apiKey);
    }
}
