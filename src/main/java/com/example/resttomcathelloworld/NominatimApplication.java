package com.example.resttomcathelloworld;

import com.example.resttomcathelloworld.service.SearchService;
import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class NominatimApplication extends Application {
    static SearchService searchService;

    public static void main(String[] args) {
        searchService.save();
    }
}