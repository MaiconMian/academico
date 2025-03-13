package com.familyvideo.service.movie;

import com.familyvideo.model.movie.Exemplar;
import com.familyvideo.repository.movie.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {

    private final ExemplarRepository exemplarRepository;

    @Autowired
    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    public List<Exemplar> getAllExemplars() {
        return exemplarRepository.findAll();
    }

    public Optional<Exemplar> getExemplarById(Integer id) {
        return exemplarRepository.findById(id);
    }

    public Exemplar saveExemplar(Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    public void deleteExemplar(Integer id) {exemplarRepository.deleteById(id);}


}
