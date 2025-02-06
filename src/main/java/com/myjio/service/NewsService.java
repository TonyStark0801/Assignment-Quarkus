package com.myjio.service;

import com.myjio.model.NewsResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class NewsService {

    @ConfigProperty(name = "news.api.key")
    protected String apiKey;


    private NewsResponse fetchNews(String URL, String paramKey, String paramValue) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(URL)
                    .queryParam("apiKey", apiKey)
                    .queryParam(paramKey, paramValue);

            Response response = target.request().get();
            NewsResponse newsResponse = response.readEntity(NewsResponse.class);
            newsResponse.setCountry(paramValue != null ? paramValue : "Unknown");

            return newsResponse;
        }
    }

    public NewsResponse getHeadlines(String country, String URL) {
        return fetchNews(URL, "country", country);
    }

    public NewsResponse getAllNews(String query, String URL) {
        return fetchNews(URL, "q", query);
    }
}
