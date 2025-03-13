package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "genre_name", length = 45, nullable = true)
    private String genreName;

    @Column(name = "genre_description", length = 100, nullable = true)
    private String genreDescription;

}