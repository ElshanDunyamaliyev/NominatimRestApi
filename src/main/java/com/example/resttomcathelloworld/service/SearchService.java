package com.example.resttomcathelloworld.service;

import com.example.resttomcathelloworld.NominatimResource;
import com.example.resttomcathelloworld.repository.SearchRepository;
import jakarta.inject.Inject;

public class SearchService {

    @Inject
    NominatimResource nominatimResource;
    @Inject
    SearchRepository searchRepository;

    public void save(){
        searchRepository.save(nominatimResource.search(""));
    }
}
