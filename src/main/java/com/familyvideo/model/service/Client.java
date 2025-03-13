package com.familyvideo.model.service;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_address", referencedColumnName = "id")
    private Addresses address;

    @Column(name = "client_name", length = 45)
    private String clientName;

    @Column(name = "client_last_name", length = 45)
    private String clientLastName;

}