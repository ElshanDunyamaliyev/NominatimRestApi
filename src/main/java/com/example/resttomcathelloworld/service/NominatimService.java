package com.example.resttomcathelloworld.service;

import com.example.resttomcathelloworld.db.DbInsertion;
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
    DbInsertion dbInsertion;

    public Response search(String query){
        WebTarget uri = client.target(BASE_URL + "/search").queryParam("q",query);
        Response response = executeRequest(uri);
        dbInsertion.insertDb(uri,query,response);
        return executeRequest(uri);
    }

    public Response reverse(double lat, double lon){
        WebTarget uri = client.target(BASE_URL + "/reverse").
                queryParam("lat",lat).
                queryParam("lon",lon).
                queryParam("format","jsonv2");
        String query = uri.toString().substring(uri.toString().indexOf("?") + 1,uri.toString().indexOf("&format"));
        Response response = executeRequest(uri);
        dbInsertion.insertDb(uri,query,response);
        return executeRequest(uri);
    }

    private Response executeRequest(WebTarget uri){
        return uri.request(MediaType.WILDCARD_TYPE).get();
    }
}
