package com.familyvideo.repository.movie;

import com.familyvideo.model.movie.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorsRepository extends JpaRepository<Actors, Integer> {
}
