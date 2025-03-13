package com.familyvideo.service.service;

import com.familyvideo.model.service.Borrowing;
import com.familyvideo.repository.service.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Optional<Borrowing> getBorrowingById(Integer id) {
        return borrowingRepository.findById(id);
    }

    public Borrowing saveBorrowing(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(Integer id) {
        borrowingRepository.deleteById(id);
    }
}
