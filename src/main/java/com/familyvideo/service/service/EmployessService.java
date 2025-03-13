package com.familyvideo.service.service;

import com.familyvideo.model.service.Employees;
import com.familyvideo.repository.service.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployessService {

    private final EmployeesRepository employeeRepository;

    @Autowired
    public EmployessService(EmployeesRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employees> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employees saveEmployee(Employees employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}
