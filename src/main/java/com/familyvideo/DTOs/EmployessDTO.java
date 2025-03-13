package com.familyvideo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployessDTO {

    private Integer id;
    private Integer sectionId;
    private String employeeName;
    private String employeeLastName;
    private Float employeeSalary;
    private Date employeeBirthdayDate;

    private String addressStreet;
    private Integer addressNumber;
    private String addressPostalCode;
    private String addressCity;
    private String addressCountry;

}
