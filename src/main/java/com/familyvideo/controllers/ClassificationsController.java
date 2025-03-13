package com.familyvideo.controllers;

import com.familyvideo.model.movie.Classifications;
import com.familyvideo.service.movie.ClassificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifications")
public class ClassificationsController {

    @Autowired
    private ClassificationsService classificationsService;

    @GetMapping
    public ResponseEntity<List<Classifications>> getAllClassifications() {
        List<Classifications> classifications = classificationsService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classifications> getClassificationById(@PathVariable Integer id) {
        return classificationsService.getClassificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Classifications> addClassification(@RequestBody Classifications classification) {
        Classifications savedClassification = classificationsService.saveClassification(classification);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClassification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classifications> updateClassification(@PathVariable Integer id, @RequestBody Classifications classification) {
        if (classificationsService.getClassificationById(id).isPresent()) {
            classification.setId(id);
            Classifications updatedClassification = classificationsService.saveClassification(classification);
            return ResponseEntity.ok(updatedClassification);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Integer id) {
        if (classificationsService.getClassificationById(id).isPresent()) {
            classificationsService.deleteClassification(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
