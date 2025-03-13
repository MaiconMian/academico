package com.familyvideo.service.movie;

import com.familyvideo.model.movie.Directors;
import com.familyvideo.repository.movie.DirectorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorsService {

    private final DirectorsRepository directorsRepository;

    @Autowired
    public DirectorsService(DirectorsRepository directorsRepository) {
        this.directorsRepository = directorsRepository;
    }

    public List<Directors> getAllDirectors() {
        return directorsRepository.findAll();
    }

    public Optional<Directors> getDirectorById(Integer id) {
        return directorsRepository.findById(id);
    }

    public Directors saveDirector(Directors director) {
        return directorsRepository.save(director);
    }

    public void deleteDirector(Integer id) {
        directorsRepository.deleteById(id);
    }
}
