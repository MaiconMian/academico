package com.familyvideo.model.service;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_client", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "fk_employee", referencedColumnName = "id")
    private Employees employee;

    @Column(name = "borrowing_is_paid")
    private Boolean borrowingIsPaid;

    @Column(name = "borrowing_sale_date")
    @Temporal(TemporalType.DATE)
    private Date borrowingSaleDate;

    @Column(name = "borrowing_devolution_date")
    @Temporal(TemporalType.DATE)
    private Date borrowingDevolutionDate;

}