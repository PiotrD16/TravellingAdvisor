package com.example.TravellingAdvisor.Country;

import com.example.TravellingAdvisor.City.City;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="panstwa")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_panstwa", nullable = false)
    private int id_panstwa;

    @Column(name = "panstwo", length = 50, nullable = false)
    private String country;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<City> cities = new ArrayList<>();
}