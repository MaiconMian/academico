package com.familyvideo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDTO {

    private Integer clientId;
    private Integer employeeId;
    private Boolean borrowingIsPaid;
    private Date borrowingSaleDate;
    private Date borrowingDevolutionDate;
    private List<Integer> exemplarIds;

}
