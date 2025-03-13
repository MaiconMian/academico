package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_movie", referencedColumnName = "id")
    private Movies movie;

    @Column(name = "exemplar_buy_date")
    @Temporal(TemporalType.DATE)
    private Date exemplarBuyDate;

}