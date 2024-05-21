package com.example.resttomcathelloworld;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class NominatimResource {

    private static final String baseUrl = "https://nominatim.md7.info/";

    @GET()
    @Path("/search")
    @Produces("application/json")
    public Response search(@DefaultValue("baki") @QueryParam("q") String searchValue) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget searchUrl = client.target(baseUrl + "/search").queryParam("q",searchValue);
            Response response = searchUrl.request(MediaType.WILDCARD_TYPE).get();
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GET()
    @Path("/reverse")
    @Produces("application/json")
    public Response reverse(@DefaultValue("40.3755885") @QueryParam("lat") double lat,@DefaultValue("49.8328009") @QueryParam("lon") double lon) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget reverseUrl = client.target(baseUrl + "/reverse").
                    queryParam("lat",lat).
                    queryParam("lon",lon).
                    queryParam("format","jsonv2");
            Response response = reverseUrl.request(MediaType.WILDCARD_TYPE).get();
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}