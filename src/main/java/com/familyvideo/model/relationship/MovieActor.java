package com.familyvideo.model.relationship;

import com.familyvideo.model.movie.Actors;
import com.familyvideo.model.movie.Movies;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Table(name = "movies_actors")
public class MovieActor {

    @EmbeddedId
    private MovieActorId id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "fk_movie", referencedColumnName = "id")
    private Movies movie;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "fk_actors", referencedColumnName = "id")
    private Actors actor;
}
