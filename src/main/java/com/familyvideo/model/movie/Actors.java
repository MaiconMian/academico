package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "actors")
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "actor_name", length = 45, nullable = true)
    private String actorName;

    @Column(name = "actor_last_name", length = 45, nullable = true)
    private String actorLastName;

    @Column(name = "actor_birthday_date")
    @Temporal(TemporalType.DATE)
    private Date actorBirthdayDate;

}