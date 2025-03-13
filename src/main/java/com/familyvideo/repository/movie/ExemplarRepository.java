package com.familyvideo.repository.movie;

import com.familyvideo.model.movie.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
}
