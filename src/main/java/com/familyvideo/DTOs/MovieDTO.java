package com.familyvideo.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Integer directorId;
    private Integer classificationId;
    private String movieName;
    private String movieSynopsis;
    private Integer movieReleaseYear;
    private List<Integer> genremovies;
    private List<Integer> movieactors;

}
