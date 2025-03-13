package com.familyvideo.service.relationship;

import com.familyvideo.model.relationship.GenreMovie;
import com.familyvideo.repository.relationship.GenreMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenreMovieService {

    private final GenreMovieRepository genreMovieRepository;

    @Autowired
    public GenreMovieService(GenreMovieRepository genreMovieRepository) {
        this.genreMovieRepository = genreMovieRepository;
    }

    public List<GenreMovie> getAllGenreMovies() {
        return genreMovieRepository.findAll();
    }

    public Optional<GenreMovie> getGenreMovieById(Integer id) {
        return genreMovieRepository.findById(id);
    }

    public GenreMovie saveGenreMovie(GenreMovie genreMovie) {
        return genreMovieRepository.save(genreMovie);
    }

    public void deleteGenreMovie(Integer id) {
        genreMovieRepository.deleteById(id);
    }
}
