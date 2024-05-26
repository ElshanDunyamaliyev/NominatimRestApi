package com.example.resttomcathelloworld.repository;


import com.example.resttomcathelloworld.db.DbConnection;
import com.example.resttomcathelloworld.entity.ApiResponse;
import com.example.resttomcathelloworld.enums.Queries;
import jakarta.enterprise.context.RequestScoped;

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
        String createTableQuery = Queries.CREATE_TABLE.getQuery();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery);
            preparedStatement.execute();
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertSearchResponse(String query, ApiResponse response) {
        String insertResponseQuery = Queries.INSERT_RESPONSE.getQuery();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(insertResponseQuery);

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

    public List<ApiResponse> getAllData(){
        String getAllResponsesQuery = Queries.GET_ALL_RESPONSES.getQuery();
        List<ApiResponse> searchResponses = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(getAllResponsesQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ApiResponse searchResponse = new ApiResponse();
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

    public void deleteTable(){
        String deleteTableQuery = Queries.DELETE_TABLE.getQuery();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteTableQuery);
            preparedStatement.executeUpdate();
            System.out.println("Table deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
