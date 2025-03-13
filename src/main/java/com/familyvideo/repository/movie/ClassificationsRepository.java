package com.familyvideo.repository.movie;

import com.familyvideo.model.movie.Classifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepository extends JpaRepository<Classifications, Integer> {
}
