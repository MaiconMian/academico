package com.familyvideo.controllers;

import com.familyvideo.DTOs.ExemplarDTO;
import com.familyvideo.model.movie.Exemplar;
import com.familyvideo.model.movie.Movies;
import com.familyvideo.repository.movie.ExemplarRepository;
import com.familyvideo.repository.movie.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exemplars")
public class ExemplarsController {

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @PostMapping
    public ResponseEntity<Exemplar> createExemplar(@RequestBody ExemplarDTO exemplarDTO) {
        Optional<Movies> movieOpt = moviesRepository.findById(exemplarDTO.getMovieId());

        if (movieOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return BAD_REQUEST if movie not found
        }

        Exemplar exemplar = new Exemplar();
        exemplar.setMovie(movieOpt.get());
        exemplar.setExemplarBuyDate(exemplarDTO.getExemplarBuyDate());

        exemplarRepository.save(exemplar);

        return ResponseEntity.status(HttpStatus.CREATED).body(exemplar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exemplar> updateExemplar(@PathVariable Integer id, @RequestBody ExemplarDTO exemplarDTO) {
        Optional<Exemplar> existingExemplarOpt = exemplarRepository.findById(id);

        if (existingExemplarOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return NOT_FOUND if exemplar not found
        }

        Optional<Movies> movieOpt = moviesRepository.findById(exemplarDTO.getMovieId());

        if (movieOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return BAD_REQUEST if movie not found
        }

        Exemplar exemplar = existingExemplarOpt.get();
        exemplar.setMovie(movieOpt.get());
        exemplar.setExemplarBuyDate(exemplarDTO.getExemplarBuyDate());

        exemplarRepository.save(exemplar);

        return ResponseEntity.ok(exemplar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exemplar> getExemplar(@PathVariable Integer id) {
        Optional<Exemplar> exemplarOpt = exemplarRepository.findById(id);

        if (exemplarOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return NOT_FOUND if exemplar not found
        }

        return ResponseEntity.ok(exemplarOpt.get());
    }

    @GetMapping
    public ResponseEntity<List<Exemplar>> getAllExemplars() {
        
        List<Exemplar> exemplars = exemplarRepository.findAll();

        if (exemplars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // Return NO_CONTENT if no exemplars found
        }

        return ResponseEntity.ok(exemplars);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplar(@PathVariable Integer id) {
        Optional<Exemplar> exemplarOpt = exemplarRepository.findById(id);

        if (exemplarOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return NOT_FOUND if exemplar not found
        }

        exemplarRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
