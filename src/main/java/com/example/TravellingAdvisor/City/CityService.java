package com.example.TravellingAdvisor.City;

import com.example.TravellingAdvisor.Place.Place;
import com.example.TravellingAdvisor.City.CityDTO.CityRequest;
import com.example.TravellingAdvisor.Country.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepo cityRepo;
    private final CountryRepo countryRepo;

    // Znajduje wszystkie miasta
    public List<City> findAll() {
        return cityRepo.findAll();
    }

    // Znajduje miasto po jego nazwie
    public Optional<City> findByCity (String city) {
        return cityRepo.findByCity(city);
    }

    // Znajduje wszystkie miejsca po nazwie miasta
    public List<Place> findPlacesByCity(String city) {
        Optional<City> foundCity = cityRepo.findByCity(city);
        if (foundCity.isPresent()) {
            return foundCity.get().getPlaces();
        }
        return List.of();
    }

    // Dodaje miasto
    public City saveCity(CityRequest request) {
        Optional<City> existing = cityRepo.findByCity(request.getCityName());
        if(existing.isPresent())
            throw new RuntimeException("City already exists!");
        
        Country country = countryRepo.findByCountry(request.getCountryName())
            .orElseThrow(() -> new RuntimeException("Country not found!"));

        City city = new City();
        city.setCity(request.getCityName());
        city.setCountry(country);
        return cityRepo.save(city);    
    }

    // Aktualizuje Miasto
    public Optional<City> updateCity(Integer id, City city) {
        return cityRepo.findById(id).map(
                existingCity -> {
                    existingCity.setCity(city.getCity());
                    existingCity.setCountry(city.getCountry());
                    existingCity.setPlaces(city.getPlaces());
                    return cityRepo.save(existingCity);
                }
        );
    }

    // Usuwa miasto
    public void deleteCity(Integer id) {
        cityRepo.deleteById(id);
    }
}