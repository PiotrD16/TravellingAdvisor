package com.example.TravellingAdvisor.Country;

import com.example.TravellingAdvisor.City.City;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country/v1")
@RequiredArgsConstructor
@Validated

public class CountryController {
    private final CountryService countryService;

    @GetMapping("/")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok().body(countryService.findAll());
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String countryName) {
        return countryService.findByCountry(countryName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{countryName}/findCities")
    public ResponseEntity<List<City>> findCitiesByCountryName(@PathVariable String countryName) {
        return ResponseEntity.ok().body(countryService.findCitiesByCountry(countryName));
    }

    @PostMapping("/createCountry")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok().body(countryService.saveCountry(country));
    }

    @PutMapping("/updateCountry")
    public ResponseEntity<Country> updateCountry(@RequestParam String oldName, @RequestParam String newName) {
        return countryService.updateCountry(oldName,newName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/deleteCountry")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok().body("Country deleted successfully!");
    }
}
