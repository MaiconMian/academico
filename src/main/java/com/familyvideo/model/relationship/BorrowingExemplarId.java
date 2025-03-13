package com.familyvideo.model.relationship;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class BorrowingExemplarId implements Serializable {

    @Column(name = "fk_exemplar")
    private Integer fkExemplar;

    @Column(name = "fk_borrowing")
    private Integer fkBorrowing;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingExemplarId that = (BorrowingExemplarId) o;
        return fkExemplar.equals(that.fkExemplar) && fkBorrowing.equals(that.fkBorrowing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkExemplar, fkBorrowing);
    }
}

