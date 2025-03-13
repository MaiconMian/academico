package com.familyvideo.model.relationship;

import com.familyvideo.model.movie.Exemplar;
import com.familyvideo.model.service.Borrowing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "borrowing_exemplar")
public class BorrowingExemplar {

    @EmbeddedId
    private BorrowingExemplarId id;

    @ManyToOne
    @JoinColumn(name = "fk_exemplar", insertable = false, updatable = false)
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "fk_borrowing", insertable = false, updatable = false)
    private Borrowing borrowing;
}
