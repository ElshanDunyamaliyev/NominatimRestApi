package com.example.resttomcathelloworld.service;

import com.example.resttomcathelloworld.entity.SearchResponse;
import com.example.resttomcathelloworld.repository.NominatimRepository;
import com.example.resttomcathelloworld.util.ParseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@RequestScoped
public class  NominatimService {

    private static final String BASE_URL = "https://nominatim.md7.info";

    private final Client client = ClientBuilder.newClient();

    @Inject
    NominatimRepository nominatimRepository;

    public String search(String query){
        WebTarget uri = client.target(BASE_URL + "/search").queryParam("q",query);
        var response = executeRequest(uri);
        String jsonArray = response.readEntity(String.class);
        SearchResponse[] searchResponseArray = ParseUtils.parseJsonArray(jsonArray,SearchResponse[].class);
        nominatimRepository.createTable();
        for(var el : searchResponseArray){
            el.setQuery(query);
            nominatimRepository.insertSearchResponse(query,el);
        }
        System.out.println(nominatimRepository.getAllData());
        return response.toString();
    }

    public String reverse(double lat, double lon){
        WebTarget uri = client.target(BASE_URL + "/reverse").
                queryParam("lat",lat).
                queryParam("lon",lon).
                queryParam("format","jsonv2");

        return executeRequest(uri).toString();
    }

    private Response executeRequest(WebTarget uri){
        Response response = uri.request(MediaType.WILDCARD_TYPE).get();
        return response;
    }
}
