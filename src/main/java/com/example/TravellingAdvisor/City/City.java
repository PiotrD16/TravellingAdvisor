package com.example.TravellingAdvisor.City;
import com.example.TravellingAdvisor.Country.Country;
import com.example.TravellingAdvisor.Place.Place;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "miasta")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_miasta")
    private int id_miasta;

    @Column(name = "miasto", length = 50, nullable = false)
    private String city;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_panstwa", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Place> places = new ArrayList<>();
}
