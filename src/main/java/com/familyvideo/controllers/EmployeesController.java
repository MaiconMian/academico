package com.familyvideo.controllers;

import com.familyvideo.DTOs.EmployessDTO;
import com.familyvideo.model.service.Employees;
import com.familyvideo.model.service.Addresses;
import com.familyvideo.model.service.Section;
import com.familyvideo.repository.service.EmployeesRepository;
import com.familyvideo.repository.service.SectionRepository;
import com.familyvideo.repository.service.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody EmployessDTO employessDTO) {
        Optional<Section> sectionOpt = sectionRepository.findById(employessDTO.getSectionId());
        if (!sectionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Addresses address = new Addresses();
        address.setAddressStreet(employessDTO.getAddressStreet());
        address.setAddressNumber(employessDTO.getAddressNumber());
        address.setAddressPostalCode(employessDTO.getAddressPostalCode());
        address.setAddressCity(employessDTO.getAddressCity());
        address.setAddressCountry(employessDTO.getAddressCountry());

        addressesRepository.save(address);

        Employees employee = new Employees();
        employee.setEmployeeName(employessDTO.getEmployeeName());
        employee.setEmployeeLastName(employessDTO.getEmployeeLastName());
        employee.setEmployeeSalary(employessDTO.getEmployeeSalary());
        employee.setEmployeeBirthdayDate(employessDTO.getEmployeeBirthdayDate());
        employee.setSection(sectionOpt.get());
        employee.setAddress(address);

        employeesRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable Integer id, @RequestBody EmployessDTO employessDTO) {
        Optional<Employees> employeeOpt = employeesRepository.findById(id);
        if (!employeeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Section> sectionOpt = sectionRepository.findById(employessDTO.getSectionId());
        if (!sectionOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Employees employee = employeeOpt.get();
        employee.setEmployeeName(employessDTO.getEmployeeName());
        employee.setEmployeeLastName(employessDTO.getEmployeeLastName());
        employee.setEmployeeSalary(employessDTO.getEmployeeSalary());
        employee.setEmployeeBirthdayDate(employessDTO.getEmployeeBirthdayDate());
        employee.setSection(sectionOpt.get());

        Addresses address = employee.getAddress();
        address.setAddressStreet(employessDTO.getAddressStreet());
        address.setAddressNumber(employessDTO.getAddressNumber());
        address.setAddressPostalCode(employessDTO.getAddressPostalCode());
        address.setAddressCity(employessDTO.getAddressCity());
        address.setAddressCountry(employessDTO.getAddressCountry());

        addressesRepository.save(address);
        employeesRepository.save(employee);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Integer id) {
        return employeesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        Optional<Employees> employeeOpt = employeesRepository.findById(id);

        if (!employeeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }

        employeesRepository.deleteById(id);
        return ResponseEntity.ok("Funcionário removido com sucesso!");
    }
}
