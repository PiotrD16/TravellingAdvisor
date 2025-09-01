package com.example.TravellingAdvisor.City.CityDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class CityRequest {
    private String cityName;
    private String countryName;
}
