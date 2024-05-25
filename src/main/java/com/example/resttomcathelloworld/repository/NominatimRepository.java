package com.example.resttomcathelloworld.repository;


import com.example.resttomcathelloworld.DbConnection;
import com.example.resttomcathelloworld.entity.SearchResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class NominatimRepository {

    Connection connection = DbConnection.getConnection();

    public void createTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE if not exists searchResponse(" +
                    "                            placeId INTEGER NOT NULL," +
                    "                            displayName VARCHAR(500) NOT NULL," +
                    "                            type VARCHAR(30) NOT NULL," +
                    "                            category VARCHAR(30) NOT NULL," +
                    "                            query VARCHAR(30) NOT NULL," +
                    "                            latitude DOUBLE PRECISION NOT NULL," +
                    "                            longitude DOUBLE PRECISION NOT NULL)"
            );
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertSearchResponse(String query, SearchResponse response) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("insert into searchResponse(placeId,displayName,type,category," +
                            "query,latitude,longitude) values(?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, response.getPlaceId());
            preparedStatement.setString(2, response.getDisplayName());
            preparedStatement.setString(3, response.getType());
            preparedStatement.setString(4, response.getCategory());
            preparedStatement.setString(5, response.getQuery());
            preparedStatement.setDouble(6, response.getLatitude());
            preparedStatement.setDouble(7, response.getLongitude());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SearchResponse> getAllData(){
        List<SearchResponse> searchResponses = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from searchResponse");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                SearchResponse searchResponse = new SearchResponse();
                searchResponse.setPlaceId(resultSet.getInt("placeId"));
                searchResponse.setLatitude(resultSet.getDouble("latitude"));
                searchResponse.setLongitude(resultSet.getDouble("longitude"));
                searchResponse.setDisplayName(resultSet.getString("displayName"));
                searchResponse.setType(resultSet.getString("type"));
                searchResponse.setCategory(resultSet.getString("category"));
                searchResponse.setQuery(resultSet.getString("query"));

                searchResponses.add(searchResponse);
            }
            return searchResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
