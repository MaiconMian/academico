package com.familyvideo.controllers;

import com.familyvideo.DTOs.MovieDTO;
import com.familyvideo.model.movie.*;
import com.familyvideo.model.relationship.GenreMovie;
import com.familyvideo.model.relationship.GenreMovieId;
import com.familyvideo.model.relationship.MovieActor;
import com.familyvideo.model.relationship.MovieActorId;
import com.familyvideo.repository.movie.*;
import com.familyvideo.repository.relationship.GenreMovieRepository;
import com.familyvideo.repository.relationship.MovieActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMovieRepository genreMovieRepository;

    @Autowired
    private MovieActorRepository movieActorRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private DirectorsRepository directorsRepository;

    @Autowired
    private ClassificationsRepository classificationsRepository;

    @PostMapping
    public ResponseEntity<Movies> createMovie(@RequestBody MovieDTO movieDTO) {
        Movies movie = new Movies();
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieSynopsis(movieDTO.getMovieSynopsis());
        movie.setMovieReleaseYear(movieDTO.getMovieReleaseYear());

        if (movieDTO.getDirectorId() != null) {
            Optional<Directors> directorOpt = directorsRepository.findById(movieDTO.getDirectorId());
            directorOpt.ifPresent(movie::setDirector);
        }

        if (movieDTO.getClassificationId() != null) {
            Optional<Classifications> classificationOpt = classificationsRepository.findById(movieDTO.getClassificationId());
            classificationOpt.ifPresent(movie::setClassification);
        }

        movieRepository.save(movie);

        for (Integer genreId : movieDTO.getGenremovies()) {
            Optional<Genre> genreOpt = genreRepository.findById(genreId);

            genreOpt.ifPresent(genre -> {

                GenreMovie genreMovie = new GenreMovie();

                GenreMovieId id = new GenreMovieId();

                id.setMovieId(movie.getId());
                id.setGenreId(genreId);
                genreMovie.setId(id);

                genreMovie.setMovie(movie);
                genreMovie.setGenre(genre);
                genreMovieRepository.save(genreMovie);
            });
        }

        for (Integer actorId : movieDTO.getMovieactors()) {
            Optional<Actors> actorOpt = actorsRepository.findById(actorId);
            actorOpt.ifPresent(actor -> {
                MovieActor movieActor = new MovieActor();

                MovieActorId id = new MovieActorId();

                id.setMovieId(movie.getId());
                id.setActorId(actorId);
                movieActor.setId(id);

                movieActor.setMovie(movie);
                movieActor.setActor(actor);
                movieActorRepository.save(movieActor);
            });
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movies> updateMovie(@PathVariable Integer id, @RequestBody MovieDTO movieDTO) {
        Optional<Movies> movieOpt = movieRepository.findById(id);

        if (movieOpt.isPresent()) {
            Movies movie = movieOpt.get();
            movie.setMovieName(movieDTO.getMovieName());
            movie.setMovieSynopsis(movieDTO.getMovieSynopsis());
            movie.setMovieReleaseYear(movieDTO.getMovieReleaseYear());

            if (movieDTO.getDirectorId() != null) {
                Optional<Directors> directorOpt = directorsRepository.findById(movieDTO.getDirectorId());
                directorOpt.ifPresent(movie::setDirector);
            }

            if (movieDTO.getClassificationId() != null) {
                Optional<Classifications> classificationOpt = classificationsRepository.findById(movieDTO.getClassificationId());
                classificationOpt.ifPresent(movie::setClassification);
            }

            movieRepository.save(movie);

            // deletando todos
            List<GenreMovie> genreMovies = genreMovieRepository.findAll();
            for (GenreMovie genreMovie : genreMovies) {
                if (genreMovie.getMovie().getId().equals(id)) {
                    genreMovieRepository.delete(genreMovie);
                }
            }

            for (Integer genreId : movieDTO.getGenremovies()) {
                Optional<Genre> genreOpt = genreRepository.findById(genreId);
                genreOpt.ifPresent(genre -> {
                    GenreMovie genreMovie = new GenreMovie();

                    GenreMovieId ids = new GenreMovieId();

                    ids.setMovieId(movie.getId());
                    ids.setGenreId(genreId);

                    genreMovie.setId(ids);
                    genreMovie.setMovie(movie);
                    genreMovie.setGenre(genre);
                    genreMovieRepository.save(genreMovie);
                });
            }

            List<MovieActor> movieActors = movieActorRepository.findAll();
            for (MovieActor movieActor : movieActors) {
                if (movieActor.getMovie().getId().equals(id)) {
                    movieActorRepository.delete(movieActor);
                }
            }

            for (Integer actorId : movieDTO.getMovieactors()) {
                Optional<Actors> actorOpt = actorsRepository.findById(actorId);
                actorOpt.ifPresent(actor -> {
                    MovieActor movieActor = new MovieActor();

                    MovieActorId ids = new MovieActorId();
                    ids.setMovieId(movie.getId());
                    ids.setActorId(actorId);

                    movieActor.setId(ids);
                    movieActor.setMovie(movie);
                    movieActor.setActor(actor);
                    movieActorRepository.save(movieActor);
                });
            }

            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movies> getMovieById(@PathVariable Integer id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        Optional<Movies> movieOpt = movieRepository.findById(id);
        if (movieOpt.isPresent()) {
            Movies movie = movieOpt.get();

            List<GenreMovie> genreMovies = genreMovieRepository.findAll();
            for (GenreMovie genreMovie : genreMovies) {
                if (genreMovie.getMovie().getId().equals(id)) {
                    genreMovieRepository.delete(genreMovie);
                }
            }

            List<MovieActor> movieActors = movieActorRepository.findAll();
            for (MovieActor movieActor : movieActors) {
                if (movieActor.getMovie().getId().equals(id)) {
                    movieActorRepository.delete(movieActor);
                }
            }

            movieRepository.deleteById(id);
            return ResponseEntity.ok("Filme removido com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado.");
        }
    }

    @GetMapping
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }
}
