package com.example.exam_task_with_spring_boot.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String locatedCountry;
    private LocalDate created;
}
