package com.familyvideo.controllers;

import com.familyvideo.model.movie.Actors;
import com.familyvideo.service.movie.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorsController {

    @Autowired
    private ActorsService actorsService;

    @GetMapping
    public ResponseEntity<List<Actors>> getAllActors() {
        List<Actors> actors = actorsService.getAllActors();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actors> getActorById(@PathVariable Integer id) {
        return actorsService.getActorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Actors> addActor(@RequestBody Actors actor) {
        Actors savedActor = actorsService.saveActor(actor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actors> updateActor(@PathVariable Integer id, @RequestBody Actors actor) {
        if (actorsService.getActorById(id).isPresent()) {
            actor.setId(id);
            Actors updatedActor = actorsService.saveActor(actor);
            return ResponseEntity.ok(updatedActor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Integer id) {
        if (actorsService.getActorById(id).isPresent()) {
            actorsService.deleteActor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
