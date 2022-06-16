package com.example.exam_task_with_spring_boot.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanySaveRequest {

    private String companyName;
    private String locatedCountry;
}
