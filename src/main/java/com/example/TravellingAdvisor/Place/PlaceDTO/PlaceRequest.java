package com.example.TravellingAdvisor.Place.PlaceDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data

public class PlaceRequest {
    private String placeName;
    private String placeDescription;
    private String placeCity;
    private String placeCountry;
}
