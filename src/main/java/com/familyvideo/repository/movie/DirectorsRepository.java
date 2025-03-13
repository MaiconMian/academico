package com.familyvideo.repository.movie;

import com.familyvideo.model.movie.Directors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorsRepository extends JpaRepository<Directors, Integer> {
}
