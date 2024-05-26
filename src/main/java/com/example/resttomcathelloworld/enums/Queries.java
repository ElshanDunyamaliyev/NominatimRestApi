package com.example.resttomcathelloworld.enums;

public enum Queries {

    CREATE_TABLE("CREATE TABLE if not exists responses(" +
            "                            placeId INTEGER NOT NULL," +
            "                            displayName VARCHAR(500) NOT NULL," +
            "                            type VARCHAR(30) NOT NULL," +
            "                            category VARCHAR(30) NOT NULL," +
            "                            query VARCHAR(100) NOT NULL," +
            "                            latitude DOUBLE PRECISION NOT NULL," +
            "                            longitude DOUBLE PRECISION NOT NULL)"),
    INSERT_RESPONSE("insert into responses(placeId,displayName,type,category," +
                            "query,latitude,longitude) values(?,?,?,?,?,?,?)"),

    GET_ALL_RESPONSES("select * from responses"),
    DELETE_TABLE("DROP TABLE IF EXISTS responses");
    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
