package com.familyvideo.service.movie;

import com.familyvideo.model.movie.Movies;
import com.familyvideo.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    public Optional<Movies> getMovieById(Integer id) {
        return moviesRepository.findById(id);
    }

    public Movies saveMovie(Movies movie) {
        return moviesRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        moviesRepository.deleteById(id);
    }
}
