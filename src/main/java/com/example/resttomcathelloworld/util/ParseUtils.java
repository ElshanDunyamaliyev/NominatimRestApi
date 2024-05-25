package com.example.resttomcathelloworld.util;

import com.example.resttomcathelloworld.entity.SearchResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ParseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T[] parseJsonArray(String jsonResponse, Class<T[]> responseType){
        T[] arr;
        try {
             arr = objectMapper.readValue(jsonResponse, responseType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }
}
