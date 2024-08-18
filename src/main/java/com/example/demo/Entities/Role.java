package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    Integer id;

    @Column(name="Name")
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> listOfUsers;


    Role(String name){
        this.name = name;

    }

}
