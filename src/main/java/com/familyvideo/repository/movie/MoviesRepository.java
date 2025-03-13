package com.familyvideo.repository.movie;

import com.familyvideo.model.movie.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Integer> {
}
