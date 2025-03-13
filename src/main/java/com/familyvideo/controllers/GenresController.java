package com.familyvideo.controllers;

import com.familyvideo.model.movie.Genre;
import com.familyvideo.service.movie.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Integer id) {
        return genreService.getGenreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreService.saveGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        if (genreService.getGenreById(id).isPresent()) {
            genre.setId(id);
            Genre updatedGenre = genreService.saveGenre(genre);
            return ResponseEntity.ok(updatedGenre);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        if (genreService.getGenreById(id).isPresent()) {
            genreService.deleteGenre(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
