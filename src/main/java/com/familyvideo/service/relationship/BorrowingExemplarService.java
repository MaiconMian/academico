package com.familyvideo.service.relationship;

import com.familyvideo.model.relationship.BorrowingExemplar;
import com.familyvideo.repository.relationship.BorrowingExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingExemplarService {

    private final BorrowingExemplarRepository borrowingExemplarRepository;

    @Autowired
    public BorrowingExemplarService(BorrowingExemplarRepository borrowingExemplarRepository) {
        this.borrowingExemplarRepository = borrowingExemplarRepository;
    }

    public List<BorrowingExemplar> getAllBorrowingExemplars() {
        return borrowingExemplarRepository.findAll();
    }

    public Optional<BorrowingExemplar> getBorrowingExemplarById(Integer id) {
        return borrowingExemplarRepository.findById(id);
    }

    public BorrowingExemplar saveBorrowingExemplar(BorrowingExemplar borrowingExemplar) {
        return borrowingExemplarRepository.save(borrowingExemplar);
    }

    public void deleteBorrowingExemplar(Integer id) {
        borrowingExemplarRepository.deleteById(id);
    }
}
