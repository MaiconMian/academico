package com.familyvideo.service.movie;

import com.familyvideo.model.movie.Actors;
import com.familyvideo.repository.movie.ActorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ActorsService {

    private final ActorsRepository actorsRepository;

    @Autowired
    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }

    public List<Actors> getAllActors() {
        return actorsRepository.findAll();
    }

    public Optional<Actors> getActorById(Integer id) {
        return actorsRepository.findById(id);
    }

    public Actors saveActor(Actors actor) {
        return actorsRepository.save(actor);
    }

    public void deleteActor(Integer id) {
        actorsRepository.deleteById(id);
    }
}
