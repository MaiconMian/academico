package com.familyvideo.controllers;

import com.familyvideo.model.movie.Directors;
import com.familyvideo.service.movie.DirectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorsController {

    @Autowired
    private DirectorsService directorsService;

    @GetMapping
    public ResponseEntity<List<Directors>> getAllDirectors() {
        List<Directors> directors = directorsService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Directors> getDirectorById(@PathVariable Integer id) {
        return directorsService.getDirectorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Directors> addDirector(@RequestBody Directors director) {
        Directors savedDirector = directorsService.saveDirector(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDirector);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directors> updateDirector(@PathVariable Integer id, @RequestBody Directors director) {
        if (directorsService.getDirectorById(id).isPresent()) {
            director.setId(id);
            Directors updatedDirector = directorsService.saveDirector(director);
            return ResponseEntity.ok(updatedDirector);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Integer id) {
        if (directorsService.getDirectorById(id).isPresent()) {
            directorsService.deleteDirector(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
