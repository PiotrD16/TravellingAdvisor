package com.example.TravellingAdvisor.Country;

import com.example.TravellingAdvisor.City.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CountryService {

    private final CountryRepo countryRepo;

    public List<Country> findAll() {
        return countryRepo.findAll();
    }

    public Optional<Country> findByCountry(String country) {
        return countryRepo.findByCountry(country);
    }

    public List<City> findCitiesByCountry(String country) {
        return countryRepo.findByCountry(country)
                .map(Country::getCities)
                .orElse(List.of());
    }

    public Country saveCountry(Country country) {
        return countryRepo.save(country);
    }

    public Optional<Country> updateCountry(String oldName, String newName) {
        return countryRepo.findByCountry(oldName)
                .map(country -> {
                    country.setCountry(newName);
                    return countryRepo.save(country);
                });
    }

    public void deleteCountry(int countryId) {
        countryRepo.deleteById(countryId);
    }
}