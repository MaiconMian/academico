package com.familyvideo.model.service;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Setter
@Getter
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_address", referencedColumnName = "id")
    private Addresses address;

    @ManyToOne
    @JoinColumn(name = "fk_section", referencedColumnName = "id")
    private Section section;

    @Column(name = "employee_name", length = 45)
    private String employeeName;

    @Column(name = "employee_last_name", length = 45)
    private String employeeLastName;

    @Column(name = "employee_salary")
    private Float employeeSalary;

    @Column(name = "employee_birthday_date")
    @Temporal(TemporalType.DATE)
    private Date employeeBirthdayDate;
}
