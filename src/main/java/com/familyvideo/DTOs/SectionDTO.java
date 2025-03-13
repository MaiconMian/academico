package com.familyvideo.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {

    private Integer id;
    private Integer classificationId;
    private String  sectionName;
    private String  sectionDescription;

}
