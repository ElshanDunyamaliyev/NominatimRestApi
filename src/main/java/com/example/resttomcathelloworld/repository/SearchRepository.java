package com.example.resttomcathelloworld.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;

public class SearchRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Response response){
        em.persist(response);
    }
}
