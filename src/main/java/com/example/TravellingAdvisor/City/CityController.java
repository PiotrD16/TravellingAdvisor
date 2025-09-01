package com.example.TravellingAdvisor.City;

import com.example.TravellingAdvisor.City.CityDTO.CityRequest;
import com.example.TravellingAdvisor.Place.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city/v1")
@RequiredArgsConstructor
@Validated

public class CityController {
    private final CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.ok().body(cityService.findAll());
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<City> getCity(@PathVariable String cityName){
        return cityService.findByCity(cityName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{cityName}/places")
    public ResponseEntity<List<Place>> getPlacesByCity(@PathVariable String cityName){
        return ResponseEntity.ok().body(cityService.findPlacesByCity(cityName));
    }

    @PostMapping("/saveCity")
    public ResponseEntity<City> saveCity(@RequestBody CityRequest city){
        City savedCity = cityService.saveCity(city);
        return ResponseEntity.ok(savedCity);
    }

    @PutMapping("/{id}/updateCity")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody City city){
        return cityService.updateCity(id,city)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/deleteCity")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id){
        cityService.deleteCity(id);
        return ResponseEntity.ok().body("City deleted successfully!");
    }
}
