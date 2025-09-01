package com.example.TravellingAdvisor.Place;
import com.example.TravellingAdvisor.City.City;
import com.example.TravellingAdvisor.Country.Country;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "miejsca")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_miejsca;

    @Column(name = "nazwa_miejsca",length = 100, nullable = false)
    private String name;

    @Column(name = "opis", nullable = true)
    private String opis;

    @ManyToOne
    @JoinColumn(name = "id_panstwa", nullable = false)
    @JsonBackReference // jak dziedziczy to trzeba to dac bo sie robi rekursja
    private Country country;

    @ManyToOne
    @JoinColumn(name = "id_miasta", nullable = false)
    @JsonBackReference
    private City city;
}