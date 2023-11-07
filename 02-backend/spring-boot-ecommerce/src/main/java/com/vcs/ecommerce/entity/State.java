package com.vcs.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
