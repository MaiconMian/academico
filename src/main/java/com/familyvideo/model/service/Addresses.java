package com.familyvideo.model.service;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "addresses")
public class Addresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address_street", length = 45)
    private String addressStreet;

    @Column(name = "address_number")
    private Integer addressNumber;

    @Column(name = "address_postal_code", length = 11)
    private String addressPostalCode;

    @Column(name = "address_city", length = 45)
    private String addressCity;

    @Column(name = "address_country", length = 45)
    private String addressCountry;

}