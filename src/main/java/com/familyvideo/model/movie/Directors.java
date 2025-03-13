package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "directors")
public class Directors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "director_name", length = 45, nullable = true)
    private String directorName;

    @Column(name = "director_last_name", length = 45, nullable = true)
    private String directorLastName;

    @Column(name = "director_description", length = 100, nullable = true)
    private String directorDescription;

}