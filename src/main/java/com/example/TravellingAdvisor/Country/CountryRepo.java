package com.example.TravellingAdvisor.Country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepo  extends JpaRepository<Country, Integer> {
    Optional<Country> findByCountry(String Country);
}