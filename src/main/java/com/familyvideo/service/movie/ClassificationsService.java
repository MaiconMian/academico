package com.familyvideo.service.movie;

import com.familyvideo.model.movie.Classifications;
import com.familyvideo.repository.movie.ClassificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClassificationsService {

    private final ClassificationsRepository classificationsRepository;

    @Autowired
    public ClassificationsService(ClassificationsRepository classificationsRepository) {
        this.classificationsRepository = classificationsRepository;
    }

    public List<Classifications> getAllClassifications() {
        return classificationsRepository.findAll();
    }

    public Optional<Classifications> getClassificationById(Integer id) {
        return classificationsRepository.findById(id);
    }

    public Classifications saveClassification(Classifications classification) {
        return classificationsRepository.save(classification);
    }

    public void deleteClassification(Integer id) {
        classificationsRepository.deleteById(id);
    }
}
