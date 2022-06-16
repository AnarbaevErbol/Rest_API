package com.example.exam_task_with_spring_boot.dto.response;

import com.example.exam_task_with_spring_boot.models.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponse {

    private String status;
    private String message;

    public CompanyResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
