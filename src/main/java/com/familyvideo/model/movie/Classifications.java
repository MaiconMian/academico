package com.familyvideo.model.movie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "classifications")
public class Classifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "classification_name", length = 45, nullable = true)
    private String classificationName;

    @Column(name = "classification_description", length = 45, nullable = true)
    private String classificationDescription;

    @Column(name = "classification_age")
    private Integer classificationAge;

}