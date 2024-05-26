package com.example.resttomcathelloworld.service;

import com.example.resttomcathelloworld.entity.SearchResponse;
import com.example.resttomcathelloworld.repository.NominatimRepository;
import com.example.resttomcathelloworld.utils.ParseUtils;
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

    public Response search(String query){
        WebTarget uri = client.target(BASE_URL + "/search").queryParam("q",query);
        writeToDb(uri,query);
        return executeRequest(uri);
    }

    public Response reverse(double lat, double lon){
        WebTarget uri = client.target(BASE_URL + "/reverse").
                queryParam("lat",lat).
                queryParam("lon",lon).
                queryParam("format","jsonv2");
        String query = uri.toString().substring(uri.toString().indexOf("?") + 1,uri.toString().indexOf("&format"));
        writeToDb(uri,query);
        return executeRequest(uri);
    }

    private void writeToDb(WebTarget uri, String query){
        nominatimRepository.createTable();
        Response response = executeRequest(uri);
        String jsonArray = response.readEntity(String.class);
        if(uri.toString().contains("search")){
            SearchResponse[] searchResponseArray = ParseUtils.parseJsonArray(jsonArray,SearchResponse[].class);
            for(var el : searchResponseArray){
                el.setQuery(query);
                nominatimRepository.insertSearchResponse(query,el);
            }
        }
        if(uri.toString().contains("reverse")){
            SearchResponse searchResponse = ParseUtils.parseJsonObject(jsonArray,SearchResponse.class);
            searchResponse.setQuery(query);
            nominatimRepository.insertSearchResponse(query,searchResponse);
        }
    }

    private Response executeRequest(WebTarget uri){
        return uri.request(MediaType.WILDCARD_TYPE).get();
    }
}
