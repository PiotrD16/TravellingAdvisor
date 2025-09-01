package com.example.TravellingAdvisor.Place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.TravellingAdvisor.City.*;
import com.example.TravellingAdvisor.Country.*;
import com.example.TravellingAdvisor.Place.PlaceDTO.PlaceRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class PlaceService {
    private final PlaceRepo placeRepo;
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    public List<Place> findAll() {
        return placeRepo.findAll();
    }

    public Optional<Place> findById(int id) {
        return placeRepo.findById(id);
    }

    public Optional<Place> findByName(String name) {
        return placeRepo.findByName(name);
    }

    public Place savePlace(PlaceRequest request) {
        Optional<Place> existing = placeRepo.findByName(request.getPlaceName());
        if(existing.isPresent())
            throw new RuntimeException("The place already exists!");
        
            Country country = countryRepo.findByCountry(request.getPlaceCountry())
                                .orElseGet(() -> {
                                    Country newCountry = new Country();
                                    newCountry.setCountry(request.getPlaceCountry());
                                    return countryRepo.save(newCountry);
                                });

            City city = cityRepo.findByCity(request.getPlaceCity())
                            .orElseGet(() -> {
                                City newCity = new City();
                                newCity.setCity(request.getPlaceCity());
                                newCity.setCountry(country);
                                return cityRepo.save(newCity);
                            });

            Place place = new Place();
            place.setCity(city);
            place.setCountry(country);
            place.setName(request.getPlaceName());
            place.setOpis(request.getPlaceDescription());

            return placeRepo.save(place);
    }

    public Optional<Place> updatePlace(PlaceRequest request) {
        return placeRepo.findByName(request.getPlaceName())
                .map(place -> {
                    Country country = countryRepo.findByCountry(request.getPlaceCountry())
                                .orElseThrow(() -> new RuntimeException("Country not found!"));
                    City city = cityRepo.findByCity(request.getPlaceCity())
                                .orElseThrow(() -> new RuntimeException("City not found!"));

                    place.setCity(city);
                    place.setCountry(country);
                    place.setName(request.getPlaceName());
                    place.setOpis(request.getPlaceDescription());

                    return placeRepo.save(place);
                });
    }

    public void removeById(int id) {
        Optional<Place> place = placeRepo.findById(id);
        if (place.isPresent()) {
            placeRepo.deleteById(id);
        }
        else log.info("Place with id={} was not found", id);
    }
}