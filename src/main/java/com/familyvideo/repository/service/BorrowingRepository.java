package com.familyvideo.repository.service;

import com.familyvideo.model.service.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {
}
