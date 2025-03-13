package com.familyvideo.repository.relationship;

import com.familyvideo.model.relationship.GenreMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreMovieRepository extends JpaRepository<GenreMovie, Integer> {
}
