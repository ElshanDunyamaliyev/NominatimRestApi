package com.example.resttomcathelloworld.db;

import com.example.resttomcathelloworld.entity.ApiResponse;
import com.example.resttomcathelloworld.repository.NominatimRepository;
import com.example.resttomcathelloworld.utils.ParseUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

@RequestScoped
public class DbInsertion {

    @Inject
    NominatimRepository nominatimRepository;

    public void insertDb(WebTarget uri, String query,Response response){
        nominatimRepository.createTable();
        String jsonArray = response.readEntity(String.class);
        if(uri.toString().contains("search")){
            ApiResponse[] searchResponseArray = ParseUtils.parseJsonArray(jsonArray, ApiResponse[].class);
            for(var el : searchResponseArray){
                el.setQuery(query);
                nominatimRepository.insertSearchResponse(query,el);
            }
        }
        if(uri.toString().contains("reverse")){
            ApiResponse searchResponse = ParseUtils.parseJsonObject(jsonArray, ApiResponse.class);
            searchResponse.setQuery(query);
            nominatimRepository.insertSearchResponse(query,searchResponse);
        }
    }
}
