package com.example.resttomcathelloworld.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SearchQueryResponse {
    @Id
    Integer placeId;
    Double latitude;
    Double longitude;
    String displayName;
    String type;
    String category;
}
