package com.familyvideo.model.relationship;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class GenreMovieId implements Serializable {

    @Column(name = "fk_movie")
    private Integer movieId;

    @Column(name = "fk_genre")
    private Integer genreId;

    public GenreMovieId() {
    }

    public GenreMovieId(Integer movieId, Integer genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreMovieId that = (GenreMovieId) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }

    @Override
    public String toString() {
        return "GenreMovieId{" +
                "movieId=" + movieId +
                ", genreId=" + genreId +
                '}';
    }
}
