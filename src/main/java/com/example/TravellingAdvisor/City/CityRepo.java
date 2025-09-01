package com.example.TravellingAdvisor.City;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepo extends JpaRepository <City, Integer> {
    Optional<City> findByCity(String city);
}
