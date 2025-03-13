package com.familyvideo.repository.relationship;

import com.familyvideo.model.relationship.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor, Integer> {
}
