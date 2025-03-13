package com.familyvideo.repository.relationship;

import com.familyvideo.model.relationship.BorrowingExemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingExemplarRepository extends JpaRepository<BorrowingExemplar, Integer> {
}
