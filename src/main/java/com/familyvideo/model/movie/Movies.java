package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_director", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_movie_director"), nullable = true)
    private Directors director;

    @ManyToOne
    @JoinColumn(name = "fk_classification", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_movie_classification"), nullable = true)
    private Classifications classification;

    @Column(name = "movie_name", length = 45, nullable = false)
    private String movieName;

    @Column(name = "movie_synopsis", length = 100, nullable = true)
    private String movieSynopsis;

    @Column(name = "movie_release_year", nullable = false)
    private Integer movieReleaseYear;

}