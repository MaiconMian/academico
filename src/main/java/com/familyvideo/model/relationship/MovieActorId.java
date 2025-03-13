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
public class MovieActorId implements Serializable {

    @Column(name = "fk_movie")
    private Integer movieId;

    @Column(name = "fk_actors")
    private Integer actorId;

    public MovieActorId() {
    }

    public MovieActorId(Integer movieId, Integer actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActorId that = (MovieActorId) o;
        return Objects.equals(movieId, that.movieId) && Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }

    @Override
    public String toString() {
        return "MovieActorId{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                '}';
    }
}
