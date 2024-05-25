package com.example.resttomcathelloworld.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigInteger;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@RequestScoped
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse implements Serializable {
    @Id
    @JsonProperty("place_id")
    Integer placeId;

    @JsonProperty("lat")
    Double latitude;

    @JsonProperty("lon")
    Double longitude;

    @JsonProperty("display_name")
    String displayName;

    String type;
    String category;

    String query;
}
