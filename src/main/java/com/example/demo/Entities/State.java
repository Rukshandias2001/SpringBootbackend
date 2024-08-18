package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="State")
public class State {
    @Column(name="state_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="city")
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

}
