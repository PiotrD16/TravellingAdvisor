package com.example.TravellingAdvisor.Place;

import com.example.TravellingAdvisor.Place.PlaceDTO.PlaceRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place/v1")
@RequiredArgsConstructor
@Validated
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/")
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok().body(placeService.findAll());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Integer id) {
        return placeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{placeName}")
    public ResponseEntity<Place> getPlaceByName(@PathVariable String placeName) {
        return placeService.findByName(placeName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/savePlace")
    public ResponseEntity<Place> savePlace(@RequestBody PlaceRequest request) {
        Place savedPlace = placeService.savePlace(request);
        return ResponseEntity.ok(savedPlace);
    }

    @PutMapping("/updatePlace")
    public ResponseEntity<Place> updatePlace(@RequestBody PlaceRequest place) {
        return placeService.updatePlace(place)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/deletePlace")
    public ResponseEntity<String> deletePlace(@PathVariable Integer id) {
        placeService.removeById(id);
        return ResponseEntity.ok().body("Place deleted successfully!");
    }
}