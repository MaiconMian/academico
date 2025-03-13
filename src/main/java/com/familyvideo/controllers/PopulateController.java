package com.familyvideo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/populate")
public class PopulateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static boolean moviesPopulated = false;
    private static final List<HashMap<String, Object>> movieList = new ArrayList<>();

    static {
        movieList.add(createMovie("Vida de Inseto", "A história de uma formiga que quer ser mais do que uma formiga.", 1998, 8, 4, Arrays.asList(1, 3, 8), Arrays.asList(7, 8)));
        movieList.add(createMovie("Star Wars: Episódio 8 - Os Últimos Jedi", "A luta contra a Primeira Ordem continua, com Rey buscando seu treinamento com Luke Skywalker.", 2015, 8, 4, Arrays.asList(2, 9), Arrays.asList(6, 5)));
        movieList.add(createMovie("Oppenheimer", "A vida e os dilemas de J. Robert Oppenheimer, o criador da bomba atômica.", 2021, 7, 4, Arrays.asList(3, 7), Arrays.asList(4, 2)));
        movieList.add(createMovie("Klaus", "A história de como Klaus e Jesper começam uma grande amizade e espalham o espírito natalino.", 2017, 8, 3, Arrays.asList(6, 8), Arrays.asList(8, 10)));
        movieList.add(createMovie("Vingadores: Guerra Infinita", "Os Vingadores se reúnem para combater Thanos, que busca as Joias do Infinito.", 2018, 9, 3, Arrays.asList(2, 9), Arrays.asList(1, 11)));
        movieList.add(createMovie("Matrix", "Um hacker aprende que sua realidade é uma simulação e deve lutar contra ela.", 1999, 12, 3, Arrays.asList(1, 2), Arrays.asList(12, 13)));
        movieList.add(createMovie("Interstellar", "Exploradores viajam por um buraco de minhoca para salvar a humanidade.", 2014, 5, 4, Arrays.asList(2, 3), Arrays.asList(15, 16)));
        movieList.add(createMovie("Pulp Fiction", "Três histórias interligadas de crime e redenção em Los Angeles.", 1994, 3, 4, Arrays.asList(3, 4), Arrays.asList(7, 16)));
        movieList.add(createMovie("O Rei Leão", "O jovem leão Simba deve reivindicar seu lugar como rei da savana.", 1994, 8, 2, Arrays.asList(8, 2), Arrays.asList(9, 10)));
        movieList.add(createMovie("Jurassic Park", "Cientistas criam um parque de dinossauros, mas os animais se tornam uma ameaça.", 1993, 8, 3, Arrays.asList(1, 9), Arrays.asList(12, 14)));
    }

    @PostMapping
    public ResponseEntity<String> insertMovies() {
        if (moviesPopulated) {
            return new ResponseEntity<>("Os filmes já foram populados.", HttpStatus.BAD_REQUEST);
        }

        String sql = "INSERT INTO movies (movie_name, movie_synopsis, movie_release_year, fk_classification, fk_director) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        for (HashMap<String, Object> movie : movieList) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, (String) movie.get("movieName"));
                ps.setString(2, (String) movie.get("movieSynopsis"));
                ps.setInt(3, (Integer) movie.get("movieReleaseYear"));
                ps.setInt(4, (Integer) movie.get("classificationId"));
                ps.setInt(5, (Integer) movie.get("directorId"));
                return ps;
            }, keyHolder);

            Integer generatedId = keyHolder.getKey().intValue();
            movie.put("movieId", generatedId);
        }
        moviesPopulated = true;
        return new ResponseEntity<>("10 filmes inseridos com sucesso.", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateMovies() {
        if (!moviesPopulated) {
            return new ResponseEntity<>("Os filmes ainda não foram inseridos.", HttpStatus.BAD_REQUEST);
        }

        String sql = "UPDATE movies SET movie_release_year = ? WHERE id = ?";

        for (HashMap<String, Object> movie : movieList) {
            Integer movieId = (Integer) movie.get("movieId");

            if (movieId != null) {
                int updatedYear = (Integer) movie.get("movieReleaseYear") + 2; // Corrige o ano de lançamento
                jdbcTemplate.update(sql, updatedYear, movieId);
            } else {
                return new ResponseEntity<>("Erro: ID " + movieId + " não encontrado!", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("10 filmes atualizados com sucesso.", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMovies() {
        if (!moviesPopulated) {
            return new ResponseEntity<>("Os filmes ainda não foram inseridos.", HttpStatus.BAD_REQUEST);
        }

        String sql = "DELETE FROM movies WHERE id = ?";

        for (HashMap<String, Object> movie : movieList) {
            Integer movieId = (Integer) movie.get("movieId");

            if (movieId != null) {
                jdbcTemplate.update(sql, movieId);
            } else {
                return new ResponseEntity<>("Erro: ID " + movieId + " não encontrado!", HttpStatus.BAD_REQUEST);
            }
        }
        moviesPopulated = false;
        return new ResponseEntity<>("10 filmes deletados com sucesso.", HttpStatus.OK);
    }

    private static HashMap<String, Object> createMovie(String movieName, String movieSynopsis,
                                                       int movieReleaseYear, int directorId,
                                                       int classificationId, List<Integer> genreIds,
                                                       List<Integer> actorIds) {
        HashMap<String, Object> movie = new HashMap<>();
        movie.put("movieName", movieName);
        movie.put("movieSynopsis", movieSynopsis);
        movie.put("movieReleaseYear", movieReleaseYear);
        movie.put("directorId", directorId);
        movie.put("classificationId", classificationId);
        movie.put("genreIds", genreIds);
        movie.put("actorIds", actorIds);
        return movie;
    }
}