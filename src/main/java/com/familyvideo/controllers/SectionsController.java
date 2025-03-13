package com.familyvideo.controllers;

import com.familyvideo.DTOs.SectionDTO;
import com.familyvideo.model.service.Section;
import com.familyvideo.model.movie.Classifications;
import com.familyvideo.repository.service.SectionRepository;
import com.familyvideo.repository.movie.ClassificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionsController {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ClassificationsRepository classificationsRepository;

    @PostMapping
    public ResponseEntity<Section> createSection(@RequestBody SectionDTO sectionDTO) {

        Optional<Classifications> classificationOpt = classificationsRepository.findById(sectionDTO.getClassificationId());

        if (!classificationOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Section section = new Section();

        section.setSectionName(sectionDTO.getSectionName());
        section.setSectionDescription(sectionDTO.getSectionDescription());
        section.setClassification(classificationOpt.get());

        sectionRepository.save(section);

        return ResponseEntity.status(HttpStatus.CREATED).body(section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Integer id, @RequestBody SectionDTO sectionDTO) {
        Optional<Section> sectionOpt = sectionRepository.findById(id);

        if (!sectionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Classifications> classificationOpt = classificationsRepository.findById(sectionDTO.getClassificationId());

        if (!classificationOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Section section = sectionOpt.get();
        section.setSectionName(sectionDTO.getSectionName());
        section.setSectionDescription(sectionDTO.getSectionDescription());
        section.setClassification(classificationOpt.get());

        sectionRepository.save(section);

        return ResponseEntity.ok(section);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable Integer id) {
        return sectionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable Integer id) {
        Optional<Section> sectionOpt = sectionRepository.findById(id);

        if (!sectionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seção não encontrada.");
        }

        sectionRepository.deleteById(id);
        return ResponseEntity.ok("Seção removida com sucesso!");
    }
}
