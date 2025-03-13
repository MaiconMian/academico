package com.familyvideo.controllers;

import com.familyvideo.DTOs.BorrowingDTO;
import com.familyvideo.model.relationship.BorrowingExemplar;
import com.familyvideo.model.relationship.BorrowingExemplarId;
import com.familyvideo.model.service.Borrowing;
import com.familyvideo.model.service.Client;
import com.familyvideo.model.service.Employees;
import com.familyvideo.model.movie.Exemplar;
import com.familyvideo.repository.service.BorrowingRepository;
import com.familyvideo.repository.relationship.BorrowingExemplarRepository;
import com.familyvideo.repository.service.ClientRepository;
import com.familyvideo.repository.service.EmployeesRepository;
import com.familyvideo.repository.movie.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrowings")
public class BorrowingsController {

    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeesRepository employeeRepository;
    @Autowired
    private ExemplarRepository exemplarRepository;
    @Autowired
    private BorrowingExemplarRepository borrowingExemplarRepository;

    @PostMapping
    public ResponseEntity<Borrowing> createBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        Client client = clientRepository.findById(borrowingDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        Employees employee = employeeRepository.findById(borrowingDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));
        Borrowing borrowing = new Borrowing();
        borrowing.setClient(client);
        borrowing.setEmployee(employee);
        borrowing.setBorrowingIsPaid(borrowingDTO.getBorrowingIsPaid());
        borrowing.setBorrowingSaleDate(borrowingDTO.getBorrowingSaleDate());
        borrowing.setBorrowingDevolutionDate(borrowingDTO.getBorrowingDevolutionDate());
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        List<BorrowingExemplar> borrowingExemplars = borrowingDTO.getExemplarIds().stream()
                .map(exemplarId -> {
                    Exemplar exemplar = exemplarRepository.findById(exemplarId).orElseThrow(() -> new RuntimeException("Exemplar not found"));
                    BorrowingExemplar borrowingExemplar = new BorrowingExemplar();
                    BorrowingExemplarId id = new BorrowingExemplarId();

                    id.setFkBorrowing(savedBorrowing.getId());
                    id.setFkExemplar(exemplar.getId());

                    borrowingExemplar.setId(id);
                    borrowingExemplar.setExemplar(exemplar);
                    borrowingExemplar.setBorrowing(savedBorrowing);

                    return borrowingExemplar;
                })
                .collect(Collectors.toList());
        borrowingExemplarRepository.saveAll(borrowingExemplars);
        return ResponseEntity.ok(savedBorrowing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowing(@PathVariable Integer id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
        return ResponseEntity.ok(borrowing);
    }

    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingRepository.findAll();
        return ResponseEntity.ok(borrowings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable Integer id, @RequestBody BorrowingDTO borrowingDTO) {
        Borrowing existingBorrowing = borrowingRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowing not found"));
        Client client = clientRepository.findById(borrowingDTO.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        Employees employee = employeeRepository.findById(borrowingDTO.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingBorrowing.setClient(client);
        existingBorrowing.setEmployee(employee);
        existingBorrowing.setBorrowingIsPaid(borrowingDTO.getBorrowingIsPaid());
        existingBorrowing.setBorrowingSaleDate(borrowingDTO.getBorrowingSaleDate());
        existingBorrowing.setBorrowingDevolutionDate(borrowingDTO.getBorrowingDevolutionDate());
        Borrowing updatedBorrowing = borrowingRepository.save(existingBorrowing);

        List<BorrowingExemplar> existingExemplars = borrowingExemplarRepository.findAll();
        for (BorrowingExemplar borrowingExemplar : existingExemplars) {
            if (borrowingExemplar.getBorrowing().getId().equals(id)) {
                borrowingExemplarRepository.delete(borrowingExemplar);
            }
        }

        List<BorrowingExemplar> borrowingExemplars = borrowingDTO.getExemplarIds().stream()
                .map(exemplarId -> {
                    Exemplar exemplar = exemplarRepository.findById(exemplarId).orElseThrow(() -> new RuntimeException("Exemplar not found"));
                    BorrowingExemplar borrowingExemplar = new BorrowingExemplar();

                    BorrowingExemplarId newid = new BorrowingExemplarId();

                    newid.setFkBorrowing(updatedBorrowing.getId());
                    newid.setFkExemplar(exemplar.getId());

                    borrowingExemplar.setId(newid);
                    borrowingExemplar.setExemplar(exemplar);
                    borrowingExemplar.setBorrowing(updatedBorrowing);
                    return borrowingExemplar;
                })
                .collect(Collectors.toList());
        borrowingExemplarRepository.saveAll(borrowingExemplars);
        return ResponseEntity.ok(updatedBorrowing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Integer id) {
        List<BorrowingExemplar> existingExemplars = borrowingExemplarRepository.findAll();
        for (BorrowingExemplar borrowingExemplar : existingExemplars) {
            if (borrowingExemplar.getBorrowing().getId().equals(id)) {
                borrowingExemplarRepository.delete(borrowingExemplar);
            }
        }

        borrowingRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
