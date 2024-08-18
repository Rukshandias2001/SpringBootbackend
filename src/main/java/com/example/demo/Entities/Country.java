package com.example.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="country_id")
    Long id;

    @Column(name="country_name")
    String name;

//    @OneToMany(mappedBy = "Country")
//    @JsonIgnore
//    List<Country> countries;

}
