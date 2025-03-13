package com.familyvideo.model.service;

import com.familyvideo.model.movie.Classifications;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_classification", referencedColumnName = "id")
    private Classifications classification;

    @Column(name = "section_name", length = 45)
    private String sectionName;

    @Column(name = "section_description", length = 100)
    private String sectionDescription;
}
