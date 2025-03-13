package com.familyvideo.service.relationship;

import com.familyvideo.model.relationship.MovieActor;
import com.familyvideo.repository.relationship.MovieActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieActorService {

    private final MovieActorRepository movieActorRepository;

    @Autowired
    public MovieActorService(MovieActorRepository movieActorRepository) {
        this.movieActorRepository = movieActorRepository;
    }

    public List<MovieActor> getAllMovieActors() {
        return movieActorRepository.findAll();
    }

    public Optional<MovieActor> getMovieActorById(Integer id) {
        return movieActorRepository.findById(id);
    }

    public MovieActor saveMovieActor(MovieActor movieActor) {
        return movieActorRepository.save(movieActor);
    }

    public void deleteMovieActor(Integer id) {
        movieActorRepository.deleteById(id);
    }
}
