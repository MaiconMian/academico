package com.familyvideo.model.relationship;

import com.familyvideo.model.movie.Genre;
import com.familyvideo.model.movie.Movies;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Table(name = "genres_movies")
public class GenreMovie {

    @EmbeddedId
    private GenreMovieId id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "fk_movie", referencedColumnName = "id")
    private Movies movie;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "fk_genre", referencedColumnName = "id")
    private Genre genre;
}